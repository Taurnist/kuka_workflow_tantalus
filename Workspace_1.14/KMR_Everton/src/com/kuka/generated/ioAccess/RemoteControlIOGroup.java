package com.kuka.generated.ioAccess;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.ioModel.AbstractIOGroup;
import com.kuka.roboticsAPI.ioModel.IOTypes;

/**
 * Automatically generated class to abstract I/O access to I/O group <b>RemoteControl</b>.<br>
 * <i>Please, do not modify!</i>
 * <p>
 * <b>I/O group description:</b><br>
 * IO Group for Remote Control via JAY
 */
@Singleton
public class RemoteControlIOGroup extends AbstractIOGroup
{
	/**
	 * Constructor to create an instance of class 'RemoteControl'.<br>
	 * <i>This constructor is automatically generated. Please, do not modify!</i>
	 *
	 * @param controller
	 *            the controller, which has access to the I/O group 'RemoteControl'
	 */
	@Inject
	public RemoteControlIOGroup(Controller controller)
	{
		super(controller, "RemoteControl");

		addInput("Connected", IOTypes.BOOLEAN, 1);
		addInput("Enable", IOTypes.BOOLEAN, 1);
		addInput("Mode", IOTypes.UNSIGNED_INTEGER, 3);
		addInput("X", IOTypes.INTEGER, 16);
		addInput("Y", IOTypes.INTEGER, 16);
		addInput("Z", IOTypes.INTEGER, 16);
		addInput("Theta", IOTypes.INTEGER, 16);
		addInput("ActivateAcousticWarningSignal", IOTypes.BOOLEAN, 1);
		addInput("DeactivateProtectionField", IOTypes.BOOLEAN, 1);
		addDigitalOutput("ProtectionFieldDeactivated", IOTypes.BOOLEAN, 1);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Connected</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True if the JAY Controller is connected.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Connected'
	 */
	public boolean getConnected()
	{
		return getBooleanIOValue("Connected", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Enable</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True if controlling via JAY is enabled.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Enable'
	 */
	public boolean getEnable()
	{
		return getBooleanIOValue("Enable", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Mode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Rotary switch for mode 1-3.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 7]
	 *
	 * @return current value of the digital input 'Mode'
	 */
	public java.lang.Integer getMode()
	{
		return getNumberIOValue("Mode", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>X</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * X joystick IO value.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-32768; 32767]
	 *
	 * @return current value of the digital input 'X'
	 */
	public java.lang.Integer getX()
	{
		return getNumberIOValue("X", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Y</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Y joystick IO value.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-32768; 32767]
	 *
	 * @return current value of the digital input 'Y'
	 */
	public java.lang.Integer getY()
	{
		return getNumberIOValue("Y", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Z</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Z joystick IO value.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-32768; 32767]
	 *
	 * @return current value of the digital input 'Z'
	 */
	public java.lang.Integer getZ()
	{
		return getNumberIOValue("Z", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Theta</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Rotary joystick IO value.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-32768; 32767]
	 *
	 * @return current value of the digital input 'Theta'
	 */
	public java.lang.Integer getTheta()
	{
		return getNumberIOValue("Theta", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>ActivateAcousticWarningSignal</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Boolean signal to activate the vehicle integrated honk.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'ActivateAcousticWarningSignal'
	 */
	public boolean getActivateAcousticWarningSignal()
	{
		return getBooleanIOValue("ActivateAcousticWarningSignal", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>DeactivateProtectionField</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Deactivates the protection field monitoring.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'DeactivateProtectionField'
	 */
	public boolean getDeactivateProtectionField()
	{
		return getBooleanIOValue("DeactivateProtectionField", false);
	}

	/**
	 * Gets the value of the <b>digital output '<i>ProtectionFieldDeactivated</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Forwarding of ProtectionField state to the Safe-PLC via ProfiNet.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'ProtectionFieldDeactivated'
	 */
	public boolean getProtectionFieldDeactivated()
	{
		return getBooleanIOValue("ProtectionFieldDeactivated", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ProtectionFieldDeactivated</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Forwarding of ProtectionField state to the Safe-PLC via ProfiNet.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'ProtectionFieldDeactivated'
	 */
	public void setProtectionFieldDeactivated(java.lang.Boolean value)
	{
		setDigitalOutput("ProtectionFieldDeactivated", value);
	}

}
