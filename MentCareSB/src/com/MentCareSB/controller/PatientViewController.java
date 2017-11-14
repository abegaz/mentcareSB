package com.MentCareSB.controller;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Adapter;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PatientViewController
{
	private String firstName, lastName, assignedDoc, bloodType, email, phone, illness, treatment, prescriptionNameS, height, weight, appointmentDatee;
	int age;
	@FXML private Label patientName, patientLastName, clinicName, doctorName;
	@FXML private Label ageLabel, heightLabel, weightLabel, bloodLabel, illnessLabel, emailLabel, phoneLabel, prescriptionNameLabel, appointmentLabel;

	
	public void initialize() 
	{
		int patientRow = LogInController.getPatientRow();
		ResultSet rs = Adapter.getResultSet("patient");	
		try
		{
				rs.absolute(patientRow);
			    firstName = rs.getString("firstName");
			    lastName = rs.getString("lastName");
			    assignedDoc = rs.getString("assignedDoctor");
			    prescriptionNameS = rs.getString("prescriptionName");
			    age = rs.getInt("age");
			    height = rs.getString("height");
			    weight = rs.getString("weight");
			    bloodType = rs.getString("bloodType");
			    illness = rs.getString("illness");
			    email = rs.getString("emailAddress");
			    phone = rs.getString("phoneNumber");
			    treatment = rs.getString("treatment");
			    appointmentDatee = rs.getString("appointmentDate"); 
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

			patientName.setText(firstName);
			patientLastName.setText(lastName);
			doctorName.setText(assignedDoc);
			ageLabel.setText("Age : " +age);
			heightLabel.setText("Height : " +height);
			weightLabel.setText("Weight : " +weight);
			bloodLabel.setText("Blood type : " +bloodType);
			illnessLabel.setText("Illness : " +illness);
			emailLabel.setText("Email : " +email);
			phoneLabel.setText("Phone : "+phone);
			prescriptionNameLabel.setText(" " +prescriptionNameS);
			appointmentLabel.setText("Your next appointment is on "+appointmentDatee+" with Dr."+assignedDoc);
	}
}
