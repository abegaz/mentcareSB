package com.MentCareSB.model;
import application.Adapter;
import javafx.beans.property.SimpleStringProperty;

public class Doctor 
{
	public static void addDoctor(String firstName, String lastName, String specialty, String userName, String password)
	{
		String fieldNames = "firstName, lastName, specialty, username, password";
		String values = "'"+firstName+"', '"+lastName+"', '"+specialty+"', '"+userName+"', '"+password+"'";
		Adapter.addRow("doctor", fieldNames, values);
	}
	private final SimpleStringProperty  idNumberColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  firstNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  lastNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  specialtyColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  userNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  passwordColumnProperty = new SimpleStringProperty("");

	public Doctor()
	{
	}
	public Doctor(String idNumber, String firstName, String lastName, String specialty, String userName, String password)
	{
		setIdNumberColumn(idNumber);
		setFirstNameColumn(firstName);
		setLastNameColumn(lastName);
		setSpecialtyColumn(specialty);
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
	public String getSpecialtyColumn()
	{
		return specialtyColumnProperty.get();
	}
	public void setSpecialtyColumn(String specialty)
	{
		specialtyColumnProperty.set(specialty);
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
