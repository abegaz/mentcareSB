package com.MentCareSB.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.MentCareSB.model.Doctor;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController 
{
	@FXML TextField usernameField,passwordField;
	private static int doctorRow, nurseRow, patientRow;
	
	public void registerAction(ActionEvent event) throws IOException
	{
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("../../../com/MentCareSB/view/DoctorRegistration.fxml"));
		Parent tableViewParent1 = loader1.load();
		Scene tableViewScene1 = new Scene(tableViewParent1);
		Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		window1.setScene(tableViewScene1);
		window1.show();
	}
	public void loginAction(ActionEvent event) throws IOException
	{
		String username = usernameField.getText();
		String password = passwordField.getText();
		int identity=0;
	
		ResultSet resultSetDoctor = Adapter.getResultSet("doctor");
		ResultSet resultSetNurse = Adapter.getResultSet("nurse");
		ResultSet resultSetPatient = Adapter.getResultSet("patient");
		
		patientRow=0;
		try
		{
			while (resultSetDoctor.next())
			{
				if (resultSetDoctor.getString("userName").equals(username) && resultSetDoctor.getString("password").equals(password))
				{
					identity = 1;
					doctorRow = resultSetPatient.getRow();
					break;
				}
			}
			while (resultSetNurse.next())
			{
				if (resultSetNurse.getString("userName").equals(username) && resultSetNurse.getString("password").equals(password))
				{
					identity = 2;
					nurseRow = resultSetPatient.getRow();
					break;
				}
			}		
			while (resultSetPatient.next())
			{
				if (resultSetPatient.getString("userName").equals(username) && resultSetPatient.getString("password").equals(password))
				{
					
					identity = 3;
					patientRow = resultSetPatient.getRow();
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
			loader.setLocation(getClass().getResource("../../../com/MentCareSB/view/DoctorView.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		else if (identity==2)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../../../com/MentCareSB/view/NurseView.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		else if (identity==3)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../../../com/MentCareSB/view/PatientView.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		
		
	}
	public static int getPatientRow()
	{
		return patientRow;
	}
	
}

