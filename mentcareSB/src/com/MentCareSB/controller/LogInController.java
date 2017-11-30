package com.MentCareSB.controller;

import java.io.IOException;

import java.sql.*;

import javax.swing.JOptionPane;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController 
{
	@FXML private TextField usernameField,passwordField;
	private static String doctorIdNumber, nurseIdNumber, patientIdNumber;	
	
	public void initialize()
	{
		Adapter.connect();
	}
	public void registerAction(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/MentCareSB/view/DoctorRegistration.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
	public void loginAction(ActionEvent event) throws IOException
	{
		String userName = usernameField.getText();
		String password = passwordField.getText();
		int identity=0;
	
		ResultSet resultSetDoctor = Adapter.getResultSet("doctor");
		ResultSet resultSetNurse = Adapter.getResultSet("nurse");
		ResultSet resultSetPatient = Adapter.getResultSet("patient");
		try
		{
			while (resultSetDoctor.next())
			{
				if (resultSetDoctor.getString("userName").equals(userName) && resultSetDoctor.getString("password").equals(password))
				{
					identity = 1;
					doctorIdNumber = resultSetDoctor.getString("idNumber");
					break;
				}
			}
			
			while (resultSetNurse.next())
			{
				if (resultSetNurse.getString("userName").equals(userName) && resultSetNurse.getString("password").equals(password))
				{
					identity = 2;
					nurseIdNumber = resultSetNurse.getString("idNumber");
					break;
				}
			}		
			while (resultSetPatient.next())
			{
				if (resultSetPatient.getString("userName").equals(userName) && resultSetPatient.getString("password").equals(password))
				{
					identity = 3;
					patientIdNumber = resultSetPatient.getString("idNumber");
					break;
				}
			}
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		if (identity==1)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/com/MentCareSB/view/DoctorView.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		else if (identity==2)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/com/MentCareSB/view/NurseView.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		else if (identity==3)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/com/MentCareSB/view/PatientView.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid username or password", "Access Denied", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static String getDoctorIdNumber()
	{
		return doctorIdNumber;
	}
	public static String getNurseIdNumber()
	{
		return nurseIdNumber;
	}
	public static String getPatientIdNumber()
	{
		return patientIdNumber;
	}
}