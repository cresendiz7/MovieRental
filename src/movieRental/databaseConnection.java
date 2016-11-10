package movieRental;
import java.sql.*;
import javax.swing.*;

public class databaseConnection {
Connection conn = null;
	
	public static Connection dbConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/project_wei", "root", "1234");
			System.out.println("Connection Successful ");
			return conn;
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Connection was not successful.");
			return null;
		}
	}
}
