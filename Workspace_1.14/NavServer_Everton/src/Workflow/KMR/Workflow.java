package Workflow.KMR;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import application.TestClassRemote;

import com.kuka.nav.robot.SafetyState;
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
public class Workflow extends MOAGraphMotionTasks {
	boolean TaskStopped;
	KMRTCPClient client;
	Date lastCheck;
	Date lastTaskStarted = new Date();

	// The following 2 options allow the robot to drive around on its on when not
	// been sent on a task for 5 minutes,
	protected boolean DriveAround = false;
	protected int delayBeforeDriving = 2; // minutes delay before driving after
																				// having received the last task.
	protected int delayBeforeCheckingCharge = 5;
	ScheduledExecutorService executer_service;

	public Workflow() {
		super();  
		

	} 
	
	private void UpdateStatus() {
		// TODO Auto-generated method stub
		client.setPose(_kmp.getPose());
	
		client.setSafetyState(_kmp.getSafetyState());
		client.SetBatteryState(_kmp.getBatteryState());

	}

	@Override
	public void run() throws Exception {
		LOG.info("workflow created");
		try {
			
			KMRRobotStatus Status = new KMRRobotStatus(new Date(),
				RobotStatusEnum.Commandable, null, "", null,0,null, false, SafetyState.SAFE);
			client = new KMRTCPClient(Status, "172.31.1.70", 666,LOG);
			Random rand = new Random();
			
			AllowCharge();
			
			executer_service = Executors.newSingleThreadScheduledExecutor();
	    executer_service.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run() {
	            try {

	               		UpdateStatus();

	            } catch (Exception e) {
	               LOG.error(e.getMessage());
	            }
	        }

					
	    }, 0, 1000, TimeUnit.MILLISECONDS);
			TaskStopped = false;
			while (!TaskStopped) {
				if (CheckWithArmWhetherBaseNeedsCharge())// NEED TO CHARGE)
				{
					client.UpdateLastCommandedLocation(Location.Unknown);
					System.out.println("WARNING CHARGE NOT FULL");
					client.setCharging(true);
					GoAndFinePosition(PhotocatNodeIdFinePositioning.ChargingStation);
					finePositioning("plate_pos");

					ChargeRobotOnStation();
					lastCheck = null; // Force robot to check charge sufficiently charged
					finePositioning("plate_pre_pos");
					client.UpdateLastCommandedLocation(Location.ChargingStation);
					client.setCharging(false);
				} else {
					System.out.println("CheckforTasks");
					KMRTask task = (KMRTask) client.getNewTask();
					
					if (task == null) {
						Date now = new Date();
						now.setTime(now.getTime() - delayBeforeDriving * 60 * 1000);

						if (DriveAround && lastTaskStarted.before(now)) {
							
							client.UpdateLastCommandedLocation(Location.Unknown);
							
							Location[] locations = new Location[]{
									Location.Quantos,
//									Location.Photocat,
											Location.LiquidHandlingSystem,
											Location.GC, 
											Location.Crossroads,
											Location.Sonicator,
											Location.Cartridge,
											Location.DryingStation,
											Location.InputStation,
											Location.ChargingStation,
											Location.Vibratory_Photocat};

							int random_number = (int) Math
								.floor((rand.nextDouble() * locations.length));
							PhotocatNodeIdFinePositioning loc = PhotocatNodeIdFinePositioning
								.getPhotocatNodeIdFinePositionFromLocation(locations[random_number]);
							GoAndFinePosition(loc);

						} else {
							Thread.sleep(1 * 1000);
						}
					} else {
						boolean result = false;
						String error = null;
						switch (task.getType()) {
							case TransportKMRAndStartLBR : {
								LOG.info("TransportKMRAndStartLBR:" + task.getLocation());

								try {
									client.UpdateLastCommandedLocation(Location.Unknown);
									GoAndFinePosition(PhotocatNodeIdFinePositioning
										.getPhotocatNodeIdFinePositionFromLocation(task
											.getLocation()));
									client.UpdateLastCommandedLocation(task.getLocation());
									StartLBRR();
									result = true;
								} catch (InterruptedException e) {
									error = "IntereruptedException while graph and fine";
								} catch (LockException e) {
									error = "LockException while graph and fine";
								} catch (Exception e) {
									error = e.getMessage();
								}
								break;
							}
							case TransportKMR : {
								LOG.info("TransportKMR");
								try {
									client.UpdateLastCommandedLocation(Location.Unknown);
									GoAndFinePosition(PhotocatNodeIdFinePositioning
										.getPhotocatNodeIdFinePositionFromLocation(task
											.getLocation()));
									client.UpdateLastCommandedLocation(task.getLocation());
									result = true;
								} catch (InterruptedException e) {
									error = "IntereruptedException while graph and fine";
								} catch (LockException e) {
									error = "LockException while graph and fine";
								}
								break;
							}
							case TestProgram : {
								LOG.info("Starting Test Program");
								try {
									String taskId = "application.SixPointBaseCalibration";
									RemoteTaskId remoteId = new RemoteTaskId(taskId);
									com.kuka.nav.task.remote.TaskRequest taskRequest = new com.kuka.nav.task.remote.TaskRequest(
										remoteId);

									TaskRequestContainer container = _kmp
										.execute(taskRequest);
									container.awaitFinished();
									result = true;
								} catch (Exception e) {
									result = false;
								}

								LOG.info("Finished Test Program");

								break;
							}
							case AllowCharge : {
								LOG.info("AllowCharge");
								AllowCharge();
								result = true;
								break;
							}
							case DisallowCharge : {
								LOG.info("DisallowCharge");
								try {
									String taskId = "application.DisallowCharging";
									RemoteTaskId remoteId = new RemoteTaskId(taskId);
									com.kuka.nav.task.remote.TaskRequest taskRequest = new com.kuka.nav.task.remote.TaskRequest(
										remoteId);
									TaskRequestContainer container = _kmp
										.execute(taskRequest);
									container.awaitFinished();
									result = true;
									} catch (Exception e) {
									result = false;
								}

								break;
							}
							case StartLBR : {
								LOG.info("StartLBR");
								try {
									StartLBRR();
									lastCheck = null;
									result = true;
								} catch (Exception e) {
									result = false;
									error = e.getMessage();
								}
								break;
							}
							case SetToDrivePos : {
								LOG.info("StartLBR");
								try {
									String taskId = "application.PutArmInDrivePosition";
									// String taskId = "application.LBRWorkflowSuper";
									RemoteTaskId remoteId = new RemoteTaskId(taskId);

									com.kuka.nav.task.remote.TaskRequest taskRequest = new com.kuka.nav.task.remote.TaskRequest(
										remoteId);

									LOG.info("Starting LBR");

									TaskRequestContainer container = _kmp
										.execute(taskRequest);
									container.awaitFinished();
									result = true;
								} catch (Exception e) {
									result = false;
								}
								LOG.info("Finished LBR task");

								break;
							}
							default :
								throw new NotImplementedException();
						}
						lastTaskStarted = new Date();
						client.updateStatus(result, error);
					}
				}

			}
		} catch (InterruptedException e) {

		} finally {
			if (client != null) {
				client.Destruct();
				client = null;
			}
		}
	}
	private void AllowCharge() {
		String taskId = "application.AllowCharging";
		RemoteTaskId remoteId = new RemoteTaskId(taskId);
		com.kuka.nav.task.remote.TaskRequest taskRequest = new com.kuka.nav.task.remote.TaskRequest(
			remoteId);
		TaskRequestContainer container = _kmp.execute(taskRequest);
		container.awaitFinished();
	}

	private void StartLBRR() {
		String taskId;
		switch (client.getLastCommandedLocation()) {
			case LiquidHandlingSystem :
				taskId = "Workflow.LBR.LiquidStationWorkflow";
				break;
			case GC :
				taskId = "Workflow.LBR.GCWorkflow";
				break;
			case Photocat :
				taskId = "Workflow.LBR.PhotoWorkflow";
				break;
			case Quantos :
				taskId = "Workflow.LBR.QuantosWorkflow";
				break;
			case Cartridge :
				taskId = "Workflow.LBR.CartridgeWorkflow";
				break;
			case Sonicator :
				taskId = "Workflow.LBR.SonicatorWorkflow";
				break;
			case DryingStation :
				taskId = "Workflow.LBR.DryingStationWorkflow";
				break;
			case InputStation :
				taskId = "Workflow.LBR.InputWorkflow";
				break;
			case Vibratory_Photocat :
				taskId = "Workflow.LBR.VibratoryPhotolysisWorkflow";
				break;
			case InputStation2 :
				taskId = "Workflow.LBR.InputTwoWorkflow";
				break;
			case InputStation3 :
				taskId = "Workflow.LBR.InputThreeWorkflow";
				break;	
			default :
				taskId = "Workflow.LBR.WorkflowSuper";
				break;
		}

		RemoteTaskId remoteId = new RemoteTaskId(taskId);
		Location stat;

		stat = client.getLastCommandedLocation();

		com.kuka.nav.task.remote.TaskRequest taskRequest = new com.kuka.nav.task.remote.TaskRequest(
			remoteId, stat);

		LOG.info("Starting LBR");

		TaskRequestContainer container = _kmp.execute(taskRequest);
		container.awaitFinished();

		LOG.info("Finished LBR task");
	}

	private void ChargeRobotOnStation() throws Exception {
		LOG.info("Charging Robot");
		String taskId = "BatteryCheck.ChargeRobot";

		RemoteTaskId remoteId = new RemoteTaskId(taskId);

		com.kuka.nav.task.remote.TaskRequest taskRequest = new com.kuka.nav.task.remote.TaskRequest(
			remoteId);

		TaskRequestContainer container = _kmp.execute(taskRequest);
		container.awaitFinished();
		if (container.hasError()) {
			throw new Exception("Did not charge succesfully");
		}
	}

	private boolean CheckWithArmWhetherBaseNeedsCharge() throws Exception {
		//eventhough we can check chage with kmp.getBatteryState()
		//We want to be able to tune this from the smartpad
		//So process stuff is to be used.
		
		if (!(lastCheck == null || lastCheck.getTime() < (new Date())
			.getTime() - (delayBeforeCheckingCharge * 60 * 1000))) {
			return false;
		} 

		lastCheck = new Date();

		LOG.info("Check Battery Levels to check need charge");
		String taskId = "BatteryCheck.BatteryNeedsFullCharge";;
		RemoteTaskId remoteId = new RemoteTaskId(taskId);

		com.kuka.nav.task.remote.TaskRequest taskRequest = new com.kuka.nav.task.remote.TaskRequest(
			remoteId);

		TaskRequestContainer container = _kmp.execute(taskRequest);
		container.awaitFinished();

		Throwable throwable = container.getError();
		LOG.info(throwable.toString());

		// BIG HACK WARNING - JUST EXPLOITING ERROR PASSING AS A WAY TO SENT DATA
		// DATA
		if (throwable instanceof IllegalStateException) // IllegalStateException
																										// means robot NEEDS CHARGE
		{
			return true;
		} else if (throwable instanceof StackOverflowError) { // STACK OVERFLOW
																													// MEANS ITS CHARGED
			return false;
		} else {
			throw new Exception("Can not measure battery data");
		}
	}

	@Override
	public void dispose() throws Exception {
		TaskStopped = true;
		if (client != null) {
			client.Destruct();
			client = null;
		}
		if(executer_service != null)
		{
			executer_service.shutdownNow();
			executer_service = null;
		}
		super.dispose();

		
	}

}
