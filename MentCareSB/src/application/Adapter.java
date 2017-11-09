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
	public static int getInt(String tableName, String fieldName, String whatever, String searchValue)
	{
		//SELECT * FROM [table name] WHERE [field name] = "whatever";
		int number = 0;
		try
		{
			Statement myStat = myConn.createStatement();
			ResultSet myRs = myStat.executeQuery("SELECT * FROM "+tableName);
			while (myRs.next())
			{
				if (myRs.getString(fieldName).equals(whatever))
				{
					number = myRs.getInt(searchValue);
					break;
				}
			}
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		return number;
	}
	public static String getString(String tableName, String fieldName, String whatever, String searchValue)
	{
		//SELECT * FROM [table name] WHERE [field name] = "whatever";
		String str="";
		try
		{
			Statement myStat = myConn.createStatement();
			ResultSet myRs = myStat.executeQuery("SELECT * FROM "+tableName);
			while (myRs.next())
			{
				if (myRs.getString(fieldName).equals(whatever))
				{
					str = myRs.getString(searchValue);
					break;
				}
			}
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		return str;
	}
}