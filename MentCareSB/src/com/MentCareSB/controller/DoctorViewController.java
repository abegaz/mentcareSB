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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DoctorViewController implements Initializable
{
	@FXML private Label labelInsertDoctorName, labelInsertDoctorSpecialty;
	@FXML private TextField patientIdNumberField;
	@FXML private TableView <Patient> tableViewPatientList;
	@FXML private TableColumn<Patient, String> firstNameColumn, lastNameColumn, illnessColumn;
	private static String doctorIdNumber, patientIdNumber;

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) 
	{
		ResultSet doctorResultSetRow = LogInController.getDoctorResultSet();
		try
		{
			String idNumber = doctorResultSetRow.getString("idNumber");
			String firstName = doctorResultSetRow.getString("firstName");
			String lastName = doctorResultSetRow.getString("lastName");
			String specialty = doctorResultSetRow.getString("specialty");
			String userName = doctorResultSetRow.getString("userName");
			String password = doctorResultSetRow.getString("password");
			
			doctorIdNumber = idNumber;
			
			
			labelInsertDoctorName.setText(firstName+" "+lastName);
			labelInsertDoctorSpecialty.setText(specialty);
			
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
				String idNumber = patientResultSet.getString("idNumber");
				String firstName = patientResultSet.getString("firstName");
				String lastName = patientResultSet.getString("lastName");
				String illness = patientResultSet.getString("illness");
				String assignedDoctor = patientResultSet.getString("assignedDoctor");
				String userName = patientResultSet.getString("userName");
				String password = patientResultSet.getString("password");
				String assignedClinic = patientResultSet.getString("assignedClinic");
				String notes = patientResultSet.getString("notes");
				
				if (assignedDoctor.equals(doctorIdNumber))
				{
						patientLinkedList.add(new Patient(idNumber, firstName, lastName, illness, assignedDoctor, userName, password, assignedClinic, notes));
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
			Adapter.deleteRow("patient", "idNumber", patientIdNumber);
			setTableView();
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
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("/com/MentCareSB/view/PatientRegistration.fxml"));
		Parent tableViewParent1 = loader1.load();
		Scene tableViewScene1 = new Scene(tableViewParent1);
		Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		window1.setScene(tableViewScene1);
		window1.show();
	}
}	