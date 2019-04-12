package Workflow.LBR;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;
import Workflow.Configuration.Location;
import Workflow.LBR.Action.RackConfig;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.geometricModel.math.CoordinateAxis;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianSineImpedanceControlMode;

public abstract class RackHandlingApplicationSuper
	extends
		GripperApplicationsSuper {
	
	protected CartesianImpedanceControlMode placeVialMode;

	private CartesianImpedanceControlMode pressRackMode;
	private CartesianImpedanceControlMode stiffMode;
	private CartesianImpedanceControlMode pullRackMode;
	private ForceCondition pressRackStop;
	
	private Thread PositionHoldThread; 
	private Boolean HoldPosition = false;
	
	protected double LinMoveRackSpeed = 300;
	protected double LinMoveVialSpeed = 200;
	protected double LinMoveVialSpeedSlow = 50;
	protected double PTPMoveSpeed = 0.5;
	protected double Blending = 0;
	protected double PTPRackHoldingMoveSpeed = 0.5;
	
	protected Location Station = Location.Unknown;
	
	@Override
	public void initialize() {
		super.initialize();
		pressRackMode = new CartesianImpedanceControlMode();
		pressRackMode.parametrize(CartDOF.X).setStiffness(5000);
		pressRackMode.parametrize(CartDOF.Y).setStiffness(5000);
		pressRackMode.parametrize(CartDOF.Z).setStiffness(1000);
		pressRackMode.parametrize(CartDOF.A).setStiffness(300);
		pressRackMode.parametrize(CartDOF.B).setStiffness(300);
		pressRackMode.parametrize(CartDOF.C).setStiffness(300);
		
		placeVialMode = CartesianSineImpedanceControlMode.createSinePattern(CartDOF.X, 2, 5, 1000);
		placeVialMode.parametrize(CartDOF.X).setStiffness(5000);
		placeVialMode.parametrize(CartDOF.Z).setStiffness(1000);
		placeVialMode.parametrize(CartDOF.A).setStiffness(300);
		placeVialMode.parametrize(CartDOF.B).setStiffness(300);
		placeVialMode.parametrize(CartDOF.C).setStiffness(300);
		
		
		pressRackStop = ForceCondition.createNormalForceCondition(emptyRack.getFrame("/spacer/tcp"), CoordinateAxis.Z, 50);

		stiffMode = new CartesianImpedanceControlMode();
		stiffMode.parametrize(CartDOF.X).setStiffness(5000);
		stiffMode.parametrize(CartDOF.Y).setStiffness(5000);
		stiffMode.parametrize(CartDOF.Z).setStiffness(5000);
		stiffMode.parametrize(CartDOF.A).setStiffness(300);
		stiffMode.parametrize(CartDOF.B).setStiffness(300);
		stiffMode.parametrize(CartDOF.C).setStiffness(300);
		
		pullRackMode = new CartesianImpedanceControlMode();
		pullRackMode.parametrize(CartDOF.X).setStiffness(2000);
		pullRackMode.parametrize(CartDOF.Y).setStiffness(2000);
		pullRackMode.parametrize(CartDOF.Z).setStiffness(5000);
		pullRackMode.parametrize(CartDOF.A).setStiffness(150);
		pullRackMode.parametrize(CartDOF.B).setStiffness(300);
		pullRackMode.parametrize(CartDOF.C).setStiffness(300);

		PositionHoldThread = new Thread(new Runnable() {
      @Override
      public void run() {
          try {
          	Date start = new Date();
          	Date last_test = new Date();
          	last_test.setTime(last_test.getTime() - 10);
          	Gripper.getFrame("/spacer/tcp").moveAsync(positionHold(pullRackMode,100, TimeUnit.MILLISECONDS));
          	while(HoldPosition)
          		if((new Date().getTime() - last_test.getTime()) > 100){
          			Gripper.getFrame("/spacer/tcp").moveAsync(positionHold(pullRackMode,100, TimeUnit.MILLISECONDS));
          		}
          } catch (Exception e) {
              
          }
      }
  });
	PositionHoldThread.start();
	}


	protected void GraspRackFromRobot(int robot_index) throws Exception {
		RackConfig RobotRack = getRobotRack(robot_index);
		
		Gripper.getFrame("/spacer/tcp").move(
			ptp(getApplicationData().getFrame("/Station/"+Station+"/robot_pre_rack_grasp"))
				.setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
			ptp(RobotRack.get_pre_grasp_position())
				.setJointVelocityRel(PTPMoveSpeed));

		GraspRack(RobotRack);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(getApplicationData().getFrame("/Station/"+Station+"/robot_pre_rack_grasp"))
				.setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));
	}

	private RackConfig getRobotRack(int robot_index) throws Exception {
		if(robot_index >= 0 && robot_index <= 5)
		{
		return new RackConfig(getApplicationData(),
			getApplicationData().getFrame("/Station/Robot/rack_grasp" + robot_index),
			logger);// TODO Auto-generated method stub
		} else 
		{
			//"Rackindex on the robot should always be between 0 and 5"
			throw new NotImplementedException();
		}
	}
	protected void PlaceRackOnRobot(int robot_index) throws Exception {
		RackConfig RobotRack = getRobotRack(robot_index);
		
		// NO PTP is very close to bench
		emptyRack.getFrame("/spacer/tcp").move(
			lin(RobotRack.get_pre_grasp_position())
				.setCartVelocity(LinMoveRackSpeed));
		
		

		PlaceRack(RobotRack);

		ArmToDrivePos();
	}
	
	public void GraspRack(RackConfig manipulationRack) throws UnexpectedCollisionDetected,
		GripperFailException {
		GraspRack(manipulationRack, false);
	}
	public void GraspRackMinimal(RackConfig manipulationRack) throws UnexpectedCollisionDetected,
			GripperFailException {
			GraspRack(manipulationRack,true);
		}
	
	// Assume start at RackConfig.get_pre_grasp_position, end at the same
	// get_pre_grasp_position
	private void GraspRack(RackConfig manipulationRack,
		boolean use_minimal_pre_grasp) throws UnexpectedCollisionDetected,
		GripperFailException {

		GripperPrepareForGraspCartridge();

		Frame rack_grasp = new Frame();
		rack_grasp.setParent(manipulationRack.GetRackGrasp());
		rack_grasp.setZ(rack_grasp.getZ() - 20);

		Gripper.getFrame("/spacer/tcp").move(
			lin(rack_grasp).setCartVelocity(100));

		// Shove it in there
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulationRack.GetRackGrasp()).setCartVelocity(50));

