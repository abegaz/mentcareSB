package com.MentCareSB.controller;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Adapter;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PatientViewController
{
	private String firstName, lastName, assignedDoc,illness;
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
			    illness = rs.getString("illness");
 
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

			patientName.setText(firstName);
			patientLastName.setText(lastName);
			doctorName.setText(assignedDoc);
			illnessLabel.setText("Illness : " +illness);

	}
}
