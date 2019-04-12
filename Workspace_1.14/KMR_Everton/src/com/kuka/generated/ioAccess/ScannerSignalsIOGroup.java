package com.kuka.generated.ioAccess;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.ioModel.AbstractIOGroup;
import com.kuka.roboticsAPI.ioModel.IOTypes;

/**
 * Automatically generated class to abstract I/O access to I/O group <b>ScannerSignals</b>.<br>
 * <i>Please, do not modify!</i>
 * <p>
 * <b>I/O group description:</b><br>
 * Contains necessary information from the laser scanners of the AGV
 */
@Singleton
public class ScannerSignalsIOGroup extends AbstractIOGroup
{
	/**
	 * Constructor to create an instance of class 'ScannerSignals'.<br>
	 * <i>This constructor is automatically generated. Please, do not modify!</i>
	 *
	 * @param controller
	 *            the controller, which has access to the I/O group 'ScannerSignals'
	 */
	@Inject
	public ScannerSignalsIOGroup(Controller controller)
	{
		super(controller, "ScannerSignals");

		addInput("WarningField_S1", IOTypes.BOOLEAN, 1);
		addInput("WarningField_S2", IOTypes.BOOLEAN, 1);
		addInput("WarningFieldComplete", IOTypes.BOOLEAN, 1);
		addInput("ProtectionField_S1", IOTypes.BOOLEAN, 1);
		addInput("ProtectionField_S2", IOTypes.BOOLEAN, 1);
		addInput("ProtectionFieldComplete", IOTypes.BOOLEAN, 1);
		addInput("ActiveScanField", IOTypes.INTEGER, 16);
		addInput("ProtectionFieldMuted", IOTypes.BOOLEAN, 1);
		addInput("LastActiveScanField", IOTypes.INTEGER, 16);
	}

	/**
	 * Gets the value of the <b>digital input '<i>WarningField_S1</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Warning Field from Scanner B1 is free.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'WarningField_S1'
	 */
	public boolean getWarningField_S1()
	{
		return getBooleanIOValue("WarningField_S1", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>WarningField_S2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Warning Field from Scanner B4 is free.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'WarningField_S2'
	 */
	public boolean getWarningField_S2()
	{
		return getBooleanIOValue("WarningField_S2", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>WarningFieldComplete</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Warning Field from every Scanner is free.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'WarningFieldComplete'
	 */
	public boolean getWarningFieldComplete()
	{
		return getBooleanIOValue("WarningFieldComplete", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>ProtectionField_S1</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Protection Field from Scanner B1 is free
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'ProtectionField_S1'
	 */
	public boolean getProtectionField_S1()
	{
		return getBooleanIOValue("ProtectionField_S1", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>ProtectionField_S2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Protection Field from Scanner B4 is free
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'ProtectionField_S2'
	 */
	public boolean getProtectionField_S2()
	{
		return getBooleanIOValue("ProtectionField_S2", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>ProtectionFieldComplete</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Protection Field from eyery Scanner is free 
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'ProtectionFieldComplete'
	 */
	public boolean getProtectionFieldComplete()
	{
		return getBooleanIOValue("ProtectionFieldComplete", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>ActiveScanField</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Information witch Scanfield number is already active from 0 to 15 (16 Cases).
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-32768; 32767]
	 *
	 * @return current value of the digital input 'ActiveScanField'
	 */
	public java.lang.Integer getActiveScanField()
	{
		return getNumberIOValue("ActiveScanField", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>ProtectionFieldMuted</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Information if the protection fields are active or not. State is "true" means they are inactive.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'ProtectionFieldMuted'
	 */
	public boolean getProtectionFieldMuted()
	{
		return getBooleanIOValue("ProtectionFieldMuted", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>LastActiveScanField</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Number of the last active scanfield before protection field error. 
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-32768; 32767]
	 *
	 * @return current value of the digital input 'LastActiveScanField'
	 */
	public java.lang.Integer getLastActiveScanField()
	{
		return getNumberIOValue("LastActiveScanField", false).intValue();
	}

}
