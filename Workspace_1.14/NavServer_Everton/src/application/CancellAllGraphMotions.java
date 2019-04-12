package application;

import javax.inject.Inject;

import com.kuka.nav.fleet.FleetManager;
import com.kuka.nav.fleet.GraphMotionContainer;
import com.kuka.nav.task.NavTaskCategory;
import com.kuka.task.RoboticsAPITask;

/**
 * Small app to cancel all currently running GraphMotions managed by the fleet
 * manager. This can be used for example to stop a GraphMotion that was
 * commanded via MapView. Note, however, that this will cancel all GraphMotions
 * that are currently active, so in case other AGVs are also currently moved by
 * the FleetManager they also will be stopped.
 * 
 */
@NavTaskCategory
public class CancellAllGraphMotions extends RoboticsAPITask 
{
	@Inject
	private FleetManager _fleetMan;

	@Override
	public void run() 
	{
		for (GraphMotionContainer container : _fleetMan.getGraphMotionContainers())
		{
			container.cancel();
		}
	}
}
