package com.MentCareSB.controller;
import java.io.IOException;
import java.sql.*;
import java.util.*;


import com.MentCareSB.model.Patient;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DoctorViewController implements Initializable
{
	@FXML private Label doctorNameLabel, doctorSpecialtyLabel;
	@FXML private TableView<Patient> tableViewPatientList;
	@FXML private TableColumn<Patient, String> firstNameColumn, lastNameColumn, illnessColumn;
	private static String doctorIdNumber, patientIdNumber;

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) 
	{
		Adapter.connect();
		doctorIdNumber = LogInController.getDoctorIdNumber();
		ResultSet doctorResultSetRow = Adapter.getResultSetRow("doctor", doctorIdNumber);
		try
		{
			String idNumber = doctorResultSetRow.getString("idNumber");
			String firstName = doctorResultSetRow.getString("firstName");
			String lastName = doctorResultSetRow.getString("lastName");
			String specialty = doctorResultSetRow.getString("specialty");
			String userName = doctorResultSetRow.getString("userName");
			String password = doctorResultSetRow.getString("password");
			doctorIdNumber = idNumber;
			doctorNameLabel.setText("Welcome "+firstName+" "+lastName);
			doctorSpecialtyLabel.setText("Your specialty is "+specialty);
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		setPatientTableView();	
	}
	private void setPatientTableView()
	{
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstNameColumn"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastNameColumn"));
		illnessColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("illnessColumn"));
		tableViewPatientList.getItems().setAll(createPatientList());
	}
	private List<Patient> createPatientList()
	{
		ResultSet patientResultSet = Adapter.getResultSet("patient");
		LinkedList<Patient> patientLinkedList = new LinkedList<>();
		try
		{
			while (patientResultSet.next())
			{
				String idNumber = patientResultSet.getString("idNumber");
				String firstName = patientResultSet.getString("firstName");
				String lastName = patientResultSet.getString("lastName");
				String illness = patientResultSet.getString("illness");
				String assignedDoctor = patientResultSet.getString("assignedDoctor");
				String userName = patientResultSet.getString("userName");
				String password = patientResultSet.getString("password");
				String assignedClinic = patientResultSet.getString("assignedClinic");
				String notes = patientResultSet.getString("notes");
				String assignedMedication = patientResultSet.getString("assignedMedication");
				
				if (assignedDoctor.equals(doctorIdNumber))
				{
						patientLinkedList.add(new Patient(idNumber, firstName, lastName, illness, assignedDoctor, userName, password, assignedClinic, notes, assignedMedication));
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
	public static String getDoctorIdNumber()
	{
		return doctorIdNumber;
	}
	public static String getPatientIdNumber()
	{
		return patientIdNumber;
	}
	public void editPatient(ActionEvent event) throws IOException
	{
		boolean checkIfAnythingIsSelected = tableViewPatientList.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = tableViewPatientList.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Patient patient = tableViewPatientList.getItems().get(row);
			patientIdNumber = patient.getIdNumberColumn();
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/com/MentCareSB/view/PatientEdit.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		else
		{
		}
	}
	public void deletePatient()
	{
		boolean checkIfAnythingIsSelected = tableViewPatientList.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = tableViewPatientList.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Patient patient = tableViewPatientList.getItems().get(row);
			String patientIdNumber = patient.getIdNumberColumn();
			
			Patient.deletePatient(patientIdNumber);
			setPatientTableView();
		}
		else
		{
		}
	}
	public void scheduleAppointment(ActionEvent event) throws IOException
	{
		boolean checkIfAnythingIsSelected = tableViewPatientList.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = tableViewPatientList.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Patient patient = tableViewPatientList.getItems().get(row);
			patientIdNumber = patient.getIdNumberColumn();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/com/MentCareSB/view/ScheduleAppointment.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		else
		{
		}
	}
	public void updateMedication(ActionEvent event) throws IOException
	{
		boolean checkIfAnythingIsSelected = tableViewPatientList.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = tableViewPatientList.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Patient patient = tableViewPatientList.getItems().get(row);
			patientIdNumber = patient.getIdNumberColumn();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/com/MentCareSB/view/UpdateMedication.fxml"));
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		}
		else
		{
		}
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
	public void registerNurse(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/MentCareSB/view/NurseRegistration.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
	public void registerPatient(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/MentCareSB/view/PatientRegistration.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
}	