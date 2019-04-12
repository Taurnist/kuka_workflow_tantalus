package Workflow.KMR.MapConfiguration;

//Class used to store Magic numbers and Strings
//There can be no magic numbers for movements in here, this is strictly for settings
public class Map {
	public static int photocat_graph_id = 5;
	public static int robotId = 1;
	public static int photocat_map_id = 6;

	// TODO read paths from config file
	// public static Path[] PATHS = new Path[] { };
	public static Path[] PATHS = new Path[]{ // FLOOR EXP
//					new Path(
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.GC,
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Capper,
//						new int[]{12, 11, 9,7}),
//					new Path(
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.GC,
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Cartridge,
//						new int[]{12,11,9,10,4,3,2}),
//					new Path(
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.GC,
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Photocat,
//						new int[]{12,11,6}),
//					new Path(
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.GC,
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.LiquidHandlingSystem,
//						new int[]{13,12,11,9,10}),
//					new Path(
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.GC,
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Quantos,
//						new int[]{12,11,9,10,4,5}),
//					new Path(
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.GC,
//						Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Sonicator,
//						new int[]{12,11,9,8}),
//				
//						
//						
//						new Path(
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Capper,
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Cartridge,
//							new int[]{7,9,10,4,3,2}),
//						new Path(
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Capper,
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Photocat,
//							new int[]{7,9,6}),
//						new Path(
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Capper,
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.LiquidHandlingSystem,
//							new int[]{13,7,9,10}),
//						new Path(
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Capper,
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Quantos,
//							new int[]{7,9,10,4,5}),
//						new Path(
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Capper,
//							Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Sonicator,
//							new int[]{7,9,8}),
//					
//					
//							new Path(
//								Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Cartridge,
//								Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Photocat,
//								new int[]{2,3,4,10,9,6}),
//							new Path(
//								Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Cartridge,
//								Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.LiquidHandlingSystem,
//								new int[]{13,2,3,4,10,9,10}),
//							new Path(
//								Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Cartridge,
//								Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Quantos,
//								new int[]{2,3,4,5}),
//							new Path(
//								Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Cartridge,
//								Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Sonicator,
//								new int[]{2,3,4,10,8}),
//					
//								new Path(
//									Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Photocat,
//									Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.LiquidHandlingSystem,
//									new int[]{13,6,9,10}),
//								new Path(
//									Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Photocat,
//									Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Quantos,
//									new int[]{6,9,10,4,5}),
//								new Path(
//									Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Photocat,
//									Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Sonicator,
//									new int[]{6,9,8}),
//
//									new Path(
//										Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.LiquidHandlingSystem,
//										Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Quantos,
//										new int[]{13,10,4,5}),
//									new Path(
//										Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.LiquidHandlingSystem,
//										Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Sonicator,
//										new int[]{13,10,8}),
//					
//										new Path(
//											Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Quantos,
//											Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Sonicator,
//											new int[]{5,4,10,8}),
//
//	// Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.FumeHood1.graphnode_id,
//	// Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Sonicator.graphnode_id,
//	// new int[] { 17, 18, 19, 3, 5, 6, 26, 25 }),
//	// new Path(
//	// Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Sonicator.graphnode_id,
//	// Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Photocat.graphnode_id,
//	// new int[] { 25, 26, 6, 22, 27 }),
//	// new Path(
//	// Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.Photocat.graphnode_id,
//	// Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning.GC.graphnode_id,
//	// new int[] { 27, 22, 5, 20, 21, 28 })
	};
}
