package com.MentCareSB.controller;

import java.io.IOException;

import com.MentCareSB.model.Doctor;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DoctorRegistrationController 
{
	@FXML TextField firstnameField, lastnameField, specialtyField, usernameField, passwordField;
	public void registerDoctor()
	{
		String firstName = firstnameField.getText();
		String lastName = lastnameField.getText();
		String specialty = specialtyField.getText();
		String userName = usernameField.getText();
		String password = passwordField.getText();
		
		Doctor.addDoctor(firstName, lastName, specialty, userName, password);
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
