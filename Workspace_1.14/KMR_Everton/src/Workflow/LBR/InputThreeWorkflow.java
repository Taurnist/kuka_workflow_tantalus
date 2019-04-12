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
 
public class InputThreeWorkflow extends WorkflowSuper {

	RackConfig[] manipulation_rack = new RackConfig[18];

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
			for (int i = 0; i < 18; i++) {
				manipulation_rack[i] = new RackConfig(getApplicationData(),
					getApplicationData().getFrame(
						"/Station/" + Station + "/Cube_Corner/rack_grasp" + i),
					logger, false, true, false);
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
						"/Station/" + Station + "/robot_pre_rack_grasp"))
					.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
				Blending).setJointVelocityRel(PTPMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
				lin(
					getApplicationData().getFrame(
						"/Station/" + Station + "/P1")).setBlendingCart(
					Blending).setCartVelocity(LinMoveRackSpeed));
		emptyRack.getFrame("/spacer/tcp").move(
			lin(manipulation_rack[station_index].get_minimal_pre_grasp_position())
				.setCartVelocity(LinMoveRackSpeed));

		PlaceRackMinimal(manipulation_rack[station_index]);

		Gripper.getFrame("/spacer/tcp").move(
				lin(
					getApplicationData().getFrame(
						"/Station/" + Station + "/P1")).setBlendingCart(
					Blending).setCartVelocity(LinMoveRackSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
				Blending).setCartVelocity(LinMoveRackSpeed));

		ArmToDrivePos();
	}

	// Start and end in Drive Pos
	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index,
		int station_index) throws Exception {
 
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
				Blending).setJointVelocityRel(PTPMoveSpeed)); 

		Gripper.getFrame("/spacer/tcp").move(
				lin(
					getApplicationData().getFrame(
						"/Station/" + Station + "/P1")).setBlendingCart(
					Blending).setCartVelocity(LinMoveRackSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			ptp(manipulation_rack[station_index].get_minimal_pre_grasp_position())
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		GraspRackMinimal(manipulation_rack[station_index]);

		emptyRack.getFrame("/spacer/tcp").move(
				lin(
					getApplicationData().getFrame(
						"/Station/" + Station + "/P1")).setBlendingCart(
					Blending).setCartVelocity(LinMoveRackSpeed));
		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
				Blending).setCartVelocity(LinMoveRackSpeed));
		

		

//		emptyRack.getFrame("/spacer/tcp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/pre_ptp_to_workbench"))
//				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));
		
	
		

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/robot_pre_rack_grasp"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		PlaceRackOnRobot(robot_index);
	}

}
