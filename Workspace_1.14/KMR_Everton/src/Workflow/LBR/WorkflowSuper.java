package Workflow.LBR;

import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptpHome;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tool.GripperFesto;

import application.util.ObjectFrameUtilsVeins11;

import com.kuka.common.ThreadUtil;
import com.kuka.generated.ioAccess.BMSIOGroup;
import com.kuka.nav.task.remote.RemoteTaskId;
import com.kuka.nav.task.remote.TaskRequestContainer;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.controllerModel.sunrise.ISunriseRequestService;
import com.kuka.roboticsAPI.controllerModel.sunrise.SunriseSafetyState.SafetyStopType;
import com.kuka.roboticsAPI.controllerModel.sunrise.api.SSR;
import com.kuka.roboticsAPI.controllerModel.sunrise.api.SSRFactory;
import com.kuka.roboticsAPI.controllerModel.sunrise.connectionLib.Message;
import com.kuka.roboticsAPI.controllerModel.sunrise.positionMastering.PositionMastering;
import com.kuka.roboticsAPI.deviceModel.JointPosition;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.OperationMode;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;

import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.KROSMotion;

import com.kuka.roboticsAPI.motionModel.PTP;
import com.kuka.roboticsAPI.persistenceModel.PersistenceException;
import com.kuka.task.ITaskLogger;
import com.kuka.task.properties.ParameterSetter;

import Exception.CriticalActionFailException;
import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;
import Workflow.LBR.LBRRobotStatus;
import Workflow.LBR.Action.CubeFinderAngled;
import Workflow.LBR.Messages.LBRTask;
import Workflow.TCP.RobotStatusEnum;
import Workflow.Configuration.Location;

public class WorkflowSuper extends RackHandlingApplicationSuper {
	 
	@Inject
	public BMSIOGroup BMS;
	
	protected Frame CubeOrigin;
	boolean TaskStopped;
	LBRTCPClient client;
	double MinDistanceToStart = 50;
  ScheduledExecutorService executer_service;

	@Override
	public void initialize() { 
		super.initialize();

	}
 
	public WorkflowSuper() {
		super();
		

	}
	private void UpdateStatus() {
		// TODO Auto-generated method stub
		client.SetSafety(lbr_iiwa.getSafetyState().getSafetyStopSignal());
//		client.SetCartesianPosition(lbr_iiwa.getCurrentCartesianPosition(Gripper.getDefaultMotionFrame()));
	}
	@ParameterSetter
	public void setParameters(Location station) {
		Station = station;

	}

