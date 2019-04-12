package com.kuka.generated.ioAccess;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.ioModel.AbstractIOGroup;
import com.kuka.roboticsAPI.ioModel.IOTypes;

/**
 * Automatically generated class to abstract I/O access to I/O group <b>BMS</b>.<br>
 * <i>Please, do not modify!</i>
 * <p>
 * <b>I/O group description:</b><br>
 * This I/O group contains BMS data like StateOfCharge, Current, Voltage, Diagnostic
 */
@Singleton
public class BMSIOGroup extends AbstractIOGroup
{
	/**
	 * Constructor to create an instance of class 'BMS'.<br>
	 * <i>This constructor is automatically generated. Please, do not modify!</i>
	 *
	 * @param controller
	 *            the controller, which has access to the I/O group 'BMS'
	 */
	@Inject
	public BMSIOGroup(Controller controller)
	{
		super(controller, "BMS");

		addInput("TotalCurrent", IOTypes.INTEGER, 16);
		addInput("StateOfCharge", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("MinCellTemperature", IOTypes.INTEGER, 8);
		addInput("MaxCellTemperature", IOTypes.INTEGER, 8);
		addInput("Protection_Overcurrent", IOTypes.BOOLEAN, 1);
		addInput("TotalVoltage", IOTypes.INTEGER, 16);
		addInput("Protection_Undervoltage", IOTypes.BOOLEAN, 1);
		addInput("Protection_Overvoltage", IOTypes.BOOLEAN, 1);
		addInput("Protection_ComErrorModule", IOTypes.BOOLEAN, 1);
		addInput("Protection_Overtemperature", IOTypes.BOOLEAN, 1);
		addInput("Protection_FaultMinusContactor", IOTypes.BOOLEAN, 1);
		addInput("Protection_FaultPlusContactor", IOTypes.BOOLEAN, 1);
		addInput("Protection_SensorBroken", IOTypes.BOOLEAN, 1);
		addInput("Warning_PreChargingFailure", IOTypes.BOOLEAN, 1);
		addInput("Warning_Overtemperature", IOTypes.BOOLEAN, 1);
		addInput("ReferenceCharge", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Heartbeat", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Warning_Undervoltage", IOTypes.BOOLEAN, 1);
		addInput("AIR_Status", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("StateOfHealth", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Warning_Overvoltage", IOTypes.BOOLEAN, 1);
		addInput("Status", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("Warning_Overcurrent", IOTypes.BOOLEAN, 1);
		addInput("CountdownForShutdown", IOTypes.UNSIGNED_INTEGER, 4);
		addInput("EmergencyShutdown", IOTypes.BOOLEAN, 1);
		addInput("ErrorModuleNumber", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("ErrorCodeNumber", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("NumberOfModules", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("MinCellVoltage", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("MaxCellVoltage", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SW_Version_YearMonth", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SW_Version_Day", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SW_VersionHash_0", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SW_VersionHash_1", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SW_VersionHash_2", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SW_VersionHash_3", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SW_VersionHash_4", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SW_VersionHash_5", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("LoadCycles", IOTypes.UNSIGNED_INTEGER, 16);
		addInput("OperatingHours", IOTypes.UNSIGNED_INTEGER, 16);
		addDigitalOutput("ChargingEnable", IOTypes.BOOLEAN, 1);
		addInput("SerialNumberPart1", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SerialNumberPart2", IOTypes.UNSIGNED_INTEGER, 16);
		addInput("SerialNumberPart3", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("SerialNumberPart4", IOTypes.UNSIGNED_INTEGER, 32);
		addInput("ProductionDate1", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("ProductionDate2", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("IMD_ErrorCode", IOTypes.UNSIGNED_INTEGER, 4);
		addInput("IMD_Value", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("IMD_Error", IOTypes.BOOLEAN, 1);
		addDigitalOutput("OperationModeBattery", IOTypes.BOOLEAN, 1);
		addDigitalOutput("ChargerState", IOTypes.BOOLEAN, 1);
		addInput("Warning_LowVoltageTrigger1", IOTypes.BOOLEAN, 1);
		addInput("Warning_LowVoltageTrigger2", IOTypes.BOOLEAN, 1);
		addDigitalOutput("ChargingRelayEnable", IOTypes.BOOLEAN, 1);
	}

	/**
	 * Gets the value of the <b>digital input '<i>TotalCurrent</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Total current value encoded in 0.01A units
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-32768; 32767]
	 *
	 * @return current value of the digital input 'TotalCurrent'
	 */
	public java.lang.Integer getTotalCurrent()
	{
		return getNumberIOValue("TotalCurrent", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>StateOfCharge</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Estimated state of charge of the battery pack from 0 to 100 Percent.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'StateOfCharge'
	 */
	public java.lang.Integer getStateOfCharge()
	{
		return getNumberIOValue("StateOfCharge", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>MinCellTemperature</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Temperature of cell with the lowest temperature
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-128; 127]
	 *
	 * @return current value of the digital input 'MinCellTemperature'
	 */
	public java.lang.Integer getMinCellTemperature()
	{
		return getNumberIOValue("MinCellTemperature", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>MaxCellTemperature</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Temperature of cell with the highest temperature
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-128; 127]
	 *
	 * @return current value of the digital input 'MaxCellTemperature'
	 */
	public java.lang.Integer getMaxCellTemperature()
	{
		return getNumberIOValue("MaxCellTemperature", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Protection_Overcurrent</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Current is above critical maximum
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Protection_Overcurrent'
	 */
	public boolean getProtection_Overcurrent()
	{
		return getBooleanIOValue("Protection_Overcurrent", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>TotalVoltage</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Total voltage word of all cells added together, encoded in 0.01V
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-32768; 32767]
	 *
	 * @return current value of the digital input 'TotalVoltage'
	 */
	public java.lang.Integer getTotalVoltage()
	{
		return getNumberIOValue("TotalVoltage", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Protection_Undervoltage</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Some cell is below critical minimum voltage
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Protection_Undervoltage'
	 */
	public boolean getProtection_Undervoltage()
	{
		return getBooleanIOValue("Protection_Undervoltage", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Protection_Overvoltage</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Some cell is above critical maximum voltage
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Protection_Overvoltage'
	 */
	public boolean getProtection_Overvoltage()
	{
		return getBooleanIOValue("Protection_Overvoltage", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Protection_ComErrorModule</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * No Communication to one of the Modules
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Protection_ComErrorModule'
	 */
	public boolean getProtection_ComErrorModule()
	{
		return getBooleanIOValue("Protection_ComErrorModule", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Protection_Overtemperature</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Cell temperature exceeds maximum critical temperature setting
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Protection_Overtemperature'
	 */
	public boolean getProtection_Overtemperature()
	{
		return getBooleanIOValue("Protection_Overtemperature", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Protection_FaultMinusContactor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Error with minus Contactor
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Protection_FaultMinusContactor'
	 */
	public boolean getProtection_FaultMinusContactor()
	{
		return getBooleanIOValue("Protection_FaultMinusContactor", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Protection_FaultPlusContactor</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Error with plus Contactor
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Protection_FaultPlusContactor'
	 */
	public boolean getProtection_FaultPlusContactor()
	{
		return getBooleanIOValue("Protection_FaultPlusContactor", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Protection_SensorBroken</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * A Current/Voltage sensor is defective.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Protection_SensorBroken'
	 */
	public boolean getProtection_SensorBroken()
	{
		return getBooleanIOValue("Protection_SensorBroken", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Warning_PreChargingFailure</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * No charging is possible.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Warning_PreChargingFailure'
	 */
	public boolean getWarning_PreChargingFailure()
	{
		return getBooleanIOValue("Warning_PreChargingFailure", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Warning_Overtemperature</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Maximum cell temperature exceeds warning temperature setting.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Warning_Overtemperature'
	 */
	public boolean getWarning_Overtemperature()
	{
		return getBooleanIOValue("Warning_Overtemperature", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>ReferenceCharge</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Reference Charge is neccessary
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'ReferenceCharge'
	 */
	public java.lang.Integer getReferenceCharge()
	{
		return getNumberIOValue("ReferenceCharge", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Heartbeat</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Counts up from 0 to 255 every second.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Heartbeat'
	 */
	public java.lang.Integer getHeartbeat()
	{
		return getNumberIOValue("Heartbeat", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Warning_Undervoltage</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Voltage is below low voltage warning setting.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Warning_Undervoltage'
	 */
	public boolean getWarning_Undervoltage()
	{
		return getBooleanIOValue("Warning_Undervoltage", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>AIR_Status</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = both contactors (+/-) are active
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'AIR_Status'
	 */
	public java.lang.Integer getAIR_Status()
	{
		return getNumberIOValue("AIR_Status", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>StateOfHealth</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'StateOfHealth'
	 */
	public java.lang.Integer getStateOfHealth()
	{
		return getNumberIOValue("StateOfHealth", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Warning_Overvoltage</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Voltage is over high voltage warning setting.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Warning_Overvoltage'
	 */
	public boolean getWarning_Overvoltage()
	{
		return getBooleanIOValue("Warning_Overvoltage", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Status</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Status of the BMS/Battery. [0 = Error, 1 = Charge, 2 = Discharge]
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'Status'
	 */
	public java.lang.Integer getStatus()
	{
		return getNumberIOValue("Status", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>Warning_Overcurrent</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Current is over high current warning setting.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'Warning_Overcurrent'
	 */
	public boolean getWarning_Overcurrent()
	{
		return getBooleanIOValue("Warning_Overcurrent", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>CountdownForShutdown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Timefrom 15sec to 0sec if a Protection Flag switches to 1
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 15]
	 *
	 * @return current value of the digital input 'CountdownForShutdown'
	 */
	public java.lang.Integer getCountdownForShutdown()
	{
		return getNumberIOValue("CountdownForShutdown", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>EmergencyShutdown</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = If the countdown for shutdown reaches zero after a Protection Flag
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'EmergencyShutdown'
	 */
	public boolean getEmergencyShutdown()
	{
		return getBooleanIOValue("EmergencyShutdown", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>ErrorModuleNumber</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Number of the Module within the Battery who has an error. [0 = no module faulty]
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'ErrorModuleNumber'
	 */
	public java.lang.Integer getErrorModuleNumber()
	{
		return getNumberIOValue("ErrorModuleNumber", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>ErrorCodeNumber</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Error number from the Module from 1 to 8. [Explanation within the Can Protocol]
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'ErrorCodeNumber'
	 */
	public java.lang.Integer getErrorCodeNumber()
	{
		return getNumberIOValue("ErrorCodeNumber", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>NumberOfModules</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Identified Number of existing Modules.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'NumberOfModules'
	 */
	public java.lang.Integer getNumberOfModules()
	{
		return getNumberIOValue("NumberOfModules", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>MinCellVoltage</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Voltage of cell with the lowest voltage. [Offset = 1800mV / Factor = 0,1V]
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'MinCellVoltage'
	 */
	public java.lang.Integer getMinCellVoltage()
	{
		return getNumberIOValue("MinCellVoltage", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>MaxCellVoltage</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Voltage of cell with the highest voltage. [Offset = 1800mV / Factor = 10]
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'MaxCellVoltage'
	 */
	public java.lang.Integer getMaxCellVoltage()
	{
		return getNumberIOValue("MaxCellVoltage", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SW_Version_YearMonth</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Date if the Software Version. [Month: Bit 0-3 / Year: Bit 4-7]
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SW_Version_YearMonth'
	 */
	public java.lang.Integer getSW_Version_YearMonth()
	{
		return getNumberIOValue("SW_Version_YearMonth", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SW_Version_Day</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SW_Version_Day'
	 */
	public java.lang.Integer getSW_Version_Day()
	{
		return getNumberIOValue("SW_Version_Day", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SW_VersionHash_0</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SW_VersionHash_0'
	 */
	public java.lang.Integer getSW_VersionHash_0()
	{
		return getNumberIOValue("SW_VersionHash_0", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SW_VersionHash_1</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SW_VersionHash_1'
	 */
	public java.lang.Integer getSW_VersionHash_1()
	{
		return getNumberIOValue("SW_VersionHash_1", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SW_VersionHash_2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SW_VersionHash_2'
	 */
	public java.lang.Integer getSW_VersionHash_2()
	{
		return getNumberIOValue("SW_VersionHash_2", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SW_VersionHash_3</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SW_VersionHash_3'
	 */
	public java.lang.Integer getSW_VersionHash_3()
	{
		return getNumberIOValue("SW_VersionHash_3", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SW_VersionHash_4</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SW_VersionHash_4'
	 */
	public java.lang.Integer getSW_VersionHash_4()
	{
		return getNumberIOValue("SW_VersionHash_4", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SW_VersionHash_5</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SW_VersionHash_5'
	 */
	public java.lang.Integer getSW_VersionHash_5()
	{
		return getNumberIOValue("SW_VersionHash_5", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>LoadCycles</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Number of load cycles of this battery
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 65535]
	 *
	 * @return current value of the digital input 'LoadCycles'
	 */
	public java.lang.Integer getLoadCycles()
	{
		return getNumberIOValue("LoadCycles", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>OperatingHours</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Sum of rounded operating hours
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 65535]
	 *
	 * @return current value of the digital input 'OperatingHours'
	 */
	public java.lang.Integer getOperatingHours()
	{
		return getNumberIOValue("OperatingHours", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital output '<i>ChargingEnable</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Charging is allowed. 0 = Charging is NOT allowed.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'ChargingEnable'
	 */
	public boolean getChargingEnable()
	{
		return getBooleanIOValue("ChargingEnable", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ChargingEnable</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * 1 = Charging is allowed. 0 = Charging is NOT allowed.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'ChargingEnable'
	 */
	public void setChargingEnable(java.lang.Boolean value)
	{
		setDigitalOutput("ChargingEnable", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>SerialNumberPart1</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SerialNumberPart1'
	 */
	public java.lang.Integer getSerialNumberPart1()
	{
		return getNumberIOValue("SerialNumberPart1", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SerialNumberPart2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 65535]
	 *
	 * @return current value of the digital input 'SerialNumberPart2'
	 */
	public java.lang.Integer getSerialNumberPart2()
	{
		return getNumberIOValue("SerialNumberPart2", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SerialNumberPart3</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'SerialNumberPart3'
	 */
	public java.lang.Integer getSerialNumberPart3()
	{
		return getNumberIOValue("SerialNumberPart3", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>SerialNumberPart4</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 4294967295]
	 *
	 * @return current value of the digital input 'SerialNumberPart4'
	 */
	public java.lang.Long getSerialNumberPart4()
	{
		return getNumberIOValue("SerialNumberPart4", false).longValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>ProductionDate1</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'ProductionDate1'
	 */
	public java.lang.Integer getProductionDate1()
	{
		return getNumberIOValue("ProductionDate1", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>ProductionDate2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'ProductionDate2'
	 */
	public java.lang.Integer getProductionDate2()
	{
		return getNumberIOValue("ProductionDate2", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>IMD_ErrorCode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 15]
	 *
	 * @return current value of the digital input 'IMD_ErrorCode'
	 */
	public java.lang.Integer getIMD_ErrorCode()
	{
		return getNumberIOValue("IMD_ErrorCode", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>IMD_Value</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital input 'IMD_Value'
	 */
	public java.lang.Integer getIMD_Value()
	{
		return getNumberIOValue("IMD_Value", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital input '<i>IMD_Error</i>'</b>.<br>
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
	 * @return current value of the digital input 'IMD_Error'
	 */
	public boolean getIMD_Error()
	{
		return getBooleanIOValue("IMD_Error", false);
	}

	/**
	 * Gets the value of the <b>digital output '<i>OperationModeBattery</i>'</b>.<br>
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
	 * @return current value of the digital output 'OperationModeBattery'
	 */
	public boolean getOperationModeBattery()
	{
		return getBooleanIOValue("OperationModeBattery", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>OperationModeBattery</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'OperationModeBattery'
	 */
	public void setOperationModeBattery(java.lang.Boolean value)
	{
		setDigitalOutput("OperationModeBattery", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>ChargerState</i>'</b>.<br>
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
	 * @return current value of the digital output 'ChargerState'
	 */
	public boolean getChargerState()
	{
		return getBooleanIOValue("ChargerState", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ChargerState</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'ChargerState'
	 */
	public void setChargerState(java.lang.Boolean value)
	{
		setDigitalOutput("ChargerState", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Warning_LowVoltageTrigger1</i>'</b>.<br>
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
	 * @return current value of the digital input 'Warning_LowVoltageTrigger1'
	 */
	public boolean getWarning_LowVoltageTrigger1()
	{
		return getBooleanIOValue("Warning_LowVoltageTrigger1", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>Warning_LowVoltageTrigger2</i>'</b>.<br>
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
	 * @return current value of the digital input 'Warning_LowVoltageTrigger2'
	 */
	public boolean getWarning_LowVoltageTrigger2()
	{
		return getBooleanIOValue("Warning_LowVoltageTrigger2", false);
	}

	/**
	 * Gets the value of the <b>digital output '<i>ChargingRelayEnable</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Switches the ChargingRelay to enable/disable of the vehicles bottom mounted charging contacts.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'ChargingRelayEnable'
	 */
	public boolean getChargingRelayEnable()
	{
		return getBooleanIOValue("ChargingRelayEnable", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>ChargingRelayEnable</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Switches the ChargingRelay to enable/disable of the vehicles bottom mounted charging contacts.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'ChargingRelayEnable'
	 */
	public void setChargingRelayEnable(java.lang.Boolean value)
	{
		setDigitalOutput("ChargingRelayEnable", value);
	}

}
