package com.kuka.generated.ioAccess;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.ioModel.AbstractIOGroup;
import com.kuka.roboticsAPI.ioModel.IOTypes;

/**
 * Automatically generated class to abstract I/O access to I/O group <b>MobilePlatformStateSignals</b>.<br>
 * <i>Please, do not modify!</i>
 * <p>
 * <b>I/O group description:</b><br>
 * State signals of a mobile platform
 */
@Singleton
public class MobilePlatformStateSignalsIOGroup extends AbstractIOGroup
{
	/**
	 * Constructor to create an instance of class 'MobilePlatformStateSignals'.<br>
	 * <i>This constructor is automatically generated. Please, do not modify!</i>
	 *
	 * @param controller
	 *            the controller, which has access to the I/O group 'MobilePlatformStateSignals'
	 */
	@Inject
	public MobilePlatformStateSignalsIOGroup(Controller controller)
	{
		super(controller, "MobilePlatformStateSignals");

		addDigitalOutput("BatteryStateOK", IOTypes.BOOLEAN, 1);
		addDigitalOutput("BatteryStateUnknown", IOTypes.BOOLEAN, 1);
		addDigitalOutput("ApplicationStateError", IOTypes.BOOLEAN, 1);
		addDigitalOutput("ApplicationStateWarning", IOTypes.BOOLEAN, 1);
		addDigitalOutput("ApplicationStateOK", IOTypes.BOOLEAN, 1);
		addDigitalOutput("ApplicationStateUnknown", IOTypes.BOOLEAN, 1);
		addDigitalOutput("BatteryStateWarning", IOTypes.BOOLEAN, 1);
		addDigitalOutput("BatteryStateError", IOTypes.BOOLEAN, 1);
		addDigitalOutput("DeviceStateUnknown", IOTypes.BOOLEAN, 1);
		addDigitalOutput("DeviceStateOK", IOTypes.BOOLEAN, 1);
		addDigitalOutput("DeviceStateWarning", IOTypes.BOOLEAN, 1);
		addDigitalOutput("DeviceStateError", IOTypes.BOOLEAN, 1);
		addDigitalOutput("FieldBusStateUnknown", IOTypes.BOOLEAN, 1);
		addDigitalOutput("FieldBusStateOK", IOTypes.BOOLEAN, 1);
		addDigitalOutput("SafetyStateUnknown", IOTypes.BOOLEAN, 1);
		addDigitalOutput("FieldBusStateError", IOTypes.BOOLEAN, 1);
		addDigitalOutput("FieldBusStateWarning", IOTypes.BOOLEAN, 1);
		addDigitalOutput("SafetyStateOK", IOTypes.BOOLEAN, 1);
		addDigitalOutput("SafetyStateWarning", IOTypes.BOOLEAN, 1);
		addDigitalOutput("SafetyStateError", IOTypes.BOOLEAN, 1);
		addDigitalOutput("StationStateUnknown", IOTypes.BOOLEAN, 1);
		addDigitalOutput("StationStateOK", IOTypes.BOOLEAN, 1);
		addDigitalOutput("StationStateWarning", IOTypes.BOOLEAN, 1);
		addDigitalOutput("StationStateError", IOTypes.BOOLEAN, 1);
	}

