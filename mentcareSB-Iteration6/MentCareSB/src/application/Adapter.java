package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executor;

public class Adapter
{
	private static Connection myConn;
	
	public static void connect() 
	{
		try
		{
			// Data below is for my local database, so ignore it
			//String host = "jdbc:mysql://localhost:3306/mentcaresb";
			//String uName = "Sarefx";
			//String uPass = "1234";
			String host = "jdbc:mysql://50.62.209.186:3306/mentcaresb", uName = "TeamMentCareSB", uPass = "P4$$word";
			if (myConn != null && !myConn.isClosed())
				myConn.close();
			myConn = DriverManager.getConnection(host, uName, uPass);
		
		}
		catch(SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
	}
	public static void addRow(String tableName, String fieldNames, String values)
	{
		String sql = "INSERT INTO "+tableName+" ("+fieldNames+") VALUES ("+values+");";
		try
		{
			PreparedStatement pr = myConn.prepareStatement(sql);
			pr.execute();
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
	}
	public static void updateRow(String tableName, String values, String userIdNumber)
	{
		//UPDATE [table name] SET [VALUES] WHERE 'idNumber' = "userIdNumber";
		String sql = "UPDATE "+tableName+" SET "+values+" WHERE idNumber = "+userIdNumber+"";
		try
		{
			PreparedStatement pr = myConn.prepareStatement(sql);
			pr.execute();
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
	}
	public static void injection(String injection)
	{
		try
		{
			PreparedStatement pr = myConn.prepareStatement(injection);
			pr.execute();
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
	}
	
	public static void deleteRow(String tableName, String idNumber)
	{
		String sql = "DELETE FROM "+tableName+" WHERE idNumber = '"+idNumber+"';";
		try
		{
			PreparedStatement pr = myConn.prepareStatement(sql);
			pr.execute();
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
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
	public static ResultSet getResultSetRow(String tableName, String idNumber)
	{
		ResultSet MyResultSet=null;
		try
		{
			Statement myStat = myConn.createStatement();
			MyResultSet = myStat.executeQuery("SELECT * FROM "+tableName);
			while (MyResultSet.next())
			{
				if (MyResultSet.getString("idNumber").equals(idNumber))
				{
					MyResultSet.absolute(MyResultSet.getRow());
					break;
				}
			}
		}
		catch (SQLException err)
		{
			System.out.println( err.getMessage( ) );
		}
		return MyResultSet;
	}
	public static String getString(String tableName, String idNumber, String searchValue)
	{
		//SELECT * FROM [table name] WHERE idNumber = "idNumber";
		String str="";
		try
		{
			Statement myStat = myConn.createStatement();
			ResultSet myRs = myStat.executeQuery("SELECT * FROM "+tableName);
			while (myRs.next())
			{
				if (myRs.getString("idNumber").equals(idNumber))
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