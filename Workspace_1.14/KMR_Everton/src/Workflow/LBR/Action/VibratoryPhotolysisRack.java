package Workflow.LBR.Action;

import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;

public class VibratoryPhotolysisRack {
	private ObjectFrame GraspForVialOne;
	private ObjectFrame GraspForVialFour;
	private ObjectFrame GraspForVialSixteen;
	
	
	
	public VibratoryPhotolysisRack(ObjectFrame P1, ObjectFrame P4, ObjectFrame P16)
	{
		this.GraspForVialOne = P1;
		this.GraspForVialFour = P4;
		this.GraspForVialSixteen = P16;
	}
	 
	public Frame GetVialGrasp(int index)
	{
		FancyFrame result = FancyFrame.Create(GraspForVialOne.copyWithRedundancy());
		{
		FancyFrame OneToFour = FancyFrame.Create(GraspForVialFour.copyWithRedundancy()).minus(FancyFrame.Create(GraspForVialOne.copyWithRedundancy())); 
		int row_steps = index % 4;
		result = result.add(OneToFour.times((((double) row_steps) / 3.0d)));  
		}
		
		{
		FancyFrame FourToSixteen = FancyFrame.Create(GraspForVialSixteen.copyWithRedundancy()).minus(FancyFrame.Create(GraspForVialFour.copyWithRedundancy())); 
		double column_steps = (double) Math.floor(((double)index)/4.0d);
		result = result.add(FourToSixteen.times((((double) column_steps) / 3.0d)));
		}
		return result;
	}
	public Frame GetVialPreGrasp(int index)
	{
		Frame result =  new Frame(GetVialGrasp(index));
		 
		result = result.setZ(result.getZ() - 50);
		
		return result;
	}
}
