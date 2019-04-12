package application;

import javax.inject.Inject;

import com.kuka.generated.ioAccess.BMSIOGroup;
import com.kuka.generated.ioAccess.ExternalControlIOGroup;

import Workflow.LBR.GripperApplicationsSuper;

public class NormalChargeHack extends GripperApplicationsSuper {
	@Inject
	public BMSIOGroup BMS;

	@Inject
	public ExternalControlIOGroup ExternalControl;

	@Override
	public void run() throws Exception {
		logger.error("NORMAL CHARGE HACK");
		logger
			.error("This program will try to reset the charging if the robot has charged fully, and stops charging");
		StartCharging();
		Thread.sleep(60 * 1000);
		
		while (true) {
			logger.info("State of Charge: " + BMS.getStateOfCharge() + " I: " + BMS.getTotalCurrent() + " Status: " + BMS.getStatus());
			if (BatteryNotChargingWhileItShould()) {
				logger
					.error("WARNING: robot stoped charging detected eventhough SOC < 90 & ChargeEnabled=1");
				logger.warn("Stop Charge");
				StopCharging();
				Thread.sleep(60 * 1000);

				logger.warn("Start Charge");
				StartCharging();

			}
			Thread.sleep(60 * 1000);
		}
	}

	private boolean BatteryNotChargingWhileItShould() {
		return BMS.getStatus() == 2 && BMS.getChargingEnable()
			&& BMS.getStateOfCharge() < 30;
	}

	private void StopCharging() {
		BMS.setChargingEnable(false);
		// TODO Auto-generated method stub

	}

	private void StartCharging() {
		// TODO Auto-generated method stub
		BMS.setChargingEnable(true);
	}

}
