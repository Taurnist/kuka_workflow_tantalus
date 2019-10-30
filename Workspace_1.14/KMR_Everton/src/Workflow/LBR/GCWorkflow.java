package Workflow.LBR;
 
import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;

import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.geometricModel.World;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;
import Workflow.Configuration.Location;
import Workflow.LBR.Action.RackConfig;
import Workflow.LBR.Messages.LBRCartridgePlaceCartidgeInHotel;
import Workflow.LBR.Messages.LBRTakeRackFromRobotAndPlaceOnStation;
import Workflow.LBR.Messages.LBRTakeRackFromStationAndPlaceOnRobot;
import Workflow.LBR.Messages.LBRTask;
import Workflow.LBR.WorkflowSuper.ArmPoseEnum;

public class GCWorkflow extends WorkflowSuper {

	RackConfig manipulation_rack_0, manipulation_rack_1,
		manipulation_rack_2;

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
			manipulation_rack_0 = new RackConfig(getApplicationData(),
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/rack_grasp0"), logger);

			manipulation_rack_1 = new RackConfig(getApplicationData(),
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/rack_grasp1"), logger);

			manipulation_rack_2 = new RackConfig(getApplicationData(),
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/rack_grasp2"), logger);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void OtherTasksHook(LBRTask task) {
		switch (task.getType()) {
			case TakeRackFromRobotAndPlaceOnStation : {
				int station_index = ((LBRTakeRackFromRobotAndPlaceOnStation) task)
					.getStationIndex();
				int robot_index = ((LBRTakeRackFromRobotAndPlaceOnStation) task)
					.getRobotIndex();

				if (!CorrectStationIndex(station_index)) {
					client.updateStatus(false,
						"only station indices between 0 and 2 allowed.");
				}

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

				if (!CorrectStationIndex(station_index)) {
					client.updateStatus(false,
						"only station indices between 0 and 2 allowed.");
				}
				try {
					AssertDrivePosition();
					GraspRackFromBufferAndPlaceOnRobot(robot_index, station_index);
					client.updateStatus(true, null);
					} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case PressHeadSpaceParkButton : {
				try {
					AssertDrivePosition();
					PressParkHeadspaceButton();
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

	private boolean CorrectStationIndex(int station_index) {
		return 0 <= station_index && station_index <= 2;
	}

	private void PressParkHeadspaceButton() {
		GripperPrepareForSixPointCalibration();
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station
						+ "/Cube_Corner/park_button_PTP_pre_press"))
				.setJointVelocityRel(PTPMoveSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/park_button_pre_press"))
				.setJointVelocityRel(PTPMoveSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/park_button_press"))
				.setCartVelocity(10));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/park_button_pre_press"))
				.setJointVelocityRel(PTPMoveSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station
						+ "/Cube_Corner/park_button_PTP_pre_press"))
				.setJointVelocityRel(PTPMoveSpeed));
		ArmToDrivePos();

	}

	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index,
		int station_index) throws Exception {
		RackConfig manipulation_rack = GetGCRack(station_index);

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_minimal_pre_grasp_position())
				.setJointVelocityRel(PTPMoveSpeed));

		GraspRackMinimal(manipulation_rack);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/robot_pre_rack_grasp"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		PlaceRackOnRobot(robot_index);
	}

	private RackConfig GetGCRack(int gc_rack_index) {
		RackConfig result;
		switch (gc_rack_index) {
			case 0 :
				result = manipulation_rack_0;
				break;
			case 1 :
				result = manipulation_rack_1;
				break;
			case 2 :
				result = manipulation_rack_2;
				break;
			default :
				throw new NotImplementedException();
		}
		return result;
	}

	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index,
		int station_index) throws Exception {
		RackConfig manipulation_rack = GetGCRack(station_index);

		GraspRackFromRobot(robot_index);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_minimal_pre_grasp_position())
				.setCartVelocity(LinMoveRackSpeed));

		PlaceRackMinimal(manipulation_rack);

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P2"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/LinearMoveToBench_P1"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		ArmToDrivePos();

	}

}
