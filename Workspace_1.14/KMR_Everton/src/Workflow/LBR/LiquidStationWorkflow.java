package Workflow.LBR;
 
import javax.inject.Inject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import Exception.CriticalActionFailException;
import Exception.GripperFailException;
import Exception.UnexpectedCollisionDetected;
import Workflow.Configuration.Location;
import Workflow.LBR.Action.CubeFinderAngled;
import Workflow.LBR.Action.RackConfig;
import Workflow.LBR.Messages.LBRGraspVialFromBuffer;
import Workflow.LBR.Messages.LBRGraspVialFromLiquidStation;
import Workflow.LBR.Messages.LBRPlaceVialInBuffer;
import Workflow.LBR.Messages.LBRPlaceVialInLiquidStation;
import Workflow.LBR.Messages.LBRTakeRackFromRobotAndPlaceOnStation;
import Workflow.LBR.Messages.LBRTakeRackFromStationAndPlaceOnRobot;
import Workflow.LBR.Messages.LBRTask;
import Workflow.LBR.WorkflowSuper.ArmPoseEnum;

import application.util.ObjectFrameUtilsVeins11;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianSineImpedanceControlMode;
import com.kuka.roboticsAPI.persistenceModel.PersistenceException;

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
public class LiquidStationWorkflow extends WorkflowSuper {

	RackConfig manipulation_rack;

	private CartesianImpedanceControlMode pullOutMode;
	
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

				pullOutMode = 
						
				CartesianSineImpedanceControlMode.createSinePattern(CartDOF.ROT, 15.0,
				0.3, 150.0); 
				
				
				pullOutMode.parametrize(CartDOF.X).setStiffness(2000);
				pullOutMode.parametrize(CartDOF.Y).setStiffness(2000);
				pullOutMode.parametrize(CartDOF.Z).setStiffness(5000);
				pullOutMode.parametrize(CartDOF.B).setStiffness(300);
				pullOutMode.parametrize(CartDOF.C).setStiffness(300);
				
				Station = Station.LiquidHandlingSystem;
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

