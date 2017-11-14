package application;

import java.sql.ResultSet;

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
		Adapter.connect(); // Creates a connection to a database which will be use by Adapter methods
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
		//////////////////////////////////TESTING //////////////////////////////////////////////
	/*	try
		{
			ResultSet NewResultSet = Adapter.getResultSetRow("doctor", "userName", "Doctor12345");
			System.out.println(NewResultSet.getString("password"));
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}*/
		//Doctor.doctor("sam", "123145");
		//System.out.println(Adapter.getInt("doctor", "firstName", "pedro", "idNumber"));
		//////////////////////////////////TESTING //////////////////////////////////////////////
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
