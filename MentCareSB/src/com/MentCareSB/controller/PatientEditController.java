package com.MentCareSB.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.MentCareSB.model.Patient;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatientEditController implements Initializable
{
	@FXML TextField firstNameField, lastNameField, illnessField, userNameField, passwordField;
	@FXML Label assignedClinicLabel;
	@FXML TextArea notesField;
	private String patientIdNumber;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		patientIdNumber = DoctorViewController.getPatientIdNumber();
		ResultSet patientResultSetRow = Adapter.getResultSetRow("patient", "idNumber", patientIdNumber);
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
			firstNameField.setText(firstName);
			lastNameField.setText(lastName);
			illnessField.setText(illness);
			userNameField.setText(userName);
			passwordField.setText(password);
			assignedClinicLabel.setText(assignedClinic);
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
		
		Patient.updatePatient(patientIdNumber, firstName, lastName, illness, userName, password, notes);
		
	
		
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