			case GraspVialInLiquidStation : {
				try {
					AssertNeutralVialHandlingPosition();
					int index = ((LBRGraspVialFromLiquidStation) task).getIndex();
					GraspVialInLiquidStation(index);
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
			
				break;
			}
			case PlaceVialInLiquidStation : {
				try {
					AssertNeutralVialHandlingPosition();
					int index = ((LBRPlaceVialInLiquidStation) task).getIndex();
					PlaceVialInLiquidStation(index);
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				
				break;
			}
			case MoveVialFromLiquidStationOneToTwo : {
				try {
					AssertNeutralVialHandlingPosition();
					MoveVialFromStationOnetoStationTwo();
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
	
				break;
			}
			case PlaceVialInCapper : {
				try {
					AssertNeutralVialHandlingPosition();
					PlaceVialInCapper();
					client.updateStatus(true, null);
				} catch (Exception e) {
					client.updateStatus(false, e.getMessage());
				}
				
				break;
			}
			case GraspVialFromCapper : {
				try {
					AssertNeutralVialHandlingPosition();
					GraspVialFromCapper();
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
	private void GraspVialFromCapper() throws GripperFailException {
		GripperPrepareForGraspVial();
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P6"))
				.setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P7"))
				.setCartVelocity(LinMoveVialSpeed));
		

		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P8"))
				.setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P9"))
				.setCartVelocity(LinMoveVialSpeedSlow));
		
		GripperGraspVial();
		
			
		Gripper.getFrame("/spacer/tcp").move(
				linRel(0, 0, -30, 0, 0, 0, Gripper.getFrame("/spacer/tcp"))
				.setCartVelocity(6).setMode(pullOutMode));
		
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P8"))
				.setCartVelocity(LinMoveVialSpeedSlow));
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P7"))
				.setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P6"))
				.setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/" + Station +"/Cube_Corner/neutral"))
				.setCartVelocity(LinMoveVialSpeed));
		
	}
	private void PlaceVialInCapper() {
	
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P6"))
				.setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P7"))
				.setCartVelocity(LinMoveVialSpeed));
		

		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P8"))
				.setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P10"))
				.setCartVelocity(50));
			
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P9"))
				.setCartVelocity(50));
		
		GripperPrepareForGraspVial();
			
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Capper_Cube_Corner/P8"))
				.setCartVelocity(LinMoveVialSpeedSlow));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P7"))
				.setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/LiquidHandlingSystem/Cube_Corner/P6"))
				.setCartVelocity(LinMoveVialSpeed));
		
		Gripper.getFrame("/spacer/tcp").move(
				lin(getApplicationData().getFrame("/Station/" + Station +"/Cube_Corner/neutral"))
				.setCartVelocity(LinMoveVialSpeed));
		
	}

	private void MoveVialFromStationOnetoStationTwo()
		throws GripperFailException {

		// Flip gripper
		GripperPrepareForGraspVial();

		Gripper
			.getFrame("/spacer/tcp/flipped_liquid")
			.move(
				ptp(
					getApplicationData()
						.getFrame(
							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
					.setJointVelocityRel(PTPMoveSpeed));

		GraspVialInStationFlipped(0);
		PlaceVialInStationFlipped(1);

		Gripper
			.getFrame("/spacer/tcp/flipped_liquid")
			.move(
				ptp(
					getApplicationData()
						.getFrame(
							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
					.setJointVelocityRel(PTPMoveSpeed));

		// Flip Gripper Back

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setJointVelocityRel(PTPMoveSpeed));

	}
	private void PlaceVialInLiquidStation(int i)
		throws GripperFailException {
		FlipVialGraspToFlipped();

		PlaceVialInStationFlipped(i);

		Gripper
			.getFrame("/spacer/tcp/flipped_liquid")
			.move(
				lin(
					getApplicationData()
						.getFrame(
							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
					.setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setJointVelocityRel(PTPMoveSpeed));

	}
	private void PlaceVialInStationFlipped(int i) {
		ObjectFrame pre_station = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/pre_station" + i);
		ObjectFrame vial_pre_grasp_station = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/vial_pre_grasp_station" + i);
		ObjectFrame vial_grasp_station = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/vial_grasp_station" + i);

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(pre_station).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_pre_grasp_station).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));

		GripperPrepareForGraspVial();

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_pre_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(pre_station).setCartVelocity(LinMoveVialSpeed));
	}
	private void GraspVialInLiquidStation(int i)
		throws GripperFailException {

		GripperPrepareForGraspVial();

		Gripper
			.getFrame("/spacer/tcp/flipped_liquid")
			.move(
				ptp(
					getApplicationData()
						.getFrame(
							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
					.setJointVelocityRel(PTPMoveSpeed));

		GraspVialInStationFlipped(i);

		Gripper
			.getFrame("/spacer/tcp/flipped_liquid")
			.move(
				lin(
					getApplicationData()
						.getFrame(
							"/Station/LiquidHandlingSystem/Cube_Corner/grasp_switch_mid_point"))
					.setCartVelocity(LinMoveVialSpeed));

		// TODO Auto-generated method stub
		FlipVialGraspToNormal();
	}
	private void GraspVialInStationFlipped(int i)
		throws GripperFailException {
		ObjectFrame pre_station = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/pre_station" + i);
		ObjectFrame vial_pre_grasp_station = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/vial_pre_grasp_station" + i);
		ObjectFrame vial_grasp_station = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/vial_grasp_station" + i);

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(pre_station).setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_pre_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));

		GripperGraspVial();

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_pre_grasp_station).setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(pre_station).setCartVelocity(LinMoveVialSpeed));
	}
	// Start and end in Drive Pos
	private void GraspRackFromRobotAndPlaceInBuffer(int robot_index,
		int station_index) throws Exception {

		GraspRackFromRobot(robot_index);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/pre_ptp_to_workbench"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
				Blending).setJointVelocityRel(PTPMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(manipulation_rack.get_pre_grasp_position()).setCartVelocity(
				LinMoveRackSpeed));

		PlaceRack(manipulation_rack);

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
			ptp(manipulation_rack.get_pre_grasp_position()).setBlendingCart(
				Blending).setJointVelocityRel(PTPMoveSpeed));

		GraspRack(manipulation_rack);

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/ptp_to_workbench")).setBlendingCart(
				Blending).setCartVelocity(LinMoveRackSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/pre_ptp_to_workbench"))
				.setBlendingCart(Blending).setJointVelocityRel(PTPMoveSpeed));

		emptyRack.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/robot_pre_rack_grasp"))
				.setBlendingCart(Blending).setCartVelocity(LinMoveRackSpeed));

		PlaceRackOnRobot(robot_index);
	}

	private void StartVialHandling() {
		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setJointVelocityRel(PTPMoveSpeed));
	}
	private void StopVialHandling() {

		ArmToDrivePos();
	}

	private void PlaceVialInBuffer(int index) throws Exception {

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
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setJointVelocityRel(PTPMoveSpeed));

	}

	private void GraspVialFromBuffer(int index) throws Exception {

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
			lin(
				getApplicationData().getFrame(
					"/Station/" + Station + "/Cube_Corner/neutral"))
				.setCartVelocity(LinMoveVialSpeed));

	}

	private void FlipVialGraspToNormal() throws GripperFailException {
		ObjectFrame vial_grasp_normal = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/vial_fixture_grasp_normal");
		ObjectFrame vial_pre_grasp_normal = getApplicationData().getFrame(
			"/Station/" + Station
				+ "/Cube_Corner/pre_vial_fixture_grasp_normal");
		ObjectFrame flipped_mid_point = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/grasp_switch_mid_point");
		ObjectFrame vial_pre_grasp_flipped = getApplicationData().getFrame(
			"/Station/" + Station
				+ "/Cube_Corner/pre_vial_fixture_grasp_flipped");
		ObjectFrame vial_grasp_flipped = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/vial_fixture_grasp_flipped");

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_pre_grasp_flipped).setBlendingCart(Blending)
				.setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_grasp_flipped).setBlendingCart(Blending).setCartVelocity(
				LinMoveVialSpeedSlow));

		GripperPrepareForGraspVial();

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_pre_grasp_flipped).setBlendingCart(Blending)
				.setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			ptp(
				getApplicationData().getFrame(
					"/Station/LiquidHandlingSystem/Cube_Corner/neutral"))
				.setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed * 0.5));

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_pre_grasp_normal).setBlendingCart(Blending)
				.setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_grasp_normal).setCartVelocity(LinMoveVialSpeedSlow));

		GripperGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_pre_grasp_normal).setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp").move(
			lin(
				getApplicationData().getFrame(
					"/Station/LiquidHandlingSystem/Cube_Corner/neutral"))
				.setCartVelocity(LinMoveVialSpeedSlow));

	}
	private void FlipVialGraspToFlipped() throws GripperFailException {
		ObjectFrame vial_grasp_normal = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/vial_fixture_grasp_normal");
		ObjectFrame vial_pre_grasp_normal = getApplicationData().getFrame(
			"/Station/" + Station
				+ "/Cube_Corner/pre_vial_fixture_grasp_normal");
		ObjectFrame flipped_mid_point = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/grasp_switch_mid_point");
		ObjectFrame vial_pre_grasp_flipped = getApplicationData().getFrame(
			"/Station/" + Station
				+ "/Cube_Corner/pre_vial_fixture_grasp_flipped");
		ObjectFrame vial_grasp_flipped = getApplicationData().getFrame(
			"/Station/" + Station + "/Cube_Corner/vial_fixture_grasp_flipped");

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_pre_grasp_normal).setBlendingCart(Blending)
				.setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_grasp_normal).setCartVelocity(LinMoveVialSpeedSlow));

		GripperPrepareForGraspVial();

		Gripper.getFrame("/spacer/tcp").move(
			lin(vial_pre_grasp_normal).setBlendingCart(Blending)
				.setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			ptp(flipped_mid_point).setBlendingCart(Blending)
				.setJointVelocityRel(PTPMoveSpeed * 0.5));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_pre_grasp_flipped).setBlendingCart(Blending)
				.setCartVelocity(LinMoveVialSpeed));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_grasp_flipped).setBlendingCart(Blending).setCartVelocity(
				LinMoveVialSpeedSlow));

		GripperGraspVial();

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(vial_pre_grasp_flipped).setBlendingCart(Blending)
				.setCartVelocity(LinMoveVialSpeedSlow));

		Gripper.getFrame("/spacer/tcp/flipped_liquid").move(
			lin(flipped_mid_point).setBlendingCart(Blending).setCartVelocity(
				LinMoveVialSpeed));
	}
	
	@Override
	protected void SixPointCalibration() throws RobotAssertionError,
	CriticalActionFailException {
		AssertDrivePosition();
		gripper.driveToPosition(1);
		getApplicationData().getProcessData("gripPos")
				.setValue(1);
		
//		String Cube_pre_estimation = "/Station/" + Station.getPointSetName() + "/Pre_Cube_Pos";
//		
//		String Cube_estimation = "/Station/"
//				+ Station.getPointSetName()
//				+ "/new_Cube_Pos";
//		String Cube_origin = "/Station/"
//				+ Station.getPointSetName()
//				+ "/new_Cube_Corner";
//		
		String Cube_pre_estimation = "/Station/" + Station.getPointSetName() + "/Pre_Cube_Pos";
		
		String Cube_estimation = "/Station/"
				+ Station.getPointSetName()
				+ "/Cube_Pos"; 
		String Cube_origin = "/Station/"
				+ Station.getPointSetName()
				+ "/Cube_Corner";
		
		PerformSixPoint(Cube_pre_estimation, Cube_estimation, Cube_origin);
		
		Cube_pre_estimation = "/Station/" + Station.getPointSetName() + "/Pre_Capper_Cube_Pos";
		
		 Cube_estimation = "/Station/"
				+ Station.getPointSetName()
				+ "/Capper_Cube_Pos";
		 Cube_origin = "/Station/"
				+ Station.getPointSetName()
				+ "/Capper_Cube_Corner";
		
		PerformSixPoint(Cube_pre_estimation, Cube_estimation, Cube_origin);
		
		
		SetObjectsBasedOn6PHook();
		}

}