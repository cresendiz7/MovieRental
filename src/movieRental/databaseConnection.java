package movieRental;
import java.sql.*;
import javax.swing.*;

public class databaseConnection {
Connection conn = null;

	public static Connection dbConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/project_wei", "cresendiz7", "123456");
			System.out.println("Connection Successful ");
			return conn;
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Connection was not successful.");
			return null;
		}
	}
}