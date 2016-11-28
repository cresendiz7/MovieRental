package movieRental;
import java.sql.*;
import javax.swing.*;

public class databaseConnection {
Connection conn = null;

	public static Connection dbConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9146720?autoReconnect=true&useSSL=false", "sql9146720", "a1PU1hi6wS");
			System.out.println("Connection Successful ");
			return conn;
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Connection was not successful.");
			return null;
		}
	}
}

//To access online database 
// use this:   Connection conn = DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9146720?autoReconnect=true&useSSL=false", "sql9146720", "a1PU1hi6wS");

//To access local database
// use this:   Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3306/project_wei?autoReconnect=true&useSSL=false", "root", "1234");