package com.MentCareSB.controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.MentCareSB.model.Patient;

import application.Adapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientViewController
{
	private String firstName, lastName, assignedDoc,illness;
	int age;
	@FXML private Label patientName, patientLastName, clinicName, doctorName, ageLabel;
	@FXML private Label heightLabel, weightLabel, bloodLabel, illnessLabel, emailLabel, phoneLabel, prescriptionNameLabel, appointmentLabel;
	
	public void initialize() 
	{
		ResultSet patientResultSet = LogInController.getPatientResultSet();
		try
		{
			    firstName = patientResultSet.getString("firstName");
			    lastName = patientResultSet.getString("lastName");
			    assignedDoc = patientResultSet.getString("assignedDoctor");
			    illness = patientResultSet.getString("illness");
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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/MentCareSB/view/LogIn.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
}
