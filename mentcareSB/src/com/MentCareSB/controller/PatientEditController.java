package com.MentCareSB.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import com.MentCareSB.model.*;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PatientEditController implements Initializable
{
	@FXML private TextField firstNameField, lastNameField, illnessField, userNameField, passwordField;
	@FXML private TextArea notesField;
	
	private String patientIdNumber, assignedMedication;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Adapter.connect();
		patientIdNumber = DoctorViewController.getPatientIdNumber();
		ResultSet patientResultSetRow = Adapter.getResultSetRow("patient", patientIdNumber);
		try
		{
			String idNumber = patientResultSetRow.getString("idNumber");
			String firstName = patientResultSetRow.getString("firstName");
			String lastName = patientResultSetRow.getString("lastName");
			String illness = patientResultSetRow.getString("illness");
			String assignedDoctor = patientResultSetRow.getString("assignedDoctor");
			String userName = patientResultSetRow.getString("userName");
			String password = patientResultSetRow.getString("password");
			String assignedClinic = patientResultSetRow.getString("assignedClinic");
			String notes = patientResultSetRow.getString("notes");
			assignedMedication = patientResultSetRow.getString("assignedMedication");
			firstNameField.setText(firstName);
			lastNameField.setText(lastName);
			illnessField.setText(illness);
			userNameField.setText(userName);
			passwordField.setText(password);
			notesField.setText(notes);
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
	}
	
	public void update()
	{
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String illness = illnessField.getText();
		String userName = userNameField.getText();
		String password = passwordField.getText();
		String notes = notesField.getText();
		Patient.updatePatient1(patientIdNumber, firstName, lastName, illness, userName, password, notes);
	}
	public void back(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/MentCareSB/view/DoctorView.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
}