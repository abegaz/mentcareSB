package com.MentCareSB.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class DoctorViewController
{
	@FXML private Label labelInsertDoctorName;
	@FXML private Label labelInsertDoctorSpecialty;
	@FXML private Label labelInsertDoctorClinic;

	@FXML private TableView<Patient> tableViewPatientList;
	@FXML private TableColumn<Patient, String> tableColumnFirstName;
	@FXML private TableColumn<Patient, String> tableColumnLastName;
	@FXML private TableColumn<Patient, String> tableColumnAge;
	@FXML private TableColumn<Patient, String> tableColumnIllness;
	@FXML private TableColumn<Patient, String> tableColumnTreatment;
	@FXML private TableColumn<Patient, String> tableColumnPhoneNumber;
	@FXML private TableColumn<Patient, String> tableColumnEmailAddress;




	public void initialize()
	{
		int doctorRow = LogInController.getPatientRow();
		ResultSet rs = Adapter.getResultSet("doctor");	
		try
		{
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
		
		
		tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientFirstName"));
		tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientLastName"));
		tableColumnAge.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientAge"));
		tableColumnIllness.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientIllness"));
		tableColumnTreatment.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientTreatment"));
		tableColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientPhoneNumber"));
		tableColumnEmailAddress.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientEmailAddress"));
		
		tableViewPatientList.setItems(getPatientList());
		    System.out.println(getPatientList().toString());

		 /*table.setItems(data);
	     Patient.getColumns().addAll(tableColumnFirstName, tableColumnLastName, tableColumnIllness,
	    		 					tableColumnTreatment,tableColumnEmailAddress,patientPhoneNumber,patientAge);*/

		/*tableViewPatientList.setEditable(true);
		tableColumnFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
		tableColumnLastName.setCellFactory(TextFieldTableCell.forTableColumn());
		tableColumnAge.setCellFactory(TextFieldTableCell.forTableColumn());
		tableColumnIllness.setCellFactory(TextFieldTableCell.forTableColumn());
		tableColumnTreatment.setCellFactory(TextFieldTableCell.forTableColumn());
		tableColumnPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
		tableColumnEmailAddress.setCellFactory(TextFieldTableCell.forTableColumn());*/

		tableViewPatientList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public ObservableList<Patient> getPatientList() 
	{
		ObservableList<Patient> patient = FXCollections.observableArrayList();
		ResultSet PatientList = Adapter.getResultSet("patient");
		try 
		{
			while(PatientList.next())
			{
				String firstName = PatientList.getString("firstName");
				String lastName = PatientList.getString("lastName");
				int age = PatientList.getInt("age");
				String illness = PatientList.getString("illness");
				String treatment = PatientList.getString("treatment");
				String phoneNumber = PatientList.getString("phoneNumber");
				String emailAddress = PatientList.getString("emailAddress");
				patient.add(new Patient(firstName, lastName, age, illness, treatment, phoneNumber, emailAddress));
			}
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		return patient;

	}

	// public void addPatientButtonPushed(){
	// Patient newPatient = new Patient(
		


	public void changeSceneToTableViewPatientList(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../../com/MentCareSB/view/DoctorView.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		// access the controller and calls a method
		// This line gets the Stage information
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}
	
	public void registerNurse(ActionEvent event) throws IOException
	{
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("../../../com/MentCareSB/view/NurseRegistration.fxml"));
		Parent tableViewParent1 = loader1.load();
		Scene tableViewScene1 = new Scene(tableViewParent1);
		Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		window1.setScene(tableViewScene1);
		window1.show();
	}
	public void registerPatient(ActionEvent event) throws IOException
	{
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("../../../com/MentCareSB/view/PatientRegistration.fxml"));
		Parent tableViewParent1 = loader1.load();
		Scene tableViewScene1 = new Scene(tableViewParent1);
		Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		window1.setScene(tableViewScene1);
		window1.show();
	}
}	
