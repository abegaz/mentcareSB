package com.MentCareSB.model;

import application.Adapter;
import javafx.beans.property.SimpleStringProperty;

public class Appointment
{
	public static void addAppointment(String assignedClinic, String time, String assignedDoctor, String assignedPatient)
	{
		String fieldNames = "assignedClinic, time, assignedDoctor, assignedPatient";
		String values = "'"+assignedClinic+"', '"+time+"', '"+assignedDoctor+"', '"+assignedPatient+"'";
		Adapter.addRow("appointment", fieldNames, values);
	}
	public static void deleteAppointment(String idNumber)
	{
		Adapter.deleteRow("appointment", idNumber);
	}
	private final SimpleStringProperty  idNumberColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  assignedClinicColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  timeColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  assignedDoctorColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  assignedPatientColumnProperty = new SimpleStringProperty("");

	public Appointment()
	{
	}
	public Appointment(String idNumber, String assignedClinic, String time, String assignedDoctor, String assignedPatient)
	{
		setIdNumberColumn(idNumber);
		setAssignedClinicColumn(assignedClinic);
		setTimeColumn(time);
		setAssignedDoctorColumn(assignedDoctor);
		setAssignedPatientColumn(assignedPatient);
	}
	public String getIdNumberColumn()
	{
		return idNumberColumnProperty.get();
	}
	public void setIdNumberColumn(String idNumber)
	{
		idNumberColumnProperty.set(idNumber);
	}
	public String getAssignedClinicColumn()
	{
		return assignedClinicColumnProperty.get();
	}
	public void setAssignedClinicColumn(String assignedClinic)
	{
		assignedClinicColumnProperty.set(assignedClinic);
	}
	public String getTimeColumn()
	{
		return timeColumnProperty.get();
	}
	public void setTimeColumn(String time)
	{
		timeColumnProperty.set(time);
	}
	public String getAssignedDoctorColumn()
	{
		return assignedDoctorColumnProperty.get();
	}
	public void setAssignedDoctorColumn(String assignedDoctor)
	{
		assignedDoctorColumnProperty.set(assignedDoctor);
	}
	public String getAssignedPatientColumn()
	{
		return assignedPatientColumnProperty.get();
	}
	public void setAssignedPatientColumn(String assignedPatient)
	{
		assignedPatientColumnProperty.set(assignedPatient);
	}
}
