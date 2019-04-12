package application;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Workflow.LBR.GripperApplicationsSuper;
import Workflow.LBR.Action.CubeFinderAngled;
import application.util.*;

import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import javax.inject.Inject;
import javax.inject.Named;

import tool.GripperFesto;

import com.kuka.roboticsAPI.applicationModel.IApplicationData;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.persistenceModel.PersistenceException;
import com.kuka.task.ITaskLogger;
import com.kukasystems.modules.measurement.baseframe.util.BaseFrameUtil;

public class SixPStation extends GripperApplicationsSuper {


	@Override
	public void run() throws Exception{
		gripper.driveToPosition(1);
		getApplicationData().getProcessData("gripPos").setValue(1);
		String station = getApplicationData().getProcessData("Station").getValue();

		
//		String Cube_estimation = "/Station/" + station + "/Cube_Pos/variance";
//		Frame target = getApplicationData().getFrame(Cube_estimation).copy();
//		Random r = new Random();
//		target.setX( -20d +40d * r.nextDouble());
//		target.setY(-20d+ 40d * r.nextDouble());
//		target.setAlphaRad(Math.toRadians( -5d+ 10d * r.nextDouble()));
//		
		String Cube_pre_estimation = "/Station/" + station + "/Pre_Cube_Pos";
		String Cube_estimation = "/Station/" + station + "/Cube_Pos";
		String Cube_origin = "/Station/" + station + "/Cube_Corner";
		
		CubeFinderAngled calibration = new CubeFinderAngled(
				getApplicationData(), lbr_iiwa, Gripper, logger);
		
		try{
			Frame pre_target = getApplicationData().getFrame(Cube_pre_estimation).copyWithRedundancy();
			Gripper.getFrame("/spacer/tcp").move(ptp(pre_target).setJointVelocityRel(0.5));
		} catch (PersistenceException e)
		{
			//If the point is not there, then we don't care.
		}
		
		
		Frame target = getApplicationData().getFrame(Cube_estimation).copyWithRedundancy();

		Frame cube = calibration.FindCube(target);

		
		logger.error(cube.toString());
	 	
		try{
			Frame pre_target = getApplicationData().getFrame(Cube_pre_estimation).copyWithRedundancy();
			Gripper.getFrame("/spacer/tcp").move(ptp(pre_target).setJointVelocityRel(0.5));
		} catch (PersistenceException  e)
		{
			//If the point is not there, then we don't care.
		}
		
		ArmToDrivePos();

		ObjectFrameUtilsVeins11.changeObjectFrame(getContext(),
				getApplicationData().getFrame(Cube_origin), cube);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
