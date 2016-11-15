package movieRental;
import java.sql.*;
import javax.swing.*;

public class databaseConnection {
Connection conn = null;

	public static Connection dbConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3306/project_wei?autoReconnect=true&useSSL=false", "root", "1234");
			System.out.println("Connection Successful ");
			return conn;
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Connection was not successful.");
			return null;
		}
	}
}

//To access online database 
// use this:   Connection conn = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/project_wei?autoReconnect=true&useSSL=false", "cresendiz7", "123456");

//To access local database
// use this:   Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3306/project_wei?autoReconnect=true&useSSL=false", "root", "1234");