package com.MentCareSB.model;

import application.Adapter;
import javafx.beans.property.SimpleStringProperty;

public class Patient
{
	public static void patient(String firstName, String lastName, String illness, String assignedDoctor, String userName, String password)
	{
		String fieldNames = "firstName, lastName, illness, assignedDoctor, userName, password";
		String values = "'"+firstName+"', '"+lastName+"', '"+illness+"', '"+assignedDoctor+"', '"+userName+"', '"+password+"'";
		Adapter.addRow("patient", fieldNames, values);
	}
	private final SimpleStringProperty  firstNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  lastNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  illnessColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  assignedDoctorColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  userNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  passwordColumnProperty = new SimpleStringProperty("");

	public Patient()
	{
	}
	public Patient(String firstName, String lastName, String illness, String assignedDoctor, String userName, String password)
	{
		setFirstNameColumn(firstName);
		setLastNameColumn(lastName);
		setIllnessColumn(illness);
		setAssignedDoctorColumn(assignedDoctor);
		setUserNameColumn(userName);
		setPasswordColumn(password);
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
}