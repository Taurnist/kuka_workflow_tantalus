//package application.test;
//
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.linRel;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
//import sun.rmi.runtime.Log;
//import tool.GripperFesto;
//import Exception.GripperFailException;
//import Exception.UnexpectedCollisionDetected;
//import Workflow.Configuration.Location;
//import Workflow.LBR.GripperApplicationsSuper;
//import Workflow.LBR.RackHandlingApplicationSuper;
//import Workflow.LBR.Action.RackConfig;
//
//
//
//import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
//import com.kuka.roboticsAPI.conditionModel.ForceCondition;
//import com.kuka.roboticsAPI.controllerModel.Controller;
//import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.geometricModel.Frame;
//import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
//import com.kuka.roboticsAPI.geometricModel.Tool;
//import com.kuka.roboticsAPI.geometricModel.World;
//import com.kuka.roboticsAPI.motionModel.IMotion;
//import com.kuka.roboticsAPI.motionModel.IMotionContainer;
//import com.kuka.task.ITaskLogger;
//
//public class GCTestingStuff extends RackHandlingApplicationSuper {
//
//
//	RackConfig manipulation_rack_0, manipulation_rack_1,manipulation_rack_2;
//
//
//	
//
//	@Override
//	public void initialize() {
//		super.initialize();
//		LinMoveRackSpeed = 300;
//		PTPMoveSpeed = 0.5;
//		PTPRackHoldingMoveSpeed = 0.5;
//		Blending = 0;
//		
//		Station = Location.GC;
//		
//		try {
//			manipulation_rack_0 = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/rack_grasp0"), logger);
//			
//			manipulation_rack_1 = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/rack_grasp1"), logger);
//			
//			manipulation_rack_2 = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/rack_grasp2"), logger);
//
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	public void run() throws Exception {
//		PressParkHeadspaceButton();
//		for(int gc_rack_index = 0;gc_rack_index<3;gc_rack_index++)
//		{
//			GraspRackFromRobotAndPlaceInBuffer(0,gc_rack_index);
//			GraspRackFromBufferAndPlaceOnRobot(0,gc_rack_index);
//		}
//		PressParkHeadspaceButton();
//	}
//
//	private void PressParkHeadspaceButton() {
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/park_button_PTP_pre_press"))
//				.setJointVelocityRel(PTPMoveSpeed));
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/park_button_pre_press"))
//				.setJointVelocityRel(PTPMoveSpeed));
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/park_button_press"))
//				.setCartVelocity(10));
//				
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/park_button_pre_press"))
//				.setJointVelocityRel(PTPMoveSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/park_button_PTP_pre_press"))
//				.setJointVelocityRel(PTPMoveSpeed));
//		ArmToDrivePos();
//		
//	}
//
//	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index, int station_index) throws Exception {
//		RackConfig manipulation_rack = GetGCRack(station_index); 
//		
//			Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//		
//			Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//			
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_minimal_pre_grasp_position())
//				.setJointVelocityRel(PTPMoveSpeed));
//		
//		GraspRackMinimal(manipulation_rack);
//
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(getApplicationData().getFrame("/Station/"+Station+"/robot_pre_rack_grasp"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//		
//
//		PlaceRackOnRobot(robot_index);
//	}
//
//	private RackConfig GetGCRack(int gc_rack_index) {
//		RackConfig result;
//		switch(gc_rack_index)
//		{
//			case 0:
//				result = manipulation_rack_0;
//				break;
//			case 1:
//				result = manipulation_rack_1;
//				break;
//			case 2:
//				result = manipulation_rack_2;
//				break;
//			default:
//					throw new NotImplementedException();
//			}
//		return result;
//	}
//
//	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index, int station_index) throws Exception {
//		RackConfig manipulation_rack = GetGCRack(station_index); 
//		
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
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
//				.setBlendingCart(Blending)
//				.setCartVelocity(LinMoveRackSpeed));
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack.get_minimal_pre_grasp_position())
//				.setCartVelocity(LinMoveRackSpeed));
//
//		PlaceRackMinimal(manipulation_rack);
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//		
//			Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
//				.setBlendingCart(Blending).setJointVelocityRel(
//					PTPMoveSpeed));
//
//			
//		ArmToDrivePos();
//		
//	}
//
//	
//}
