package Workflow.LBR;

import javax.inject.Inject;
import javax.inject.Named;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tool.GripperFesto;

import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;

import Workflow.LBR.Action.RackConfig;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.controllerModel.sunrise.ISafetyState;
import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.task.ITaskLogger;

/**
 * Implementation of a robot application.
 * <p>
 * The application provides a {@link RoboticsAPITask#initialize()} and a
 * {@link RoboticsAPITask#run()} method, which will be called successively in
 * the application lifecycle. The application will terminate automatically after
 * the {@link RoboticsAPITask#run()} method has finished or after stopping the
 * task. The {@link RoboticsAPITask#dispose()} method will be called, even if an
 * exception is thrown during initialization or run.
 * <p>
 * <b>It is imperative to call <code>super.dispose()</code> when overriding the
 * {@link RoboticsAPITask#dispose()} method.</b>
 * 
 * @see UseRoboticsAPIContext
 * @see #initialize()
 * @see #run()
 * @see #dispose()
 */
public abstract class GripperApplicationsSuper
	extends
		RoboticsAPIApplication {

	@Inject
	protected GripperFesto gripper;

	@Inject
	protected ITaskLogger logger;

	@Inject
	@Named("Gripper")
	protected Tool Gripper;

	protected Controller kukaController;
	protected LBR lbr_iiwa;

	@Inject
	@Named("EmptyRack")
	protected Tool emptyRack;

	
	@Override
	public void initialize() {
		// initialize your application here
		kukaController = (Controller) getContext().getControllers().toArray()[0];
		lbr_iiwa = (LBR) kukaController.getDevices().toArray()[0];
		

		Gripper.attachTo(lbr_iiwa.getFlange());
	}

	
	
	public void GripperPrepareForSixPointCalibration() {
		gripper.driveToPosition(3);
		getApplicationData().getProcessData("gripPos").setValue(3);
	}

	public void GripperPrepareForGraspRack() {
		gripper.driveToPosition(3);
		getApplicationData().getProcessData("gripPos").setValue(3);
	}

	public void GripperGraspRack() throws GripperFailException {
		gripper.prepareSensitiveMove((Integer) getApplicationData()
			.getProcessData("openForceRack").getValue());
		gripper.moveSensitive(3);

		logger.info("current positon:  "
			+ Double.toString(gripper.currentPosition()));
		if (Math.abs(((Double) getApplicationData().getProcessData(
			"openPosRack").getValue())
			- gripper.currentPosition()) > ((Double) getApplicationData()
			.getProcessData("threshhold").getValue()))

		{
			throw new GripperFailException();
		}
	}

	public void GripperPrepareForGraspCartridge() {
		gripper.driveToPosition(3);
		getApplicationData().getProcessData("gripPos").setValue(3);
	}

	public void GripperGraspCartridge() throws GripperFailException {
		gripper.prepareSensitiveMove((Integer) getApplicationData()
			.getProcessData("closeForceCartridge").getValue());
		gripper.moveSensitive(1);

		if (Math.abs(((Double) getApplicationData().getProcessData(
			"openPosCartridge").getValue())
			- gripper.currentPosition()) > ((Double) getApplicationData()
			.getProcessData("threshhold").getValue()))

		{
			throw new GripperFailException();
		}
	}

	public void GripperPrepareForGraspVial() {
		gripper.driveToPosition(1);
		getApplicationData().getProcessData("gripPos").setValue(1);
	}

	public void GripperGraspVial() throws GripperFailException {
		gripper.prepareSensitiveMove((Integer) getApplicationData()
			.getProcessData("closeForceVial").getValue());
		gripper.moveSensitive(1);

		if (Math.abs(((Double) getApplicationData().getProcessData(
			"openPosVial").getValue())
			- gripper.currentPosition()) > ((Double) getApplicationData()
			.getProcessData("threshhold").getValue()))

		{
			throw new GripperFailException();
		}
	}

	public void GripperHomeGripper() {
		gripper.startHoming();
	}

	
	protected void ArmToDrivePos() {
		lbr_iiwa.getFlange().move(ptp(getApplicationData().getFrame("/DrivePos")).setJointVelocityRel(0.5));
	}

}