package com.MentCareSB.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import application.Adapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

	public String loginAction()
	{
		Connection connect = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		connect = Adapter.connect();
        String Sql="Select * from mentcaresb.doctor where userName=? and password=?";
        try
        {
            pst=connect.prepareStatement(Sql);
            pst.setString(1, getUsername());
            pst.setString(2, getPassword());
            rs = pst.executeQuery();
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "Welcome "+getUsername());
               // Welcome w = new Welcome();
                //w.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Access Denied", JOptionPane.ERROR_MESSAGE);

            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
		return null;
	}

	public void registerAction()
	{
		try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/MentCareSB/view/RegistrationType.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage registrationTypeStage = new Stage();
           // registrationTypeStage.initModality(Modality.APPLICATION_MODAL);
           // registrationTypeStage.initStyle(StageStyle.UNDECORATED);
            registrationTypeStage.setTitle("Registration Type");
            registrationTypeStage.setScene(new Scene(root1));  
            registrationTypeStage.show();
          }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	
	
	@FXML
	private String getUsername() {
	    return usernameField.getText();
	}

	@FXML
	private String getPassword()
	{
		return passwordField.getText();
	}
}
