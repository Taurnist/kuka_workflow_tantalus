package Workflow.LBR;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;
import Workflow.Configuration.Location;
import Workflow.LBR.Action.FancyFrame;
import Workflow.LBR.Action.RackConfig;
import Workflow.LBR.Action.VibratoryPhotolysisRack;
import Workflow.LBR.Messages.*;
import Workflow.LBR.WorkflowSuper.ArmPoseEnum;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianSineImpedanceControlMode;

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
public class VibratoryPhotolysisWorkflow extends WorkflowSuper {
	RackConfig manipulation_rack;
	VibratoryPhotolysisRack photolysis_rack;
	private CartesianImpedanceControlMode stiffMode;
	private CartesianImpedanceControlMode vibrateMode;
	@Override
	public void initialize() {
		super.initialize();

		LinMoveRackSpeed = 300;
		LinMoveVialSpeed = 200;
		LinMoveVialSpeedSlow = 35;
		PTPMoveSpeed = 0.5;
		Blending = 0;
		vibrateMode = 
				
		CartesianSineImpedanceControlMode.createSinePattern(CartDOF.ROT, 15.0,
		0.5, 150.0); 
		
		
		vibrateMode.parametrize(CartDOF.X).setStiffness(2000);
		vibrateMode.parametrize(CartDOF.Y).setStiffness(2000);
		vibrateMode.parametrize(CartDOF.Z).setStiffness(3000);
		vibrateMode.parametrize(CartDOF.B).setStiffness(200);
		vibrateMode.parametrize(CartDOF.C).setStiffness(200);
		
		//
		// Station = Location.Vibratory_Photocat;

		stiffMode = new CartesianImpedanceControlMode();
		stiffMode.parametrize(CartDOF.X).setStiffness(5000);
		stiffMode.parametrize(CartDOF.Y).setStiffness(5000);
		stiffMode.parametrize(CartDOF.Z).setStiffness(5000);
		stiffMode.parametrize(CartDOF.A).setStiffness(300);
		stiffMode.parametrize(CartDOF.B).setStiffness(300);
		stiffMode.parametrize(CartDOF.C).setStiffness(300);

		SetObjectsBasedOn6PHook();

	}

