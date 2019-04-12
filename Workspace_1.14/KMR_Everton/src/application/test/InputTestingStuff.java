//package application.test;
//
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
//
//import java.sql.PreparedStatement;
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
//public class InputTestingStuff
//	extends
//		RackHandlingApplicationSuper {
//
//
//	
//	RackConfig[] manipulation_rack = new RackConfig[10];
//
//	
//	
//	@Override
//	public void initialize() {
//		super.initialize();
//		Station = Location.InputStation;
//		
//		LinMoveRackSpeed = 300;
//		LinMoveVialSpeed = 200;
//		LinMoveVialSpeedSlow = 50; 
//		PTPMoveSpeed = 0.5;
//		Blending = 0;
//		
//		try {
//			for(int i=0;i<10;i++)
//			{
//				manipulation_rack[i] = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/Cube_Corner/rack_grasp" + i), logger, false, true, false);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Override
//	public void run() throws Exception {
//		for(int i=3;i<10;i++)
//		{GraspRackFromRobotAndPlaceInBuffer(0,i);
//		GraspRackFromBufferAndPlaceOnRobot(0,i);
//		}
//	}
//	// Start and end in Drive Pos
//	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index, int station_index) throws Exception {
//		
//		GraspRackFromRobot(robot_index);
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/pre_ptp_to_workbench"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//		
//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/ptp_to_workbench"))
//				.setBlendingCart(Blending)
//				.setJointVelocityRel(PTPMoveSpeed));
//		
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack[station_index].get_pre_grasp_position())
//				.setCartVelocity(LinMoveRackSpeed));
//
//		PlaceRack(manipulation_rack[station_index]);
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/ptp_to_workbench"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		ArmToDrivePos();
//	}
//
//	
//
//	// Start and end in Drive Pos
//	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index, int station_index) throws Exception {
//		
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/ptp_to_workbench"))
//				.setBlendingCart(Blending)
//				.setJointVelocityRel(PTPMoveSpeed));
//
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(manipulation_rack[station_index].get_pre_grasp_position())
//			.setBlendingCart(Blending)
//			.setJointVelocityRel(PTPMoveSpeed));
//		
//		GraspRack(manipulation_rack[station_index]);
//		
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/ptp_to_workbench"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed)); 
//		
//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/"+Station+"/pre_ptp_to_workbench"))
//				.setBlendingCart(Blending)
//				.setJointVelocityRel(PTPMoveSpeed));
//				
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(getApplicationData().getFrame("/Station/"+Station+"/robot_pre_rack_grasp"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//		
//		PlaceRackOnRobot(robot_index);
//	}
//
//	
//}
