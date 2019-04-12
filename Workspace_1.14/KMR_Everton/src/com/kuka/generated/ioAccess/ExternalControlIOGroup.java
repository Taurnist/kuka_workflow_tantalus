package com.kuka.generated.ioAccess;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.ioModel.AbstractIOGroup;
import com.kuka.roboticsAPI.ioModel.IOTypes;

/**
 * Automatically generated class to abstract I/O access to I/O group <b>ExternalControl</b>.<br>
 * <i>Please, do not modify!</i>
 * <p>
 * <b>I/O group description:</b><br>
 * Contains the Automatic Extern Interface and some basic information between Sunrise and PLC
 */
@Singleton
public class ExternalControlIOGroup extends AbstractIOGroup
{
	/**
	 * Constructor to create an instance of class 'ExternalControl'.<br>
	 * <i>This constructor is automatically generated. Please, do not modify!</i>
	 *
	 * @param controller
	 *            the controller, which has access to the I/O group 'ExternalControl'
	 */
	@Inject
	public ExternalControlIOGroup(Controller controller)
	{
		super(controller, "ExternalControl");

		addDigitalOutput("AutExt_Active", IOTypes.BOOLEAN, 1);
		addDigitalOutput("AutExt_AppR2S", IOTypes.BOOLEAN, 1);
		addDigitalOutput("DefaultApp_Error", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Station_Error", IOTypes.BOOLEAN, 1);
		addInput("App_Start", IOTypes.BOOLEAN, 1);
		addInput("App_Enable", IOTypes.BOOLEAN, 1);
		addDigitalOutput("PlatformReleased", IOTypes.BOOLEAN, 1);
		addInput("MotionEnable", IOTypes.BOOLEAN, 1);
		addDigitalOutput("EnableNavigationControl", IOTypes.BOOLEAN, 1);
		addDigitalOutput("ChargingRelayEnabled", IOTypes.BOOLEAN, 1);
	}

	/**
	 * Gets the value of the <b>digital output '<i>AutExt_Active</i>'</b>.<br>
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
	 * @return current value of the digital output 'AutExt_Active'
	 */
	public boolean getAutExt_Active()
	{
		return getBooleanIOValue("AutExt_Active", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>AutExt_Active</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'AutExt_Active'
	 */
	public void setAutExt_Active(java.lang.Boolean value)
	{
		setDigitalOutput("AutExt_Active", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>AutExt_AppR2S</i>'</b>.<br>
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
	 * @return current value of the digital output 'AutExt_AppR2S'
	 */
	public boolean getAutExt_AppR2S()
	{
		return getBooleanIOValue("AutExt_AppR2S", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>AutExt_AppR2S</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'AutExt_AppR2S'
	 */
	public void setAutExt_AppR2S(java.lang.Boolean value)
	{
		setDigitalOutput("AutExt_AppR2S", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>DefaultApp_Error</i>'</b>.<br>
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
	 * @return current value of the digital output 'DefaultApp_Error'
	 */
	public boolean getDefaultApp_Error()
	{
		return getBooleanIOValue("DefaultApp_Error", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>DefaultApp_Error</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'DefaultApp_Error'
	 */
	public void setDefaultApp_Error(java.lang.Boolean value)
	{
		setDigitalOutput("DefaultApp_Error", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Station_Error</i>'</b>.<br>
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
	 * @return current value of the digital output 'Station_Error'
	 */
	public boolean getStation_Error()
	{
		return getBooleanIOValue("Station_Error", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Station_Error</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'Station_Error'
	 */
	public void setStation_Error(java.lang.Boolean value)
	{
		setDigitalOutput("Station_Error", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>App_Start</i>'</b>.<br>
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
	 * @return current value of the digital input 'App_Start'
	 */
	public boolean getApp_Start()
	{
		return getBooleanIOValue("App_Start", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>App_Enable</i>'</b>.<br>
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
	 * @return current value of the digital input 'App_Enable'
	 */
	public boolean getApp_Enable()
	{
		return getBooleanIOValue("App_Enable", false);
	}

	/**
	 * Gets the value of the <b>digital output '<i>PlatformReleased</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * This state is "true" when the Vehicle is unlocked.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'PlatformReleased'
	 */
	public boolean getPlatformReleased()
	{
		return getBooleanIOValue("PlatformReleased", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>PlatformReleased</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * This state is "true" when the Vehicle is unlocked.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'PlatformReleased'
	 */
	public void setPlatformReleased(java.lang.Boolean value)
	{
		setDigitalOutput("PlatformReleased", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>MotionEnable</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Is "true" when the Safety of the Vehicle is Ok.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'MotionEnable'
	 */
	public boolean getMotionEnable()
	{
		return getBooleanIOValue("MotionEnable", false);
	}

	/**
	 * Gets the value of the <b>digital output '<i>EnableNavigationControl</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Navigation can control the AGV in operating mode T1/T2/KRF
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'EnableNavigationControl'
	 */
	public boolean getEnableNavigationControl()
	{
		return getBooleanIOValue("EnableNavigationControl", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>EnableNavigationControl</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Navigation can control the AGV in operating mode T1/T2/KRF
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'EnableNavigationControl'
	 */
	public void setEnableNavigationControl(java.lang.Boolean value)
	{
		setDigitalOutput("EnableNavigationControl", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>ChargingRelayEnabled</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Enables the charging relay.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'ChargingRelayEnabled'
	 */
	public boolean getChargingRelayEnabled()
	{
		return getBooleanIOValue("ChargingRelayEnabled", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ChargingRelayEnabled</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Enables the charging relay.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'ChargingRelayEnabled'
	 */
	public void setChargingRelayEnabled(java.lang.Boolean value)
	{
		setDigitalOutput("ChargingRelayEnabled", value);
	}

}
