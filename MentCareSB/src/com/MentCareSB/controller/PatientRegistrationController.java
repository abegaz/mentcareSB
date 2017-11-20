package com.MentCareSB.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.MentCareSB.model.Clinic;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientRegistrationController implements Initializable
{
	@FXML private TextField firstnameField, lastnameField, illnessField, usernameField, passwordField;
	@FXML private ChoiceBox<String> assingedClinicChoiceBox;
	@FXML private TableView<Clinic> clinicTableView;
	@FXML private TableColumn<Clinic, String> nameColumn, locationColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		setTableView();
	}
	private void setTableView()
	{
		nameColumn.setCellValueFactory(new PropertyValueFactory<Clinic, String>("nameColumn"));
		locationColumn.setCellValueFactory(new PropertyValueFactory<Clinic, String>("locationColumn"));
		clinicTableView.getItems().setAll(createList());
		
	}
	private List<Clinic> createList()
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
	public void registerPatient()
	{
		boolean checkIfAnythingIsSelected = clinicTableView.getSelectionModel().isEmpty(); // This is to prevent error in case nothing is selected
		if (!checkIfAnythingIsSelected)
		{
			TablePosition pos = clinicTableView.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			Clinic clinic = clinicTableView.getItems().get(row);
			String firstName = firstnameField.getText();
			String lastName = lastnameField.getText();
			String illness = illnessField.getText();
			String assignedDoctor = DoctorViewController.getDoctorIdNumber();
			String userName = usernameField.getText();
			String password = passwordField.getText();
			String assignedClinic = clinic.getIdNumberColumn();
			Patient.addPatient(firstName, lastName, illness, assignedDoctor, userName, password, assignedClinic);
		}
		else{}
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



/*	ArrayList<String> choices = new ArrayList<>();
ResultSet clinicResultSet = Adapter.getResultSet("clinic");
try
{
	while (clinicResultSet.next())
	{
		choices.add(clinicResultSet.getString("idNumber"));
	}
}
catch (SQLException e) 
{
	e.printStackTrace();
}
ObservableList<String> list = FXCollections.observableArrayList(choices);
assingedClinicChoiceBox.setItems(list);*/