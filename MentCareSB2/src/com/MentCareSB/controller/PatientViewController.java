package com.MentCareSB.controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.MentCareSB.model.PrescriptionTableModel;

import application.Adapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PatientViewController
{
	private String firstName, lastName, assignedDoc, bloodType, email, phone, illness, treatment, prescriptionNames, height, weight, appointmentDatee;
	int age;
	@FXML private Label patientName, patientLastName, clinicName, doctorName;
	@FXML private Label ageLabel, heightLabel, weightLabel, bloodLabel, illnessLabel, emailLabel, phoneLabel, prescriptionNameLabel, appointmentLabel;
	@FXML private TableView <PrescriptionTableModel> prescriptionTable;
	
	ObservableList<PrescriptionTableModel> prescriptionData = FXCollections.observableArrayList();
	
	public void initialize() 
	{
		int patientRow = LogInController.getPatientRow();
		ResultSet rs = Adapter.getResultSet("patient");	
		try
		{
				rs.absolute(patientRow);
			    firstName = rs.getString("firstName");
			    lastName = rs.getString("lastName");
			    assignedDoc = rs.getString("assignedDoctor");
			    prescriptionNames = rs.getString("Prescriptions");
			    //age = rs.getInt("age");
			    //height = rs.getString("height");
			    //weight = rs.getString("weight");
			    //bloodType = rs.getString("bloodType");
			    //illness = rs.getString("illness");
			    //email = rs.getString("emailAddress");
			    //phone = rs.getString("phoneNumber");
			    //treatment = rs.getString("treatment");
			    //appointmentDatee = rs.getString("appointmentDate"); 
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
			if(firstName!=null){
			patientName.setText(firstName);
			}else
			{
				patientName.setText("Not listed");
			}
			
			if(lastName!=null){
			patientLastName.setText(lastName);
			}else
			{
				patientLastName.setText("Not listed");
			}
			
			if(assignedDoc!=null){
			doctorName.setText(assignedDoc);
			}else{
				doctorName.setText("Not listed");
			}
			
			if(age>0){
			ageLabel.setText("Age : " +age);
			}else{
				ageLabel.setText("Not listed");
			}
			
			if(height!=null){
			heightLabel.setText("Height : " +height);
			}else{
				heightLabel.setText("Not listed");
			}
			
			if(weight!=null){
			weightLabel.setText("Weight : " +weight);
			}else{
				weightLabel.setText("Not listed");
			}
			
			if(bloodType!=null){
			bloodLabel.setText("Blood type : " +bloodType);
			}else{
				bloodLabel.setText("Not listed");
			}
			
			if(illness!=null){
			illnessLabel.setText("Illness : " +illness);
			}else{
				illnessLabel.setText("Not listed");
			}
			
			if(email!=null){
			emailLabel.setText("Email : " +email);
			}else{
				emailLabel.setText("Not listed");
			}
			
			if(phone!=null){
			phoneLabel.setText("Phone : "+phone);
			}else{
				phoneLabel.setText("Not listed");
			}
			
			if(appointmentDatee!=null && assignedDoc!=null){
			appointmentLabel.setText("Your next appointment is on "+appointmentDatee+" with Dr."+assignedDoc);
			}
			else{
				appointmentLabel.setText("Not listed");
			}
			
			TableColumn<PrescriptionTableModel, String> prescriptionNameCol = new TableColumn<PrescriptionTableModel, String>("Prescriptions");
			prescriptionNameCol.setCellValueFactory(new PropertyValueFactory<PrescriptionTableModel, String>("prescriptionName"));
			prescriptionTable.getColumns().addAll(prescriptionNameCol);
			
			
			prescriptionTable.setItems(prescriptionData);
			List<String> prescriptionList = Arrays.asList(prescriptionNames.split(", "));
			
			for(int i=0;i<prescriptionList.size();i++){
				prescriptionData.add(new PrescriptionTableModel(prescriptionList.get(i)));
			}
	}
}
