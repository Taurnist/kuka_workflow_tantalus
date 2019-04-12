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
//import Workflow.LBR.Action.CartridgeHotel;
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
//public class CartridgeTestingStuff extends GripperApplicationsSuper {
//	CartridgeHotel[] hotelpoints = new CartridgeHotel[2];
//	Location Station = Location.Cartridge;
//
//	@Override
//public void initialize() {
//	super.initialize();
//	
//	try {
//		hotelpoints[0] = new CartridgeHotel("hotel_0_cartridge1_grasp",getApplicationData());
//		hotelpoints[1] = new CartridgeHotel("hotel_1_cartridge1_grasp",getApplicationData());
//		
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
//	
//	
//	@Override
//	public void run() throws Exception {
//
// 		
//		MoveIntoCartridgeHandlingPos();
//		GraspCartridgeInHotel(0);
//		PlaceCartridgeInQuantos();
////		for(int i=1;i<20;i++) {
////			PlaceCartridgeInHotel(i);
////			GraspCartridgeInHotel(i);
////		} 
////		PlaceCartridgeInHotel(0);
////		
////		
////		MoveOutOfCartridgeHandlingPos();
//	}	
//
//	public void PlaceCartridgeInQuantos() throws GripperFailException,
//		UnexpectedCollisionDetected {
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_pre_grasp"))
//				.setJointVelocityRel(0.3).setBlendingCart(10));
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_grasp/step2"))
//				.setJointVelocityRel(0.3));
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_grasp"))
//				.setJointVelocityRel(0.3));
//
//		GripperPrepareForGraspCartridge();
//
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_pre_grasp"))
//				.setJointVelocityRel(0.3).setBlendingCart(20));
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral")).setJointVelocityRel(
//				0.3));
//
//	}
//
//	public void GraspCartridgeInQuantos() throws GripperFailException,
//		UnexpectedCollisionDetected {
//		GripperPrepareForGraspCartridge();
//
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_pre_grasp"))
//				.setJointVelocityRel(0.3).setBlendingCart(10));
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_grasp/step2"))
//				.setJointVelocityRel(0.3));
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_grasp"))
//				.setJointVelocityRel(0.3));
//
//		GripperGraspCartridge();
//
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			lin(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_grasp/step2"))
//				.setJointVelocityRel(0.3).setBlendingCart(1));
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/quantos_pre_grasp"))
//				.setJointVelocityRel(0.3).setBlendingCart(20));
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral")).setJointVelocityRel(
//				0.3));
//
//	}
//
//	public void GraspCartridgeInHotel(int index)
//		throws GripperFailException, UnexpectedCollisionDetected {
//		GripperPrepareForGraspCartridge();
//		MoveToGraspCartridge(index, false);
//		GripperGraspCartridge();
//		MoveBackFromCartridge(index, true);
//	}
//	public void PlaceCartridgeInHotel(int index)
//		throws GripperFailException, UnexpectedCollisionDetected {
//		MoveToGraspCartridge(index, true);
//		GripperPrepareForGraspCartridge();
//		MoveBackFromCartridge(index, false);
//	}
//
//	public void MoveIntoCartridgeHandlingPos() {
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
//				.setJointVelocityRel(0.3).setBlendingCart(20));
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral")).setJointVelocityRel(
//				0.3));
//	}
//
//	public void MoveOutOfCartridgeHandlingPos() {
//
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").moveAsync(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/pre_lin_workbench"))
//				.setJointVelocityRel(0.3).setBlendingCart(50));
//		lbr_iiwa.getFlange().move(
//			ptp(getApplicationData().getFrame("/DrivePos"))
//				.setJointVelocityRel(0.3));
//	}
//
//	private void MoveToGraspCartridge(int index, boolean placing_cartridge)
//		throws GripperFailException, UnexpectedCollisionDetected {
//
//	//NEW
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(GetPreLinHotelPoint(index)).setJointVelocityRel(0.3)
//				.setBlendingCart(10));
//		
//		logger.info("cartridge1_step0 ");
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(GetStepPoint(index, 0)).setJointVelocityRel(0.3)
//				.setBlendingCart(2));
//
//		if (placing_cartridge) {
//			// Check for collision cartridge
//			ForceCondition foreConditionCheckCartridge = ForceCondition
//				.createSpatialForceCondition(Gripper.getDefaultMotionFrame(),
//					World.Current.getRootFrame(), 15);
//
//			logger.info("cartridge1_step2 ");
//			IMotionContainer motion_container = Gripper.getFrame(
//				"/spacer/tcp/cartridge_grasp").move(
//				lin(GetStepPoint(index, 2)).setCartVelocity(20)
//					.setBlendingCart(2).breakWhen(foreConditionCheckCartridge));
//
//			if (motion_container.hasFired(foreConditionCheckCartridge)) {
//				logger.error("cartridge collision");// throw new
//																						// UnexpectedCollisionDetected("unexpected cartridge");
//			}
//
//			logger.info("cartridge1_step3 ");
//			Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//				lin(GetStepPoint(index, 3)).setCartVelocity(10));
//		} else {
//			logger.info("cartridge1_step3 ");
//			Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//				lin(GetStepPoint(index, 3)).setCartVelocity(30));
//		}
//
//	}
//
//	private Frame GetStepPoint(int cartridgeIndex, int step) {
//		CartridgeHotel hotel = SelectHotelFromIndex(cartridgeIndex);
//		return TransformpointToIndex(hotel.GetStep(step), cartridgeIndex);
//	}
//
//	private Frame GetPreLinHotelPoint(int cartridgeIndex) {
//		CartridgeHotel hotel = SelectHotelFromIndex(cartridgeIndex);
//		return hotel.GetPreLinHotel();
//	}
//
//	private CartridgeHotel SelectHotelFromIndex(int cartridgeIndex) {
//		if (cartridgeIndex >= 0 && cartridgeIndex < 10) {
//			return hotelpoints[0];
//		} else if (cartridgeIndex >= 10 && cartridgeIndex < 20) {
//			return hotelpoints[1];
//		}
//		throw new NotImplementedException();
//	}
//
//	private Frame TransformpointToIndex(Frame frame, int cartridgeIndex) {
//		Frame p = frame.copyWithRedundancy();
//		int index = ((cartridgeIndex) % 10);
//
//		p.setX(p.getX() - (65 * (index % 5))); // decide which column cartridge is
//																						// found in, rows are 65mm apart
//		p.setZ(p.getZ() + (120 * Math.floor(index / 5))); // decide which row, rows
//																											// are 120 mm apart
//
//		return p;
//	}
//
//	private void MoveBackFromCartridge(int index, boolean holding_cartridge) {
//		if (holding_cartridge) {
//			logger.info("cartridge1_step3 ");
//			Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//				lin(GetStepPoint(index, 3)).setCartVelocity(10)
//					.setBlendingCart(0));
//			logger.info("cartridge1_step2 ");
//			Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//				lin(GetStepPoint(index, 2)).setCartVelocity(20)
//					.setBlendingCart(5));
//			logger.info("cartridge1_step0 ");
//			Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//				ptp(GetStepPoint(index, 0)).setJointVelocityRel(0.3)
//					.setBlendingCart(20));
//		} else {
//			logger.info("cartridge1_step0 ");
//			Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//				ptp(GetStepPoint(index, 0)).setJointVelocityRel(0.3));
//		}
//		//NEW
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(GetPreLinHotelPoint(index)).setJointVelocityRel(0.3)
//				.setBlendingCart(10));
//		
//		Gripper.getFrame("/spacer/tcp/cartridge_grasp").move(
//			ptp(
//				getApplicationData().getFrame(
//					"/Station/" + Station + "/Cube_Corner/neutral")).setJointVelocityRel(
//				0.3));
//	}
//
//}
