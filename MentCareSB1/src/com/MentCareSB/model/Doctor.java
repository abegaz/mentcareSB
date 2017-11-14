package com.MentCareSB.model;
import java.sql.Statement;

import application.Adapter;

public class Doctor 
{
	public static void doctor(String username, String password)
	{
		String injection = "INSERT INTO doctor (username, password) VALUES ('"+username+"', '"+password+"');";
		Adapter.injection(injection);
	}
	public static void doctor(String firstName, String lastName, String specialty, String clinic, String username, String password)
	{
		String injection = "INSERT INTO doctor (firstName, lastName, specialty, clinic, username, password) VALUES ('"+firstName+"', '"+lastName+"', '"+specialty+"', '"+clinic+"', '"+username+"', '"+password+"');";
		Adapter.injection(injection);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////// NOT USED METHODS ///////////////////
	
	
	
	public static int getIdNumber(String name)
	{
		int idNumber = Adapter.getInt("doctor", "firstName", name, "idNumber");
		return idNumber;
	}
	public String getFirstName(String name)
	{
		String firstName = Adapter.getString("doctor", "firstName", name, "firstName");
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		String injection = "INSERT INTO doctor (`firstName`) VALUES ('"+firstName+"');";
		Adapter.injection(injection);
	}
	public String getLastName(String name)
	{
		String lastName = Adapter.getString("doctor", "firstName", name, "lastName");
		return lastName;
	}
	public void setLastName(String lastName) {
		String injection = "INSERT INTO doctor (`lastName`) VALUES ('"+lastName+"');";
	}
	public String getSpecialty(String name)
	{
		String specialty = Adapter.getString("doctor", "firstName", name, "specialty");
		return specialty;
	}
	public void setSpecialty(String specialty)
	{
		String injection = "INSERT INTO doctor (`specialty`) VALUES ('"+specialty+"');";
	}
	public String getClinic(String name)
	{
		String clinic = Adapter.getString("doctor", "firstName", name, "clinic");
		return clinic;
	}
	public void setClinic(String clinic)
	{
		String injection = "INSERT INTO doctor (`clinic`) VALUES ('"+clinic+"');";
	}
	public String getUserName(String name)
	{
		String password = Adapter.getString("doctor", "firstName", name, "userName");
		return password;
	}
	public void setUserName(String username) {
		String injection = "INSERT INTO doctor (`userName`) VALUES ('"+username+"');";
	}
	public String getPassword(String name)
	{
		String password = Adapter.getString("doctor", "firstName", name, "password");
		return password;
	}
	public void setPassword(String password) {
		String injection = "INSERT INTO doctor (`password`) VALUES ('"+password+"');";
	}

}
