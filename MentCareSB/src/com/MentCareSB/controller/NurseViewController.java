package com.MentCareSB.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.MentCareSB.model.Patient;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class NurseViewController implements Initializable
{
	@FXML TableView <Patient> patientListTableView;
	@FXML TableColumn<Patient, String> firstNameColumn, lastNameColumn, illnessColumn, assignedDoctorColumn; 
	private String clinicIdNumber;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		ResultSet nurseResultSet = LogInController.getNurseResultSet();
		try
		{
			String idNumber = nurseResultSet.getString("idNumber");
			String firstName = nurseResultSet.getString("firstName");
			String lastName = nurseResultSet.getString("lastName");
			String assignedClinic = nurseResultSet.getString("assignedClinic");
			String userName = nurseResultSet.getString("userName");
			String password = nurseResultSet.getString("password");
			
			clinicIdNumber = assignedClinic;
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
		patientListTableView.getItems().setAll(createList());
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
			
				String assignedDoctorName = Adapter.getResultSetRow("doctor", "idNumber", assignedDoctor).getString("firstName");
				
				if (assignedClinic.equals(clinicIdNumber))
				{
					patientLinkedList.add(new Patient(idNumber, firstName, lastName, illness, assignedDoctorName, userName, password, assignedClinic, notes));
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

	
}
