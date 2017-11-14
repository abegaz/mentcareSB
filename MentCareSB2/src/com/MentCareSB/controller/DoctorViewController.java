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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DoctorViewController
{
	@FXML Label labelInsertDoctorName, labelInsertDoctorSpecialty, labelInsertDoctorClinic;
	@FXML TextField patientUsernameField;

	public void initialize()
	{
		int doctorRow = LogInController.getDoctorRow();
		ResultSet rs = Adapter.getResultSet("doctor");	
		try
		{
			rs.absolute(doctorRow);
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String specialty = rs.getString("specialty");
			String clinic = rs.getString("clinic");
			labelInsertDoctorName.setText(firstName+" "+lastName);
			labelInsertDoctorSpecialty.setText(specialty);
			labelInsertDoctorClinic.setText(clinic);
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
	}
	public void editPatient()
	{
		String username = patientUsernameField.getText();
		
		
	}
	public void deletePatient()
	{
		String username = patientUsernameField.getText();
		Adapter.deleteRow("patient", "userName", username);
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
	
	
	
	
	
	
	
	public void registerNurse(ActionEvent event) throws IOException
	{
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("/com/MentCareSB/view/NurseRegistration.fxml"));
		Parent tableViewParent1 = loader1.load();
		Scene tableViewScene1 = new Scene(tableViewParent1);
		Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		window1.setScene(tableViewScene1);
		window1.show();
	}
	public void registerPatient(ActionEvent event) throws IOException
	{
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("/com/MentCareSB/view/PatientRegistration.fxml"));
		Parent tableViewParent1 = loader1.load();
		Scene tableViewScene1 = new Scene(tableViewParent1);
		Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		window1.setScene(tableViewScene1);
		window1.show();
	}
}	
