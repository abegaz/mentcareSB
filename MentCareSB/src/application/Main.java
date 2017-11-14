package application;
import com.MentCareSB.view.*;

	
import com.MentCareSB.model.Doctor;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) 
	{
		Adapter.connect1(); // Creates a connection to a database which will be use by Adapter methods
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
		
		////////////////////////////////// TESTING //////////////////////////////////////////////
		//Doctor.doctor("sam", "123145");
		//System.out.println(Adapter.getInt("doctor", "firstName", "pedro", "idNumber"));
		//////////////////////////////////TESTING //////////////////////////////////////////////
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
