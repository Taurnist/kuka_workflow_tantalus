package application;

import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import Workflow.LBR.GripperApplicationsSuper;

import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.controllerModel.sunrise.ISunriseRequestService;
import com.kuka.roboticsAPI.controllerModel.sunrise.api.SSR;
import com.kuka.roboticsAPI.controllerModel.sunrise.api.SSRFactory;
import com.kuka.roboticsAPI.controllerModel.sunrise.connectionLib.Message;
import com.kuka.roboticsAPI.controllerModel.sunrise.positionMastering.PositionMastering;
import com.kuka.roboticsAPI.deviceModel.JointPosition;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.OperationMode;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.motionModel.PTP;

/**
 * This application can be used as template for Lbr iiwa Position and GMS Referencing.
 * The safety needs exactly 10 measurements to perform a successful GMS Referencing. 
 * The time between two measurements must be less than 15 seconds.
 */
public class FixSafety extends GripperApplicationsSuper {

    private final static double sideOffset = Math.toRadians(5);       // offset in radians for side motion
    private static int reduceVibrationWaitTime = 2500;             // ms
    private static double fastjoggingVelocity = 0.4;                  // relative velocity
    private static double joggingVelocity = 0.2;                      // relative velocity
    private final static int axisId[] = {0, 1, 2, 3, 4, 5, 6};        // axes to be referenced
    private final static int GMS_REFERENCING_COMMAND = 2;             // safety command for GMS referencing
    private final static int COMMAND_SUCCESSFUL = 1;
    private int positionCounter = 0;
  	
    @Inject
  	@Named("Gripper")
  	protected Tool Gripper;

    @Override
    public void initialize()
    {
        kukaController = (Controller) getContext().getControllers().toArray()[0];
        lbr_iiwa = (LBR) kukaController.getDevices().toArray()[0];
//    		Gripper.attachTo(lbr_iiwa.getFlange());
    }

    public void run()
    {
    	GripperHomeGripper();
            lbr_iiwa.move(ptpHome().setJointVelocityRel(fastjoggingVelocity));

            // In this example 5 positions are defined, though each one 
            // will be reached from negative and from positive axis 
            // direction resulting 10 measurements. The safety needs 
            // exactly 10 measurements to perform the referencing.
            performMotion(new JointPosition(Math.toRadians(20.0),
                                            Math.toRadians(20.0),
                                            Math.toRadians(20.0),
                                            Math.toRadians(20.0),
                                            Math.toRadians(20.0),
                                            Math.toRadians(20.0),
                                            Math.toRadians(20.0)));
            
            performMotion(new JointPosition(Math.toRadians(-20.0),
              Math.toRadians(-20.0),
              Math.toRadians(-20.0),
              Math.toRadians(-20.0),
              Math.toRadians(-20.0),
              Math.toRadians(-20.0),
              Math.toRadians(-20.0)));
            
            ArmToDrivePos();
        	getApplicationData().getProcessData("lastCalibrationOfArm").setValue(1);
    }

    private void performMotion(JointPosition position)
    {
        getLogger().info("Moving to position #" + (++positionCounter));

        PTP mainMotion = new PTP(position).setJointVelocityRel(fastjoggingVelocity);
        lbr_iiwa.move(mainMotion);
        
    }

}
