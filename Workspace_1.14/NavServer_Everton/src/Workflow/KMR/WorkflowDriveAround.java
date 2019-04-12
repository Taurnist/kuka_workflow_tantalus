package Workflow.KMR;

import java.util.Date;
import java.util.Random;

import javax.inject.Inject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import application.TestClassRemote;


import com.kuka.nav.task.NavTaskCategory;
import com.kuka.nav.task.remote.RemoteTaskId;
//import com.kuka.nav.task.remote.TaskRequest;

import com.kuka.nav.task.remote.TaskRequestContainer;
import com.kuka.resource.locking.LockException;

import Workflow.KMR.MapConfiguration.PhotocatNodeIdFinePositioning;
import Workflow.KMR.Messages.KMRTask;
import Workflow.KMR.Messages.KMRTaskTypeEnum;
import Workflow.KMR.Util.MOAGraphMotionTasks;
import Workflow.TCP.RobotStatus;
import Workflow.TCP.RobotStatusEnum;

import Workflow.TCP.TaskStatusEnum;
import Workflow.TCP.TaskSuper;
import Workflow.Configuration.*;

@NavTaskCategory
public class WorkflowDriveAround extends Workflow {
	
	public WorkflowDriveAround() {
		super();
		DriveAround = true;
		
	}


}
