package com.MentCareSB.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.MentCareSB.model.Patient;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PatientSelfEditController implements Initializable
{
	@FXML TextField firstNameField, lastNameField, userNameField, passwordField;
	private String patientIdNumber;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Adapter.connect();
		patientIdNumber = PatientViewController.getPatientIdNumber();
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
			firstNameField.setText(firstName);
			lastNameField.setText(lastName);
			userNameField.setText(userName);
			passwordField.setText(password);
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
		String userName = userNameField.getText();
		String password = passwordField.getText();
		Patient.updatePatient2(patientIdNumber, firstName, lastName, userName, password);
	}
	public void back(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/MentCareSB/view/PatientView.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
}