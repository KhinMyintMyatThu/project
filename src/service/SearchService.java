package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Dbconnection;

public class SearchService {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();

	/**
	 * Searching the photo by keyword
	 * 
	 * @param typedString
	 * @return photoId
	 * @throws SQLException
	 */
	public List<Integer> searchByKeyword(String typedString) throws SQLException {
		String sql = "select p.photoid from photo p inner join photo_keyword_map map on map.photoid=p.photoid where keyword like '"
				+ typedString + "%'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<Integer> photoId = new ArrayList<Integer>();
		while (result.next()) {
			photoId.add(result.getInt("photoid"));
		}
		return photoId;
	}

	/**
	 * Searching the photo by people
	 * 
	 * @param typedString
	 * @return photoId
	 * @throws SQLException
	 */
	public List<Integer> searchByPeople(String typedString) throws SQLException {
		String sql = "SELECT tp.photoid FROM tagged_people tp INNER JOIN user u on u.userid=tp.userid1 WHERE u.firstname like '"
				+ typedString + "%'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<Integer> photoId = new ArrayList<Integer>();
		while (result.next()) {
			photoId.add(result.getInt("photoid"));
		}
		return photoId;
	}

	/**
	 * Searching the photo by tagged people
	 * 
	 * @param typedString
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> searchByTagPeople(String typedString) throws SQLException {
		String sql = "SELECT tp.photoid FROM tagged_people tp INNER JOIN user u on u.userid=tp.userid2 WHERE u.firstname like '"
				+ typedString + "%'";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<Integer> photoId = new ArrayList<Integer>();
		while (result.next()) {
			photoId.add(result.getInt("photoid"));
		}
		return photoId;
	}

}
