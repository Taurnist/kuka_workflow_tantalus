package Workflow.KMR.MapConfiguration;

public class Path {
	public int startNodeID;
	public int goalNodeID;
	public 	PhotocatNodeIdFinePositioning start;
	public PhotocatNodeIdFinePositioning goal;
	public int[] WhiteList;

	public  Path(PhotocatNodeIdFinePositioning start,PhotocatNodeIdFinePositioning goal, int[] list)
	{
		startNodeID = start.graphnode_id;
		goalNodeID = goal.graphnode_id;
		WhiteList = list;
		this.start = start;
		this.goal=goal;
	}
	}	