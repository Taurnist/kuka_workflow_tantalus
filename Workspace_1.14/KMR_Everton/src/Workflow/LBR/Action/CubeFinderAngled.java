package Workflow.LBR.Action;

import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.linRel;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.positionHold;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;

import java.util.concurrent.TimeUnit;

import Exception.CriticalActionFailException;
import Workflow.LBR.WorkflowSuper;


import com.kuka.roboticsAPI.applicationModel.IApplicationData;
import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.conditionModel.MotionPathCondition;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.geometricModel.math.CoordinateAxis;
import com.kuka.roboticsAPI.motionModel.IMotionContainer;
import com.kuka.roboticsAPI.motionModel.PositionHold;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.task.ITaskLogger;
import com.kukasystems.modules.measurement.baseframe.util.BaseFrameUtil;
import com.kukasystems.modules.measurement.baseframe.util.CalculationMethod;

public class CubeFinderAngled {
	private boolean DEBUG = true;
	private LBR lbr_iiwa;
	private Tool Tool;
	private ITaskLogger _logger;
	IApplicationData data;
	private CartesianImpedanceControlMode stiffMode;
	private CartesianImpedanceControlMode relaxMode;

	public CubeFinderAngled(IApplicationData data, LBR iiwa, Tool tool,
			ITaskLogger log) {

		lbr_iiwa = iiwa;
		Tool = tool;
		_logger = log;
		this.data = data;

		stiffMode = new CartesianImpedanceControlMode();
		stiffMode.parametrize(CartDOF.X).setStiffness(5000);
		stiffMode.parametrize(CartDOF.Y).setStiffness(5000);
		stiffMode.parametrize(CartDOF.Z).setStiffness(5000);
		stiffMode.parametrize(CartDOF.A).setStiffness(300);
		stiffMode.parametrize(CartDOF.B).setStiffness(300);
		stiffMode.parametrize(CartDOF.C).setStiffness(300);

		relaxMode = new CartesianImpedanceControlMode();
		relaxMode.parametrize(CartDOF.X).setStiffness(500);
		relaxMode.parametrize(CartDOF.Y).setStiffness(500);
		relaxMode.parametrize(CartDOF.Z).setStiffness(500);
		relaxMode.parametrize(CartDOF.A).setStiffness(300);
		relaxMode.parametrize(CartDOF.B).setStiffness(300);
		relaxMode.parametrize(CartDOF.C).setStiffness(300);

	}

	public Frame FindCube(Frame cube_estimation)
			throws CriticalActionFailException {
		_logger.info("Performing 6-point base measurement");

		// Go To estimation
		Tool.getFrame("/spacer/tcp").moveAsync(
				ptp(cube_estimation).setBlendingCart(10).setJointVelocityRel(
						0.3));

		// Go To estimation
		Tool.getFrame("/spacer/calibration_z/rotated").moveAsync(
				lin(cube_estimation).setBlendingCart(10).setJointVelocityRel(
						0.3));

		_logger.info("Taking 3 points on top surface (z)");

		Frame z1 = measurePoint(Tool.getFrame("/spacer/calibration_z/rotated"),
				cube_estimation, "Z1", CoordinateAxis.Z, 0, 0, 75);
		Frame z2 = measurePoint(Tool.getFrame("/spacer/calibration_z/rotated"),
				cube_estimation, "Z2", CoordinateAxis.Z, 0, 0, 75);
		Frame z3 = measurePoint(Tool.getFrame("/spacer/calibration_z/rotated"),
				cube_estimation, "Z3", CoordinateAxis.Z, 0, 0, 75);

		Tool.getFrame("/spacer/calibration_x/rotated").moveAsync(
				lin(GetFrameRelative(cube_estimation, "PRE_X"))
						.setBlendingCart(10).setJointVelocityRel(0.3));

		if (DEBUG)
			_logger.info("Taking 2 points on top surface (x)");

		Frame x1 = measurePoint(Tool.getFrame("/spacer/calibration_x/rotated"),
				cube_estimation, "X1", CoordinateAxis.X, -75, 0, 0);
		Frame x2 = measurePoint(Tool.getFrame("/spacer/calibration_x/rotated"),
				cube_estimation, "X2", CoordinateAxis.X, -75, 0, 0);

		Tool.getFrame("/spacer/tcp").moveAsync(
				lin(GetFrameRelative(cube_estimation, "PRE_Y"))
						.setBlendingCart(10).setJointVelocityRel(0.3));
		if (DEBUG)
			_logger.info("Taking 1 points on y surface");

		Frame y1 = measurePoint(Tool.getFrame("/spacer/calibration_y/rotated"),
				cube_estimation, "Y1", CoordinateAxis.X, 75, 0, 0);

		if (DEBUG)
			_logger.info("Moving Back");

		Tool.getFrame("/spacer/calibration_y/rotated").moveAsync(
				linRel(-10, 0, 0, 0, 0, 0).setBlendingRel(0.2).setCartVelocity(
						60));
		if (DEBUG)
			_logger.info("Moving Back");

		Tool.getFrame("/spacer/calibration_y/rotated").moveAsync(
				linRel(0, 0, -45, 0, 0, 0).setBlendingRel(0.2).setCartVelocity(
						60));
		if (DEBUG)
			_logger.info("Moving Back");

		Tool.getFrame("/spacer/tcp").moveAsync(
				ptp(cube_estimation).setBlendingRel(0.5).setJointVelocityRel(
						0.3));

		Frame newBase = BaseFrameUtil.calculateBase6P(CalculationMethod.TWO_X_ONE_Y,x1, x2, y1, z1, z2, z3);

		return newBase;
	}

	private Frame measurePoint(ObjectFrame tcp_frame, Frame estimation,
			String Point, CoordinateAxis axis, int x, int y, int z)
			throws CriticalActionFailException {

		Frame p = GetFrameRelative(estimation, Point);

		ForceCondition forceCond = ForceCondition.createNormalForceCondition(
				tcp_frame, tcp_frame, axis, 15);
		if (DEBUG) 
			_logger.error("Go To Point");

		tcp_frame.move((lin(p).setCartVelocity(100)));

		if (DEBUG)
			_logger.error("Measure");

		IMotionContainer collision = tcp_frame.move(linRel(x, y, z, 0, 0, 0, tcp_frame)
					.setMode(stiffMode).breakWhen(forceCond)
					.setCartVelocity(10));

		
			if (collision.getFiredBreakConditionInfo() == null) {
				if (DEBUG)
					_logger.error("Did not detect collision");
				throw new CriticalActionFailException();

			} else {
				if (DEBUG)
					_logger.error("detect collision: " + lbr_iiwa.getCurrentCartesianPosition(tcp_frame,
				World.Current.getRootFrame()).toString());
				
			}

//		tcp_frame.move(positionHold(relaxMode, 300, TimeUnit.MILLISECONDS));

		Frame f = lbr_iiwa.getCurrentCartesianPosition(tcp_frame,
				World.Current.getRootFrame());
		if (DEBUG)
			_logger.error("back from");
		tcp_frame.moveAsync(lin(p).setCartVelocity(70).setBlendingRel(0.5));
		return f;
	}

	private Frame GetFrameRelative(Frame estimation, String Point) {
		Frame p = data.getFrame("/Static/Cube_Estimate_Touchtool_Angled")
				.getChild(Point).copyWithRedundancy();
		p.setParent(estimation);
		return p;
	}
}
