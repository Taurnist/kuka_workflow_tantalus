package application;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.inject.Named;

import com.kuka.nav.Location;
import com.kuka.nav.OrientationMode;
import com.kuka.nav.Orientations;
import com.kuka.nav.Pose;
import com.kuka.nav.Position;
import com.kuka.nav.command.NavExecutionResult;
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
import com.kuka.nav.recover.OffPathCondition;
import com.kuka.nav.recover.OffStartPoseCondition;
import com.kuka.nav.recover.ReturnToPathRecover;
import com.kuka.nav.recover.ReturnToStartPoseRecover;
import com.kuka.nav.rel.RelativeMotion;
import com.kuka.nav.rel.RelativeMotionContainer;
import com.kuka.nav.robot.MobileRobot;
import com.kuka.nav.robot.MobileRobotManager;
import com.kuka.nav.task.NavTaskCategory;
import com.kuka.nav.task.remote.RemoteTaskId;
import com.kuka.nav.task.remote.TaskRequest;
import com.kuka.nav.task.remote.TaskRequestContainer;
import com.kuka.roboticsAPI.deviceModel.Robot;
import com.kuka.task.ITaskLogger;
import com.kuka.task.RoboticsAPITask;

@NavTaskCategory
/**
 * This application contains some simple examples of motion commands
 * for mobile robots using the KUKA Navigation solution.
 */
public class Training extends RoboticsAPITask
{
	@Inject
	private ITaskLogger LOG;

	@Inject
	private MobileRobotManager robotManager;
	
	@Inject
	@Named("Nav_KMP")
	private MobileRobot _kmp;
	
	@Inject
	private LocationData _locationData;
	
	@Inject
	private GraphData _graph;
	
	@Inject
	private FleetManager _fleet;
	
	
	private Location p1;
	private Location p2;

	/**
	 * For accessing locations created in the map view workbench plugin.
	 */
	@Inject
	private LocationData locations;

    @Override
    public void initialize() throws Exception
    {
    	// wait until a robot registered at the VMS
    	robotManager.getCount().awaitGreater(0);
    	p1=_locationData.get(1);
    	p2=_locationData.get(4, "P2");
    	 
    }

