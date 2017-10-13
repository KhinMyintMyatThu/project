package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbconnection.Dbconnection;

public class MakeFriendshipService {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();

	/**
	 * Insert the relation between friends
	 * 
	 * @param userid1
	 * @param userid2
	 * @throws SQLException
	 */
	public void follow(int userid1, int userid2) throws SQLException {
		String sql = "insert into follower_relation (userid1,userid2,isfollow) values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, userid1);
		stmt.setInt(2, userid2);
		stmt.setBoolean(3, true);
		stmt.executeUpdate();
	}

	/**
	 * Update the relation between friends
	 * 
	 * @param userid1
	 * @param userid2
	 * @throws SQLException
	 */
	public void unfollow(int userid1, int userid2) throws SQLException {
		String sql = "update follower_relation set isfollow=? where userid1=? and userid2=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setBoolean(1, false);
		stmt.setInt(2, userid1);
		stmt.setInt(3, userid2);
		stmt.executeUpdate();
	}

}
