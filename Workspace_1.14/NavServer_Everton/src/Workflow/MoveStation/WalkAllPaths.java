package Workflow.MoveStation;

import java.util.Random;
import java.util.logging.Logger;

import com.kuka.nav.task.NavTaskCategory;

import Workflow.KMR.MapConfiguration.Map;
import Workflow.KMR.MapConfiguration.Path;
import Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning;
import Workflow.KMR.Util.MOAGraphMotionTasks;
@NavTaskCategory
public class WalkAllPaths extends MOAGraphMotionTasks {
	@Override
	public void run() throws Exception {
		Random rand = new Random();

		PhotocatNodeIdFinePositioning[] locations = {
						PhotocatNodeIdFinePositioning.InputStation2,
						PhotocatNodeIdFinePositioning.Cartridge,
						PhotocatNodeIdFinePositioning.GC,
						PhotocatNodeIdFinePositioning.LiquidHandlingSystem,
						PhotocatNodeIdFinePositioning.Photocat,
						PhotocatNodeIdFinePositioning.Quantos,
						PhotocatNodeIdFinePositioning.Sonicator,
						PhotocatNodeIdFinePositioning.InputStation,
						PhotocatNodeIdFinePositioning.DryingStation
						};
		
		//while (true) 
		{
			LOG.info("Starting loop");
			for (Path p : Map.PATHS) {
				LOG.info("traveling to " + p.start.toString());
				graphMotion(p.start);
				LOG.info("traveling and positioning to " +  p.goal.toString());
				GoAndFinePosition(p.goal);
				GoAndFinePosition(p.start);
			}
			LOG.info("Stopping loop");
		}
	}

}