	@Override
	protected void SetObjectsBasedOn6PHook() {
		try {
			manipulation_rack = new RackConfig(getApplicationData(),
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/rack_grasp"), logger, false, true, false);

			photolysis_rack = new VibratoryPhotolysisRack(getApplicationData()
				.getFrame(
					"/Station/" + Station.getPointSetName() + "/Cube_Corner/P1"),
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName() + "/Cube_Corner/P4"),
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName() + "/Cube_Corner/P16"));
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
					PlaceVialInRack(index);
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
					GraspVialFromRack(index);
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case PlaceSampleInPhotolysisStation : {
				int index = ((LBRPlaceSampleInPhotolysisStation) task).getIndex();
				try {
					AssertNeutralVialHandlingPosition();
					ShakeVial();
					PlaceInPhotolysisBuffer(index);
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case GraspSampleFromPhotolysisStation : {
				int index = ((LBRGraspSampleFromPhotolysisStation) task)
					.getIndex();
				try {
					AssertNeutralVialHandlingPosition();
					GraspFromPhotolysisBuffer(index);
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case MoveToDryPosition : {
				try {
					AssertNeutralVialHandlingPosition();
					MoveToDryPosition();
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case DryProcedure : {
				try {
					AssertDryPosition();
					DryProcedure();
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				break;
			}
			case MoveToNeutralAfterDry : {
				try {
					AssertDryPosition();
					MoveToNeutralAfterDry();
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
				client.updateStatus(false, "Not implemented task requested");

		}

	}

	private void ShakeVial() {
		CartesianSineImpedanceControlMode shake_vial = 
				
				CartesianSineImpedanceControlMode.createSinePattern(CartDOF.ALL, 15.0,
				3, 150.0); 
				
				
		shake_vial.parametrize(CartDOF.X).setStiffness(1000);
		shake_vial.parametrize(CartDOF.Y).setStiffness(1000);
		shake_vial.parametrize(CartDOF.Z).setStiffness(5000);
		shake_vial.parametrize(CartDOF.B).setStiffness(300);
		shake_vial.parametrize(CartDOF.C).setStiffness(300);

				
		Gripper.getFrame("/spacer/tcp").move(positionHold(shake_vial, 3, TimeUnit.SECONDS));
	}
	protected void AssertDryPosition() throws RobotAssertionError {
		double distance = lbr_iiwa.getCurrentCartesianPosition(
			Gripper.getDefaultMotionFrame(), World.Current.getRootFrame())
			.distanceTo(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/dry_side"));
		if (distance > MinDistanceToStart)
			throw new RobotAssertionError("Robot not in neutral, distance: "
				+ distance + " with motion frame"
				+ Gripper.getDefaultMotionFrame().getName());

	}

	private void MoveToNeutralAfterDry() {

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/pre_dry")).setCartVelocity(LinMoveVialSpeed));
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/neutral")).setCartVelocity(LinMoveVialSpeed));

	}

	private void DryProcedure() {

		Gripper.getFrame("/spacer/tcp")
			.move(
				lin(
					getApplicationData().getFrame(
						"/Station/" + Station.getPointSetName()
							+ "/Cube_Corner/dry_side")).setCartVelocity(
					LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/dry_bottom")).setCartVelocity(4));
		Gripper.getFrame("/spacer/tcp").move(
			positionHold(stiffMode, 15, TimeUnit.SECONDS));
	}

	private void MoveToDryPosition() {
		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/pre_dry")).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp")
			.move(
				lin(
					getApplicationData().getFrame(
						"/Station/" + Station.getPointSetName()
							+ "/Cube_Corner/dry_side")).setCartVelocity(
					LinMoveVialSpeed));

	}

	private void GraspFromPhotolysisBuffer(int index)
		throws GripperFailException {

		GripperPrepareForGraspVial();
		Gripper.getFrame("/spacer/tcp").move(
			lin(photolysis_rack.GetVialPreGrasp(index)).setCartVelocity(
				LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(photolysis_rack.GetVialGrasp(index)).setMode(vibrateMode).setCartVelocity(
				LinMoveVialSpeedSlow));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(photolysis_rack.GetVialGrasp(index)).setCartVelocity(
					LinMoveVialSpeedSlow));

		GripperGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(photolysis_rack.GetVialPreGrasp(index)).setMode(vibrateMode).setCartVelocity(
				LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/neutral")).setCartVelocity(LinMoveVialSpeed));
	}

	private void PlaceInPhotolysisBuffer(int index) {

		Gripper.getFrame("/spacer/tcp").move(
			lin(photolysis_rack.GetVialPreGrasp(index)).setCartVelocity(
				LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(photolysis_rack.GetVialGrasp(index)).setMode(vibrateMode).setCartVelocity(
				LinMoveVialSpeedSlow));

		GripperPrepareForGraspVial();

		GripperPrepareForGraspVial();
		Gripper.getFrame("/spacer/tcp").move(
			lin(photolysis_rack.GetVialPreGrasp(index)).setCartVelocity(
				LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/neutral")).setCartVelocity(LinMoveVialSpeed));

	}

	private void GraspVialFromRack(int index) throws Exception {
		GripperPrepareForGraspVial();
		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
				LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_position(index)).setCartVelocity(
				LinMoveVialSpeedSlow).setMode(placeVialMode));
		Gripper.getFrame("/spacer/tcp").move(
				lin(manipulation_rack.get_position(index)).setCartVelocity(
					LinMoveVialSpeedSlow));


		GripperGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
				LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/neutral")).setCartVelocity(LinMoveVialSpeed));

	}

	private void PlaceVialInRack(int index) throws Exception {

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setBlendingCart(
				Blending).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_position(index))
				.setBlendingCart(Blending).setCartVelocity(LinMoveVialSpeedSlow));

		GripperPrepareForGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_position(index)).setCartVelocity(
				LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/neutral")).setJointVelocityRel(PTPMoveSpeed));

	}

	private void StopVialHandling() {

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/pre_lin_rack")).setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));

		ArmToDrivePos();

	}

	private void StartVialHandling() {

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/pre_lin_rack")).setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/neutral")).setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));

	}

	// Start and end in Drive Pos
	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index,
		int station_index) throws Exception {
		GraspRackFromRobot(robot_index);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/pre_lin_rack")).setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setCartVelocity(
				0.5 * LinMoveRackSpeed));

		PlaceRack(manipulation_rack);

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/pre_lin_rack")).setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));

		ArmToDrivePos();
	}

	// Start and end in Drive Pos
	private void GraspRackFromBufferAndPlaceOnRobot(int robot_index,
		int station_index) throws Exception {

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/pre_lin_rack")).setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(manipulation_rack.get_pre_grasp_position())
			// looks weird.setJointVelocityRel(RackHoldingMoveSpeed)
				.setJointVelocityRel(0.5 * PTPMoveSpeed));

		GraspRack(manipulation_rack);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/Cube_Corner/pre_lin_rack")).setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station.getPointSetName()
						+ "/robot_pre_rack_grasp")).setBlendingCart(Blending)
				.setCartVelocity(LinMoveRackSpeed));

		PlaceRackOnRobot(robot_index);
	}

}