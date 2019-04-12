package com.kuka.generated.ioAccess;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.ioModel.AbstractIOGroup;
import com.kuka.roboticsAPI.ioModel.IOTypes;

/**
 * Automatically generated class to abstract I/O access to I/O group <b>FestoController</b>.<br>
 * <i>Please, do not modify!</i>
 * <p>
 * <b>I/O group description:</b><br>
 * ./.
 */
@Singleton
public class FestoControllerIOGroup extends AbstractIOGroup
{
	/**
	 * Constructor to create an instance of class 'FestoController'.<br>
	 * <i>This constructor is automatically generated. Please, do not modify!</i>
	 *
	 * @param controller
	 *            the controller, which has access to the I/O group 'FestoController'
	 */
	@Inject
	public FestoControllerIOGroup(Controller controller)
	{
		super(controller, "FestoController");

		addDigitalOutput("Out_1_0_DriveEnable", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_1_1_Stop", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_1_3_ResetFault", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_1_6_SelectOperatingMode", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_2_0_Halt", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_2_1_Start", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_2_2_StartHoming", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_2_6_Clear", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_3_0_Abs", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_3_1_ControlMode", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_3_2_ControlMode", IOTypes.BOOLEAN, 1);
		addInput("In_1_1_OperationEnabled", IOTypes.BOOLEAN, 1);
		addInput("In_1_2_Warning", IOTypes.BOOLEAN, 1);
		addInput("In_1_3_Fault", IOTypes.BOOLEAN, 1);
		addInput("In_2_0_Halt", IOTypes.BOOLEAN, 1);
		addInput("In_2_1_AcknowledgeStart", IOTypes.BOOLEAN, 1);
		addInput("In_2_2_MotionComplete", IOTypes.BOOLEAN, 1);
		addInput("In_2_3_AcknowledgeTeach", IOTypes.BOOLEAN, 1);
		addInput("In_2_4_AxisIsMoving", IOTypes.BOOLEAN, 1);
		addInput("In_2_7_AxisIsReferenced", IOTypes.BOOLEAN, 1);
		addInput("In_3_1_ControlModeFeedback", IOTypes.BOOLEAN, 1);
		addInput("In_3_2_ControlModeFeedback", IOTypes.BOOLEAN, 1);
		addInput("In_3_5_StrokeLimitReached", IOTypes.BOOLEAN, 1);
		addInput("In_1_0_DriveEnabled", IOTypes.BOOLEAN, 1);
		addInput("In_1_5_DriveControlByFCT_MMI", IOTypes.BOOLEAN, 1);
		addInput("In_1_6_DisplayOperatingMode", IOTypes.BOOLEAN, 1);
		addInput("In_3_0_Absolute", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_1_5_HMIAccessLock", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_1_7_SelectOperatingMode", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_3_5_StrokeLimitNotActive", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_Force_0", IOTypes.INTEGER, 8);
		addInput("In_4_Force", IOTypes.INTEGER, 8);
		addDigitalOutput("Out_2_3_JogPos", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_2_7", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_3_3", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_3_6", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_3_7", IOTypes.BOOLEAN, 1);
		addDigitalOutput("Out_Force_1", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Out_Force_2", IOTypes.UNSIGNED_INTEGER, 8);
		addDigitalOutput("Out_Force_3", IOTypes.UNSIGNED_INTEGER, 8);
		addInput("In_CurrentPosition", IOTypes.INTEGER, 32);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_1_0_DriveEnable</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 1: Antrieb freigeben
= 0: Antrieb gesperrt
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_1_0_DriveEnable'
	 */
	public boolean getOut_1_0_DriveEnable()
	{
		return getBooleanIOValue("Out_1_0_DriveEnable", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_1_0_DriveEnable</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 1: Antrieb freigeben
= 0: Antrieb gesperrt
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_1_0_DriveEnable'
	 */
	public void setOut_1_0_DriveEnable(java.lang.Boolean value)
	{
		setDigitalOutput("Out_1_0_DriveEnable", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_1_1_Stop</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 1: Betrieb freigeben
= 0: Stop 1 aktiv
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_1_1_Stop'
	 */
	public boolean getOut_1_1_Stop()
	{
		return getBooleanIOValue("Out_1_1_Stop", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_1_1_Stop</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 1: Betrieb freigeben
= 0: Stop 1 aktiv
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_1_1_Stop'
	 */
	public void setOut_1_1_Stop(java.lang.Boolean value)
	{
		setDigitalOutput("Out_1_1_Stop", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_1_3_ResetFault</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit steigender Flanke wird anliegende Störung quittiert und Störwert gelöscht
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_1_3_ResetFault'
	 */
	public boolean getOut_1_3_ResetFault()
	{
		return getBooleanIOValue("Out_1_3_ResetFault", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_1_3_ResetFault</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit steigender Flanke wird anliegende Störung quittiert und Störwert gelöscht
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_1_3_ResetFault'
	 */
	public void setOut_1_3_ResetFault(java.lang.Boolean value)
	{
		setDigitalOutput("Out_1_3_ResetFault", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_1_6_SelectOperatingMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit Out_1_7_SelectOperatingMode
= 00: Satzselektion
= 01: Direktauftrag
= 10: reserviert
= 11: reserviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_1_6_SelectOperatingMode'
	 */
	public boolean getOut_1_6_SelectOperatingMode()
	{
		return getBooleanIOValue("Out_1_6_SelectOperatingMode", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_1_6_SelectOperatingMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit Out_1_7_SelectOperatingMode
= 00: Satzselektion
= 01: Direktauftrag
= 10: reserviert
= 11: reserviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_1_6_SelectOperatingMode'
	 */
	public void setOut_1_6_SelectOperatingMode(java.lang.Boolean value)
	{
		setDigitalOutput("Out_1_6_SelectOperatingMode", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_2_0_Halt</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 1: Halt nicht aktiv
= 0: Halt aktiviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_2_0_Halt'
	 */
	public boolean getOut_2_0_Halt()
	{
		return getBooleanIOValue("Out_2_0_Halt", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_2_0_Halt</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 1: Halt nicht aktiv
= 0: Halt aktiviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_2_0_Halt'
	 */
	public void setOut_2_0_Halt(java.lang.Boolean value)
	{
		setDigitalOutput("Out_2_0_Halt", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_2_1_Start</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Bei steigender Flanke wird Positionierung (Solldaten) gestartet (Satz 0 = Referenzfahrt)
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_2_1_Start'
	 */
	public boolean getOut_2_1_Start()
	{
		return getBooleanIOValue("Out_2_1_Start", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_2_1_Start</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Bei steigender Flanke wird Positionierung (Solldaten) gestartet (Satz 0 = Referenzfahrt)
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_2_1_Start'
	 */
	public void setOut_2_1_Start(java.lang.Boolean value)
	{
		setDigitalOutput("Out_2_1_Start", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_2_2_StartHoming</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Bei steigender Flanke wird Referenzfahrt mit eingestellten Parametern gestartet
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_2_2_StartHoming'
	 */
	public boolean getOut_2_2_StartHoming()
	{
		return getBooleanIOValue("Out_2_2_StartHoming", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_2_2_StartHoming</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Bei steigender Flanke wird Referenzfahrt mit eingestellten Parametern gestartet
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_2_2_StartHoming'
	 */
	public void setOut_2_2_StartHoming(java.lang.Boolean value)
	{
		setDigitalOutput("Out_2_2_StartHoming", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_2_6_Clear</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Im Zustand "Halt" bewirkt steigende Flanke das Löschen des Auftrages und den Übergang in Zustand "Bereit".
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_2_6_Clear'
	 */
	public boolean getOut_2_6_Clear()
	{
		return getBooleanIOValue("Out_2_6_Clear", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_2_6_Clear</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Im Zustand "Halt" bewirkt steigende Flanke das Löschen des Auftrages und den Übergang in Zustand "Bereit".
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_2_6_Clear'
	 */
	public void setOut_2_6_Clear(java.lang.Boolean value)
	{
		setDigitalOutput("Out_2_6_Clear", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_3_0_Abs</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Sollwert ist absolut
= 1: Sollwert ist relativ zum letzten Sollwert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_3_0_Abs'
	 */
	public boolean getOut_3_0_Abs()
	{
		return getBooleanIOValue("Out_3_0_Abs", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_3_0_Abs</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Sollwert ist absolut
= 1: Sollwert ist relativ zum letzten Sollwert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_3_0_Abs'
	 */
	public void setOut_3_0_Abs(java.lang.Boolean value)
	{
		setDigitalOutput("Out_3_0_Abs", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_3_1_ControlMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit Out_3_2_ControlMode:
= 00: Positionierbetrieb
= 01: Kraftbetrieb
= 10: reserviert
= 11: reserviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_3_1_ControlMode'
	 */
	public boolean getOut_3_1_ControlMode()
	{
		return getBooleanIOValue("Out_3_1_ControlMode", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_3_1_ControlMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit Out_3_2_ControlMode:
= 00: Positionierbetrieb
= 01: Kraftbetrieb
= 10: reserviert
= 11: reserviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_3_1_ControlMode'
	 */
	public void setOut_3_1_ControlMode(java.lang.Boolean value)
	{
		setDigitalOutput("Out_3_1_ControlMode", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_3_2_ControlMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit Out_3_1_ControlMode:
= 00: Positionierbetrieb
= 01: Kraftbetrieb
= 10: reserviert
= 11: reserviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_3_2_ControlMode'
	 */
	public boolean getOut_3_2_ControlMode()
	{
		return getBooleanIOValue("Out_3_2_ControlMode", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_3_2_ControlMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit Out_3_1_ControlMode:
= 00: Positionierbetrieb
= 01: Kraftbetrieb
= 10: reserviert
= 11: reserviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_3_2_ControlMode'
	 */
	public void setOut_3_2_ControlMode(java.lang.Boolean value)
	{
		setDigitalOutput("Out_3_2_ControlMode", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_1_1_OperationEnabled</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Stopp aktiv
= 1: Betrieb freigegeben, Positionieren möglich
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_1_1_OperationEnabled'
	 */
	public boolean getIn_1_1_OperationEnabled()
	{
		return getBooleanIOValue("In_1_1_OperationEnabled", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_1_2_Warning</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Warnung liegt nicht an
= 1: Warnung liegt an
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_1_2_Warning'
	 */
	public boolean getIn_1_2_Warning()
	{
		return getBooleanIOValue("In_1_2_Warning", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_1_3_Fault</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Keine Störung
= 1: Störung liegt an bzw. Störreaktion aktiv
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_1_3_Fault'
	 */
	public boolean getIn_1_3_Fault()
	{
		return getBooleanIOValue("In_1_3_Fault", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_2_0_Halt</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Halt aktiv
= 1: Halt nicht aktive, Achse kann bewegt werden
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_2_0_Halt'
	 */
	public boolean getIn_2_0_Halt()
	{
		return getBooleanIOValue("In_2_0_Halt", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_2_1_AcknowledgeStart</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Bereit für Start (Positionieren, Referenzieren)
= 1: Start ausgeführt (Positionieren, Referenzieren)
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_2_1_AcknowledgeStart'
	 */
	public boolean getIn_2_1_AcknowledgeStart()
	{
		return getBooleanIOValue("In_2_1_AcknowledgeStart", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_2_2_MotionComplete</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Fahrauftrag aktiv
= 1: Fahrauftrag abgeschlossen, ggf. mit Fehler
Hinweis: MC wird erstmals nach Einschalten gesetzt
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_2_2_MotionComplete'
	 */
	public boolean getIn_2_2_MotionComplete()
	{
		return getBooleanIOValue("In_2_2_MotionComplete", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_2_3_AcknowledgeTeach</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Bereit für Teachen
= 1: Teachen ausgeführt, Istwert wurde übernommen
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_2_3_AcknowledgeTeach'
	 */
	public boolean getIn_2_3_AcknowledgeTeach()
	{
		return getBooleanIOValue("In_2_3_AcknowledgeTeach", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_2_4_AxisIsMoving</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Geschwindigkeit der Achse < Grenzwert
= 1: Geschwindigkeit der Achse >= Grenzwert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_2_4_AxisIsMoving'
	 */
	public boolean getIn_2_4_AxisIsMoving()
	{
		return getBooleanIOValue("In_2_4_AxisIsMoving", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_2_7_AxisIsReferenced</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Referenzierung muss durchgeführt werden
= 1: Referenzinfo vorhanden, Referenzfahrt muss nicht durchgeführt werden
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_2_7_AxisIsReferenced'
	 */
	public boolean getIn_2_7_AxisIsReferenced()
	{
		return getBooleanIOValue("In_2_7_AxisIsReferenced", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_3_1_ControlModeFeedback</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit In_3_2_ControlModeFeedback:
= 00: Positionierbetrieb
= 01: Kraftbetrieb
= 10: reserviert
= 11: reserviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_3_1_ControlModeFeedback'
	 */
	public boolean getIn_3_1_ControlModeFeedback()
	{
		return getBooleanIOValue("In_3_1_ControlModeFeedback", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_3_2_ControlModeFeedback</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit In_3_1_ControlModeFeedback:
= 00: Positionierbetrieb
= 01: Kraftbetrieb
= 10: reserviert
= 11: reserviert
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_3_2_ControlModeFeedback'
	 */
	public boolean getIn_3_2_ControlModeFeedback()
	{
		return getBooleanIOValue("In_3_2_ControlModeFeedback", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_3_5_StrokeLimitReached</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Hubgrenzwert nicht erreicht
= 1: Hubgrenzwert erreicht
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_3_5_StrokeLimitReached'
	 */
	public boolean getIn_3_5_StrokeLimitReached()
	{
		return getBooleanIOValue("In_3_5_StrokeLimitReached", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_1_0_DriveEnabled</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Antrieb gesperrt, Regler nicht aktiv
= 1: Antrieb freigegeben
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_1_0_DriveEnabled'
	 */
	public boolean getIn_1_0_DriveEnabled()
	{
		return getBooleanIOValue("In_1_0_DriveEnabled", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_1_5_DriveControlByFCT_MMI</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: Gerätesteuerung durch Feldbus
= 1: Gerätesteuerung durch FCT/MMI
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_1_5_DriveControlByFCT_MMI'
	 */
	public boolean getIn_1_5_DriveControlByFCT_MMI()
	{
		return getBooleanIOValue("In_1_5_DriveControlByFCT_MMI", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_1_6_DisplayOperatingMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit In_1_7_DisplayOperatingMode
= 00: Satzselektion
= 10: Direktauftrag
= 10
= 11
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_1_6_DisplayOperatingMode'
	 */
	public boolean getIn_1_6_DisplayOperatingMode()
	{
		return getBooleanIOValue("In_1_6_DisplayOperatingMode", false);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_3_0_Absolute</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Positionsbetrieb
= 0: Sollwert absolut
= 1: Sollwert relativ
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital input 'In_3_0_Absolute'
	 */
	public boolean getIn_3_0_Absolute()
	{
		return getBooleanIOValue("In_3_0_Absolute", false);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_1_5_HMIAccessLock</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: MMI oder FCT dürfen Gerätesteuerung übernehmen
= 1: MMI oder FCT dürfen Gerätesteuerung nicht übernehmen
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_1_5_HMIAccessLock'
	 */
	public boolean getOut_1_5_HMIAccessLock()
	{
		return getBooleanIOValue("Out_1_5_HMIAccessLock", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_1_5_HMIAccessLock</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * = 0: MMI oder FCT dürfen Gerätesteuerung übernehmen
= 1: MMI oder FCT dürfen Gerätesteuerung nicht übernehmen
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_1_5_HMIAccessLock'
	 */
	public void setOut_1_5_HMIAccessLock(java.lang.Boolean value)
	{
		setDigitalOutput("Out_1_5_HMIAccessLock", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_1_7_SelectOperatingMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit Out_1_6_SelectOperatingMode:
= 00: Satzselektion
= 01: Direktauftrag
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @return current value of the digital output 'Out_1_7_SelectOperatingMode'
	 */
	public boolean getOut_1_7_SelectOperatingMode()
	{
		return getBooleanIOValue("Out_1_7_SelectOperatingMode", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_1_7_SelectOperatingMode</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * Mit Out_1_6_SelectOperatingMode:
= 00: Satzselektion
= 01: Direktauftrag
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [false; true]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_1_7_SelectOperatingMode'
	 */
	public void setOut_1_7_SelectOperatingMode(java.lang.Boolean value)
	{
		setDigitalOutput("Out_1_7_SelectOperatingMode", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_3_5_StrokeLimitNotActive</i>'</b>.<br>
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
	 * @return current value of the digital output 'Out_3_5_StrokeLimitNotActive'
	 */
	public boolean getOut_3_5_StrokeLimitNotActive()
	{
		return getBooleanIOValue("Out_3_5_StrokeLimitNotActive", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_3_5_StrokeLimitNotActive</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'Out_3_5_StrokeLimitNotActive'
	 */
	public void setOut_3_5_StrokeLimitNotActive(java.lang.Boolean value)
	{
		setDigitalOutput("Out_3_5_StrokeLimitNotActive", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_Force_0</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-128; 127]
	 *
	 * @return current value of the digital output 'Out_Force_0'
	 */
	public java.lang.Integer getOut_Force_0()
	{
		return getNumberIOValue("Out_Force_0", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_Force_0</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-128; 127]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_Force_0'
	 */
	public void setOut_Force_0(java.lang.Integer value)
	{
		setDigitalOutput("Out_Force_0", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_4_Force</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-128; 127]
	 *
	 * @return current value of the digital input 'In_4_Force'
	 */
	public java.lang.Integer getIn_4_Force()
	{
		return getNumberIOValue("In_4_Force", false).intValue();
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_2_3_JogPos</i>'</b>.<br>
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
	 * @return current value of the digital output 'Out_2_3_JogPos'
	 */
	public boolean getOut_2_3_JogPos()
	{
		return getBooleanIOValue("Out_2_3_JogPos", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_2_3_JogPos</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'Out_2_3_JogPos'
	 */
	public void setOut_2_3_JogPos(java.lang.Boolean value)
	{
		setDigitalOutput("Out_2_3_JogPos", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_2_7</i>'</b>.<br>
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
	 * @return current value of the digital output 'Out_2_7'
	 */
	public boolean getOut_2_7()
	{
		return getBooleanIOValue("Out_2_7", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_2_7</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'Out_2_7'
	 */
	public void setOut_2_7(java.lang.Boolean value)
	{
		setDigitalOutput("Out_2_7", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_3_3</i>'</b>.<br>
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
	 * @return current value of the digital output 'Out_3_3'
	 */
	public boolean getOut_3_3()
	{
		return getBooleanIOValue("Out_3_3", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_3_3</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'Out_3_3'
	 */
	public void setOut_3_3(java.lang.Boolean value)
	{
		setDigitalOutput("Out_3_3", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_3_6</i>'</b>.<br>
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
	 * @return current value of the digital output 'Out_3_6'
	 */
	public boolean getOut_3_6()
	{
		return getBooleanIOValue("Out_3_6", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_3_6</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'Out_3_6'
	 */
	public void setOut_3_6(java.lang.Boolean value)
	{
		setDigitalOutput("Out_3_6", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_3_7</i>'</b>.<br>
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
	 * @return current value of the digital output 'Out_3_7'
	 */
	public boolean getOut_3_7()
	{
		return getBooleanIOValue("Out_3_7", true);
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_3_7</i>'</b>.<br>
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
	 *            the value, which has to be written to the digital output 'Out_3_7'
	 */
	public void setOut_3_7(java.lang.Boolean value)
	{
		setDigitalOutput("Out_3_7", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_Force_1</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Out_Force_1'
	 */
	public java.lang.Integer getOut_Force_1()
	{
		return getNumberIOValue("Out_Force_1", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_Force_1</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_Force_1'
	 */
	public void setOut_Force_1(java.lang.Integer value)
	{
		setDigitalOutput("Out_Force_1", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_Force_2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Out_Force_2'
	 */
	public java.lang.Integer getOut_Force_2()
	{
		return getNumberIOValue("Out_Force_2", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_Force_2</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_Force_2'
	 */
	public void setOut_Force_2(java.lang.Integer value)
	{
		setDigitalOutput("Out_Force_2", value);
	}

	/**
	 * Gets the value of the <b>digital output '<i>Out_Force_3</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @return current value of the digital output 'Out_Force_3'
	 */
	public java.lang.Integer getOut_Force_3()
	{
		return getNumberIOValue("Out_Force_3", true).intValue();
	}

	/**
	 * Sets the value of the <b>digital output '<i>Out_Force_3</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital output
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [0; 255]
	 *
	 * @param value
	 *            the value, which has to be written to the digital output 'Out_Force_3'
	 */
	public void setOut_Force_3(java.lang.Integer value)
	{
		setDigitalOutput("Out_Force_3", value);
	}

	/**
	 * Gets the value of the <b>digital input '<i>In_CurrentPosition</i>'</b>.<br>
	 * <i>This method is automatically generated. Please, do not modify!</i>
	 * <p>
	 * <b>I/O direction and type:</b><br>
	 * digital input
	 * <p>
	 * <b>User description of the I/O:</b><br>
	 * ./.
	 * <p>
	 * <b>Range of the I/O value:</b><br>
	 * [-2147483648; 2147483647]
	 *
	 * @return current value of the digital input 'In_CurrentPosition'
	 */
	public java.lang.Long getIn_CurrentPosition()
	{
		return getNumberIOValue("In_CurrentPosition", false).longValue();
	}

}
