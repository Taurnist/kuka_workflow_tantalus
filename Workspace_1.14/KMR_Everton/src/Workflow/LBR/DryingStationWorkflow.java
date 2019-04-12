 package Workflow.LBR;

import javax.inject.Inject;

import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;
import Workflow.Configuration.Location;
import Workflow.LBR.Action.CartridgeHotel;
import Workflow.LBR.Action.FancyFrame;
import Workflow.LBR.Action.RackConfig;
import Workflow.LBR.Messages.*;
import Workflow.LBR.WorkflowSuper.ArmPoseEnum;


import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;

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
public class DryingStationWorkflow extends WorkflowSuper {

	RackConfig[] manipulation_rack = new RackConfig[6];
	double SignPTPSpeed = 0.2;
	int SignLINSpeed = 300;
	@Override
	public void initialize() {
		super.initialize();

		LinMoveRackSpeed = 300;
		LinMoveVialSpeed = 200;
		LinMoveVialSpeedSlow = 50;
		PTPMoveSpeed = 0.5;
		Blending = 0;

		SetObjectsBasedOn6PHook();

	}
	@Override
	protected void SetObjectsBasedOn6PHook() {
		try {
			for (int i = 0; i < 6; i++) {
				manipulation_rack[i] = new RackConfig(getApplicationData(),
					getApplicationData().getFrame(
						"/Station/" + Station + "/Cube_Corner/rack_grasp" + i),
					logger, true, true, false);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void OtherTasksHook(LBRTask task)
		throws UnexpectedCollisionDetected, InterruptedException,
		GripperFailException {
		switch (task.getType()) {
			case TakeRackFromRobotAndPlaceOnStation : {
				int station_index = ((LBRTakeRackFromRobotAndPlaceOnStation) task)
					.getStationIndex();
				int robot_index = ((LBRTakeRackFromRobotAndPlaceOnStation) task)
					.getRobotIndex();
				try {
					AssertDrivePosition();
					GraspRackFromRobotAndPlaceInBuffer(robot_index, station_index);
					client.updateStatus(true, null);
					
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}

			case GraspSign : {
				try {
					AssertDrivePosition();
					GraspSign();
					client.updateStatus(true, null);
					} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}

			case TakeRackFromStationAndPlaceOnRobot : {
				int station_index = ((LBRTakeRackFromStationAndPlaceOnRobot) task)
					.getStationIndex();
				int robot_index = ((LBRTakeRackFromStationAndPlaceOnRobot) task)
					.getRobotIndex();
				try {
					AssertDrivePosition();
					GraspRackFromBufferAndPlaceOnRobot(robot_index, station_index);
					client.updateStatus(true, null);
					} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			default :
				logger.error("task.getType(): " + task.getType().toString());
				logger
					.error("Server requesting task that is not defined for this station");
				client.updateStatus(false,
					"Not implemented task requested");

		}

	}

	private void GraspSign() throws GripperFailException {
		GripperPrepareForGraspCartridge();
		// TODO Auto-generated method stub
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station
						+ "/Cube_Corner/sign_grasp/sign_pre_grasp")).setBlendingCart(
				Blending).setJointVelocityRel(SignPTPSpeed * 1.5));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/sign_grasp"))
				.setBlendingCart(Blending).setCartVelocity(SignLINSpeed / 3));

		GripperGraspRack();

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station
						+ "/Cube_Corner/sign_grasp/sign_after_grasp"))
				.setBlendingCart(Blending).setCartVelocity(SignLINSpeed / 3));

		ArmToDrivePos();
	}

	// Start and end in Drive Pos
	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index,
		int station_index) throws Exception {
		GraspRackFromRobot(robot_index);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin( 
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/pre_lin_rackgrasp"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp")
			.move(
				lin(
					manipulation_rack[station_index]
						.get_minimal_pre_grasp_position()).setCartVelocity(
					LinMoveRackSpeed));

		PlaceRackMinimal(manipulation_rack[station_index]);

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/pre_lin_rackgrasp"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin( // PTP here bit dangerous
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		ArmToDrivePos();
	}

	// Start and end in Drive Pos
	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index,
		int station_index) throws Exception {
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/pre_lin_rackgrasp"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp")
			.move(
				lin(
					manipulation_rack[station_index]
						.get_minimal_pre_grasp_position()).setBlendingCart(Blending)
					.setJointVelocityRel(PTPMoveSpeed));

		GraspRackMinimal(manipulation_rack[station_index]);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/pre_lin_rackgrasp"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/robot_pre_rack_grasp"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		PlaceRackOnRobot(robot_index);
	}

}