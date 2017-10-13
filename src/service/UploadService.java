package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.Dbconnection;

public class UploadService {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();

	/**
	 * Inserting photo data
	 * @param photo
	 * @param description
	 * @param privacy
	 * @param userid
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public void insertPhoto(File photo, String description, String privacy, int userid)
			throws SQLException, FileNotFoundException {
		String sql = "insert into photo (photo,description,privacy,userid) values (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		FileInputStream fis = new FileInputStream(photo);
		stmt.setBinaryStream(1, (InputStream) fis, (int) (photo.length()));
		stmt.setString(2, description);
		stmt.setString(3, privacy);
		stmt.setInt(4, userid);
		stmt.executeUpdate();
	}
	/**
	 * Selecting last inserted photo id
	 * @return photoid
	 * @throws SQLException
	 */
	public int lastInsertedPhoto() throws SQLException {
		String sql = "select last_insert_id() as last_id from photo";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		result.next();
		int photoid = result.getInt("last_id");
		return photoid;
	}

	/**
	 * Searching the keyword is already existed or not
	 * @param keyword
	 * @return count
	 * @throws SQLException
	 */
	public int searchExistingKeyword(String keyword) throws SQLException {
		String sql = "select count(*) as n from tagged_keywords where keyword=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, keyword);
		ResultSet result = stmt.executeQuery();
		result.next();
		int count = result.getInt("n");
		return count;
	}

	/**
	 * Select existing keyword id
	 * @param keyword
	 * @return keywordId
	 * @throws SQLException
	 */
	public int existingKeywordId(String keyword) throws SQLException {
		String sql = "select keywordid from tagged_keywords where keyword=" + keyword;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		result.next();
		int keywordId = result.getInt("keywordid");
		return keywordId;
	}

	/**
	 * Inserting keyword
	 * @param keyword
	 * @throws SQLException
	 */
	public void insertKeyword(String keyword) throws SQLException {
		String sql = "insert into tagged_keywords (keyword) values (?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, keyword);
		stmt.executeUpdate();
	}

	/**
	 * Selecting last inserted keyword id
	 * @return keywordid
	 * @throws SQLException
	 */
	public int lastInsertedKeywordId() throws SQLException {
		String sql = "select last_insert_id() as last_id from tagged_keywords";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		result.next();
		int keywordid = result.getInt("last_id");
		return keywordid;
	}

	/**
	 * Inserting data into photo keyword map
	 * @param photoid
	 * @param keywordid
	 * @param keyword
	 * @throws SQLException
	 */
	public void insertPhotoKeywordMap(int photoid, int keywordid, String keyword) throws SQLException {
		String sql = "insert into photo_keyword_map (photoid, keywordid,keyword) values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, photoid);
		stmt.setInt(2, keywordid);
		stmt.setString(3, keyword);
		stmt.executeUpdate();
	}

	/**
	 * Inserting data into tagged people 
	 * @param photoid
	 * @param userid1
	 * @param userid2
	 * @throws SQLException
	 */
	public void insertTagPeople(int photoid, int userid1, int userid2) throws SQLException {
		String sql = "insert into tagged_people (photoid, userid1,userid2) values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, photoid);
		stmt.setInt(2, userid1);
		stmt.setInt(3, userid2);
		stmt.executeUpdate();
	}
	
	/**
	 * Check the photo keyword is existing or not in photo keyword map 
	 * @param photoid
	 * @param keywordid
	 * @return count
	 * @throws SQLException
	 */
	public int checkExistingPhotoKeyword(int photoid, int keywordid) throws SQLException {
		String sql = "select count(*) as n from photo_keyword_map where photoid=" + photoid + " and keywordid="
				+ keywordid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		result.next();
		int count = result.getInt("n");
		return count;
	}

}
