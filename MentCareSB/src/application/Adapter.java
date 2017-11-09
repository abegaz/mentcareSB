package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Adapter
{
	private static Connection myConn;
	public static Connection connect()
	{
		try{
		String host = "jdbc:mysql://50.62.209.186:3306/mentcaresb";
		String uName = "TeamMentCareSB";
		String uPass = "P4$$word";
		myConn = DriverManager.getConnection(host, uName, uPass);
		return myConn;
		}
		catch(SQLException err){
			System.out.println( err.getMessage( ) );
			return null;
		}
	}
	public static void injection(String injection)
	{
		try
		{
			myConn.createStatement().execute(injection);
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
	}

}