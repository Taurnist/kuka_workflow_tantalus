package application;


import javax.inject.Inject;

import Workflow.LBR.GripperApplicationsSuper;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.JointPosition;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
import com.kuka.roboticsAPI.motionModel.PTP;

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
public class POSArm extends GripperApplicationsSuper {
	@Inject
	private LBR lBR_iiwa_14_R820_1;

	@Inject
	private KmpOmniMove kMR_omniMove_200_1;
	@Override
	public void initialize() {
		// initialize your application here 
	}
	@Override
	public void run() {
		GripperHomeGripper();
		// your application execution starts here
		lBR_iiwa_14_R820_1.move(ptpHome().setJointVelocityRel(0.3));
		float x = -20f;
		
		 PTP mainMotion = new PTP(new JointPosition(Math.toRadians(9.01),
       Math.toRadians(x),
       Math.toRadians(x),
       Math.toRadians(x),
       Math.toRadians(x),
       Math.toRadians(x),
       Math.toRadians(x))).setJointVelocityRel(0.3);
		 lBR_iiwa_14_R820_1.move(mainMotion);
		 x = -x;
		 
		 mainMotion = new PTP(new JointPosition(Math.toRadians(9.01),
       Math.toRadians(x),
       Math.toRadians(x),
       Math.toRadians(x),
       Math.toRadians(x),
       Math.toRadians(x),
       Math.toRadians(x))).setJointVelocityRel(0.3);
		 lBR_iiwa_14_R820_1.move(mainMotion);
		 lBR_iiwa_14_R820_1.getFlange().move(ptp(getApplicationData().getFrame("/DrivePos")).setJointVelocityRel(0.5));
			
	}
}