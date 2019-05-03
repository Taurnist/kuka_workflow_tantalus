package application.test;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import Exception.GripperFailException;
import Workflow.Configuration.Location;
import Workflow.LBR.RackHandlingApplicationSuper;
import Workflow.LBR.Action.RackConfig;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.kmp.KmpOmniMove;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;

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
public class MoveRoboBackZ extends RackHandlingApplicationSuper {

	RackConfig manipulation_rack;
	
	private CartesianImpedanceControlMode relaxMode;

	long waitWhileGrasping;
	@Override
	public void initialize() {
		super.initialize();
		
	
	}

	@Override
	public void run() throws Exception {
		Gripper.getFrame("/spacer/tcp").move(
				linRel(0,0,-50).setCartVelocity(10));
			
	}
		

}