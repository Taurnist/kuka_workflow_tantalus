package backgroundTask;

import javax.inject.Inject;

import tool.GripperFesto;



import Exception.GripperFailException;

import com.kuka.common.ThreadUtil;
import com.kuka.generated.ioAccess.FestoControllerIOGroup;
import com.kuka.roboticsAPI.applicationModel.tasks.RoboticsAPIBackgroundTask;
import com.kuka.roboticsAPI.applicationModel.tasks.UseRoboticsAPIContext;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKey;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKeyBar;
import com.kuka.roboticsAPI.uiModel.userKeys.IUserKeyListener;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyAlignment;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyEvent;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyLED;
import com.kuka.roboticsAPI.uiModel.userKeys.UserKeyLEDSize;

/**
 * Implementation of a background task.
 * <p>
 * The background task provides a {@link RoboticsAPIBackgroundTask#initialize()} and a
 * {@link RoboticsAPIBackgroundTask#run()} method, which will be called successively in the task lifecycle.<br>
 * The task will terminate automatically after the <code>run</code> method has finished or after stopping the task.
 * <p>
 * <b>It is imperative to call <code>super.dispose()</code> when overriding the {@link RoboticsAPITask#dispose()}
 * method.</b>
 * 
 * @see UseRoboticsAPIContext
 * 
 */
public class UtilBarBackgroundTask2 extends RoboticsAPIBackgroundTask
{
    private IUserKeyBar _utilBar;
    @Inject private GripperFesto _gripper;
    @Inject private FestoControllerIOGroup festoIO;
    private boolean _doLoop;

    @Override
    public void initialize()
    {
        _doLoop = true;
        initUtilBar();
    }

    @Override
    public void run()
    {
        while (_doLoop)
        {
            ThreadUtil.milliSleep(100);
        }
    }

    @Override
    public void dispose()
    {
        _doLoop = false;
        super.dispose();
    }

    private void initUtilBar ()
    {
    	
        _utilBar = getApplicationUI().createUserKeyBar("Gripper2");
        IUserKeyListener listenerWorkpiece = new IUserKeyListener()
        {

            @Override
            public void onKeyEvent(IUserKey key, UserKeyEvent event)
            {
            	if(festoIO.getIn_2_7_AxisIsReferenced()){
	            	
	            	
	
	
	                if (key.getSlot() == 0 && event == UserKeyEvent.KeyDown)
	                {
	                	_gripper.driveToPosition(3);
	                	getApplicationData().getProcessData("gripPos").setValue(3);
	                } else if (key.getSlot() == 1 && event == UserKeyEvent.KeyDown)
	                {
	                	_gripper.prepareSensitiveMove((Integer) getApplicationData().getProcessData("openForceRack").getValue());
	            		_gripper.moveSensitive(3);
	            		
	            	
	                } else if (key.getSlot() == 2 && event == UserKeyEvent.KeyDown)
	              {
	            		_gripper.driveToPosition(1);
	            		getApplicationData().getProcessData("gripPos").setValue(1);
	              } else if (key.getSlot() == 3 && event == UserKeyEvent.KeyDown)
	              {
	            		_gripper.startHoming();
	              		
	              }
            }}
        };

        IUserKey openKey5 = _utilBar.addUserKey(0, listenerWorkpiece, true);
        openKey5.setText(UserKeyAlignment.TopMiddle, "prepare grasp rack");
        openKey5.setLED(UserKeyAlignment.Middle, UserKeyLED.Green,
                UserKeyLEDSize.Small);
        IUserKey openKey1 = _utilBar.addUserKey(1, listenerWorkpiece, true);
        openKey1.setText(UserKeyAlignment.TopMiddle, "grasp rack");
        openKey1.setLED(UserKeyAlignment.Middle, UserKeyLED.Green,
                UserKeyLEDSize.Small);

        IUserKey openKey3 = _utilBar.addUserKey(2, listenerWorkpiece, true);
        openKey3.setText(UserKeyAlignment.TopMiddle, "Prepare for 6P");
        openKey3.setLED(UserKeyAlignment.Middle, UserKeyLED.Green,
                UserKeyLEDSize.Small);
        IUserKey openKey4 = _utilBar.addUserKey(3, listenerWorkpiece, true);
        openKey4.setText(UserKeyAlignment.TopMiddle, "Home");
        openKey4.setLED(UserKeyAlignment.Middle, UserKeyLED.Green,
                UserKeyLEDSize.Small);


        _utilBar.getUserKey(0).setEnabled(true);
        _utilBar.getUserKey(1).setEnabled(true);
        _utilBar.getUserKey(2).setEnabled(true);
        _utilBar.getUserKey(3).setEnabled(true);

        _utilBar.publish();
    }
    
    // Obtains the force from project data
    private int getForce()
    {
    	if (getApplicationData().getProcessData("posDir").getValue())
    		return getApplicationData().getProcessData("closeForce").getValue();
    	
    	return getApplicationData().getProcessData("openForce").getValue();
    }
    
    // Obtains previous pos project data
    private int gripPos()
    {
    	return getApplicationData().getProcessData("gripPos").getValue();
    }
    
    
}
