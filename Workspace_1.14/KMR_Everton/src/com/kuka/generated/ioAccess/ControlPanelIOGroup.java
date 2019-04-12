package com.kuka.generated.ioAccess;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.ioModel.AbstractIOGroup;
import com.kuka.roboticsAPI.ioModel.IOTypes;

/**
 * Automatically generated class to abstract I/O access to I/O group <b>ControlPanel</b>.<br>
 * <i>Please, do not modify!</i>
 * <p>
 * <b>I/O group description:</b><br>
 * Contains necessary information of the control panel (Buttons, Key-Switch, LED-States)
 */
@Singleton
public class ControlPanelIOGroup extends AbstractIOGroup
{
	/**
	 * Constructor to create an instance of class 'ControlPanel'.<br>
	 * <i>This constructor is automatically generated. Please, do not modify!</i>
	 *
	 * @param controller
	 *            the controller, which has access to the I/O group 'ControlPanel'
	 */
	@Inject
	public ControlPanelIOGroup(Controller controller)
	{
		super(controller, "ControlPanel");

		addInput("PLAY_BUTTON", IOTypes.BOOLEAN, 1);
		addInput("STOP_BUTTON", IOTypes.BOOLEAN, 1);
		addInput("KEY_SWITCH_0", IOTypes.BOOLEAN, 1);
		addInput("KEY_SWITCH_1", IOTypes.BOOLEAN, 1);
		addInput("KEY_SWITCH_2", IOTypes.BOOLEAN, 1);
		addDigitalOutput("APP_GREEN", IOTypes.BOOLEAN, 1);
		addDigitalOutput("APP_RED", IOTypes.BOOLEAN, 1);
		addDigitalOutput("APP_BLUE", IOTypes.BOOLEAN, 1);
		addDigitalOutput("SAFETY_BLUE", IOTypes.BOOLEAN, 1);
		addDigitalOutput("SAFETY_RED", IOTypes.BOOLEAN, 1);
		addDigitalOutput("SAFETY_GREEN", IOTypes.BOOLEAN, 1);
		addDigitalOutput("DEVICE_BLUE", IOTypes.BOOLEAN, 1);
		addDigitalOutput("DEVICE_RED", IOTypes.BOOLEAN, 1);
		addDigitalOutput("DEVICE_GREEN", IOTypes.BOOLEAN, 1);
		addDigitalOutput("BATTERY_GREEN", IOTypes.BOOLEAN, 1);
		addDigitalOutput("BATTERY_RED", IOTypes.BOOLEAN, 1);
		addDigitalOutput("BATTERY_BLUE", IOTypes.BOOLEAN, 1);
		addDigitalOutput("PLAY_BUTTON_LED", IOTypes.BOOLEAN, 1);
		addDigitalOutput("STOP_BUTTON_LED", IOTypes.BOOLEAN, 1);
		addDigitalOutput("HONK", IOTypes.BOOLEAN, 1);
		addInput("SOCKET_CONNECTED", IOTypes.BOOLEAN, 1);
	}

