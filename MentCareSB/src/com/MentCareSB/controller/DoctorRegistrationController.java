package com.MentCareSB.controller;

import java.io.IOException;

import com.MentCareSB.model.Doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DoctorRegistrationController 
{
	@FXML TextField firstnameField, lastnameField, specialtyField, clinicField, usernameField, passwordField;
	public void registerDoctor()
	{
		String firstname = firstnameField.getText();
		String lastname = lastnameField.getText();
		String specialty = specialtyField.getText();
		String clinic = clinicField.getText();
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		Doctor.doctor(firstname, lastname, specialty, clinic, username, password);
	}
	public void back(ActionEvent event) throws IOException
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
