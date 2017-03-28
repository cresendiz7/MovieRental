package movieRental;
import java.sql.*;
import javax.swing.*;

public class databaseConnection {
Connection conn = null;

	public static Connection dbConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mysql3.gear.host:3306/movierental?autoReconnect=true&useSSL=false", "movierental", "Hd46~Ld9mH_s");
			System.out.println("Connection Successful ");
			return conn;
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Connection was not successful.");
			return null;
		}
	}
}

//To access online database 
// use this:   Connection conn = DriverManager.getConnection("jdbc:mysql://mysql3.gear.host:3306/movierental?autoReconnect=true&useSSL=false", "movierental", "Hd46~Ld9mH_s");

//To access local database
// use this:   Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3306/project_wei?autoReconnect=true&useSSL=false", "root", "1234");