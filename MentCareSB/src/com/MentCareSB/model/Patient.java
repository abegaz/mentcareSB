package com.MentCareSB.model;

import application.Adapter;

public class Patient
{
	private String firstName;
	private String lastName;
	private int age;
	private String illness;
	private String treatment;
	private String phoneNumber;
	private String emailAddress;
	
	
	

	public static void patient(String username, String password)
	{
		String injection = "INSERT INTO patient (userName, password) VALUES ('"+username+"', '"+password+"');";
		Adapter.injection(injection);
	}
	
	public Patient(String firstName, String lastName, int age, String illness, String treatment, String phoneNumber, String emailAddress)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.illness = illness;
		this.treatment = treatment;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
}
