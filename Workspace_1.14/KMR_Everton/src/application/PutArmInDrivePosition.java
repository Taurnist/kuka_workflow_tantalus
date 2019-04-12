package application;

import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptpHome;

import javax.inject.Inject;
import javax.inject.Named;

import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.task.ITaskLogger;

public class PutArmInDrivePosition extends RoboticsAPIApplication{
	@Inject
	private ITaskLogger _logger;

	@Inject
    @Named("Gripper")
    private Tool Gripper;
    
    private Controller kukaController;
    private LBR lbr_iiwa;
    
    @Override
    public void initialize()
    {
           kukaController = (Controller) getContext().getControllers().toArray()[0];
           lbr_iiwa = (LBR) kukaController.getDevices().toArray()[0];
           Gripper.attachTo(lbr_iiwa.getFlange());
    }

	@Override
	public void run() {
		// your application execution starts here	
		_logger.info("lbr_iiwa started");
		lbr_iiwa.getFlange().move(ptp(getApplicationData().getFrame("/DrivePos")).setJointVelocityRel(0.5));
	
	}
}
