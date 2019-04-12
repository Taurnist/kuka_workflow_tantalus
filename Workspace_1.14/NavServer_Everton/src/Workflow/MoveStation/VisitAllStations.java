package Workflow.MoveStation;

import com.kuka.nav.task.NavTaskCategory;

import Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning;
import Workflow.KMR.Util.MOAGraphMotionTasks;
@NavTaskCategory
public class VisitAllStations extends MOAGraphMotionTasks {
	@Override
	public void run() throws Exception {
		for(int i=0;i<3;i++){		
			GoAndFinePosition(PhotocatNodeIdFinePositioning.Quantos);
		GoAndFinePosition(PhotocatNodeIdFinePositioning.Cartridge);
		GoAndFinePosition(PhotocatNodeIdFinePositioning.Sonicator);
		GoAndFinePosition(PhotocatNodeIdFinePositioning.DryingStation);
		GoAndFinePosition(PhotocatNodeIdFinePositioning.LiquidHandlingSystem);
		GoAndFinePosition(PhotocatNodeIdFinePositioning.InputStation2);
		GoAndFinePosition(PhotocatNodeIdFinePositioning.Photocat);
		GoAndFinePosition(PhotocatNodeIdFinePositioning.GC);
		GoAndFinePosition(PhotocatNodeIdFinePositioning.InputStation);
	}}
}
