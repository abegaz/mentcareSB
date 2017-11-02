package com.MentCareSB.model;

public class Patient
{
	private int id;
	private String firstName;
	private String lastName;
	private String illness;
	private String emailAddress;
	private String phoneNumber;
	private int age;
	private String treatment;

	public Patient()
	{
	}
	public Patient(int id, String firstName, String lastName, String illness, String emailAddress, String phoneNumber,
			int age, String treatment) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.illness = illness;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.treatment = treatment;
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
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
}