	@Override
	public void run() {
		try { 
			{
				LBRRobotStatus Status = new LBRRobotStatus(new Date(),
						RobotStatusEnum.Commandable, null, "", null, Station,null,SafetyStopType.NOSTOP);
				client = new LBRTCPClient(Status, "172.31.1.70", 667,logger);
			}
			executer_service = Executors.newSingleThreadScheduledExecutor();
		    executer_service.scheduleAtFixedRate(new Runnable() {
		        @Override
		        public void run() {
		            try {

		               		UpdateStatus();

		            } catch (Exception e) {
		               logger.error(e.getMessage());
		            }
		        }

						
		    }, 0, 1000, TimeUnit.MILLISECONDS);
			TaskStopped = false;
			
			CheckCalibrationArm();
			getApplicationData().getProcessData("AllowAutomatedCharging").setValue(false);
			while (!TaskStopped) {
				
					LBRTask task = (LBRTask) client.getNewTask();
					if (task != null) {
						try {

							switch (task.getType()) {
							case SixPointCalibration: {
								SixPointCalibration();

								client
										.updateStatus(true, null);
								break;
							}
							case SixPointCalibrationVariance: {
								AssertDrivePosition();
								gripper.driveToPosition(1);
								getApplicationData().getProcessData("gripPos")
										.setValue(1);

								// String Cube_estimation = "/Station/"
								// + Station.getPointSetName() + "/Cube_Pos";
								// Frame target =
								// getApplicationData().getFrame(Cube_estimation).copy();
								// String Cube_origin =
								// "/Station/Cartridge/Cube_Corner";
								String Cube_estimation = "/Station/"
										+ Station.getPointSetName()
										+ "/Cube_Pos/variance";
								Frame target = getApplicationData().getFrame(
										Cube_estimation).copy();
								Random r = new Random();
								target.setX(-15d + 30d * r.nextDouble());
								target.setY(-15d + 30d * r.nextDouble());
								target.setAlphaRad(Math.toRadians(-3d + 6d
										* r.nextDouble()));

								String Cube_origin = "/Station/"
										+ Station.getPointSetName()
										+ "/Cube_Corner";
								
								CubeFinderAngled calibration = new CubeFinderAngled(
										getApplicationData(), lbr_iiwa,
										Gripper, logger);

								Frame cube = calibration.FindCube(target);

								logger.info("CUBE FOUND AT " + cube.toString()
										+ " FOR STATION " + Station.toString());
								client.setCubeOrigin(cube);
								CubeOrigin = cube.copyWithRedundancy();
								ObjectFrameUtilsVeins11.changeObjectFrame(
										getContext(), getApplicationData()
												.getFrame(Cube_origin), cube);

								lbr_iiwa.getFlange().move(
										ptp(
												getApplicationData().getFrame(
														"/DrivePos"))
												.setJointVelocityRel(0.3));

								client
										.updateStatus(true, null);
								break;
							}
							case DrivePos: {
								lbr_iiwa.getFlange().move(
										ptp(
												getApplicationData().getFrame(
														"/DrivePos"))
												.setJointVelocityRel(0.5));
								client
										.updateStatus(true, null);
								break;
							}
							case CalibratePosAndTorque: {
								AssertDrivePosition();
								Reference();
								client
										.updateStatus(true, null);
								break;
							}
							case AllowCharge: {
								logger.info("AllowCharge");
								getApplicationData().getProcessData("AllowAutomatedCharging").setValue(true);
								client
									.updateStatus(true, null);
								break;
							}
							case DisallowCharge: {
								logger.info("DisallowCharge");
								getApplicationData().getProcessData("AllowAutomatedCharging").setValue(false);
								client
									.updateStatus(true, null);
								break;
							}
							case Finish: {
								logger.info("FinishTaskReceived");
								TaskStopped = true;

								break;
							}

							default:
								if (CubeOrigin == null)
									client.updateStatus(false,
											"No Cube Origin Set");
								else
									OtherTasksHook(task);
							}
						} catch (Exception e) {
							client.updateStatus(false, e.getMessage());
						} 
					} else {
						//No Task
						Thread.sleep(100);
					}
					
					if (((Boolean) getApplicationData().getProcessData("AllowAutomatedCharging").getValue()) &&  BMS.getStateOfCharge() < ((Integer) getApplicationData().getProcessData("tempMinimalChargeBattery").getValue()) && ((Boolean) getApplicationData().getProcessData("EnableAutomatedCharging").getValue()))
					{
						TaskStopped = true;
					}
			}
		} catch (InterruptedException e) {

		} finally {
			//
			if (client != null) {
				client.Destruct();
				client = null;
			}
		}
	}

	protected void SixPointCalibration() throws RobotAssertionError,
			CriticalActionFailException {
		AssertDrivePosition();
		gripper.driveToPosition(1);
		getApplicationData().getProcessData("gripPos")
				.setValue(1);
		
		String Cube_pre_estimation = "/Station/" + Station.getPointSetName() + "/Pre_Cube_Pos";
		
		String Cube_estimation = "/Station/"
				+ Station.getPointSetName()
				+ "/Cube_Pos";
		


		String Cube_origin = "/Station/"
				+ Station.getPointSetName()
				+ "/Cube_Corner";
		
		PerformSixPoint(Cube_pre_estimation, Cube_estimation, Cube_origin);
		
		SetObjectsBasedOn6PHook();
	}

	protected void PerformSixPoint(String Cube_pre_estimation,
			String Cube_estimation, String Cube_origin)
			throws CriticalActionFailException {
		Frame target = getApplicationData().getFrame(
				Cube_estimation).copyWithRedundancy();
		try{
			Frame pre_target = getApplicationData().getFrame(Cube_pre_estimation).copyWithRedundancy();
			Gripper.getFrame("/spacer/tcp").move(ptp(pre_target).setJointVelocityRel(0.5));
		} catch (PersistenceException e)
		{
			//If the point is not there, then we don't care.
		}
		
		
		CubeFinderAngled calibration = new CubeFinderAngled(
				getApplicationData(), lbr_iiwa,
				Gripper, logger);

		Frame cube = calibration.FindCube(target);

		logger.info("CUBE FOUND AT " + cube.toString()
				+ " FOR STATION " + Station.toString());
		client.setCubeOrigin(cube);
		CubeOrigin = cube.copyWithRedundancy();
		ObjectFrameUtilsVeins11.changeObjectFrame(
				getContext(), getApplicationData()
						.getFrame(Cube_origin), cube);

		try{
			Frame pre_target = getApplicationData().getFrame(Cube_pre_estimation).copyWithRedundancy();
			Gripper.getFrame("/spacer/tcp").move(ptp(pre_target).setJointVelocityRel(0.5));
		} catch (PersistenceException e)
		{
			//If the point is not there, then we don't care.
		}
		
		
		ArmToDrivePos();
	}

