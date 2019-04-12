package tool;

import javax.inject.Inject;

import com.kuka.common.ThreadUtil;
import com.kuka.generated.ioAccess.FestoControllerIOGroup;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.task.ITaskLogger;

public class GripperFesto extends Tool
{
    @Inject private FestoControllerIOGroup _gripperFunction;
    @Inject private ITaskLogger _logger;
    public boolean gripped = false;

    @Inject
    public GripperFesto(String name)
    {
        super(name);
    }

    public void setHMI()
    {
        boolean HMIon = _gripperFunction.getIn_1_5_DriveControlByFCT_MMI();
        _logger.info("setHMI() = " + HMIon);
        if (HMIon == false)
        {
            _gripperFunction.setOut_1_5_HMIAccessLock(false);
        }
        else
        {
            _gripperFunction.setOut_1_5_HMIAccessLock(true);
        }
    }

    /* Start homing */
    public void startHoming()
    {
        if (_gripperFunction.getIn_2_7_AxisIsReferenced() == false)
        {
            _gripperFunction.setOut_1_3_ResetFault(true);
            ThreadUtil.milliSleep(200);
            do
            {
                _logger.info("Wait for homing");
                prepareHoming();
                ThreadUtil.milliSleep(200);
                _gripperFunction.setOut_2_2_StartHoming(true);
                ThreadUtil.milliSleep(500);
            }
            while (_gripperFunction.getIn_2_4_AxisIsMoving() == false);

            while (_gripperFunction.getIn_2_4_AxisIsMoving() == true)
            {
                ThreadUtil.milliSleep(5000);
                _logger.info("Gripper moves");
            }
            if (_gripperFunction.getIn_2_2_MotionComplete() == true
                    && _gripperFunction.getIn_2_7_AxisIsReferenced() == true)
            {
                _logger.info("Homing was successful");
            }
            _gripperFunction.setOut_2_2_StartHoming(false);
        }
    }

    public void driveToPosition(int pos)
    {
        while (_gripperFunction.getIn_1_3_Fault() == true || _gripperFunction.getIn_1_2_Warning() == true)
        {
            _gripperFunction.setOut_1_3_ResetFault(true);
            _logger.info("Gripper has disorder before open().");
        }
        _gripperFunction.setOut_1_1_Stop(false);

        boolean state = true;
        _gripperFunction.setOut_1_3_ResetFault(true);
        preparePos(pos);
        ThreadUtil.milliSleep(50);
        while (state)
        {
            state = moving(Integer.toString(pos));
            if (state)
            {
                _gripperFunction.setOut_1_3_ResetFault(true);
                ThreadUtil.milliSleep(200);
            }
        }
        _logger.info("curPos = " + currentPosition());
    }

    public void prepareSensitiveMove(int force)
    {
        _gripperFunction.setOut_1_3_ResetFault(true);

        setSetpointCurrent(force);

        _gripperFunction.setOut_1_0_DriveEnable(true);
        _gripperFunction.setOut_1_1_Stop(true);
        _gripperFunction.setOut_1_3_ResetFault(false);
        _gripperFunction.setOut_1_6_SelectOperatingMode(true);
        _gripperFunction.setOut_1_7_SelectOperatingMode(false);

        _gripperFunction.setOut_2_0_Halt(true);
        _gripperFunction.setOut_2_1_Start(false);
        _gripperFunction.setOut_2_2_StartHoming(false);
        _gripperFunction.setOut_2_3_JogPos(false);
        _gripperFunction.setOut_2_6_Clear(false);
        _gripperFunction.setOut_2_7(false);

        _gripperFunction.setOut_3_0_Abs(false);
        _gripperFunction.setOut_3_1_ControlMode(true);
        _gripperFunction.setOut_3_2_ControlMode(false);
        _gripperFunction.setOut_3_3(false);
        _gripperFunction.setOut_3_5_StrokeLimitNotActive(false);
        _gripperFunction.setOut_3_6(false);
        _gripperFunction.setOut_3_7(false);
    }

