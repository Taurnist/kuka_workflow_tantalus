package Workflow.LBR; 

import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;

import javax.inject.Inject;
import javax.inject.Named;

import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Workpiece;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;
import Workflow.Configuration.Location;
import Workflow.LBR.Action.RackConfig;
import Workflow.LBR.Messages.LBRCartridgePlaceCartidgeInHotel;
import Workflow.LBR.Messages.LBRCartridgeRemoveCartidgeFromHotel;
import Workflow.LBR.Messages.LBRGraspVialFromBuffer;
import Workflow.LBR.Messages.LBRPlaceVialInBuffer;
import Workflow.LBR.Messages.LBRTakeRackFromRobotAndPlaceOnStation;
import Workflow.LBR.Messages.LBRTakeRackFromStationAndPlaceOnRobot;
import Workflow.LBR.Messages.LBRTask;
import Workflow.LBR.WorkflowSuper.ArmPoseEnum;

public class QuantosWorkflow extends WorkflowSuper {

	RackConfig manipulation_rack;

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
			manipulation_rack = new RackConfig(getApplicationData(),
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/rack_grasp"), logger,
				false, true, false);
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

			case StartVialHandling : {
				try {
					AssertDrivePosition();
					StartVialHandling();
					client.updateStatus(true, null);
					} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}

			case StopVialHandling : {
				try {
					AssertNeutralVialHandlingPosition();
					StopVialHandling();
					client.updateStatus(true, null);
					} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case PlaceVialInBuffer : {
				int index = ((LBRPlaceVialInBuffer) task).getIndex();
				try {
					AssertNeutralVialHandlingPosition();
					PlaceVialInBuffer(index);
					client.updateStatus(true, null);
					} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case GraspVialFromBuffer : {
				int index = ((LBRGraspVialFromBuffer) task).getIndex();
				try {
					AssertNeutralVialHandlingPosition();
					GraspVialFromBuffer(index);
					client.updateStatus(true, null);
					} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case PlaceVialInQuantos : {
				try {
					AssertNeutralVialHandlingPosition();
					PlaceVialInQuantos();
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case GraspVialFromQuantos : {
				try {
					AssertNeutralVialHandlingPosition();
					GraspVialInQuantos();
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
					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setCartVelocity(
				LinMoveRackSpeed));

		PlaceRack(manipulation_rack);

		Gripper.getFrame("/spacer/tcp").move(
			lin( // PTP here bit dangerous
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		ArmToDrivePos();
	}

	// Start and end in Drive Pos
	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index,
		int station_index) throws Exception {
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(manipulation_rack.get_pre_grasp_position()).setBlendingCart(
				Blending).setJointVelocityRel(PTPMoveSpeed));

		GraspRack(manipulation_rack);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/robot_pre_rack_grasp"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		PlaceRackOnRobot(robot_index);
	}

	private void PlaceVialInBuffer(int index) throws Exception {

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setBlendingCart(
				Blending).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setBlendingCart(
				Blending).setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_position(index))
				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeedSlow));

		GripperPrepareForGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
				LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(manipulation_rack.get_pre_grasp_position())
				.setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/neutral")).setJointVelocityRel(
				PTPMoveSpeed));

	}

	private void GraspVialFromBuffer(int index) throws Exception {
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setBlendingCart(20)
				.setCartVelocity(LinMoveRackSpeed));

		GripperPrepareForGraspVial();
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
				LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_position(index)).setCartVelocity(
				LinMoveVialSpeedSlow));

		GripperGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
				LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setCartVelocity(
				LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/neutral")).setJointVelocityRel(
				PTPMoveSpeed));
	}

	private void StartVialHandling() {
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/neutral")).setJointVelocityRel(
				PTPMoveSpeed));
	}
	private void StopVialHandling() {

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/pre_lin_workbench"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		ArmToDrivePos();
	}

	private void PlaceVialInQuantos() {
		ObjectFrame vial_grasp = getApplicationData().getFrame(
			"/Station/Quantos/Cube_Corner/vial_grasp");

		Frame vial_pre_grasp = new Frame();
		vial_pre_grasp.setParent(vial_grasp);
		vial_pre_grasp.setZ(vial_pre_grasp.getZ() - 60);

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_pre_grasp).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_grasp).setCartVelocity(LinMoveVialSpeedSlow));

		GripperPrepareForGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_pre_grasp).setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/neutral")).setCartVelocity(
				LinMoveVialSpeed));

	}
	private void GraspVialInQuantos() throws GripperFailException {
		ObjectFrame vial_grasp = getApplicationData().getFrame(
			"/Station/Quantos/Cube_Corner/vial_grasp");

		Frame vial_pre_grasp = new Frame();
		vial_pre_grasp.setParent(vial_grasp);
		vial_pre_grasp.setZ(vial_pre_grasp.getZ() - 60);

		GripperPrepareForGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_pre_grasp).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_grasp).setCartVelocity(LinMoveVialSpeedSlow));

		GripperGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_pre_grasp).setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/Quantos/Cube_Corner/neutral")).setCartVelocity(
				LinMoveVialSpeed));
	}
}
