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
public class PositionAndGMSReferencing extends GripperApplicationsSuper {

    private final static double sideOffset = Math.toRadians(5);       // offset in radians for side motion
    private static int reduceVibrationWaitTime = 2500;             // ms
    private static double fastjoggingVelocity = 0.2;                  // relative velocity
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
        PositionMastering mastering = new PositionMastering(lbr_iiwa);

        boolean allAxesMastered = true;
        for (int i = 0; i < axisId.length; ++i)
        {
            // Check if the axis is mastered - if not, no referencing is possible
            boolean isMastered = mastering.isAxisMastered(axisId[i]);
            if (!isMastered)
            {
                getLogger().warn("Axis with axisId " + axisId[i] + " is not mastered, therefore it cannot be referenced");
            }
            
            allAxesMastered &= isMastered;
        }
        
        // We can move faster, if operation mode is T1
        if (OperationMode.T1 == lbr_iiwa.getOperationMode())
        {
            joggingVelocity = 0.4;
        }
        
        if (allAxesMastered)
        {
            getLogger().info("Perform position and GMS referencing with 5 positions");
            
            // Move to home position
            getLogger().info("Moving to home position");
            lbr_iiwa.move(ptpHome().setJointVelocityRel(fastjoggingVelocity));

            // In this example 5 positions are defined, though each one 
            // will be reached from negative and from positive axis 
            // direction resulting 10 measurements. The safety needs 
            // exactly 10 measurements to perform the referencing.
            performMotion(new JointPosition(Math.toRadians(0.0),
                                            Math.toRadians(16.18),
                                            Math.toRadians(23.04),
                                            Math.toRadians(37.35),
                                            Math.toRadians(-67.93),
                                            Math.toRadians(38.14),
                                            Math.toRadians(-2.13)));
            
            performMotion(new JointPosition(Math.toRadians(18.51),
                                            Math.toRadians(9.08),
                                            Math.toRadians(-1.90),
                                            Math.toRadians(49.58),
                                            Math.toRadians(-2.92),
                                            Math.toRadians(18.60),
                                            Math.toRadians(-31.18)));

            performMotion(new JointPosition(Math.toRadians(-18.53),
                                            Math.toRadians(-25.76),
                                            Math.toRadians(-47.03),
                                            Math.toRadians(-49.55),
                                            Math.toRadians(30.76),
                                            Math.toRadians(-30.73),
                                            Math.toRadians(20.11)));

            performMotion(new JointPosition(Math.toRadians(-48.66),
                                            Math.toRadians(24.68),
                                            Math.toRadians(-11.52),
                                            Math.toRadians(10.48),
                                            Math.toRadians(-11.38),
                                            Math.toRadians(-20.70),
                                            Math.toRadians(20.87)));

            performMotion(new JointPosition(Math.toRadians(9.01),
                                            Math.toRadians(-35.00),
                                            Math.toRadians(24.72),
                                            Math.toRadians(-82.04),
                                            Math.toRadians(14.65),
                                            Math.toRadians(-29.95),
                                            Math.toRadians(1.57)));
            
            // Move to home position at the end
            getLogger().info("Moving to home position");  
        	
    		lbr_iiwa.getFlange().move(ptp(getApplicationData().getFrame("/DrivePos")).setJointVelocityRel(fastjoggingVelocity));
    		getApplicationData().getProcessData("lastCalibrationOfArm").setValue((new Date()).getTime()+"");
    		
        }
    }

    private void performMotion(JointPosition position)
    {
        getLogger().info("Moving to position #" + (++positionCounter));

        PTP mainMotion = new PTP(position).setJointVelocityRel(fastjoggingVelocity);
        lbr_iiwa.move(mainMotion);
        mainMotion = new PTP(position).setJointVelocityRel(joggingVelocity);
        
        // Wait a little to reduce robot vibration after stop.
        ThreadUtil.milliSleep(reduceVibrationWaitTime);
        
        getLogger().info("Moving to current position from negative direction");
        JointPosition position1 = new JointPosition(lbr_iiwa.getJointCount());
        for (int i = 0; i < lbr_iiwa.getJointCount(); ++i)
        {
            position1.set(i, position.get(i) - sideOffset);
        }
        PTP motion1 = new PTP(position1).setJointVelocityRel(joggingVelocity);
        lbr_iiwa.move(motion1);
        lbr_iiwa.move(mainMotion);

        // Wait a little to reduce robot vibration after stop.
        ThreadUtil.milliSleep(reduceVibrationWaitTime);
        
        // Send the command to safety to trigger the measurement
        sendSafetyCommand();

        getLogger().info("Moving to current position from positive direction");
        JointPosition position2 = new JointPosition(lbr_iiwa.getJointCount());
        for (int i = 0; i < lbr_iiwa.getJointCount(); ++i)
        {
            position2.set(i, position.get(i) + sideOffset);
        }
        PTP motion2 = new PTP(position2).setJointVelocityRel(joggingVelocity);
        lbr_iiwa.move(motion2);
        lbr_iiwa.move(mainMotion);

        // Wait a little to reduce robot vibration after stop
        ThreadUtil.milliSleep(reduceVibrationWaitTime);
        
      	getApplicationData().getProcessData("lastCalibrationOfArm").setValue((new Date()).getTime()+"");
        // Send the command to safety to trigger the measurement
        sendSafetyCommand();
    }
    
    private void sendSafetyCommand()
    {
        ISunriseRequestService requestService = (ISunriseRequestService) (kukaController.getRequestService());
        SSR ssr = SSRFactory.createSafetyCommandSSR(GMS_REFERENCING_COMMAND);
        Message response = requestService.sendSynchronousSSR(ssr);
        int result = response.getParamInt(0);
        if (COMMAND_SUCCESSFUL != result)
        {
            getLogger().warn("Command did not execute successfully, response = " + result);
        }
    }
}
