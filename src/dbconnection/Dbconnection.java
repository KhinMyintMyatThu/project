package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Dbconnection {

	/**
	 * @param args
	 */

	public Connection DBconnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found : "+e);
		}
		
		String url="jdbc:mysql://localhost/imagearchivesystem";
		String user="root";
		String password="root";
		
		Connection con=null;
		try {
			con=DriverManager.getConnection(url,user,password);
		}catch(SQLException e) {
			System.out.println("Something went wrong in the connection string");
		}
		return con;
	}
}
