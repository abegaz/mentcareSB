package com.MentCareSB.controller;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.MentCareSB.model.Doctor;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientViewController implements Initializable
{
	private String idNumber, firstName, lastName, illness, assignedDoctorIdNumber, assignedDoctorName, userName, password, assignedClinic;
	@FXML private Label firstNameLabel, lastNameLabel, assignedClinicLabel, assignedDoctorLabel;
	@FXML private Label heightLabel, weightLabel, bloodLabel, illnessLabel, emailLabel, phoneLabel, prescriptionNameLabel, appointmentLabel;
	@FXML private TableView<Doctor> doctorListTableView;
	@FXML private TableColumn<Doctor, String> firstNameColumn, lastNameColumn, specialtyColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		ResultSet patientResultSet = LogInController.getPatientResultSet();
		try
		{
			idNumber =  patientResultSet.getString("idNumber");
			firstName = patientResultSet.getString("firstName");
			lastName = patientResultSet.getString("lastName");
			illness = patientResultSet.getString("illness");
			assignedDoctorIdNumber = patientResultSet.getString("assignedDoctor");
			userName = patientResultSet.getString("userName");
			password = patientResultSet.getString("password");
			assignedClinic = patientResultSet.getString("assignedClinic");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		firstNameLabel.setText(firstName);
		lastNameLabel.setText(lastName);
		setDoctorName();
		
		assignedClinicLabel.setText(assignedClinic);
		illnessLabel.setText("Illness : " +illness);
		
		setTableView();
	}
	private void setDoctorName()
	{
		ResultSet doctorResultSet = Adapter.getResultSetRow("doctor", "idNumber", assignedDoctorIdNumber);
		try
		{
			assignedDoctorName = doctorResultSet.getString("firstName") +" "+ doctorResultSet.getString("lastName");
			assignedDoctorLabel.setText(assignedDoctorName);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	private void setTableView()
	{
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("firstNameColumn"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("lastNameColumn"));
		specialtyColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialtyColumn"));
		doctorListTableView.getItems().setAll(createList());
	}
	private List<Doctor> createList()
	{
		ResultSet doctorResultSet = Adapter.getResultSet("doctor");
		LinkedList<Doctor> doctorLinkedList = new LinkedList<>();
		try
		{
			while (doctorResultSet.next())
			{
				String idNumber = doctorResultSet.getString("idNumber");
				String firstName = doctorResultSet.getString("firstName");
				String lastName = doctorResultSet.getString("lastName");
				String specialty = doctorResultSet.getString("specialty");
				String userName = doctorResultSet.getString("userName");
				String password = doctorResultSet.getString("password");
				doctorLinkedList.add(new Doctor(idNumber, firstName, lastName, specialty, userName, password));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		List<Doctor> doctorList = doctorLinkedList;
		return doctorList;
	}
	public void changeDoctor()
	{
		boolean checkIfAnythingIsSelected = doctorListTableView.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = doctorListTableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Doctor doctor = doctorListTableView.getItems().get(row);
			assignedDoctorIdNumber = doctor.getIdNumberColumn();
			Patient.changeDoctor(idNumber, assignedDoctorIdNumber);
			
		}
		else
		{
		}
		
		setDoctorName();
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