package Workflow.KMR.MapConfiguration;
import Workflow.Configuration.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum PhotocatNodeIdFinePositioning {

	GC(22, "gc_station", null), Quantos(2, "quantos_sample_station", null), Cartridge(
		4, "quantos_cartridge_station", null), Photocat(16,
		"photocat_station", null), Sonicator(
		13, "sonicator_station", null), Crossroads(11, "crossroads", null), LiquidHandlingSystem(
		18, "liquid_station", null), DryingStation(12, "drying_station", null), ChargingStation(
		5, "plate_pre_pos", null), InputStation(27, "InputStation", null), Vibratory_Photocat(
		26, "vibratory_photolysis", null), InputStation2(30,"input_station_2",null), InputStation3(29,"input_station_3",null); 
 
	int graphnode_id;
	String fine_pos_location;
	Location location;

	PhotocatNodeIdFinePositioning(int graph_node_id,
		String fine_pos_location_, Location loc) {
		graphnode_id = graph_node_id;
		fine_pos_location = fine_pos_location_;
		location = loc;
	}

	public int getNodeId() {
		return graphnode_id;
	}
	public String getFinePositionString() {
		return fine_pos_location;
	}

	public static PhotocatNodeIdFinePositioning getPhotocatNodeIdFinePositionFromLocation(
		Location node) {
		switch (node) {
			case Quantos :
				return PhotocatNodeIdFinePositioning.Quantos;
			case GC :
				return PhotocatNodeIdFinePositioning.GC;
			case LiquidHandlingSystem :
				return PhotocatNodeIdFinePositioning.LiquidHandlingSystem;
			case Crossroads :
				return PhotocatNodeIdFinePositioning.Crossroads;
			case Photocat :
				return PhotocatNodeIdFinePositioning.Photocat;
			case Sonicator :
				return PhotocatNodeIdFinePositioning.Sonicator;
			case Cartridge :
				return PhotocatNodeIdFinePositioning.Cartridge;
			case DryingStation :
				return PhotocatNodeIdFinePositioning.DryingStation;
			case InputStation :
				return PhotocatNodeIdFinePositioning.InputStation;
			case ChargingStation :
				return PhotocatNodeIdFinePositioning.ChargingStation;
			case Vibratory_Photocat :
				return PhotocatNodeIdFinePositioning.Vibratory_Photocat;
			case InputStation2:
				return PhotocatNodeIdFinePositioning.InputStation2;
			case InputStation3:
				return PhotocatNodeIdFinePositioning.InputStation3;
			default :
				throw new NotImplementedException();
		}
	}
}
