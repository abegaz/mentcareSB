package com.MentCareSB.controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
	public void logOut(ActionEvent event) throws IOException
	{
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("/com/MentCareSB/view/LogIn.fxml"));
		Parent tableViewParent1 = loader1.load();
		Scene tableViewScene1 = new Scene(tableViewParent1);
		Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		window1.setScene(tableViewScene1);
		window1.show();
	}
}
