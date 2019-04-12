package Workflow.KMR.Util;

import com.kuka.nav.Pose;
import com.kuka.nav.Position;

public class Utill {
	

	public static double Distance(Pose pose, Position position)
	{
		double result = 0;
		result += Math.pow(pose.getX()-position.getX(),2);
		result += Math.pow(pose.getY()-position.getY(),2);
		return Math.sqrt(result);
	}

}
