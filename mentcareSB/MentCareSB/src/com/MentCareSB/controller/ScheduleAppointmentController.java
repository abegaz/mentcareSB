package com.MentCareSB.controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.MentCareSB.model.*;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ScheduleAppointmentController implements Initializable
{
	@FXML private TableView<Appointment> appointmentTableView;
	@FXML private TableColumn<Appointment, String> locationColumn, timeColumn;
	
	@FXML private TableView<Clinic> clinicTableView;
	@FXML private TableColumn<Clinic, String> nameColumn, clinicLocationColumn;
	
	@FXML private TextField timeField;
	private String patientIdNumber, doctorIdNumber;
	
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) 
	{
		Adapter.connect();
		patientIdNumber = DoctorViewController.getPatientIdNumber();
		doctorIdNumber = DoctorViewController.getDoctorIdNumber();
		setAppointmentTableView();
		setClinicTableView();
	}
	private void setAppointmentTableView()
	{
		locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("assignedClinicColumn"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("timeColumn"));
		appointmentTableView.getItems().setAll(createAppointmentList());
	}
	private List<Appointment> createAppointmentList()
	{
		ResultSet appointmentResultSet = Adapter.getResultSet("appointment");
		LinkedList<Appointment> appointmentLinkedList = new LinkedList<>();
		try
		{
			while (appointmentResultSet.next())
			{
				String idNumber = appointmentResultSet.getString("idNumber");
				String assignedClinic = appointmentResultSet.getString("assignedClinic");
				String time = appointmentResultSet.getString("time");
				String assignedDoctor = appointmentResultSet.getString("assignedDoctor");
				String assignedPatient = appointmentResultSet.getString("assignedPatient");	
				
				String clinicAddress = Adapter.getString("clinic", assignedClinic, "location");
				
				if (assignedPatient.equals(patientIdNumber))
				{
					appointmentLinkedList.add(new Appointment(idNumber, clinicAddress, time, assignedDoctor, assignedPatient));
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
		List<Appointment> appointmentList = appointmentLinkedList;
		return appointmentList;
	}
	private void setClinicTableView()
	{
		nameColumn.setCellValueFactory(new PropertyValueFactory<Clinic, String>("nameColumn"));
		clinicLocationColumn.setCellValueFactory(new PropertyValueFactory<Clinic, String>("locationColumn"));
		clinicTableView.getItems().setAll(createClinicList());
	}
	private List<Clinic> createClinicList()
	{
		ResultSet clinicResultSet = Adapter.getResultSet("clinic");
		LinkedList<Clinic> clinicLinkedList = new LinkedList<>();
		try
		{
			while (clinicResultSet.next())
			{
				String idNumber = clinicResultSet.getString("idNumber");
				String name = clinicResultSet.getString("name");
				String location = clinicResultSet.getString("location");
				String userName = clinicResultSet.getString("userName");
				String password = clinicResultSet.getString("password");
				clinicLinkedList.add(new Clinic(idNumber, name, location, userName, password));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		List<Clinic> clinicList = clinicLinkedList;
		return clinicList;
	}
	public void scheduleAppointment()
	{
		boolean checkIfAnythingIsSelected = clinicTableView.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = clinicTableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Clinic clinic = clinicTableView.getItems().get(row);
			
			
			String clinicIdNumber = clinic.getIdNumberColumn();
			String time = timeField.getText();
			
			Appointment.addAppointment(clinicIdNumber, time, doctorIdNumber, patientIdNumber);
			
			setAppointmentTableView();
		}
		else
		{
		}
	}
	public void deleteAppointment()
	{
		boolean checkIfAnythingIsSelected = appointmentTableView.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = appointmentTableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			
			Appointment appointment = appointmentTableView.getItems().get(row);
			
			String appointmentIdNumber = appointment.getIdNumberColumn();
			Appointment.deleteAppointment(appointmentIdNumber);
			setAppointmentTableView();
		}
		else
		{
		}
	}
	public void back(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/MentCareSB/view/DoctorView.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
}
