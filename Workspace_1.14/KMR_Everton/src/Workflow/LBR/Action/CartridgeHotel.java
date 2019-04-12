package Workflow.LBR.Action;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.kuka.roboticsAPI.applicationModel.IApplicationData;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;

public class CartridgeHotel 
{
	
	private ObjectFrame GraspForCartridgeOne;
	private ObjectFrame[] steps = new ObjectFrame[4];
	private ObjectFrame pre_lin_hotel;

	public CartridgeHotel(String pointName, IApplicationData appData)
	{
		GraspForCartridgeOne = appData.getFrame("/Station/Cartridge/Cube_Corner/" + pointName);
		for(int i=0;i<4;i++)
		{
			steps[i] = appData.getFrame("/Static/hotel_cartridge1_grasp/step"+i);
		}
		pre_lin_hotel = appData.getFrame("/Static/hotel_cartridge1_grasp/pre_lin_hotel");
	}
	
	public Frame GetStep(int i)
	{
		if(!(i>=0&&i<=3))
			throw new NotImplementedException();
		
		Frame result = steps[i].copyWithRedundancy();
		result.setParent(GraspForCartridgeOne);
		return result;
	}
	
	public Frame GetPreLinHotel()
	{
		Frame result = pre_lin_hotel.copyWithRedundancy();
		result.setParent(GraspForCartridgeOne);
		return result;
	}
}
