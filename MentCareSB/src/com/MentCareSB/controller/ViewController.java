package com.MentCareSB.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import application.Adapter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController {
	
	public void loginAction()
	{
		Connection connect = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		connect = Adapter.connect();
        String Sql="Select * from mentcaresb.doctor where firstname=? and lastname=?";
        try
        {
            pst=connect.prepareStatement(Sql);
            pst.setString(1, "Scott");
            pst.setString(2, "Hogan");
            rs = pst.executeQuery();
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "Welcome user");
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
	}
	
	public void registerAction()
	{
		
	}
	
}
