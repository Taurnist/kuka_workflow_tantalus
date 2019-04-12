package Workflow.LBR;
 
import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;

import javax.inject.Inject;
import javax.inject.Named;

import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.Workpiece;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;
import Workflow.Configuration.Location;
import Workflow.LBR.Action.RackConfig;
import Workflow.LBR.Messages.LBRTakeRackFromRobotAndPlaceOnStation;
import Workflow.LBR.Messages.LBRTakeRackFromStationAndPlaceOnRobot;
import Workflow.LBR.Messages.LBRTask;
import Workflow.LBR.WorkflowSuper.ArmPoseEnum;

public class PhotoWorkflow extends WorkflowSuper {

	RackConfig manipulation_rack;

	@Override
	public void initialize() {
		super.initialize();

		LinMoveRackSpeed = 300;
		PTPMoveSpeed = 0.5;
		PTPRackHoldingMoveSpeed = 0.5;
		Blending = 0;

		SetObjectsBasedOn6PHook();

	}

	@Override
	protected void SetObjectsBasedOn6PHook() {
		try {
			manipulation_rack = new RackConfig(getApplicationData(),
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/rack_grasp"), logger);
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

	// Start and end in Drive Pos
	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index,
		int station_index) throws Exception {
		GraspRackFromRobot(robot_index);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPRackHoldingMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPRackHoldingMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P4"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setCartVelocity(
				LinMoveRackSpeed));

		PlaceRack(manipulation_rack);

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
		ArmToDrivePos();
	}

	// Start and end in Drive Pos
	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index,
		int station_index) throws Exception {

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(manipulation_rack.get_pre_grasp_position())
				.setJointVelocityRel(PTPMoveSpeed));

		GraspRack(manipulation_rack);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P4"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P3"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPRackHoldingMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setJointVelocityRel(
					PTPRackHoldingMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/robot_pre_rack_grasp"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		PlaceRackOnRobot(robot_index);
	}

}
