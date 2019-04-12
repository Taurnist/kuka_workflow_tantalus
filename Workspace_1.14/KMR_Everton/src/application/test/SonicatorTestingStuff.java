//package application.test;
//
//import java.util.InputMismatchException;
//import java.util.concurrent.TimeUnit;
//
//import javax.inject.Inject;
//
//import Workflow.Configuration.Location;
//import Workflow.LBR.RackHandlingApplicationSuper;
//import Workflow.LBR.Action.FancyFrame;
//import Workflow.LBR.Action.RackConfig;
//
//import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
//
//import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
//import com.kuka.roboticsAPI.geometricModel.CartDOF;
//import com.kuka.roboticsAPI.geometricModel.Frame;
//import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
//import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
//import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianSineImpedanceControlMode;
//
///**
// * Implementation of a robot application.
// * <p>
// * The application provides a {@link RoboticsAPITask#initialize()} and a
// * {@link RoboticsAPITask#run()} method, which will be called successively in
// * the application lifecycle. The application will terminate automatically after
// * the {@link RoboticsAPITask#run()} method has finished or after stopping the
// * task. The {@link RoboticsAPITask#dispose()} method will be called, even if an
// * exception is thrown during initialization or run.
// * <p>
// * <b>It is imperative to call <code>super.dispose()</code> when overriding the
// * {@link RoboticsAPITask#dispose()} method.</b>
// * 
// * @see UseRoboticsAPIContext
// * @see #initialize()
// * @see #run()
// * @see #dispose()
// */
//public class SonicatorTestingStuff extends RackHandlingApplicationSuper {
//
//	RackConfig manipulation_rack;
//
//	private FancyFrame Position0;
//	private FancyFrame Position5;
//	private FancyFrame Position12;
//	private CartesianImpedanceControlMode stiffMode;
//	@Override
//	public void initialize() {
//		super.initialize();
//
//		LinMoveRackSpeed = 300;
//		LinMoveVialSpeed = 200;
//		LinMoveVialSpeedSlow = 50;
//		PTPMoveSpeed = 0.5;
//		Blending = 0;
//
//		Station = Location.Sonicator;
//
//
//		stiffMode = new CartesianImpedanceControlMode();
//		stiffMode.parametrize(CartDOF.X).setStiffness(5000);
//		stiffMode.parametrize(CartDOF.Y).setStiffness(5000);
//		stiffMode.parametrize(CartDOF.Z).setStiffness(5000);
//		stiffMode.parametrize(CartDOF.A).setStiffness(300);
//		stiffMode.parametrize(CartDOF.B).setStiffness(300);
//		stiffMode.parametrize(CartDOF.C).setStiffness(300);
//
//	
//		try {
//			manipulation_rack = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/rack_grasp"), logger,
//				false,true,false);  
//
//			Position0 = FancyFrame.Create(getApplicationData().getFrame(
//				"/Station/" + Station + "/Cube_Corner/sonicator_vial_grasp_p0")
//				.copy());
//			Position5 = FancyFrame.Create(getApplicationData().getFrame(
//				"/Station/" + Station + "/Cube_Corner/sonicator_vial_grasp_p5")
//				.copy());
//			Position12 = FancyFrame.Create(getApplicationData().getFrame(
//				"/Station/" + Station + "/Cube_Corner/sonicator_vial_grasp_p12")
//				.copy());
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void run() throws Exception {
//
//			
//			
//			StartVialHandling();
//
//
//
//		for (int i = 1; i < 18; i++) {
//			if (i == 9) {
//				i++;
//				i++; 
//			}
//
//
//			GraspVialInSonicator(i);
//
//			MoveToDryPosition();
//			DryProcedure();
//			MoveToNeutralAfterDry();
//			
//			if(i>8) {
//				PlaceVialInBuffer(i-3);
//				
//			} else {
//				PlaceVialInBuffer(i-1);
//			}
//			
//			
//		}
//
//		StopVialHandling();
//
//		GraspRackFromBufferAndPlaceOnRobot(0,0);
//	} 
//
//
//
//	private void MoveToDryPosition() {
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName()
//						+ "/Cube_Corner/dry_bottom/pre_dry")).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp")
//			.move(
//				lin(
//					getApplicationData().getFrame(
//						"/Station/" + Station.getPointSetName()
//							+ "/Cube_Corner/dry_bottom/dry_side")).setCartVelocity(
//					LinMoveVialSpeed));
//
//	}
//
//	
//
//		private void DryProcedure() {
//
//			Gripper.getFrame("/spacer/tcp")
//				.move(
//					lin(
//						getApplicationData().getFrame(
//							"/Station/" + Station.getPointSetName()
//								+ "/Cube_Corner/dry_bottom/dry_side")).setCartVelocity(
//						LinMoveVialSpeed));
//
//			Gripper.getFrame("/spacer/tcp").move(
//				lin(
//					getApplicationData().getFrame(
//						"/Station/" + Station.getPointSetName()
//							+ "/Cube_Corner/dry_bottom")).setCartVelocity(4).setMode(CartesianSineImpedanceControlMode.createSinePattern(CartDOF.Z, 15, 20, 5000)));
//			
//			Gripper.getFrame("/spacer/tcp").move(
//				positionHold(stiffMode,15, TimeUnit.SECONDS));
//		
//
//	}
//
//
//	private void MoveToNeutralAfterDry() {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName()
//						+ "/Cube_Corner/dry_bottom/pre_dry")).setCartVelocity(LinMoveVialSpeed));
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName()
//						+ "/Cube_Corner/neutral")).setCartVelocity(LinMoveVialSpeed));
//
//	}
//	
//	
//	public FancyFrame get_pre_position(int i) throws Exception {
//		FancyFrame result = new FancyFrame(0, 0, 0, 0, 0, 0);
//		result.setParent(get_position(i));
//		result.setZ(result.getZ() - 80); // IN POINT FRAME
//		return result;
//	}
//
//	public ObjectFrame get_position(int i) throws Exception {
//		if(i >=1 && i <= 18 && i== 9 && i == 10)
//			throw new InputMismatchException();
//		
//		int vial_number = i;
//
//		return getApplicationData().getFrame(
//			"/Station/" + Station.getPointSetName()
//				+ "/Cube_Corner/P" + (vial_number));
//	}
//
//	private void GraspVialInSonicator(int i) throws Exception {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData()
//					.getFrame(
//						"/Station/" + Station
//							+ "/Cube_Corner/sonicator_vial_pre_grasp"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeed));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(get_pre_position(i)).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(get_position(i)).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		GripperGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(get_pre_position(i)).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeedSlow));
//		
//		float sign = 1;
//		float[] go_left = new float[] {4,5,6,9,10,14,15,16};
//		
//		for(int index = 0;index<go_left.length;index++)
//		{
//			if(go_left[index] == i)
//				sign  = -1;
//		}
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			linRel(0,0,0,0,sign* Math.PI/4,0).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeedSlow));
//		
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			positionHold(
//				CartesianSineImpedanceControlMode.createSinePattern(CartDOF.Z, 15, 20, 5000)
//				, 5, TimeUnit.SECONDS));
//		
//		
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			linRel(0,0,0,0,-1 * sign *Math.PI/4,0).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeedSlow));
//		
//		
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData()
//					.getFrame(
//						"/Station/" + Station
//							+ "/Cube_Corner/sonicator_vial_pre_grasp"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeed));
//	}
//
//	private void PlaceVialInSonicator(int i) throws Exception {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData()
//					.getFrame(
//						"/Station/" + Station
//							+ "/Cube_Corner/sonicator_vial_pre_grasp"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(get_pre_position(i)).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(get_position(i)).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(get_pre_position(i)).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData()
//					.getFrame(
//						"/Station/" + Station
//							+ "/Cube_Corner/sonicator_vial_pre_grasp"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeed));
//
//	}
//
//	// Start and end in Drive Pos
//	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index, int station_index) throws Exception {
//		GraspRackFromRobot(robot_index);
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_grasp_position()).setCartVelocity(
//				LinMoveRackSpeed));
//
//		PlaceRack(manipulation_rack);
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin( // PTP here bit dangerous
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));
//
//		ArmToDrivePos();
//	}
//
//	// Start and end in Drive Pos
//	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index, int station_index) throws Exception {
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(manipulation_rack.get_pre_grasp_position()).setBlendingCart(
//				Blending).setJointVelocityRel(PTPMoveSpeed));
//
//		GraspRack(manipulation_rack);
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/robot_pre_rack_grasp"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));
//
//		
//		PlaceRackOnRobot(robot_index);
//	}
//
//	private void PlaceVialInBuffer(int index) throws Exception {
//
//		// Dont look nice from neutral, + not needed
//		// Gripper.getFrame("/spacer/tcp").move(
//		// lin(manipulation_rack.get_pre_grasp_position())
//		// .setBlendingCart(Blending)
//		// .setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)).setBlendingCart(
//				Blending).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_position(index))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)).setBlendingCart(
//				Blending).setCartVelocity(LinMoveVialSpeedSlow));
//
//		// Dont look nice from neutral, + not needed
//		// Gripper.getFrame("/spacer/tcp").move(
//		// lin(manipulation_rack.get_pre_grasp_position())
//		// .setBlendingCart(Blending)
//		// .setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeed));
//
//	}
//
//	private void GraspVialFromBuffer(int index) throws Exception {
//		// Dont look nice from neutral, + not needed
//		// Gripper.getFrame("/spacer/tcp").move(
//		// lin(manipulation_rack.get_pre_grasp_position())
//		// .setBlendingCart(Blending)
//		// .setCartVelocity(LinMoveVialSpeed));
//		//
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)).setBlendingCart(
//				Blending).setCartVelocity(LinMoveVialSpeed));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_position(index))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)).setBlendingCart(
//				Blending).setCartVelocity(LinMoveVialSpeedSlow));
//		// Dont look nice from neutral, + not needed
//		// Gripper.getFrame("/spacer/tcp").move(
//		// lin(manipulation_rack.get_pre_grasp_position())
//		// .setBlendingCart(Blending)
//		// .setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeed));
//	}
//
//	private void StartVialHandling() {
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setJointVelocityRel(PTPMoveSpeed));
//	}
//	private void StopVialHandling() {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
//
//		ArmToDrivePos();
//	}
//
//}