//		// maybe we have some alignment issues, correct point
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulationRack.GetRackGrasp()).setCartVelocity(50));
//		
//		StartRelaxingPosition();
		
		GripperGraspRack();
//		StopRelaxingPositoon();
 

		lbr_iiwa.detachAll();
		emptyRack.attachTo(lbr_iiwa.getFlange());

		rack_grasp = new Frame();
		rack_grasp.setParent(manipulationRack.GetRackGrasp());
		rack_grasp.setZ(rack_grasp.getZ() - 30);

		emptyRack.getDefaultMotionFrame().move(
			lin(rack_grasp).setMode(pullRackMode).setCartVelocity(20).setBlendingCart(10));
		
		if (use_minimal_pre_grasp) {
			emptyRack.getDefaultMotionFrame().move(
				lin(manipulationRack.get_minimal_pre_grasp_position())
					.setCartVelocity(200));
		} else {
			emptyRack.getDefaultMotionFrame().move(
				lin(manipulationRack.get_pre_grasp_position()).setCartVelocity(
					200));
		}
	}
	private void StopRelaxingPositoon() {
		// TODO Auto-generated method stub
		HoldPosition = false;
	}


	private void StartRelaxingPosition() {
		HoldPosition = true; 
		// TODO Auto-generated method stub
		
	}


	public void PlaceRack(RackConfig manipulationRack)
		throws UnexpectedCollisionDetected {
		PlaceRack(manipulationRack,false);
	}
	
		public void PlaceRackMinimal(RackConfig manipulationRack)
			throws UnexpectedCollisionDetected {
			PlaceRack(manipulationRack,true);
	}
	// Assume start at RackConfig.get_pre_grasp_position, end at the same
	// get_pre_grasp_position
	private void PlaceRack(RackConfig manipulationRack, boolean use_minimal_pre_grasp)
		throws UnexpectedCollisionDetected {
		ForceCondition forceCondGrip = ForceCondition
			.createNormalForceCondition(emptyRack.getDefaultMotionFrame(), CoordinateAxis.Z, 20);

		// Check for existing rack with or without vials
		Frame rack_grasp_collision_detectionPoint = new Frame();
		
		rack_grasp_collision_detectionPoint.setParent(manipulationRack
			.GetRackGrasp());
		
		rack_grasp_collision_detectionPoint
			.setZ(rack_grasp_collision_detectionPoint.getZ() - 20);

		IMotionContainer cont = emptyRack.getFrame("/spacer/tcp").move(
			lin(rack_grasp_collision_detectionPoint).setCartVelocity(50)
				.breakWhen(forceCondGrip));

		if (cont.hasFired(forceCondGrip)) {
			throw new UnexpectedCollisionDetected("Unexpected object detected");
		}

		// Correct position after impedance control
		emptyRack.getFrame("/spacer/tcp").move(
			lin(rack_grasp_collision_detectionPoint).setCartVelocity(50));

		// Now we can place the rack 
		emptyRack.getFrame("/spacer/tcp").move(
			lin(manipulationRack.GetRackGrasp()).setCartVelocity(10));

//		emptyRack.getFrame("/spacer/tcp").move(
//			linRel(0,0,10).setCartVelocity(10).setMode(pressRackMode).breakWhen(pressRackStop));
//		
		
		GripperPrepareForGraspRack();

		lbr_iiwa.detachAll();
		Gripper.attachTo(lbr_iiwa.getFlange());

		Frame rack_grasp_pull_up = new Frame();
		rack_grasp_pull_up.setParent(manipulationRack.GetRackGrasp());
		rack_grasp_pull_up.setZ(rack_grasp_pull_up.getZ() - 30);

		Gripper.getDefaultMotionFrame().move(
			lin(rack_grasp_pull_up).setBlendingCart(10).setCartVelocity(20));

		if (use_minimal_pre_grasp) {
			Gripper.getDefaultMotionFrame().move(
				lin(manipulationRack.get_minimal_pre_grasp_position())
					.setCartVelocity(200));
		} else {
			Gripper.getDefaultMotionFrame().move(
				lin(manipulationRack.get_pre_grasp_position()).setCartVelocity(
					200));
		}
	

	}

}