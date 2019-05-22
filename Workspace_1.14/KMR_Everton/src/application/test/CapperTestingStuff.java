package application.test;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import Exception.GripperFailException;
import Workflow.Configuration.Location;
import Workflow.LBR.RackHandlingApplicationSuper;
import Workflow.LBR.Action.RackConfig;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;

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
public class CapperTestingStuff extends RackHandlingApplicationSuper {

	RackConfig manipulation_rack;
	
	private CartesianImpedanceControlMode relaxMode;

	long waitWhileGrasping;
	@Override
	public void initialize() {
		super.initialize();
		
		relaxMode = new CartesianImpedanceControlMode();
		relaxMode.parametrize(CartDOF.X).setStiffness(1000);
		relaxMode.parametrize(CartDOF.Y).setStiffness(1000);
		relaxMode.parametrize(CartDOF.Z).setStiffness(1000);
		relaxMode.parametrize(CartDOF.A).setStiffness(50);
		relaxMode.parametrize(CartDOF.B).setStiffness(300);
		relaxMode.parametrize(CartDOF.C).setStiffness(300);
		
		
		LinMoveRackSpeed = 300;
		LinMoveVialSpeed = 200;
		LinMoveVialSpeedSlow = 50; 
		PTPMoveSpeed = 0.5;
		Blending = 0;
		
		waitWhileGrasping = 2000;
		
		Station = Location.LiquidHandlingSystem;
		try {
			manipulation_rack = new RackConfig(getApplicationData(),
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/rack_grasp"), logger, false);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() throws Exception {
		
		Station = Location.InputStation;
		lbr_iiwa.detachAll();
		emptyRack.attachTo(lbr_iiwa.getFlange());
		emptyRack.getFrame("/spacer/tcp").move(
			lin(getApplicationData().getFrame("/Station/"+Station+"/robot_pre_rack_grasp"))
				.setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));
		
		PlaceRackOnRobot(2);
		
		// TODO Auto-generated method stub
//		GraspRackFromRobotAndPlaceInBuffer(0,0);
//
//		MoveIntoNeutral();
//		GraspVialFromBuffer(0);
//
//		PlaceVialInCapper();
//
//		GraspVialInCapper();
//
////		 for (int i = 1; i < 18; i++) {
////		 if (i == 8) {
////		 i++;
////		 i++;
////		 }
////		
////		 PlaceVialInBuffer(i);
////		 GraspVialFromBuffer(i);
////		
////		 PlaceVialInCapper();
////		
////		 GraspVialInCapper();
////		 }
//
//		PlaceVialInBuffer(0);
//
//		MoveOutOfNeutral();
//
////		GraspRackFromBufferAndPlaceOnRobot(0,0);
	}

	private void PlaceVialInCapper() {
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p1_capper_vial_grasp"))
				.setBlendingCart(20).setCartVelocity(LinMoveVialSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p2_capper_vial_grasp"))
				.setBlendingCart(20).setCartVelocity(LinMoveVialSpeed));
		
		
		
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p3_capper_vial_grasp"))
				.setCartVelocity(LinMoveVialSpeed)
				
				);

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/capper_vial_post_grasp"))
				.setCartVelocity(0.5*LinMoveVialSpeedSlow)
				);

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/capper_vial_grasp"))
				.setCartVelocity(0.5*LinMoveVialSpeedSlow)
				);
		
		GripperPrepareForGraspRack();
		//break async
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p3_capper_vial_grasp"))
				.setCartVelocity(LinMoveVialSpeedSlow)
				.setMode(relaxMode));
		
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p2_capper_vial_grasp"))
				.setBlendingCart(20).setCartVelocity(LinMoveVialSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p1_capper_vial_grasp"))
				.setBlendingCart(20).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setCartVelocity(LinMoveVialSpeed));
	}

	private void GraspVialInCapper() throws GripperFailException {
		GripperPrepareForGraspRack();
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p1_capper_vial_grasp"))
				.setBlendingCart(20).setCartVelocity(LinMoveVialSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p2_capper_vial_grasp"))
				.setBlendingCart(20).setCartVelocity(LinMoveVialSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p3_capper_vial_grasp"))
				.setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/capper_vial_grasp"))
				.setCartVelocity(LinMoveVialSpeedSlow)
				);
		
		GripperGraspVial();
		//break async

		

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p3_capper_vial_grasp"))
				.setCartVelocity(0.5*LinMoveVialSpeedSlow)
				.setMode(relaxMode));
		
		
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p2_capper_vial_grasp"))
				.setBlendingCart(20).setCartVelocity(LinMoveVialSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Capper/Cube_Corner/pre_p1_capper_vial_grasp"))
				.setBlendingCart(20).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setCartVelocity(0.5 * LinMoveVialSpeed));

	}
	private void MoveOutOfNeutral() {

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending)
				.setJointVelocityRel(
					PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPMoveSpeed));

		ArmToDrivePos();
	}

	private void PlaceVialInBuffer(int index) throws Exception {

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setBlendingCart(20)
				.setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_position(index)).setCartVelocity(LinMoveVialSpeedSlow));

		GripperPrepareForGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(LinMoveVialSpeedSlow));
		
		Gripper.getFrame("/spacer/tcp").move(
			ptp(manipulation_rack.get_pre_grasp_position())
				.setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setJointVelocityRel(PTPMoveSpeed));

	}

	private void GraspVialFromBuffer(int index) throws Exception {
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setBlendingCart(20)
				.setCartVelocity(LinMoveVialSpeed));

		GripperPrepareForGraspVial();
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_position(index)).setCartVelocity(LinMoveVialSpeedSlow));

		GripperGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(LinMoveVialSpeedSlow));
		
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position())
				.setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setCartVelocity(LinMoveVialSpeed));
	}

	private void MoveIntoNeutral() {

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPMoveSpeed));

	}

	// Start and end in Drive Pos
	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index, int station_index) throws Exception {
		GraspRackFromRobot(robot_index);
		
		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
				.setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position())
				.setCartVelocity(0.5 * LinMoveRackSpeed));

		PlaceRack(manipulation_rack);

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
				.setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));
		ArmToDrivePos();
	}

	// Start and end in Drive Pos
	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index, int station_index) throws Exception {

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(manipulation_rack.get_pre_grasp_position())
				// looks weird.setJointVelocityRel(RackHoldingMoveSpeed)
			.setJointVelocityRel(0.5 * PTPMoveSpeed)
				);

		GraspRack(manipulation_rack);

		emptyRack.getFrame("/spacer/tcp").move(
			lin( 
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
				.setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));
		
		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(getApplicationData().getFrame("/Station/"+Station+"/robot_pre_rack_grasp"))
				.setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));
		
		PlaceRackOnRobot(robot_index);
	}

}