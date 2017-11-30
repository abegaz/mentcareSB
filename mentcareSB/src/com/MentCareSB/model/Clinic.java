package com.MentCareSB.model;
import application.Adapter;
import javafx.beans.property.SimpleStringProperty;

public class Clinic 
{
	public static void clinic(String name, String location, String userName, String password)
	{
		String fieldNames = "name, location, userName, password";
		String values = "'"+name+"', '"+location+"', '"+userName+"', '"+password+"'";
		Adapter.addRow("clinic", fieldNames, values);
	}
	private final SimpleStringProperty  idNumberColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  nameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  locationColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  userNameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  passwordColumnProperty = new SimpleStringProperty("");

	public Clinic()
	{
	}
	public Clinic(String idNumber, String name, String location, String userName, String password)
	{
		setIdNumberColumn(idNumber);
		setNameColumn(name);
		setLocationColumn(location);
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
	public String getNameColumn()
	{
		return nameColumnProperty.get();
	}
	public void setNameColumn(String name)
	{
		nameColumnProperty.set(name);
	}
	public String getLocationColumn()
	{
		return locationColumnProperty.get();
	}
	public void setLocationColumn(String location)
	{
		locationColumnProperty.set(location);
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
