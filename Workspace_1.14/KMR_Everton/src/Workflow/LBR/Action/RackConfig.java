package Workflow.LBR.Action;

import javax.inject.Inject;
import javax.inject.Named;

import com.kuka.roboticsAPI.applicationModel.IApplicationData;
import com.kuka.roboticsAPI.conditionModel.ForceCondition;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.World;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.task.ITaskLogger;

public class RackConfig {
	private ObjectFrame Rack_Grasp;
	private FancyFrame Position0;
	private FancyFrame Position5;
	private FancyFrame Position12;
	private IApplicationData data;
	private ITaskLogger _logger;
	private boolean FlipOrder = false;
	private boolean FlipShort = false;
	private boolean FlipLong = false;


	public RackConfig(IApplicationData data, ObjectFrame objectFrame,
			ITaskLogger log) throws Exception {
		Rack_Grasp = objectFrame;

		this.data = data;
		if(log == null)
			throw new Exception("logger is null");
		_logger = log;	
		_logger.info("GetFrameRelative");
		
		Position0 = GetFrameRelative("P1");
		Position5 = GetFrameRelative("P6");
		Position12 = GetFrameRelative("P13");

 
	}
	public RackConfig(IApplicationData data, ObjectFrame objectFrame,
		ITaskLogger log, boolean FlipOrder, boolean flipShort, boolean flipLong) throws Exception
		{
			this(data, objectFrame,log,FlipOrder);
			this.FlipShort = flipShort;
			this.FlipLong = flipLong;
		
		}
	
	public RackConfig(IApplicationData data, ObjectFrame objectFrame,
		ITaskLogger log, boolean FlipOrder) throws Exception {
		FlipOrder = FlipOrder;
		Rack_Grasp = objectFrame;

		this.data = data;
		if(log == null)
			throw new Exception("logger is null");
		_logger = log;	
		_logger.info("GetFrameRelative");
		
		Position0 = GetFrameRelative("P1");
		Position5 = GetFrameRelative("P6");
		Position12 = GetFrameRelative("P13");
	}

	public Frame GetRackGrasp()
	{
		return Rack_Grasp.copyWithRedundancy();
	}
	
	// This is only used for pre-aligning to a rack, all manipulations vial/rack manipulations 
	// from the MotherOfAllGripperApplications done are to be performed starting from this point....
	// They will also end at this point...
	public Frame get_pre_grasp_position(){
		Frame result = new Frame();
		result.setParent(Rack_Grasp);
		result.setZ(result.getZ() - 135); 
		return result;
	}
	
	
	
	public FancyFrame get_pre_position(int i) throws Exception {
		FancyFrame result = get_position(i);
		result.setZ(result.getZ() - 60); // IN POINT FRAME
		return result;
	}
	
	public FancyFrame get_position(int i) throws Exception {
		_logger.info("getPosition : " + i);
		if (i < 0 || i >= 18 || i == 8 || i == 9)
			throw new Exception("Bad index for rack: " + i);
		
		int index = FlipOrder?17-i:i;
		
		FancyFrame A = Position5.minus(Position0);
		FancyFrame B = Position12.minus(Position0);

		FancyFrame result = Position0.copyWithRedundancyWrap();

		if(FlipLong)
			result = result.add(A.times(1-(index % 6) * 0.2d));
		else 
			result = result.add(A.times((index % 6) * 0.2d));
		
		if(FlipShort)
			result = result.add(B.times(1-Math.floor(((1.0d * index) / 6))*0.5d));
		else 
			result = result.add(B.times(Math.floor(((1.0d * index) / 6))*0.5d));


		_logger.info(result.toString());
		return result;
	}
	


	public FancyFrame GetFrameRelative(String vial_grasp_point) {
		ObjectFrame p = data.getFrame("/Static/rack_grasp");
		Frame child = p.getChild(vial_grasp_point).copyWithRedundancy();
		child.setParent(Rack_Grasp);
		return FancyFrame.Create(child);
	}

	public Frame get_minimal_pre_grasp_position() {
		Frame result = new Frame();
		result.setParent(Rack_Grasp);
		result.setZ(result.getZ() - 65); 
		return result;
	}
}