    /* Close the gripper in force mode */
    public boolean moveSensitive(int origPos)
    {
        gripped = false;
        while (_gripperFunction.getIn_1_3_Fault() == true || _gripperFunction.getIn_1_2_Warning() == true)
        {
            _gripperFunction.setOut_1_3_ResetFault(true);
            _logger.info("Gripper has disorder before close().");
        }
        _gripperFunction.setOut_2_1_Start(true);

        ThreadUtil.milliSleep(50);

        int i = 0;
        while (_gripperFunction.getIn_2_4_AxisIsMoving() == false && i < 4)
        {
            ThreadUtil.milliSleep(100);
            i++;
        }
        while (_gripperFunction.getIn_2_4_AxisIsMoving() == true
                || _gripperFunction.getIn_2_2_MotionComplete() == false)
        {
            ThreadUtil.milliSleep(100);
        }
        if (_gripperFunction.getIn_2_2_MotionComplete() == true
                && _gripperFunction.getIn_3_5_StrokeLimitReached() == false)
        {
            gripped = true;
            _logger.info("Grip was successful");
        }
        if (_gripperFunction.getIn_2_2_MotionComplete() == true
                && _gripperFunction.getIn_3_5_StrokeLimitReached() == true)
        {
            gripped = false;
            ThreadUtil.milliSleep(200);
            driveToPosition(origPos);
        }
        _gripperFunction.setOut_1_1_Stop(false);
        _gripperFunction.setOut_2_1_Start(false);

        _logger.info("curPos = " + currentPosition());

        return gripped;
    }

    private void setSetpointCurrent(int force)
    {
        // Positive direction (gripper closes), for negative direction use twos complement    	

        int fillBits = 0x00;
        if (force < 0)
            fillBits = 0xFF;

        _gripperFunction.setOut_Force_0(force);
        _gripperFunction.setOut_Force_1(fillBits);
        _gripperFunction.setOut_Force_2(fillBits);
        _gripperFunction.setOut_Force_3(fillBits);
    }

    private void preparePos(int pos)
    {
        _gripperFunction.setOut_1_0_DriveEnable(true);
        _gripperFunction.setOut_1_1_Stop(true);
        _gripperFunction.setOut_1_3_ResetFault(false);
        _gripperFunction.setOut_1_6_SelectOperatingMode(false);
        _gripperFunction.setOut_2_0_Halt(true);
        _gripperFunction.setOut_2_1_Start(false);
        _gripperFunction.setOut_2_6_Clear(false);
        // Choose position set: 001b = 1d

        switch (pos)
        {
        case 1:
            _gripperFunction.setOut_3_0_Abs(true);
            _gripperFunction.setOut_3_1_ControlMode(false);
            _gripperFunction.setOut_3_2_ControlMode(false);
            break;
        case 2:
            _gripperFunction.setOut_3_0_Abs(false);
            _gripperFunction.setOut_3_1_ControlMode(true);
            _gripperFunction.setOut_3_2_ControlMode(false);
            break;
        case 3:
            _gripperFunction.setOut_3_0_Abs(true);
            _gripperFunction.setOut_3_1_ControlMode(true);
            _gripperFunction.setOut_3_2_ControlMode(false);
            break;
        }
    }

    private boolean moving(String pos)
    {
        boolean state = false;
        _gripperFunction.setOut_2_1_Start(true);
        ThreadUtil.milliSleep(500);
        while (_gripperFunction.getIn_2_1_AcknowledgeStart() == true
                && _gripperFunction.getIn_2_4_AxisIsMoving() == true)
        {
            _logger.info("Gripper moves to position " + pos + ".");
            ThreadUtil.milliSleep(400);
        }
        ThreadUtil.milliSleep(100);
        if (_gripperFunction.getIn_2_2_MotionComplete() == true && _gripperFunction.getIn_2_4_AxisIsMoving() == false)
        {
            _logger.info("Move to position " + pos + " was successful.");
        }
        else
        {
            state = true;
            _logger.info("Position " + pos + " was not reached.");
        }
        _gripperFunction.setOut_2_1_Start(false);
        return state;
    }

    private void prepareHoming()
    {
        _gripperFunction.setOut_1_0_DriveEnable(true);
        _gripperFunction.setOut_1_1_Stop(true);
        _gripperFunction.setOut_1_3_ResetFault(false);
        _gripperFunction.setOut_1_6_SelectOperatingMode(false);
        _gripperFunction.setOut_1_7_SelectOperatingMode(false);
        _gripperFunction.setOut_2_0_Halt(true);
        _gripperFunction.setOut_2_1_Start(false);
        _gripperFunction.setOut_2_2_StartHoming(false);
        _gripperFunction.setOut_2_6_Clear(false);
    }

    /* Calculate current gripper position 1) Convert format from little endian to big endian 2) Convert from increments
     * to mm */
    public double currentPosition()
    {
        int curPos = _gripperFunction.getIn_CurrentPosition().intValue();
        return Integer.reverseBytes(curPos) * 6.47 / 79348;
    }
}
