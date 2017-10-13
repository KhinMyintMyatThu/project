package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import dbconnection.Dbconnection;

public class RegisterService {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();

	/**
	 * Check the user is already registered or not
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public int isRegUser(String email) throws SQLException {
		String sql = "select count(*) n from user where email='" + email + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		result.next();
		int count = result.getInt("n");
		return count;
	}

	/**
	 * Register user
	 * 
	 * @param firstname
	 * @param lastname
	 * @param password
	 * @param email
	 * @param gender
	 * @param dateofbirth
	 * @throws SQLException
	 */
	public void register(String firstname, String lastname, String password, String email, String gender,
			Date dateofbirth) throws SQLException {
		String sql = "insert into user (firstname,lastname,password,email,gender,dateofbirth,created_time) values (?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, firstname);
		stmt.setString(2, lastname);
		stmt.setString(3, password);
		stmt.setString(4, email);
		stmt.setString(5, gender);
		stmt.setDate(6, new java.sql.Date(dateofbirth.getTime()));
		stmt.setTimestamp(7, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
		stmt.executeUpdate();
	}

	/**
	 * Select last inserted user id
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int lastInsertedUserId() throws SQLException {
		String sql = "select last_insert_id() as last_id from user";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		result.next();
		int userid = result.getInt("last_id");
		return userid;
	}

}
