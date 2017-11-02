package com.MentCareSB.model;

public class Doctor 
{
	private int id;
	private String firstName;
	private String lastName;
	private String specialty;
	private String clinic;
	
	
	
	
	public Doctor(int id, String firstName, String lastName, String specialty, String clinic) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
		this.clinic = clinic;
	}
	public Doctor()
	{
		
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
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
		
			
			
			
			
}
