package com.MentCareSB.model;

import application.Adapter;

public class Nurse 
{
	public static void nurse(String username, String password)
	{
		String fieldNames = "userName, password";
		String values = "'"+username+"', '"+password+"'";
		Adapter.addRow("nurse", fieldNames, values);
	}
}
