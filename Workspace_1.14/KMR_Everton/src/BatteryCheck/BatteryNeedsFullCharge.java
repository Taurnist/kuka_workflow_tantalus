package BatteryCheck;


import javax.inject.Inject;

import Workflow.Exception.RobotDoesNotNeedChargeYet;
import Workflow.Exception.RobotNeedsChargeException;

import com.kuka.generated.ioAccess.BMSIOGroup;
import com.kuka.generated.ioAccess.ExternalControlIOGroup;
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
public class BatteryNeedsFullCharge extends RoboticsAPIApplication {
	@Inject
	private LBR lBR_iiwa_14_R820_1;

	@Inject
	private KmpOmniMove kMR_omniMove_200_1;
	
	@Inject
	public BMSIOGroup BMS;

	@Inject
	public ExternalControlIOGroup ExternalControl;

	@Override
	public void initialize() {
		// initialize your application here
	}
	@Override
	public void run() throws Exception {
		// your application execution starts here 
		if(((Boolean) getApplicationData().getProcessData("AllowAutomatedCharging").getValue()) &&  BMS.getStateOfCharge() < ((Integer) getApplicationData().getProcessData("tempMinimalChargeBattery").getValue()) && ((Boolean) getApplicationData().getProcessData("EnableAutomatedCharging").getValue()))
		{ throw new IllegalStateException(); //Needs Charge
		} else { 
			throw new StackOverflowError(); //Does not need charge
		}
		
	}
}