//package application.test;
//
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
//
//import com.kuka.roboticsAPI.geometricModel.Frame;
//import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
//
//import Exception.GripperFailException;
//import Exception.UnexpectedCollisionDetected;
//import Workflow.Configuration.Location;
//import Workflow.LBR.GripperApplicationsSuper;
//import Workflow.LBR.RackHandlingApplicationSuper;
//import Workflow.LBR.Action.RackConfig;
//
//public class QuantosTestingStuff extends RackHandlingApplicationSuper {
//
//	RackConfig manipulation_rack;
//
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
//		Station = Location.Quantos;
//		try {
//			manipulation_rack = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/Cube_Corner/rack_grasp"), logger, false);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Override
//	public void run() throws Exception {
//		GraspRackFromRobotAndPlaceInBuffer(0,0);
//		StartVialHandling();
//
//		GraspVialFromBuffer(0);
//
//		PlaceVialInQuantos();
//
//		GraspVialInQuantos();
//
////		for (int i = 1; i < 18; i++) {
////			if (i == 8) {
////				i++;
////				i++;
////			}
////
////			PlaceVialInBuffer(i);
////			GraspVialFromBuffer(i);
////
////			PlaceVialInQuantos();
////
////			GraspVialInQuantos();
////		}
//
//		PlaceVialInBuffer(0);
//		StopVialHandling();
//
//		GraspRackFromBufferAndPlaceOnRobot(0,0);
//
//	}
//
//	// Start and end in Drive Pos
//	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index, int station_index) throws Exception {
//
//		GraspRackFromRobot(robot_index);
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
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
//					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
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
//					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
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
//					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/robot_pre_rack_grasp"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));
//		
//		PlaceRackOnRobot(robot_index);
//	}
//
//	private void PlaceVialInBuffer(int index) throws Exception {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_grasp_position()).setBlendingCart(
//				Blending).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)).setBlendingCart(
//				Blending).setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_position(index))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(manipulation_rack.get_pre_grasp_position())
//				.setJointVelocityRel(PTPMoveSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/Quantos/Cube_Corner/neutral")).setJointVelocityRel(
//				PTPMoveSpeed));
//
//	}
//
//	private void GraspVialFromBuffer(int index) throws Exception {
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_grasp_position()).setBlendingCart(20)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		GripperPrepareForGraspVial();
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
//				LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_position(index)).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		GripperGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_grasp_position()).setCartVelocity(
//				LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/Quantos/Cube_Corner/neutral")).setJointVelocityRel(
//				PTPMoveSpeed));
//	}
//
//	private void StartVialHandling() {
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/Quantos/Cube_Corner/neutral")).setJointVelocityRel(
//				PTPMoveSpeed));
//	}
//	private void StopVialHandling() {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
//
//		ArmToDrivePos();
//	}
//
//	private void PlaceVialInQuantos() {
//		ObjectFrame vial_grasp = getApplicationData().getFrame(
//			"/Station/Quantos/Cube_Corner/vial_grasp");
//
//		Frame vial_pre_grasp = new Frame();
//		vial_pre_grasp.setParent(vial_grasp);
//		vial_pre_grasp.setZ(vial_pre_grasp.getZ() - 60);
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_pre_grasp).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_grasp).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_pre_grasp).setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/Quantos/Cube_Corner/neutral")).setCartVelocity(
//				LinMoveVialSpeed));
//
//	}
//	private void GraspVialInQuantos() throws GripperFailException {
//		ObjectFrame vial_grasp = getApplicationData().getFrame(
//			"/Station/Quantos/Cube_Corner/vial_grasp");
//
//		Frame vial_pre_grasp = new Frame();
//		vial_pre_grasp.setParent(vial_grasp);
//		vial_pre_grasp.setZ(vial_pre_grasp.getZ() - 60);
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_pre_grasp).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_grasp).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_pre_grasp).setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/Quantos/Cube_Corner/neutral")).setCartVelocity(
//				LinMoveVialSpeed));
//	}
//}
