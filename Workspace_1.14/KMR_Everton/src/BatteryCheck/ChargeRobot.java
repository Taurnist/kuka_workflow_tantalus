package BatteryCheck;


import java.util.Random;

import javax.inject.Inject;

import com.kuka.generated.ioAccess.BMSIOGroup;
import com.kuka.generated.ioAccess.ExternalControlIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
import com.kuka.roboticsAPI.persistenceModel.processDataModel.IProcessData;
import com.kuka.task.ITaskLogger;

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
public class ChargeRobot extends RoboticsAPIApplication {
	@Inject
	private LBR lBR_iiwa_14_R820_1;
	@Inject
	protected ITaskLogger logger;
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
		Random rand = new Random();
		
		StartCharging();
		Thread.sleep(2*60*1000);
		
		while(BMS.getStateOfCharge() <= (Integer) getApplicationData().getProcessData("tempMaximalChargeBattery").getValue())
		{
			if (BatteryNotChargingWhileItShould()) {
				logger
					.error("WARNING: robot stoped charging detected eventhough SOC < 50 & ChargeEnabled=1");
				break;
			}
			
			Thread.sleep(5*1000);
		}
	
		StopCharging();
		int newMaximalCharge = (Integer) getApplicationData().getProcessData("maximalChargeBattery").getValue() + (int) Math.floor(rand.nextDouble() * 11d - 5d);
		getApplicationData().getProcessData("tempMaximalChargeBattery").setValue(newMaximalCharge);
		int newTempMinimalCharge = Math.min(99,(Integer) getApplicationData().getProcessData("minimalChargeBattery").getValue() + (int) Math.floor(rand.nextDouble() * 11d - 5d));
		getApplicationData().getProcessData("tempMinimalChargeBattery").setValue(newTempMinimalCharge);
		
	}
	
	private boolean BatteryNotChargingWhileItShould() {
		return BMS.getStatus() == 2 && BMS.getChargingEnable()
			&& BMS.getStateOfCharge() < 50;
	}

	private void StopCharging() throws InterruptedException {
		Thread.sleep(1000);
		BMS.setChargingEnable(false);
		Thread.sleep(1000);
		BMS.setChargingRelayEnable(false);
		Thread.sleep(1000);
		ExternalControl.setChargingRelayEnabled(false);
		
		// TODO Auto-generated method stub

	}

	private void StartCharging() throws InterruptedException {
		Thread.sleep(1000);
		BMS.setChargingEnable(true);
		Thread.sleep(1000);
		BMS.setChargingRelayEnable(true);
		Thread.sleep(1000);
		ExternalControl.setChargingRelayEnabled(true);
	}
	
}