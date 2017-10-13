package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.Dbconnection;
import entity.User;

public class LoginService {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();

	/**
	 * Selecting user data for LoginUser
	 * @param email
	 * @param password
	 * @return User
	 * @throws SQLException
	 */
	public User isLoginUser(String email, String password) throws SQLException {
		String sql = "select * from user where email='" + email + "' and password='" + password + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		User user = new User();

		if (result.next()) {
			do {
				user.setUserId(result.getInt("userid"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setPassword(result.getString("password"));
				user.setEmail(result.getString("email"));
				user.setGender(result.getString("gender"));
				user.setDateOfBirth(result.getDate("created_time"));
			} while (result.next());
		}
		return user;
	}

}
