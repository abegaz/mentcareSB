package com.MentCareSB.model;

import application.Adapter;
import javafx.beans.property.SimpleStringProperty;

public class Nurse 
{
	public static void addNurse(String firstName, String lastName, String assignedClinic, String username, String password)
	{
		String fieldNames = "firstName, lastName, assignedClinic, userName, password";
		String values = "'"+firstName+"', '"+lastName+"', '"+assignedClinic+"', '"+username+"', '"+password+"'";
		Adapter.addRow("nurse", fieldNames, values);
	}
	private final SimpleStringProperty  idNumberColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  firstNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  lastNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  assignedClinicColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  userNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  passwordColumnProperty = new SimpleStringProperty("");

	public Nurse()
	{
	}
	public Nurse(String idNumber, String firstName, String lastName, String assignedClinic, String userName, String password)
	{
		setIdNumberColumn(idNumber);
		setFirstNameColumn(firstName);
		setLastNameColumn(lastName);
		setAssignedClinicColumn(assignedClinic);
		setUserNameColumn(userName);
		setPasswordColumn(password);
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
	public String getAssignedClinicColumn()
	{
		return assignedClinicColumnProperty.get();
	}
	public void setAssignedClinicColumn(String assignedClinic)
	{
		assignedClinicColumnProperty.set(assignedClinic);
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
