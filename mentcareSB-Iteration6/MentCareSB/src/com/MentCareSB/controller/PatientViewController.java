package com.MentCareSB.controller;
import java.io.IOException;
import java.net.URL;
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

public class PatientViewController implements Initializable
{
	private String firstName, lastName, illness, assignedDoctorIdNumber, assignedDoctorName, userName, password, assignedClinicIdNumber, assignedClinicName, assignedMedication;
	private static String patientIdNumber;
	@FXML private Label nameLabel, assignedClinicLabel1, assignedDoctorLabel1, assignedClinicLabel2, assignedDoctorLabel2;
	@FXML private Label heightLabel, weightLabel, bloodLabel, illnessLabel, emailLabel, phoneLabel, prescriptionNameLabel, appointmentLabel;
	
	@FXML private TableView<Doctor> doctorListTableView;
	@FXML private TableColumn<Doctor, String> firstNameColumn, lastNameColumn, specialtyColumn;
	
	@FXML private TableView<Appointment> appointmentTableView;
	@FXML private TableColumn<Appointment, String> locationAppointmentColumn, timeColumn, doctorColumn;
	
	@FXML private TableView<Medication> medicationTableView;
	@FXML private TableColumn<Medication, String> nameMedicationColumn, usageColumn, dosageColumn;
	
	@FXML private TableView<Clinic> clinicTableView;
	@FXML private TableColumn<Clinic, String> nameColumn, locationColumn;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		Adapter.connect();
		patientIdNumber = LogInController.getPatientIdNumber();
		ResultSet patientResultSet = Adapter.getResultSetRow("patient", patientIdNumber);
		try
		{
			patientIdNumber =  patientResultSet.getString("idNumber");
			firstName = patientResultSet.getString("firstName");
			lastName = patientResultSet.getString("lastName");
			illness = patientResultSet.getString("illness");
			assignedDoctorIdNumber = patientResultSet.getString("assignedDoctor");
			userName = patientResultSet.getString("userName");
			password = patientResultSet.getString("password");
			assignedClinicIdNumber = patientResultSet.getString("assignedClinic");
			assignedMedication = patientResultSet.getString("assignedMedication");
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		nameLabel.setText("Welcome! "+firstName+" "+lastName);
		setDoctorName();
		setClinicName();
		illnessLabel.setText("Illness : " +illness);
		
		setDoctorTableView();
		setClinicTableView();
		setAppointmentTableView();
		setMedicationTableView();
	}
	private void setDoctorName()
	{
		ResultSet doctorResultSet = Adapter.getResultSetRow("doctor", assignedDoctorIdNumber);
		try
		{
			assignedDoctorName = doctorResultSet.getString("firstName") +" "+ doctorResultSet.getString("lastName");
			assignedDoctorLabel1.setText("Your Assigned Doctor is " + assignedDoctorName);
			assignedDoctorLabel2.setText("Your Assigned Doctor is " + assignedDoctorName);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	private void setClinicName()
	{
		ResultSet clinicResultSet = Adapter.getResultSetRow("clinic", assignedClinicIdNumber);
		try
		{
			assignedClinicName = clinicResultSet.getString("name");
			assignedClinicLabel1.setText("Your Assigned Clinic is " + assignedClinicName);
			assignedClinicLabel2.setText("Your Assigned Clinic is " + assignedClinicName);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	private void setDoctorTableView()
	{
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("firstNameColumn"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("lastNameColumn"));
		specialtyColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialtyColumn"));
		doctorListTableView.getItems().setAll(createDoctorList());
	}
	private List<Doctor> createDoctorList()
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
	private void setAppointmentTableView()
	{
		locationAppointmentColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("assignedClinicColumn"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("timeColumn"));
		doctorColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("assignedDoctorColumn"));
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
				String doctorName = Adapter.getString("doctor", assignedDoctor, "firstName")+" "+Adapter.getString("doctor", assignedDoctor, "lastName");
				
				if (assignedPatient.equals(patientIdNumber))
				{
					appointmentLinkedList.add(new Appointment(idNumber, clinicAddress, time, doctorName, assignedPatient));
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
	private void setMedicationTableView()
	{
		nameMedicationColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("nameColumn"));
		usageColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("usageColumn"));
		dosageColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("dosageColumn"));
		medicationTableView.getItems().setAll(createAssignedMedicationList());
	}
	private List<Medication> createAssignedMedicationList()
	{
		LinkedList<Medication> assignedMedicationLinkedList = new LinkedList<>();
		String[] assignedMedicationArray = assignedMedication.split(",");
		try
		{
			for (int i=0; i<assignedMedicationArray.length; i++)
			{
				ResultSet assignedMedicationResultSet = Adapter.getResultSet("medication");
				while (assignedMedicationResultSet.next())
				{
					String idNumber = assignedMedicationResultSet.getString("idNumber");
					String name = assignedMedicationResultSet.getString("name");
					String usage = assignedMedicationResultSet.getString("usage");
					String dosage = assignedMedicationResultSet.getString("dosage");
					if (assignedMedicationArray[i].equals(idNumber))
					{
						assignedMedicationLinkedList.add(new Medication(idNumber, name, usage, dosage));
					}
					else
					{
					}
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		List<Medication> assignedMedicationList = assignedMedicationLinkedList;
		return assignedMedicationList;
	}
	private void setClinicTableView()
	{
		nameColumn.setCellValueFactory(new PropertyValueFactory<Clinic, String>("nameColumn"));
		locationColumn.setCellValueFactory(new PropertyValueFactory<Clinic, String>("locationColumn"));
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
	public void changeClinic()
	{
		boolean checkIfAnythingIsSelected = clinicTableView.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = clinicTableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Clinic clinic = clinicTableView.getItems().get(row);
			assignedClinicIdNumber = clinic.getIdNumberColumn();
			Patient.changeClinic(patientIdNumber, assignedClinicIdNumber);
		}
		else
		{
		}
		setClinicName();
	}
	public void updateInfo(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/MentCareSB/view/PatientSelfEdit.fxml"));
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
	}
	public static String getPatientIdNumber()
	{
		return patientIdNumber;
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
			Patient.changeDoctor(patientIdNumber, assignedDoctorIdNumber);
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