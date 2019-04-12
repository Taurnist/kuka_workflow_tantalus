package Workflow.KMR.Util;

import java.util.LinkedList;

import javax.inject.Inject;
import javax.inject.Named;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import Workflow.KMR.MapConfiguration.Map;
import Workflow.KMR.MapConfiguration.Path;
import Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning;

import com.kuka.nav.Location;
import com.kuka.nav.Pose;
import com.kuka.nav.Position;
import com.kuka.nav.data.LocationData;
import com.kuka.nav.fineloc.FineLocalizationContainer;
import com.kuka.nav.fineloc.FineLocalizationRequest;
import com.kuka.nav.fleet.ChangeGraphCommand;
import com.kuka.nav.fleet.FleetManager;
import com.kuka.nav.fleet.GraphMotion;
import com.kuka.nav.fleet.filter.InstanceFilter;
import com.kuka.nav.fleet.graph.GraphData;
import com.kuka.nav.fleet.graph.TopologyGraph;
import com.kuka.nav.fleet.graph.TopologyNode;
import com.kuka.nav.line.VirtualLineMotion;
import com.kuka.nav.rel.RelativeMotion;
import com.kuka.nav.robot.MobileRobot;
import com.kuka.nav.robot.MobileRobotManager;
import com.kuka.nav.task.NavTaskCategory;
import com.kuka.resource.locking.LockException;
import com.kuka.task.ITaskLogger;
import com.kuka.task.RoboticsAPITask;

@NavTaskCategory
public abstract class MOAGraphMotionTasks extends RoboticsAPITask {

	@Inject
	public ITaskLogger LOG;

	@Inject
	private MobileRobotManager _robotManager;

	protected MobileRobot _kmp;

	@Inject
	private LocationData _locationData;

	@Inject
	private GraphData _graph;

	@Inject
	private FleetManager _fleet;

	// We have but one robot, and one graph
	private int graphId = Workflow.KMR.MapConfiguration.Map.photocat_graph_id;
	private int robotId = Workflow.KMR.MapConfiguration.Map.robotId;
	private Object[] nodes;
	
	public boolean success=false;

	@Override
	public void initialize() throws Exception {
		// wait until a robot registered at the VMS
		if(_robotManager!=null&&_graph!=null){
			_kmp = _robotManager.getRobot(1);
			_robotManager.getCount().awaitGreater(0);
			TopologyGraph topologyGraph = _graph.get(graphId);
			nodes = (Object[]) topologyGraph.nodes().toArray();
			success=true;
		}else{
			System.out.println("Robot maanger is NULL");
		}

		
		
		
	}
	
	@Override
	public void run() throws Exception {
		

	} 

	@Override
	public void dispose() throws Exception {

		_kmp.unlock();
	}

	protected void GoAndFinePosition(PhotocatNodeIdFinePositioning goalNod) throws LockException,
	InterruptedException {
		graphMotion(findNearestGraphNode(), goalNod.getNodeId(), 0.1);
		finePositioning(goalNod);
	}

	protected void graphMotion(PhotocatNodeIdFinePositioning goalNod) throws LockException,
	InterruptedException {
graphMotion(findNearestGraphNode(), goalNod.getNodeId(), 0.1);
}


	private int findNearestGraphNode() {
		TopologyNode nearest = (TopologyNode) nodes[0];
		double distance = Utill.Distance(_kmp.getPose(), nearest.getPosition());
		for (int i = 1; i < nodes.length; i++) {
			TopologyNode new_node = (TopologyNode) nodes[i];
			double new_distance = Utill.Distance(_kmp.getPose(),
					new_node.getPosition());
			if (distance > new_distance) {
				distance = new_distance;
				nearest = new_node;
			}
		}
		
		LOG.info("nearest node id" + nearest.getId());
		return nearest.getId();
	}

	
	protected void finePositioning(PhotocatNodeIdFinePositioning node) throws LockException,
			InterruptedException {
		finePositioning(node.getFinePositionString());
	}
	
	protected void finePositioning(String location) throws LockException,
	InterruptedException {

Location p1 = _locationData.get(Workflow.KMR.MapConfiguration.Map.photocat_map_id,
	location);
_kmp.lock();
Pose offset = new Pose();
FineLocalizationRequest fineRequest = new FineLocalizationRequest(p1);

FineLocalizationContainer fineContainer = _kmp.execute(fineRequest);

offset = fineContainer.getRobotPose().invert();
LOG.info("Offset: " + offset);
RelativeMotion relMove = new RelativeMotion(offset);
relMove.setMaxVelocity(0.1);
_kmp.execute(relMove);
_kmp.unlock();
}


	private void graphMotion(int starteNod, int goalNod, double speed)
			throws LockException, InterruptedException {

		GetOnGraph(starteNod, speed);
		TopologyGraph topologyGraph = _graph.get(graphId);

		TopologyNode goalNode = topologyGraph
				.getNode(goalNod);

		// Move on the graph
		InstanceFilter instanceFilter = new InstanceFilter(robotId);
		GraphMotion moveGraph = new GraphMotion(topologyGraph, goalNode);
		int[] whiteListNodeIDs = getWhiteList(starteNod, goalNod);
		if(whiteListNodeIDs != null && whiteListNodeIDs.length != 0)
		{
			LOG.info("using white list");
			LinkedList<TopologyNode> white_list = new LinkedList<TopologyNode>();
			
			for(int i=0;i< whiteListNodeIDs.length;i++)
			{
				white_list.add(topologyGraph.getNode(whiteListNodeIDs[i]));
			}
			moveGraph.setWhiteList(white_list);
		} else {
			LOG.warn("using white list");
		}
		moveGraph.setResourceFilter(instanceFilter);
		_fleet.execute(moveGraph);

	}


	private int[] getWhiteList(int startNodeID, int goalNodeID) {
		for(int i=0;i<Workflow.KMR.MapConfiguration.Map.PATHS.length;i++)
		{
			Workflow.KMR.MapConfiguration.Path p = Workflow.KMR.MapConfiguration.Map.PATHS[i];
			if((p.startNodeID == startNodeID && p.goalNodeID == goalNodeID)
					||(p.startNodeID == goalNodeID && p.goalNodeID == startNodeID))
			{
				return p.WhiteList;
			}
		}
		
		return null;
	}

	private void GetOnGraph(int startNod, double speed) throws LockException, InterruptedException {
		_kmp.lock();
		// Get some objects, initialization
		TopologyGraph topologyGraph = _graph.get(graphId);
		TopologyNode startNode = topologyGraph.getNode(startNod);

		// Move to First Position
		Position start = startNode.getPosition();
		Pose startPose = new Pose(start.getX(), start.getY(), _kmp.getPose()
				.getTheta());
		_kmp.execute(new VirtualLineMotion(_kmp.getPose(), startPose)
				.setMaxVelocity(speed));

		// Set on Graph
		ChangeGraphCommand graphCommand = new ChangeGraphCommand(graphId,
				startNod);
		_kmp.execute(graphCommand);
		_kmp.unlock();
	}
	


}