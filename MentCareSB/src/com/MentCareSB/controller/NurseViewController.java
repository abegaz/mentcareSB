package com.MentCareSB.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NurseViewController
{
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