	private void CheckCalibrationArm() {
		boolean needs_calibration = false;
		long lastCalibration = Long.parseLong(((String) getApplicationData().getProcessData("lastCalibrationOfArm").getValue()));
		int maximal_time_without_calibration  = ((Integer) getApplicationData().getProcessData("maximal_time_without_calibration").getValue());
		
		logger.info("last calibration: " + lastCalibration);
		logger.info("last maximal_time_without_calibration: " + maximal_time_without_calibration);
		logger.info("(new Date()).getTime(): " + (new Date()).getTime());
		if((new Date()).getTime() - lastCalibration > (maximal_time_without_calibration* 60 * 1000))
			needs_calibration = true;
		
		if(needs_calibration)
		{
			Reference();
		}
	}

	// This task should always call client.updateStatus(TaskResult(succes/fail
	// will result in ERROR STATE), errorString)
	// and return its result to return whether we are in emergency stop

	protected void SetObjectsBasedOn6PHook()   {
		// TODO Auto-generated method stub
		
	}

	public void OtherTasksHook(LBRTask task) throws UnexpectedCollisionDetected, InterruptedException, GripperFailException{
		// TODO Auto-generated method stub
		client.updateStatus(true, null);
	}

	@Override
	public void dispose() {
		TaskStopped = true;
		if (client != null) {
			client.Destruct();
			client = null;
		}
		
		
		if (executer_service != null) {
			executer_service.shutdownNow();
			executer_service = null;
		}
		super.dispose();
	}

	private final static double sideOffset = Math.toRadians(5); // offset in
																// radians for
																// side motion
	private static double joggingVelocity = 0.2; // relative velocity
	private final static int axisId[] = { 0, 1, 2, 3, 4, 5, 6 }; // axes to be
																	// referenced
	private final static int GMS_REFERENCING_COMMAND = 2; // safety command for
															// GMS referencing
	private final static int COMMAND_SUCCESSFUL = 1;
	private int positionCounter = 0;

	public void Reference() {
 		GripperHomeGripper();
		PositionMastering mastering = new PositionMastering(lbr_iiwa);

		boolean allAxesMastered = true;
		for (int i = 0; i < axisId.length; ++i) {
			// Check if the axis is mastered - if not, no referencing is
			// possible
			boolean isMastered = mastering.isAxisMastered(axisId[i]);
			if (!isMastered) {
				getLogger()
						.warn("Axis with axisId "
								+ axisId[i]
								+ " is not mastered, therefore it cannot be referenced");
			}

			allAxesMastered &= isMastered;
		}

		// We can move faster, if operation mode is T1
		if (OperationMode.T1 == lbr_iiwa.getOperationMode()) {
			joggingVelocity = 0.4;
		}

		if (allAxesMastered) {
			getLogger().info(
					"Perform position and GMS referencing with 5 positions");

			// Move to home position
			getLogger().info("Moving to home position");
			

            PTP mainMotion = new PTP(new JointPosition(Math.toRadians(-89.4),
                    Math.toRadians(-0.00),
                    Math.toRadians(1.03),
                    Math.toRadians(0),
                    Math.toRadians(-0.8),
                    Math.toRadians(0),
                    Math.toRadians(37.93))).setJointVelocityRel(joggingVelocity);
            lbr_iiwa.move(mainMotion);
            
			lbr_iiwa.move(ptpHome().setJointVelocityRel(joggingVelocity));

			// In this example 5 positions are defined, though each one
			// will be reached from negative and from positive axis
			// direction resulting 10 measurements. The safety needs
			// exactly 10 measurements to perform the referencing.
			performMotion(new JointPosition(Math.toRadians(0.0),
					Math.toRadians(16.18), Math.toRadians(23.04),
					Math.toRadians(37.35), Math.toRadians(-67.93),
					Math.toRadians(38.14), Math.toRadians(-2.13)));

			performMotion(new JointPosition(Math.toRadians(18.51),
					Math.toRadians(9.08), Math.toRadians(-1.90),
					Math.toRadians(49.58), Math.toRadians(-2.92),
					Math.toRadians(18.60), Math.toRadians(-31.18)));

			performMotion(new JointPosition(Math.toRadians(-18.53),
					Math.toRadians(-25.76), Math.toRadians(-47.03),
					Math.toRadians(-49.55), Math.toRadians(30.76),
					Math.toRadians(-30.73), Math.toRadians(20.11)));

			performMotion(new JointPosition(Math.toRadians(-48.66),
					Math.toRadians(24.68), Math.toRadians(-11.52),
					Math.toRadians(10.48), Math.toRadians(-11.38),
					Math.toRadians(-20.70), Math.toRadians(20.87)));

			performMotion(new JointPosition(Math.toRadians(9.01),
					Math.toRadians(-35.00), Math.toRadians(24.72),
					Math.toRadians(-82.04), Math.toRadians(14.65),
					Math.toRadians(-29.95), Math.toRadians(1.57)));

			


            lbr_iiwa.move(mainMotion);
            
			// Move to home position at the end
			getLogger().info("Moving to home position");
			lbr_iiwa.getFlange().move(
					ptp(getApplicationData().getFrame("/DrivePos"))
							.setJointVelocityRel(joggingVelocity));
			getApplicationData().getProcessData("lastCalibrationOfArm").setValue((new Date()).getTime()+"");
		}
	}

