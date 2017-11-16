package com.MentCareSB.model;
import application.Adapter;

public class Doctor 
{
	public static void doctor(String firstName, String lastName, String specialty, String clinic, String username, String password)
	{
		String fieldNames = "firstName, lastName, specialty, clinic, username, password";
		String values = "'"+firstName+"', '"+lastName+"', '"+specialty+"', '"+clinic+"', '"+username+"', '"+password+"'";
		Adapter.addRow("doctor", fieldNames, values);
	}
	

}
