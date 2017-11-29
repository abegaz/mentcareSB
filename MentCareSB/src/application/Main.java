package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.MentCareSB.controller.LogInController;
import com.MentCareSB.model.Patient;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/MentCareSB/view/LogIn.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		//String injection = "UPDATE patient SET notes = 'asdagasfdfsdfads' WHERE idNumber = 3";
		//Adapter.injection(injection);
		//Patient.updatePatient("3", "123", "sda", "sdasd", "Patient2", "1234", "fdsfd");
	}
	public static void main(String[] args) 
	{
		launch(args);
	}
}