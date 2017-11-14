package com.MentCareSB.model;

import application.Adapter;

public class Nurse 
{
	private int id;
	private String firstName;
	private String lastName;
	private String task;
	
	public static void nurse(String username, String password)
	{
		String injection = "INSERT INTO nurse (userName, password) VALUES ('"+username+"', '"+password+"');";
		Adapter.injection(injection);
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
}