	/**
	 * Gets the value of the <b>digital input '<i>PLAY_BUTTON</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'PLAY_BUTTON'
	 */
	public boolean getPLAY_BUTTON()
	{
		return getBooleanIOValue("PLAY_BUTTON", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>STOP_BUTTON</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'STOP_BUTTON'
	 */
	public boolean getSTOP_BUTTON()
	{
		return getBooleanIOValue("STOP_BUTTON", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>KEY_SWITCH_0</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'KEY_SWITCH_0'
	 */
	public boolean getKEY_SWITCH_0()
	{
		return getBooleanIOValue("KEY_SWITCH_0", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>KEY_SWITCH_1</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'KEY_SWITCH_1'
	 */
	public boolean getKEY_SWITCH_1()
	{
		return getBooleanIOValue("KEY_SWITCH_1", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>KEY_SWITCH_2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'KEY_SWITCH_2'
	 */
	public boolean getKEY_SWITCH_2()
	{
		return getBooleanIOValue("KEY_SWITCH_2", false);
	}

	/**
	 * Gets the value of the <b>digital output '<i>APP_GREEN</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'APP_GREEN'
	 */
	public boolean getAPP_GREEN()
	{
		return getBooleanIOValue("APP_GREEN", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>APP_GREEN</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'APP_GREEN'
	 */
	public void setAPP_GREEN(java.lang.Boolean value)
	{
		setDigitalOutput("APP_GREEN", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>APP_RED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'APP_RED'
	 */
	public boolean getAPP_RED()
	{
		return getBooleanIOValue("APP_RED", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>APP_RED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'APP_RED'
	 */
	public void setAPP_RED(java.lang.Boolean value)
	{
		setDigitalOutput("APP_RED", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>APP_BLUE</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'APP_BLUE'
	 */
	public boolean getAPP_BLUE()
	{
		return getBooleanIOValue("APP_BLUE", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>APP_BLUE</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'APP_BLUE'
	 */
	public void setAPP_BLUE(java.lang.Boolean value)
	{
		setDigitalOutput("APP_BLUE", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>SAFETY_BLUE</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'SAFETY_BLUE'
	 */
	public boolean getSAFETY_BLUE()
	{
		return getBooleanIOValue("SAFETY_BLUE", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>SAFETY_BLUE</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'SAFETY_BLUE'
	 */
	public void setSAFETY_BLUE(java.lang.Boolean value)
	{
		setDigitalOutput("SAFETY_BLUE", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>SAFETY_RED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'SAFETY_RED'
	 */
	public boolean getSAFETY_RED()
	{
		return getBooleanIOValue("SAFETY_RED", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>SAFETY_RED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'SAFETY_RED'
	 */
	public void setSAFETY_RED(java.lang.Boolean value)
	{
		setDigitalOutput("SAFETY_RED", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>SAFETY_GREEN</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'SAFETY_GREEN'
	 */
	public boolean getSAFETY_GREEN()
	{
		return getBooleanIOValue("SAFETY_GREEN", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>SAFETY_GREEN</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'SAFETY_GREEN'
	 */
	public void setSAFETY_GREEN(java.lang.Boolean value)
	{
		setDigitalOutput("SAFETY_GREEN", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>DEVICE_BLUE</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'DEVICE_BLUE'
	 */
	public boolean getDEVICE_BLUE()
	{
		return getBooleanIOValue("DEVICE_BLUE", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>DEVICE_BLUE</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'DEVICE_BLUE'
	 */
	public void setDEVICE_BLUE(java.lang.Boolean value)
	{
		setDigitalOutput("DEVICE_BLUE", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>DEVICE_RED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'DEVICE_RED'
	 */
	public boolean getDEVICE_RED()
	{
		return getBooleanIOValue("DEVICE_RED", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>DEVICE_RED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'DEVICE_RED'
	 */
	public void setDEVICE_RED(java.lang.Boolean value)
	{
		setDigitalOutput("DEVICE_RED", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>DEVICE_GREEN</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'DEVICE_GREEN'
	 */
	public boolean getDEVICE_GREEN()
	{
		return getBooleanIOValue("DEVICE_GREEN", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>DEVICE_GREEN</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'DEVICE_GREEN'
	 */
	public void setDEVICE_GREEN(java.lang.Boolean value)
	{
		setDigitalOutput("DEVICE_GREEN", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>BATTERY_GREEN</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'BATTERY_GREEN'
	 */
	public boolean getBATTERY_GREEN()
	{
		return getBooleanIOValue("BATTERY_GREEN", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>BATTERY_GREEN</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'BATTERY_GREEN'
	 */
	public void setBATTERY_GREEN(java.lang.Boolean value)
	{
		setDigitalOutput("BATTERY_GREEN", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>BATTERY_RED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'BATTERY_RED'
	 */
	public boolean getBATTERY_RED()
	{
		return getBooleanIOValue("BATTERY_RED", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>BATTERY_RED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'BATTERY_RED'
	 */
	public void setBATTERY_RED(java.lang.Boolean value)
	{
		setDigitalOutput("BATTERY_RED", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>BATTERY_BLUE</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'BATTERY_BLUE'
	 */
	public boolean getBATTERY_BLUE()
	{
		return getBooleanIOValue("BATTERY_BLUE", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>BATTERY_BLUE</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'BATTERY_BLUE'
	 */
	public void setBATTERY_BLUE(java.lang.Boolean value)
	{
		setDigitalOutput("BATTERY_BLUE", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>PLAY_BUTTON_LED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'PLAY_BUTTON_LED'
	 */
	public boolean getPLAY_BUTTON_LED()
	{
		return getBooleanIOValue("PLAY_BUTTON_LED", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>PLAY_BUTTON_LED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'PLAY_BUTTON_LED'
	 */
	public void setPLAY_BUTTON_LED(java.lang.Boolean value)
	{
		setDigitalOutput("PLAY_BUTTON_LED", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>STOP_BUTTON_LED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'STOP_BUTTON_LED'
	 */
	public boolean getSTOP_BUTTON_LED()
	{
		return getBooleanIOValue("STOP_BUTTON_LED", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>STOP_BUTTON_LED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'STOP_BUTTON_LED'
	 */
	public void setSTOP_BUTTON_LED(java.lang.Boolean value)
	{
		setDigitalOutput("STOP_BUTTON_LED", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>HONK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Activates the vehicle honk
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'HONK'
	 */
	public boolean getHONK()
	{
		return getBooleanIOValue("HONK", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>HONK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Activates the vehicle honk
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'HONK'
	 */
	public void setHONK(java.lang.Boolean value)
	{
		setDigitalOutput("HONK", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>SOCKET_CONNECTED</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True when the socket is connected.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'SOCKET_CONNECTED'
	 */
	public boolean getSOCKET_CONNECTED()
	{
		return getBooleanIOValue("SOCKET_CONNECTED", false);
	}

}