    @Override
    public void run() throws Exception
    {
    	// get any robot
    	

    	try
    	{
    		_kmp.lock();
    		
//    		virtualLine();
//    		finePositioning();
//		remoteTask();
			graphMotion();
    		
		}
    	finally
    	{
    		_kmp.unlock();
    	}
    }


@Override
public void dispose() throws Exception{
	
	_kmp.unlock();
}


private void virtualLine(){
	
	Pose start = new Pose();
	Pose goal = new Pose();
	start=_kmp.getPose();
	goal = p1.getPose();
	
	VirtualLineMotion moveLine = new VirtualLineMotion(start, goal);
	moveLine.setMaxVelocity(0.2);
	//moveLine.setOrientations(Orientations.FORWARD);
	
	OffStartPoseCondition offstartPosecondition = new OffStartPoseCondition(0.5, Math.toRadians(5));
	ReturnToStartPoseRecover returntoStartPoseRecover = new ReturnToStartPoseRecover();
	
	OffPathCondition offPathCondition = new OffPathCondition(0.3, Math.toRadians(5));
	ReturnToPathRecover returnToPathRecover = new ReturnToPathRecover();
	
	moveLine.recoverWhen(offstartPosecondition, returntoStartPoseRecover);
	moveLine.recoverWhen(offPathCondition, returnToPathRecover);
	
	_kmp.execute(moveLine);
	
	
	
}

private void finePositioning(){
	
	Pose offset = new Pose();
	FineLocalizationRequest fineRequest = new FineLocalizationRequest(p1);
	FineLocalizationContainer fineContainer = _kmp.execute(fineRequest);
	
	
	offset= fineContainer.getRobotPose().invert();
	LOG.info("Offset: "+offset);
	RelativeMotion relMove = new RelativeMotion(offset);
	_kmp.execute(relMove);
	
	
	
}

private void remoteTask(){
	
	
	RemoteTaskId remoteId;
	String taskId = "application.RobotApplication";
	remoteId = new RemoteTaskId(taskId);
	
	TestClassRemote test = new TestClassRemote("Hi There","2","Hi There");
	
	TaskRequest taskRequest = new TaskRequest(remoteId ,test);
	
	
	//taskRequest.getTaskParameters();

	
	
	
	LOG.info("Starting RemoteTask");
	TaskRequestContainer container= _kmp.execute(taskRequest);
	container.awaitFinished();
	LOG.info("Finished RemoteTask");
}

private void graphMotion(){
	int graphId=6, robotId = 1;
	int starteNod =1, goalNod=2;
	
	TopologyGraph topologyGraph = _graph.get(graphId);
	TopologyNode startNode = topologyGraph.getNode(starteNod);
	TopologyNode goalNode = topologyGraph.getNode(goalNod);
	
	Position start = startNode.getPosition();
	Pose startPose = new Pose(start.getX(),start.getY(),0);
	_kmp.execute(new VirtualLineMotion(_kmp.getPose(),startPose));
	
	ChangeGraphCommand graphCommand = new ChangeGraphCommand(graphId, starteNod);
	_kmp.execute(graphCommand);
	_kmp.unlock();
	
	InstanceFilter instanceFilter = new InstanceFilter(robotId);
	GraphMotion moveGraph = new GraphMotion(topologyGraph, goalNode);
	moveGraph.setResourceFilter(instanceFilter);
	
	_fleet.execute(moveGraph);
	
	
}



}
/*
 * 
 *  NOTE: The robot has to be locked to be commandable.
    		//       Do not forget to unlock the robot in the finally block!
			robot.lock();

			// perform a relative motion async,
			// print some state infos,
			// and fetch the result of the motion
			RelativeMotionContainer cont = robot.executeAsync(new RelativeMotion(0.0, 0.0, 0.0));

			while (!cont.getState().isFinalized())
			{
				LOG.info("Path length to goal: " + cont.getPathLengthToGoal());
				LOG.info("Time to goal: " + cont.getTimeToGoal());
				Thread.sleep(1000);
			}

			NavExecutionResult result = cont.awaitFinalized();

			if (result.hasError())
			{
				LOG.error("The motion finished with an error!", result.getError());
			}
			else if (result.hasTimedOut())
			{
				LOG.error("The motion timed out!");
			}
			else if (result.isCompleted())
			{
				LOG.error("The motion was successful");
			}

			/*
			 * Performs a relative motion to the given pose (x, y, theta).
			 *
			double x = 0.2;
			double y = 0.0;
			double theta = Math.toRadians(5.0);

			robot.execute(new RelativeMotion(new Pose(x, y, theta)));

			/*
			 * Performs a motion on a virtual line from the current pose of
			 * the robot to the given goal pose (x, y, theta). The platform
			 * will rotate while moving on the line to reach the given
			 * orientation theta.
			 * NOTE: such virtual line motions are possible with holonomic devices only!
			 * For differential devices orientations 0 and 180 degree are allowed only.
			 *
			robot.execute(new VirtualLineMotion(robot.getPose(), new Pose(x, y, theta)));

			Location start = locations.get(robot.getMapInfo().getId(), "startLocation");
			Location end = locations.get(robot.getMapInfo().getId(), "endLocation");

			if((start != null) && (end != null))
			{
				/*
				 * Performs a motion on a virtual line from the start location to
				 * the goal location. The allowed orientation of the platform relative
				 * to the line is 0 degree and the platform is not allowed to rotate
				 * while moving on the line. If this is not the case the motion will fail.
				 *
				robot.execute(new VirtualLineMotion(start, end)
						.setOrientations(Orientations.LENGTHWISE)
						.setOrientationMode(OrientationMode.FIXED));

				/*
				 * Performs the same motion as before but with recover actions.
				 * Since an OffPathCondition and a ReturnToPathRecover action are
				 * parameterized, the robot will return to the path in case it
				 * leaves the virtual line up to a distance of 0.1 m and 180�.
				 *
				robot.execute(new VirtualLineMotion(start, end)
						.setOrientations(Orientations.LENGTHWISE)
						.setOrientationMode(OrientationMode.FIXED)
						.recoverWhen(new OffPathCondition(0.1, Math.toRadians(180)), new ReturnToPathRecover()));
 * 
 * 
 * 
 * 
 * 
 */
