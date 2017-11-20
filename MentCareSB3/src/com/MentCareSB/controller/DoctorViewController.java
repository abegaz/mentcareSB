package com.MentCareSB.controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import com.MentCareSB.model.Patient;

import application.Adapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DoctorViewController  implements Initializable
{
	@FXML Label labelInsertDoctorName, labelInsertDoctorSpecialty, labelInsertDoctorClinic;
	@FXML TextField patientUsernameField;
	@FXML TableView <Patient> tableViewPatientList;
	@FXML TableColumn<Patient, String> firstNameColumn;
	@FXML TableColumn<Patient, String> lastNameColumn; 
	@FXML TableColumn<Patient, String> illnessColumn;
	@FXML TableColumn<Patient, String> assignedDoctorColumn; 
	@FXML TableColumn<Patient, String> userNameColumn; 
	@FXML TableColumn<Patient, String> passwordColumn;
	private static String doctorUsername;

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) 
	{
		ResultSet doctorResultSet = LogInController.getDoctorResultSet();
		try
		{
			String firstName = doctorResultSet.getString("firstName");
			String lastName = doctorResultSet.getString("lastName");
			String specialty = doctorResultSet.getString("specialty");
			String clinic = doctorResultSet.getString("clinic");
			labelInsertDoctorName.setText(firstName+" "+lastName);
			labelInsertDoctorSpecialty.setText(specialty);
			labelInsertDoctorClinic.setText(clinic);
			doctorUsername = doctorResultSet.getString("userName");
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		setTableView();	
	}
	public void setTableView()
	{
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstNameColumn"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastNameColumn"));
		illnessColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("illnessColumn"));
		assignedDoctorColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("assignedDoctorColumn"));
		userNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("userNameColumn"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("passwordColumn"));
		
		tableViewPatientList.getItems().setAll(createList());
	}
	private List<Patient> createList()
	{
		ResultSet patientResultSet = Adapter.getResultSet("patient");
		LinkedList<Patient> patientLinkedList = new LinkedList<>();
		try
		{
			while (patientResultSet.next())
			{
				String firstName = patientResultSet.getString("firstName");
				String lastName = patientResultSet.getString("lastName");
				String illness = patientResultSet.getString("illness");
				String assignedDoctor = patientResultSet.getString("assignedDoctor");
				String userName = patientResultSet.getString("userName");
				String password = patientResultSet.getString("password");
				if (assignedDoctor.equals(doctorUsername))
				{
						patientLinkedList.add(new Patient(firstName, lastName, illness, assignedDoctor, userName, password));
				}
				else
				{		
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		List<Patient> patientList = patientLinkedList;
		return patientList;
	}
	public static String getDoctorUsername()
	{
		return doctorUsername;
	}
	public void editPatient()
	{
		String username = patientUsernameField.getText();
	}
	public void deletePatient()
	{
		String username = patientUsernameField.getText();
		Adapter.deleteRow("patient", "userName", username);
		setTableView();
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