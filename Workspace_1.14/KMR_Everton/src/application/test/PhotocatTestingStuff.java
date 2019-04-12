//package application.test;
//
//import javax.inject.Inject;
//
//import Workflow.Configuration.Location;
//import Workflow.LBR.RackHandlingApplicationSuper;
//import Workflow.LBR.Action.RackConfig;
//
//import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
//
//import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
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
//public class PhotocatTestingStuff extends RackHandlingApplicationSuper {
//
//	RackConfig manipulation_rack;
//
//
//
//	@Override
//	public void initialize() {
//		super.initialize();
//		
//		LinMoveRackSpeed = 300;
//		PTPMoveSpeed = 0.5;
//		PTPRackHoldingMoveSpeed = 0.5;
//		Blending = 0;
//		Station = Location.Photocat;
//		try {
//			manipulation_rack = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/rack_grasp"), logger);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void run() throws Exception {
//		// TODO Auto-generated method stub
//		GraspRackFromRobotAndPlaceInBuffer(3,0);
//		GraspRackFromBufferAndPlaceOnRobot(3,0);
//	}
//
//	// Start and end in Drive Pos
//	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index, int station_index) throws Exception {
//		GraspRackFromRobot(robot_index);
//		
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
//				.setBlendingCart(Blending)
//				.setJointVelocityRel(
//					PTPRackHoldingMoveSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
//				.setBlendingCart(Blending)
//				.setJointVelocityRel(
//					PTPRackHoldingMoveSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P4"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_grasp_position())
//				.setCartVelocity(LinMoveRackSpeed));
//
//		PlaceRack(manipulation_rack);
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//		ArmToDrivePos();
//	}
//
//	// Start and end in Drive Pos
//	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index, int station_index) throws Exception {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(manipulation_rack.get_pre_grasp_position())
//				.setJointVelocityRel(PTPMoveSpeed));
//		
//		GraspRack(manipulation_rack);
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P4"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
//				.setBlendingCart(Blending)
//				.setJointVelocityRel(
//					PTPRackHoldingMoveSpeed));
//		
//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
//				.setBlendingCart(Blending)
//				.setJointVelocityRel(
//					PTPRackHoldingMoveSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(getApplicationData().getFrame("/Station/"+Station+"/robot_pre_rack_grasp"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//		
//		PlaceRackOnRobot(robot_index);
//	}
//
//}