	private void performMotion(JointPosition position) {
		getLogger().info("Moving to position #" + (++positionCounter));

		PTP mainMotion = new PTP(position).setJointVelocityRel(joggingVelocity);
		lbr_iiwa.move(mainMotion);

		getLogger().info("Moving to current position from negative direction");
		JointPosition position1 = new JointPosition(lbr_iiwa.getJointCount());
		for (int i = 0; i < lbr_iiwa.getJointCount(); ++i) {
			position1.set(i, position.get(i) - sideOffset);
		}
		PTP motion1 = new PTP(position1).setJointVelocityRel(joggingVelocity);
		lbr_iiwa.move(motion1);
		lbr_iiwa.move(mainMotion);

		// Wait a little to reduce robot vibration after stop.
		ThreadUtil.milliSleep(2500);

		// Send the command to safety to trigger the measurement
		sendSafetyCommand();

		getLogger().info("Moving to current position from positive direction");
		JointPosition position2 = new JointPosition(lbr_iiwa.getJointCount());
		for (int i = 0; i < lbr_iiwa.getJointCount(); ++i) {
			position2.set(i, position.get(i) + sideOffset);
		}
		PTP motion2 = new PTP(position2).setJointVelocityRel(joggingVelocity);
		lbr_iiwa.move(motion2);
		lbr_iiwa.move(mainMotion);

		// Wait a little to reduce robot vibration after stop
		ThreadUtil.milliSleep(2500);

		// Send the command to safety to trigger the measurement
		sendSafetyCommand();
	}

	private void sendSafetyCommand() {
		ISunriseRequestService requestService = (ISunriseRequestService) (kukaController
				.getRequestService());
		SSR ssr = SSRFactory.createSafetyCommandSSR(GMS_REFERENCING_COMMAND);
		Message response = requestService.sendSynchronousSSR(ssr);
		int result = response.getParamInt(0);
		if (COMMAND_SUCCESSFUL != result) {
			getLogger().warn(
					"Command did not execute successfully, response = "
							+ result);
		}
	}

	public static IMotionContainer PerformGeneralMoveWithCollisionDetect(
			ObjectFrame tcp, KROSMotion moveWithOutBreakCondition)
			throws UnexpectedCollisionDetected {
		ForceCondition genericForceCondition = ForceCondition
				.createSpatialForceCondition(tcp, World.Current.getRootFrame(),
						50);
		IMotionContainer cont = tcp.move(moveWithOutBreakCondition
				.breakWhen(genericForceCondition));
		if (cont.hasFired(genericForceCondition))
			throw new UnexpectedCollisionDetected(
					"Generic Force condition used for moves without particular forcecondition");
		return cont;
	}
	
	protected enum ArmPoseEnum {DrivePos, Neutral}
	

	protected void AssertDrivePosition() throws RobotAssertionError
	{
			AssertArmPosition(ArmPoseEnum.DrivePos,lbr_iiwa.getFlange());
	}
	protected void AssertNeutralVialHandlingPosition() throws RobotAssertionError
	{
			AssertArmPosition(ArmPoseEnum.Neutral ,Gripper.getDefaultMotionFrame());
	}
	protected void AssertArmPosition(ArmPoseEnum pose, ObjectFrame objectFrames) throws RobotAssertionError
	{
		switch (pose) {
			case DrivePos :
			{
				double distance  = lbr_iiwa.getCurrentCartesianPosition(objectFrames,
					World.Current.getRootFrame()).distanceTo(getApplicationData().getFrame("/DrivePos"));
				if(distance > MinDistanceToStart)
						throw new RobotAssertionError("Robot not in DrivePos, distance: " + distance);
				break;
			}
			case Neutral :
			{
				double distance  = lbr_iiwa.getCurrentCartesianPosition(objectFrames,
					World.Current.getRootFrame()).distanceTo(getApplicationData().getFrame("/Station/" + Station + "/Cube_Corner/neutral"));
				if( distance > MinDistanceToStart)
						throw new RobotAssertionError("Robot not in neutral, distance: " + distance + " with motion frame" + objectFrames.getName());
				break;
			}
			default :
				throw new NotImplementedException();
		}	
	}

}
