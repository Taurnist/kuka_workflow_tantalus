package application;

import Workflow.LBR.GripperApplicationsSuper;

public class HomeGripper extends GripperApplicationsSuper {

	@Override
	public void run() throws Exception {
		// TODO Auto-generated method stub
		logger.error(Double.toString(gripper.currentPosition()));
		GripperHomeGripper();
	}

}
