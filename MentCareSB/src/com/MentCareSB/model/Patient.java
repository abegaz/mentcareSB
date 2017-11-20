package com.MentCareSB.model;

import application.Adapter;
import javafx.beans.property.SimpleStringProperty;

public class Patient
{
	public static void addPatient(String firstName, String lastName, String illness, String assignedDoctor, String userName, String password, String assignedClinic)
	{
		String fieldNames = "firstName, lastName, illness, assignedDoctor, userName, password, assignedClinic";
		String values = "'"+firstName+"', '"+lastName+"', '"+illness+"', '"+assignedDoctor+"', '"+userName+"', '"+password+"', '"+assignedClinic+"'";
		Adapter.addRow("patient", fieldNames, values);
	}
	public static void updatePatient(String idNumber, String firstName, String lastName, String illness, String userName, String password, String notes)
	{
		String values = "firstName = '"+firstName+"', lastName = '"+lastName+"', illness = '"+illness+"', userName = '"+userName+"', password = '"+password+"', notes = '"+notes+"'";
		Adapter.updateRow("patient", values, idNumber);
	}
	public static void changeDoctor(String idNumber, String assignedDoctor)
	{
		String values = "assignedDoctor = '"+assignedDoctor+"'";
		Adapter.updateRow("patient", values, idNumber);
	}
	
	
	
	
	private final SimpleStringProperty  idNumberColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  firstNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  lastNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  illnessColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  assignedDoctorColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  userNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  passwordColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  assignedClinicColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  notesColumnProperty = new SimpleStringProperty("");

	public Patient()
	{
	}
	public Patient(String idNumber, String firstName, String lastName, String illness, String assignedDoctor, String userName, String password, String assignedClinic, String notes)
	{
		setIdNumberColumn(idNumber);
		setFirstNameColumn(firstName);
		setLastNameColumn(lastName);
		setIllnessColumn(illness);
		setAssignedDoctorColumn(assignedDoctor);
		setUserNameColumn(userName);
		setPasswordColumn(password);
		setAssignedClinicColumn(assignedClinic);
		setNotesColumn(notes);
	}
	public String getIdNumberColumn()
	{
		return idNumberColumnProperty.get();
	}
	public void setIdNumberColumn(String idNumber)
	{
		idNumberColumnProperty.set(idNumber);
	}
	public String getFirstNameColumn()
	{
		return firstNameColumnProperty.get();
	}
	public void setFirstNameColumn(String firstName)
	{
		firstNameColumnProperty.set(firstName);
	}
	public String getLastNameColumn()
	{
		return lastNameColumnProperty.get();
	}
	public void setLastNameColumn(String lastName)
	{
		lastNameColumnProperty.set(lastName);
	}
	public String getIllnessColumn()
	{
		return illnessColumnProperty.get();
	}
	public void setIllnessColumn(String illness)
	{
		illnessColumnProperty.set(illness);
	}
	public String getAssignedDoctorColumn()
	{
		return assignedDoctorColumnProperty.get();
	}
	public void setAssignedDoctorColumn(String assignedDoctor)
	{
		assignedDoctorColumnProperty.set(assignedDoctor);
	}
	public String getUserNameColumn() 
	{
		return userNameColumnProperty.get();
	}
	public void setUserNameColumn(String userName)
	{
		userNameColumnProperty.set(userName);
	}
	public String getPasswordColumn()
	{
		return passwordColumnProperty.get();
	}
	public void setPasswordColumn(String password)
	{
		passwordColumnProperty.set(password);
	}
	public String getAssignedClinicColumn()
	{
		return assignedClinicColumnProperty.get();
	}
	public void setAssignedClinicColumn(String assignedClinic)
	{
		assignedClinicColumnProperty.set(assignedClinic);
	}
	public String getNotesColumn()
	{
		return notesColumnProperty.get();
	}
	public void setNotesColumn(String notes)
	{
		notesColumnProperty.set(notes);
	}
}