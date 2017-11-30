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

public class UpdateMedicationController implements Initializable
{
	@FXML private TableView<Medication> assignedMedicationTableView;
	@FXML private TableColumn<Medication, String> nameColumn1, usageColumn1, dosageColumn1;
	
	@FXML private TableView<Medication> medicationTableView;
	@FXML private TableColumn<Medication, String> nameColumn2, usageColumn2, dosageColumn2;
	
	private String assignedMedication, patientIdNumber;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Adapter.connect();
		patientIdNumber = DoctorViewController.getPatientIdNumber();
		ResultSet patientResultSetRow = Adapter.getResultSetRow("patient", patientIdNumber);
		try
		{
			String idNumber = patientResultSetRow.getString("idNumber");
			String firstName = patientResultSetRow.getString("firstName");
			String lastName = patientResultSetRow.getString("lastName");
			String illness = patientResultSetRow.getString("illness");
			String assignedDoctor = patientResultSetRow.getString("assignedDoctor");
			String userName = patientResultSetRow.getString("userName");
			String password = patientResultSetRow.getString("password");
			String assignedClinic = patientResultSetRow.getString("assignedClinic");
			String notes = patientResultSetRow.getString("notes");
			assignedMedication = patientResultSetRow.getString("assignedMedication");
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		
		setAssignedMedicationTableView();
		setMedicationTableView();
	}
	private void setAssignedMedicationTableView()
	{
		nameColumn1.setCellValueFactory(new PropertyValueFactory<Medication, String>("nameColumn"));
		usageColumn1.setCellValueFactory(new PropertyValueFactory<Medication, String>("usageColumn"));
		dosageColumn1.setCellValueFactory(new PropertyValueFactory<Medication, String>("dosageColumn"));
		assignedMedicationTableView.getItems().setAll(createAssignedMedicationList());
	}
	private List<Medication> createAssignedMedicationList()
	{
		LinkedList<Medication> assignedMedicationLinkedList = new LinkedList<>();
		String[] assignedMedicationArray = assignedMedication.split(",");
		try
		{
			for (int i=0; i<assignedMedicationArray.length; i++)
			{
				ResultSet medicationResultSet = Adapter.getResultSet("medication");
				while (medicationResultSet.next())
				{
					String idNumber = medicationResultSet.getString("idNumber");
					String name = medicationResultSet.getString("name");
					String usage = medicationResultSet.getString("usage");
					String dosage = medicationResultSet.getString("dosage");
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
	private void setMedicationTableView()
	{
		nameColumn2.setCellValueFactory(new PropertyValueFactory<Medication, String>("nameColumn"));
		usageColumn2.setCellValueFactory(new PropertyValueFactory<Medication, String>("usageColumn"));
		dosageColumn2.setCellValueFactory(new PropertyValueFactory<Medication, String>("dosageColumn"));
		medicationTableView.getItems().setAll(createMedicationList());
	}
	private List<Medication> createMedicationList()
	{
		ResultSet medicationResultSet = Adapter.getResultSet("medication");
		LinkedList<Medication> medicationLinkedList = new LinkedList<>();
		try
		{
			while (medicationResultSet.next())
			{
				String idNumber = medicationResultSet.getString("idNumber");
				String name = medicationResultSet.getString("name");
				String usage = medicationResultSet.getString("usage");
				String dosage = medicationResultSet.getString("dosage");
				medicationLinkedList.add(new Medication(idNumber, name, usage, dosage));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		List<Medication> medicationList = medicationLinkedList;
		return medicationList;
	}
	public void addMedication()
	{
		boolean checkIfAnythingIsSelected = medicationTableView.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = medicationTableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Medication medication = medicationTableView.getItems().get(row);
			String pickedMedication = medication.getIdNumberColumn();
			assignedMedication = assignedMedication+","+pickedMedication;
			Patient.updateMedicationToPatient(patientIdNumber, assignedMedication);
		}
		else
		{
		}
		setAssignedMedicationTableView();
	}
	public void removeMedication()
	{
		boolean checkIfAnythingIsSelected = assignedMedicationTableView.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = assignedMedicationTableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Medication medication = assignedMedicationTableView.getItems().get(row);
			String pickedMedication = medication.getIdNumberColumn();
			String[] assignedMedicationArray = assignedMedication.split(",");
			assignedMedication = "";
			for (int i=0; i<assignedMedicationArray.length; i++)
			{
				if (assignedMedicationArray[i].equals(pickedMedication))
				{
					assignedMedicationArray[i] = null;
					break;
				}
				else
				{
				}
			}
			for (int i=0; i<assignedMedicationArray.length; i++)
			{
				if (assignedMedicationArray[i] != null && !assignedMedicationArray[i].equals("null") && !assignedMedicationArray[i].equals(""))
				{
					assignedMedication = assignedMedication+","+assignedMedicationArray[i];
				}
			}
			Patient.updateMedicationToPatient(patientIdNumber, assignedMedication);
		}
		else
		{
		}
		setAssignedMedicationTableView();
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