package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Adapter 
{
	private static Connection myConn;
	public static void connect1()
	{
		try
		{
		String host = "jdbc:mysql://50.62.209.186:3306/mentcaresb";
		String uName = "TeamMentCareSB";
		String uPass = "P4$$word";
		
		/*String host = "jdbc:mysql://localhost:3306/mentcaresb";
		String uName = "Sarefx";
		String uPass = "1234";*/

		myConn = DriverManager.getConnection(host, uName, uPass);
		}
		catch(SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
	}
	public static Connection connect()
	{
		try
		{
		String host = "jdbc:mysql://50.62.209.186:3306/mentcaresb";
		String uName = "TeamMentCareSB";
		String uPass = "P4$$word";
		
		/*String host = "jdbc:mysql://localhost:3306/mentcaresb";
		String uName = "Sarefx";
		String uPass = "1234";*/

		myConn = DriverManager.getConnection(host, uName, uPass);
		}
		catch(SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		return myConn;
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
	public static ResultSet getResultSet(String tableName)
	{
		ResultSet MyResultSet=null;
		try
		{
			Statement myStat = myConn.createStatement();
			MyResultSet = myStat.executeQuery("SELECT * FROM "+tableName);
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		return MyResultSet;
	}
/*	public static int checkUsernamePassword(String username, String password)
	{
		//SELECT * FROM [table name] WHERE [field name] = "whatever";
		int identity=0;
		try
		{
			Statement myStat1 = myConn.createStatement();
			ResultSet resultSetDoctor = myStat1.executeQuery("SELECT * FROM doctor");
			while (resultSetDoctor.next())
			{
				if (resultSetDoctor.getString("userName").equals(username) && resultSetDoctor.getString("password").equals(password))
				{
					identity = 1;
					break;
				}
			}
			Statement myStat2 = myConn.createStatement();
			ResultSet resultSetNurse = myStat2.executeQuery("SELECT * FROM nurse");
			while (resultSetNurse.next())
			{
				if (resultSetNurse.getString("userName").equals(username) && resultSetNurse.getString("password").equals(password))
				{
					identity = 2;
					break;
				}
			}
			Statement myStat3 = myConn.createStatement();
			ResultSet resultSetPatient = myStat3.executeQuery("SELECT * FROM patient");
			while (resultSetPatient.next())
			{
				if (resultSetPatient.getString("userName").equals(username) && resultSetPatient.getString("password").equals(password))
				{
					identity = 3;
					break;
				}
			}
			
			
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		return identity;
	}*/
	
	
	
}
