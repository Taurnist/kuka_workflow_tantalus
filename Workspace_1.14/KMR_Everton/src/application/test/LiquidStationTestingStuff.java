//package application.test;
//
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.linRel;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
//
//import java.sql.PreparedStatement;
//
//import com.kuka.roboticsAPI.geometricModel.CartDOF;
//import com.kuka.roboticsAPI.geometricModel.Frame;
//import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
//import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
//import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianSineImpedanceControlMode;
//
//import Exception.GripperFailException;
//import Exception.UnexpectedCollisionDetected;
//import Workflow.Configuration.Location;
//import Workflow.LBR.GripperApplicationsSuper;
//import Workflow.LBR.RackHandlingApplicationSuper;
//import Workflow.LBR.Action.RackConfig;
//
//public class LiquidStationTestingStuff
//	extends
//		RackHandlingApplicationSuper {
//	
//	private CartesianImpedanceControlMode pullOutMode;
//	
//	
//	RackConfig manipulation_rack;
//
//	
//	
//	@Override
//	public void initialize() {
//		super.initialize();
//		
//		
//		pullOutMode = 
//				
//		CartesianSineImpedanceControlMode.createSinePattern(CartDOF.ROT, 15.0,
//		0.3, 150.0); 
//		
//		
//		pullOutMode.parametrize(CartDOF.X).setStiffness(1000);
//		pullOutMode.parametrize(CartDOF.Y).setStiffness(1000);
//		pullOutMode.parametrize(CartDOF.Z).setStiffness(5000);
//		pullOutMode.parametrize(CartDOF.B).setStiffness(300);
//		pullOutMode.parametrize(CartDOF.C).setStiffness(300);
//		
//		Station = Station.LiquidHandlingSystem;
//
//		
//		
//		
//		try {
//			manipulation_rack = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/Cube_Corner/rack_grasp"), logger, false, true, false);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Override
//	public void run() throws Exception {
//GraspVialFromCapper();
//PlaceVialInCapper(); 
//		
////		StartVialHandling();
////		GraspVialFromBuffer(0);
////		
////		PlaceVialInLiquidStation(0);
////		
////		MoveVialFromStationOnetoStationTwo();
////		GraspVialInLiquidStation(1);
////		PlaceVialInCapper();
////		GraspVialFromCapper();
////		PlaceVialInBuffer(0);
//		
////		GraspRackFromRobotAndPlaceInBuffer(0,0);
////		StartVialHandling();
////		
////		
////
////	
////		for(int i=0;i<18;i++)
////		{
////			if (i==8)
////			{
////				i++;
////				i++;
////			}
////			GraspVialFromBuffer(i);
////			
////		
////			
////			PlaceVialInLiquidStation(0);
////			
////			MoveVialFromStationOnetoStationTwo();
////			GraspVialInLiquidStation(1);
////			
////			PlaceVialInBuffer(i);
////		}	 
////		
////		
////		PlaceVialInBuffer(0);
////		
////		StopVialHandling();
////		GraspRackFromBufferAndPlaceOnRobot(0,0);
//	}
//
//
//	private void GraspVialFromCapper() throws GripperFailException {
//		GripperPrepareForGraspVial();
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P6"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P7"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P8"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P9"))
//				.setCartVelocity(LinMoveVialSpeedSlow));
//		
//		GripperGraspVial();
//		
//			
//		Gripper.getFrame("/spacer/tcp").move(
//				linRel(0, 0, -30, 0, 0, 0, Gripper.getFrame("/spacer/tcp"))
//				.setCartVelocity(6).setMode(pullOutMode));
//		
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P8"))
//				.setCartVelocity(LinMoveVialSpeedSlow));
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P7"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P6"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/" + Station +"/Cube_Corner/neutral"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//	}
//	private void PlaceVialInCapper() {
//	
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P6"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P7"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P8"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P10"))
//				.setCartVelocity(50));
//			
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P9"))
//				.setCartVelocity(50));
//		
//		GripperPrepareForGraspVial();
//			
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P8"))
//				.setCartVelocity(LinMoveVialSpeedSlow));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P7"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P6"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//				lin(getApplicationData().getFrame("/Station/" + Station +"/Cube_Corner/neutral"))
//				.setCartVelocity(LinMoveVialSpeed));
//		
//	}
//	private void MoveVialFromStationOnetoStationTwo()
//		throws GripperFailException {
//
//		// Flip gripper
//		GripperPrepareForGraspVial();
//
//		Gripper
//			.getFrame("/spacer/tcp/flipped_liquid")
//			.move(
//				ptp(
//					getApplicationData()
//						.getFrame(
//							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
//					.setJointVelocityRel(PTPMoveSpeed));
//
//		GraspVialInStationFlipped(0);
//		PlaceVialInStationFlipped(1);
//
//		Gripper
//			.getFrame("/spacer/tcp/flipped_liquid")
//			.move(
//				ptp(
//					getApplicationData()
//						.getFrame(
//							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
//					.setJointVelocityRel(PTPMoveSpeed));
//
//		// Flip Gripper Back
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setJointVelocityRel(PTPMoveSpeed));
//
//	}
//	private void PlaceVialInLiquidStation(int i)
//		throws GripperFailException {
//		FlipVialGraspToFlipped();
//
//		PlaceVialInStationFlipped(i);
//
//		Gripper
//			.getFrame("/spacer/tcp/flipped_liquid")
//			.move(
//				lin(
//					getApplicationData()
//						.getFrame(
//							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
//					.setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setJointVelocityRel(PTPMoveSpeed));
//
//	}
//	private void PlaceVialInStationFlipped(int i) {
//		ObjectFrame pre_station = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/pre_station" + i);
//		ObjectFrame vial_pre_grasp_station = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/vial_pre_grasp_station" + i);
//		ObjectFrame vial_grasp_station = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/vial_grasp_station" + i);
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(pre_station).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_pre_grasp_station).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_pre_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(pre_station).setCartVelocity(LinMoveVialSpeed));
//	}
//	private void GraspVialInLiquidStation(int i)
//		throws GripperFailException {
//
//		GripperPrepareForGraspVial();
//
//		Gripper
//			.getFrame("/spacer/tcp/flipped_liquid")
//			.move(
//				ptp(
//					getApplicationData()
//						.getFrame(
//							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
//					.setJointVelocityRel(PTPMoveSpeed));
//
//		GraspVialInStationFlipped(i);
//
//		Gripper
//			.getFrame("/spacer/tcp/flipped_liquid")
//			.move(
//				lin(
//					getApplicationData()
//						.getFrame(
//							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
//					.setCartVelocity(LinMoveVialSpeed));
//
//		// TODO Auto-generated method stub
//		FlipVialGraspToNormal();
//	}
//	private void GraspVialInStationFlipped(int i)
//		throws GripperFailException {
//		ObjectFrame pre_station = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/pre_station" + i);
//		ObjectFrame vial_pre_grasp_station = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/vial_pre_grasp_station" + i);
//		ObjectFrame vial_grasp_station = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/vial_grasp_station" + i);
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(pre_station).setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_pre_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperGraspVial();
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_pre_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(pre_station).setCartVelocity(LinMoveVialSpeed));
//	}
//	// Start and end in Drive Pos
//	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index,
//		int station_index) throws Exception {
//
//		GraspRackFromRobot(robot_index);
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/pre_ptp_to_workbench"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
//				Blending).setJointVelocityRel(PTPMoveSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_pre_grasp_position()).setCartVelocity(
//				LinMoveRackSpeed));
//
//		PlaceRack(manipulation_rack);
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
//				Blending).setCartVelocity(LinMoveRackSpeed));
//
//		ArmToDrivePos();
//	}
//
//	// Start and end in Drive Pos
//	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index,
//		int station_index) throws Exception {
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
//				Blending).setJointVelocityRel(PTPMoveSpeed));
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
//					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
//				Blending).setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/pre_ptp_to_workbench"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
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
//	private void StartVialHandling() {
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setJointVelocityRel(PTPMoveSpeed));
//	}
//	private void StopVialHandling() {
//
//		ArmToDrivePos();
//	}
//
//	private void PlaceVialInBuffer(int index) throws Exception {
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
//			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setJointVelocityRel(PTPMoveSpeed));
//
//	}
//
//	private void GraspVialFromBuffer(int index) throws Exception {
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
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral"))
//				.setCartVelocity(LinMoveVialSpeed));
//
//	}
//
//	private void FlipVialGraspToNormal() throws GripperFailException {
//		ObjectFrame vial_grasp_normal = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/vial_fixture_grasp_normal");
//		ObjectFrame vial_pre_grasp_normal = getApplicationData().getFrame(
//			"/Station/" + Station
//				+ "/Cube_Corner/pre_vial_fixture_grasp_normal");
//		ObjectFrame flipped_mid_point = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/grasp_switch_mid_point");
//		ObjectFrame vial_pre_grasp_flipped = getApplicationData().getFrame(
//			"/Station/" + Station
//				+ "/Cube_Corner/pre_vial_fixture_grasp_flipped");
//		ObjectFrame vial_grasp_flipped = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/vial_fixture_grasp_flipped");
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_pre_grasp_flipped).setBlendingCart(Blending)
//				.setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_grasp_flipped).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_pre_grasp_flipped).setBlendingCart(Blending)
//				.setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/LiquidHandlingSystem/Cube_Corner/neutral"))
//				.setBlendingCart(Blending)
//				.setJointVelocityRel(PTPMoveSpeed * 0.5));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_pre_grasp_normal).setBlendingCart(Blending)
//				.setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_grasp_normal).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_pre_grasp_normal).setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/LiquidHandlingSystem/Cube_Corner/neutral"))
//				.setCartVelocity(LinMoveVialSpeedSlow));
//
//	}
//	private void FlipVialGraspToFlipped() throws GripperFailException {
//		ObjectFrame vial_grasp_normal = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/vial_fixture_grasp_normal");
//		ObjectFrame vial_pre_grasp_normal = getApplicationData().getFrame(
//			"/Station/" + Station
//				+ "/Cube_Corner/pre_vial_fixture_grasp_normal");
//		ObjectFrame flipped_mid_point = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/grasp_switch_mid_point");
//		ObjectFrame vial_pre_grasp_flipped = getApplicationData().getFrame(
//			"/Station/" + Station
//				+ "/Cube_Corner/pre_vial_fixture_grasp_flipped");
//		ObjectFrame vial_grasp_flipped = getApplicationData().getFrame(
//			"/Station/" + Station + "/Cube_Corner/vial_fixture_grasp_flipped");
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_pre_grasp_normal).setBlendingCart(Blending)
//				.setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_grasp_normal).setCartVelocity(LinMoveVialSpeedSlow));
//
//		GripperPrepareForGraspVial();
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(vial_pre_grasp_normal).setBlendingCart(Blending)
//				.setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			ptp(flipped_mid_point).setBlendingCart(Blending)
//				.setJointVelocityRel(PTPMoveSpeed * 0.5));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_pre_grasp_flipped).setBlendingCart(Blending)
//				.setCartVelocity(LinMoveVialSpeed));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_grasp_flipped).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeedSlow));
//
//		GripperGraspVial();
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(vial_pre_grasp_flipped).setBlendingCart(Blending)
//				.setCartVelocity(LinMoveVialSpeedSlow));
//
//		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
//			lin(flipped_mid_point).setBlendingCart(Blending).setCartVelocity(
//				LinMoveVialSpeed));
//	}
//	
//	
//}
