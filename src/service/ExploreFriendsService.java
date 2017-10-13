package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import dbconnection.Dbconnection;

public class ExploreFriendsService {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();
	/**
	 * Selecting people who were followed by the login user
	 * @param userId
	 * @return HashMap<Integer,String>
	 * @throws SQLException
	 */
	public HashMap<Integer, String> selectFollowings(int userId) throws SQLException {
		String sql = "select u.userid,u.firstname,u.lastname from user u inner join follower_relation fr on fr.userid2=u.userid where fr.userid1="
				+ userId + " and fr.isfollow=1";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		HashMap<Integer, String> followings = new HashMap<Integer, String>();
		if (result.next()) {
			do {
				followings.put(result.getInt("userid"),
						result.getString("firstname") + " " + result.getString("lastname"));
			} while (result.next());
		}
		return followings;
	}

	/**
	 * Selecting people who follow login user
	 * @param userId
	 * @return HashMap<Integer,String>
	 * @throws SQLException
	 */
	public HashMap<Integer, String> selectFollowers(int userId) throws SQLException {
		String sql = "select u.userid,u.firstname,u.lastname from user u inner join follower_relation fr on fr.userid1=u.userid where fr.userid2="
				+ userId + " and fr.isfollow=1";

		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		HashMap<Integer, String> followers = new HashMap<Integer, String>();
		if (result.next()) {
			do {
				followers.put(result.getInt("userid"),
						result.getString("firstname") + " " + result.getString("lastname"));
			} while (result.next());
		}
		return followers;
	}

	/**
	 * Selecting people who are new friends for login user
	 * @param userId
	 * @return HashMap<Integer,String>
	 * @throws SQLException
	 */
	public HashMap<Integer, String> selectNewFriends(int userId) throws SQLException {
		String sql = "select u.userid,u.firstname,u.lastname from user u where u.userid not in (select fr.userid2 from follower_relation fr where fr.userid1="
				+ userId + " and fr.isfollow=1)";

		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		HashMap<Integer, String> newFriends = new HashMap<Integer, String>();
		if (result.next()) {
			do {
				newFriends.put(result.getInt("userid"),
						result.getString("firstname") + " " + result.getString("lastname"));
			} while (result.next());
		}
		return newFriends;
	}

}
