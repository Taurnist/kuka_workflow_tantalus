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
//public class DryingTestingStuff extends RackHandlingApplicationSuper {
//
//	RackConfig[] manipulation_rack = new RackConfig[6];
//	double SignPTPSpeed = 0.2;
//	int SignLINSpeed = 300;
//
//	
//
//	@Override
//	public void initialize() {
//		super.initialize();
//		LinMoveRackSpeed = 300;
//		LinMoveVialSpeed = 200;
//		LinMoveVialSpeedSlow = 50;
//		PTPMoveSpeed = 0.5;
//		Blending = 0;
//		
//
//		
//		Station = Location.DryingStation;
//		
//		try {
//		
//			for(int i=0;i<6;i++)
//			{
//				manipulation_rack[i] = new RackConfig(getApplicationData(),
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/rack_grasp"+i), logger,
//					true, true, false);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	public void run() throws Exception {
//		GraspRackFromRobotAndPlaceInBuffer(0,0);
//		GraspSign();
//		
////		for(int i=0;i<6;i++)
////		{
////			GraspRackFromRobotAndPlaceInBuffer(0,i);
////			GraspRackFromBufferAndPlaceOnRobot(0,i);
////		}
//		
//	}
//	private void GraspSign() throws GripperFailException {
//		GripperPrepareForGraspCartridge();
//		// TODO Auto-generated method stub
//		Gripper.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/sign_grasp/sign_pre_grasp"))
//				.setBlendingCart(Blending).setJointVelocityRel(SignPTPSpeed*1.5));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/sign_grasp"))
//				.setBlendingCart(Blending).setCartVelocity(SignLINSpeed/3 ));
//		
//		GripperGraspRack();
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/sign_grasp/sign_after_grasp"))
//				.setBlendingCart(Blending).setCartVelocity(SignLINSpeed/3));
//		
//		ArmToDrivePos();
//	}
//	
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
//
//		
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_rackgrasp"))
//				.setBlendingCart(Blending).setCartVelocity(
//					LinMoveRackSpeed));
//		
//		
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack[station_index].get_minimal_pre_grasp_position()).setCartVelocity(
//				LinMoveRackSpeed));
//
//		PlaceRackMinimal(manipulation_rack[station_index]);
//
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_rackgrasp"))
//				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));
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
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_rackgrasp"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
//		
//		Gripper.getFrame("/spacer/tcp").move(
//			lin(manipulation_rack[station_index].get_minimal_pre_grasp_position()).setBlendingCart(
//				Blending).setJointVelocityRel(PTPMoveSpeed));
//
//		GraspRackMinimal(manipulation_rack[station_index]);
//
//		emptyRack.getFrame("/spacer/tcp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_rackgrasp"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
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
//	
//}