	/**
	 * Gets the value of the <b>digital output '<i>BatteryStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the battery state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'BatteryStateOK'
	 */
	public boolean getBatteryStateOK()
	{
		return getBooleanIOValue("BatteryStateOK", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>BatteryStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the battery state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'BatteryStateOK'
	 */
	public void setBatteryStateOK(java.lang.Boolean value)
	{
		setDigitalOutput("BatteryStateOK", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>BatteryStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the battery state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'BatteryStateUnknown'
	 */
	public boolean getBatteryStateUnknown()
	{
		return getBooleanIOValue("BatteryStateUnknown", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>BatteryStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the battery state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'BatteryStateUnknown'
	 */
	public void setBatteryStateUnknown(java.lang.Boolean value)
	{
		setDigitalOutput("BatteryStateUnknown", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>ApplicationStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the application state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'ApplicationStateError'
	 */
	public boolean getApplicationStateError()
	{
		return getBooleanIOValue("ApplicationStateError", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ApplicationStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the application state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'ApplicationStateError'
	 */
	public void setApplicationStateError(java.lang.Boolean value)
	{
		setDigitalOutput("ApplicationStateError", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>ApplicationStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the application state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'ApplicationStateWarning'
	 */
	public boolean getApplicationStateWarning()
	{
		return getBooleanIOValue("ApplicationStateWarning", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ApplicationStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the application state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'ApplicationStateWarning'
	 */
	public void setApplicationStateWarning(java.lang.Boolean value)
	{
		setDigitalOutput("ApplicationStateWarning", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>ApplicationStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the application state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'ApplicationStateOK'
	 */
	public boolean getApplicationStateOK()
	{
		return getBooleanIOValue("ApplicationStateOK", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ApplicationStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the application state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'ApplicationStateOK'
	 */
	public void setApplicationStateOK(java.lang.Boolean value)
	{
		setDigitalOutput("ApplicationStateOK", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>ApplicationStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the application state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'ApplicationStateUnknown'
	 */
	public boolean getApplicationStateUnknown()
	{
		return getBooleanIOValue("ApplicationStateUnknown", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ApplicationStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the application state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'ApplicationStateUnknown'
	 */
	public void setApplicationStateUnknown(java.lang.Boolean value)
	{
		setDigitalOutput("ApplicationStateUnknown", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>BatteryStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the battery state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'BatteryStateWarning'
	 */
	public boolean getBatteryStateWarning()
	{
		return getBooleanIOValue("BatteryStateWarning", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>BatteryStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the battery state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'BatteryStateWarning'
	 */
	public void setBatteryStateWarning(java.lang.Boolean value)
	{
		setDigitalOutput("BatteryStateWarning", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>BatteryStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the battery state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'BatteryStateError'
	 */
	public boolean getBatteryStateError()
	{
		return getBooleanIOValue("BatteryStateError", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>BatteryStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the battery state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'BatteryStateError'
	 */
	public void setBatteryStateError(java.lang.Boolean value)
	{
		setDigitalOutput("BatteryStateError", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>DeviceStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the device state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'DeviceStateUnknown'
	 */
	public boolean getDeviceStateUnknown()
	{
		return getBooleanIOValue("DeviceStateUnknown", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>DeviceStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the device state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'DeviceStateUnknown'
	 */
	public void setDeviceStateUnknown(java.lang.Boolean value)
	{
		setDigitalOutput("DeviceStateUnknown", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>DeviceStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the device state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'DeviceStateOK'
	 */
	public boolean getDeviceStateOK()
	{
		return getBooleanIOValue("DeviceStateOK", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>DeviceStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the device state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'DeviceStateOK'
	 */
	public void setDeviceStateOK(java.lang.Boolean value)
	{
		setDigitalOutput("DeviceStateOK", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>DeviceStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the device state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'DeviceStateWarning'
	 */
	public boolean getDeviceStateWarning()
	{
		return getBooleanIOValue("DeviceStateWarning", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>DeviceStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the device state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'DeviceStateWarning'
	 */
	public void setDeviceStateWarning(java.lang.Boolean value)
	{
		setDigitalOutput("DeviceStateWarning", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>DeviceStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the device state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'DeviceStateError'
	 */
	public boolean getDeviceStateError()
	{
		return getBooleanIOValue("DeviceStateError", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>DeviceStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the device state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'DeviceStateError'
	 */
	public void setDeviceStateError(java.lang.Boolean value)
	{
		setDigitalOutput("DeviceStateError", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>FieldBusStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the field bus state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'FieldBusStateUnknown'
	 */
	public boolean getFieldBusStateUnknown()
	{
		return getBooleanIOValue("FieldBusStateUnknown", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>FieldBusStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the field bus state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'FieldBusStateUnknown'
	 */
	public void setFieldBusStateUnknown(java.lang.Boolean value)
	{
		setDigitalOutput("FieldBusStateUnknown", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>FieldBusStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the field bus state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'FieldBusStateOK'
	 */
	public boolean getFieldBusStateOK()
	{
		return getBooleanIOValue("FieldBusStateOK", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>FieldBusStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the field bus state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'FieldBusStateOK'
	 */
	public void setFieldBusStateOK(java.lang.Boolean value)
	{
		setDigitalOutput("FieldBusStateOK", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>SafetyStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the safety state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'SafetyStateUnknown'
	 */
	public boolean getSafetyStateUnknown()
	{
		return getBooleanIOValue("SafetyStateUnknown", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>SafetyStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the safety state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'SafetyStateUnknown'
	 */
	public void setSafetyStateUnknown(java.lang.Boolean value)
	{
		setDigitalOutput("SafetyStateUnknown", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>FieldBusStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the field bus state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'FieldBusStateError'
	 */
	public boolean getFieldBusStateError()
	{
		return getBooleanIOValue("FieldBusStateError", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>FieldBusStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the field bus state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'FieldBusStateError'
	 */
	public void setFieldBusStateError(java.lang.Boolean value)
	{
		setDigitalOutput("FieldBusStateError", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>FieldBusStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the field bus state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'FieldBusStateWarning'
	 */
	public boolean getFieldBusStateWarning()
	{
		return getBooleanIOValue("FieldBusStateWarning", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>FieldBusStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the field bus state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'FieldBusStateWarning'
	 */
	public void setFieldBusStateWarning(java.lang.Boolean value)
	{
		setDigitalOutput("FieldBusStateWarning", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>SafetyStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the safety state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'SafetyStateOK'
	 */
	public boolean getSafetyStateOK()
	{
		return getBooleanIOValue("SafetyStateOK", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>SafetyStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the safety state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'SafetyStateOK'
	 */
	public void setSafetyStateOK(java.lang.Boolean value)
	{
		setDigitalOutput("SafetyStateOK", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>SafetyStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the safety state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'SafetyStateWarning'
	 */
	public boolean getSafetyStateWarning()
	{
		return getBooleanIOValue("SafetyStateWarning", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>SafetyStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the safety state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'SafetyStateWarning'
	 */
	public void setSafetyStateWarning(java.lang.Boolean value)
	{
		setDigitalOutput("SafetyStateWarning", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>SafetyStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the safety state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'SafetyStateError'
	 */
	public boolean getSafetyStateError()
	{
		return getBooleanIOValue("SafetyStateError", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>SafetyStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the safety state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'SafetyStateError'
	 */
	public void setSafetyStateError(java.lang.Boolean value)
	{
		setDigitalOutput("SafetyStateError", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>StationStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the station state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'StationStateUnknown'
	 */
	public boolean getStationStateUnknown()
	{
		return getBooleanIOValue("StationStateUnknown", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>StationStateUnknown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the station state is unknown.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'StationStateUnknown'
	 */
	public void setStationStateUnknown(java.lang.Boolean value)
	{
		setDigitalOutput("StationStateUnknown", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>StationStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the station state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'StationStateOK'
	 */
	public boolean getStationStateOK()
	{
		return getBooleanIOValue("StationStateOK", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>StationStateOK</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the station state is OK.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'StationStateOK'
	 */
	public void setStationStateOK(java.lang.Boolean value)
	{
		setDigitalOutput("StationStateOK", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>StationStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the station state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'StationStateWarning'
	 */
	public boolean getStationStateWarning()
	{
		return getBooleanIOValue("StationStateWarning", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>StationStateWarning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the station state is warning.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'StationStateWarning'
	 */
	public void setStationStateWarning(java.lang.Boolean value)
	{
		setDigitalOutput("StationStateWarning", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>StationStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the station state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'StationStateError'
	 */
	public boolean getStationStateError()
	{
		return getBooleanIOValue("StationStateError", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>StationStateError</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * True (1) if the station state is error.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'StationStateError'
	 */
	public void setStationStateError(java.lang.Boolean value)
	{
		setDigitalOutput("StationStateError", value);
	}

}
