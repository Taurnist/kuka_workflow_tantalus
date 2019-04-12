package Workflow.MoveStation;

import java.util.Random;

import com.kuka.nav.task.NavTaskCategory;

import Workflow.KMR.MapConfiguration.Map;
import Workflow.KMR.MapConfiguration.Path;
import Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning;
import Workflow.KMR.Util.MOAGraphMotionTasks;
@NavTaskCategory
public class RandomWalk extends MOAGraphMotionTasks {
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
						PhotocatNodeIdFinePositioning.DryingStation,
						PhotocatNodeIdFinePositioning.ChargingStation,
						PhotocatNodeIdFinePositioning.InputStation};
		while (true) {
			int random_number = (int) Math.floor((rand.nextDouble() * 7.0d));
			GoAndFinePosition(locations[random_number]);
		}
	}

}
