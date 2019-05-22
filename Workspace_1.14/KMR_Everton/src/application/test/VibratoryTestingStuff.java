//package application.test;
//
//import java.util.concurrent.TimeUnit;
//
//import javax.crypto.spec.PSource;
//import javax.inject.Inject;
//
//import Exception.GripperFailException;
//import Workflow.Configuration.Location;
//import Workflow.LBR.RackHandlingApplicationSuper;
//import Workflow.LBR.Action.RackConfig;
//import Workflow.LBR.Action.VibratoryPhotolysisRack;
//
//import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
//
//import com.kuka.roboticsAPI.deviceModel.JointPosition;
//import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
//import com.kuka.roboticsAPI.geometricModel.CartDOF;
//import com.kuka.roboticsAPI.motionModel.PTP;
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
//public class VibratoryTestingStuff extends RackHandlingApplicationSuper {
//
//	RackConfig manipulation_rack;
//	VibratoryPhotolysisRack photolysis_rack;
//	private CartesianImpedanceControlMode stiffMode;
//
//	@Override
//	public void initialize() {
//		super.initialize();
//	
//		LinMoveRackSpeed = 300;
//		LinMoveVialSpeed = 200;
//		LinMoveVialSpeedSlow = 35;
//		PTPMoveSpeed = 0.5;
//		Blending = 0;
//		
//		
//		Station = Location.Vibratory_Photocat;
//
//		stiffMode = new CartesianImpedanceControlMode();
//		stiffMode.parametrize(CartDOF.X).setStiffness(5000);
//		stiffMode.parametrize(CartDOF.Y).setStiffness(5000);
//		stiffMode.parametrize(CartDOF.Z).setStiffness(5000);
//		stiffMode.parametrize(CartDOF.A).setStiffness(300);
//		stiffMode.parametrize(CartDOF.B).setStiffness(300);
//		stiffMode.parametrize(CartDOF.C).setStiffness(300);
//		
//		try {
//			manipulation_rack = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName() + "/Cube_Corner/rack_grasp"), logger, false, true, false);
//			
//			photolysis_rack = new VibratoryPhotolysisRack(getApplicationData().getFrame(
//				"/Station/" + Station.getPointSetName() + "/Cube_Corner/P1"), getApplicationData().getFrame(
//				"/Station/" + Station.getPointSetName() + "/Cube_Corner/P4"), getApplicationData().getFrame(
//				"/Station/" + Station.getPointSetName() + "/Cube_Corner/P16"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void run() throws Exception {
//		// TODO Auto-generated method stub
////		GraspRackFromRobotAndPlaceInBuffer(0,0);
//		
////		StartVialHandling();
////		GraspVialFromRack(0);
//		ShakeVial();
////		for(int i=0;i<16;i++)
////		{
////			int sample_number;
////			if(i<8) 
////				sample_number = i; 
////			else 
////				sample_number = i+2;
////			PlaceVialInRack(sample_number);  
////			GraspVialFromRack(sample_number);
////			
////			MoveToDryPosition();
////			DryProcedure();
////			MoveToNeutralAfterDry();
////			
////			PlaceInPhotolysisBuffer(i);
////			GraspFromPhotolysisBuffer(i);
////					
////			
////		}
////		PlaceVialInRack(0);
////		StopVialHandling();
////
////		GraspRackFromBufferAndPlaceOnRobot(0,0);
//	}
//
//
//
//
//	private void ShakeVial() {
//		CartesianSineImpedanceControlMode shake_vial = 
//				
//				CartesianSineImpedanceControlMode.createSinePattern(CartDOF.ALL, 15.0,
//				3, 150.0); 
//				
//				
//		shake_vial.parametrize(CartDOF.X).setStiffness(1000);
//		shake_vial.parametrize(CartDOF.Y).setStiffness(1000);
//		shake_vial.parametrize(CartDOF.Z).setStiffness(5000);
//		shake_vial.parametrize(CartDOF.B).setStiffness(300);
//		shake_vial.parametrize(CartDOF.C).setStiffness(300);
//
//				
//		Gripper.getFrame("/spacer/tcp").move(positionHold(shake_vial, 3, TimeUnit.SECONDS));
//	}
//
//	private void MoveToNeutralAfterDry() {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station.getPointSetName() +"/Cube_Corner/pre_dry"))
//				.setCartVelocity(LinMoveVialSpeed));
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station.getPointSetName() +"/Cube_Corner/neutral"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//	}
//
//	private void DryProcedure() {
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station.getPointSetName() +"/Cube_Corner/dry_side"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station.getPointSetName() +"/Cube_Corner/dry_bottom"))
//				.setCartVelocity(4));
//		Gripper.getFrame("/spacer/tcp").move(positionHold(stiffMode, 15, TimeUnit.SECONDS));
//	}
//
//	private void MoveToDryPosition() {
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station.getPointSetName() +"/Cube_Corner/pre_dry"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station.getPointSetName() +"/Cube_Corner/dry_side"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//	}
//
//	private void GraspFromPhotolysisBuffer(int index) throws GripperFailException {
//		
//		 GripperPrepareForGraspVial();
//		Gripper.getFrame("/spacer/tcp").move(
//		lin(photolysis_rack.GetVialPreGrasp(index))
//		.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(photolysis_rack.GetVialGrasp(index))
//			.setCartVelocity(LinMoveVialSpeedSlow));
//		
//		GripperGraspVial();
//		
//	
//			Gripper.getFrame("/spacer/tcp").move(
//			lin(photolysis_rack.GetVialPreGrasp(index))
//			.setCartVelocity(LinMoveVialSpeedSlow));
//			
//			Gripper.getFrame("/spacer/tcp").move(
//				lin(
//					getApplicationData().getFrame(
//						"/Station/"+Station.getPointSetName() +"/Cube_Corner/neutral"))
//					.setCartVelocity(LinMoveVialSpeed));
//	}
//
//	private void PlaceInPhotolysisBuffer(int index) {
//
//		Gripper.getFrame("/spacer/tcp").move(
//		lin(photolysis_rack.GetVialPreGrasp(index))
//		.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(photolysis_rack.GetVialGrasp(index))
//			.setCartVelocity(LinMoveVialSpeedSlow));
//		
//		 GripperPrepareForGraspVial();
//		
//		 GripperPrepareForGraspVial();
//			Gripper.getFrame("/spacer/tcp").move(
//			lin(photolysis_rack.GetVialPreGrasp(index))
//			.setCartVelocity(LinMoveVialSpeedSlow));
//			
//			Gripper.getFrame("/spacer/tcp").move(
//				lin(
//					getApplicationData().getFrame(
//						"/Station/"+Station.getPointSetName() +"/Cube_Corner/neutral")) 
//					.setCartVelocity(LinMoveVialSpeed));
//		
//	}
//
//	private void GraspVialFromRack(int index) throws Exception {
//		 GripperPrepareForGraspVial();
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index))
//			.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_position(index))
//				.setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index))
//			.setCartVelocity(LinMoveVialSpeedSlow));
//
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station.getPointSetName() +"/Cube_Corner/neutral"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//					
//		
//	}
//
//	private void PlaceVialInRack(int index) throws Exception {
//
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)) 
//			.setBlendingCart(Blending)
//			.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_position(index))
//			.setBlendingCart(Blending)
//			.setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperPrepareForGraspVial();
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index))
//			.setCartVelocity(LinMoveVialSpeedSlow));
//		
//
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station.getPointSetName() +"/Cube_Corner/neutral"))
//				.setJointVelocityRel(
//					PTPMoveSpeed));
//		
//	}
//
//	private void StopVialHandling() {
//		
//
//		
//		Gripper.getFrame("/spacer/tcp").move(
//		ptp(
//			getApplicationData().getFrame(
//				"/Station/" + Station.getPointSetName() + "/Cube_Corner/pre_lin_rack"))
//			.setBlendingCart(Blending).setJointVelocityRel(
//				PTPMoveSpeed));
//	
//ArmToDrivePos();
//		
//	}
//
//	private void StartVialHandling() {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName() + "/Cube_Corner/pre_lin_rack"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName() + "/Cube_Corner/neutral"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//		
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
//					"/Station/" + Station.getPointSetName() + "/Cube_Corner/pre_lin_rack"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_grasp_position())
//				.setCartVelocity(0.5 * LinMoveRackSpeed));
//
//		PlaceRack(manipulation_rack);
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName() + "/Cube_Corner/pre_lin_rack"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//		
//		ArmToDrivePos();
//	}
//
//	// Start and end in Drive Pos
//	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index, int station_index) throws Exception {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName() + "/Cube_Corner/pre_lin_rack"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(manipulation_rack.get_pre_grasp_position())
//				// looks weird.setJointVelocityRel(RackHoldingMoveSpeed)
//			.setJointVelocityRel(0.5 * PTPMoveSpeed)
//				);
//
//		GraspRack(manipulation_rack);
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin( 
//				getApplicationData().getFrame(
//					"/Station/" + Station.getPointSetName() + "/Cube_Corner/pre_lin_rack"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//		
//				emptyRack.getFrame("/spacer/tcp").move(
//			lin(getApplicationData().getFrame("/Station/"+Station.getPointSetName() +"/robot_pre_rack_grasp"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//		
//		PlaceRackOnRobot(robot_index);
//	}
//
//}