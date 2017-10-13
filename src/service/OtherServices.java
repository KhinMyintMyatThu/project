package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Dbconnection;
import entity.Photo;

/**
 * Include insert, Update, Delete, Retrieve queries
 */
public class OtherServices {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();
	
	/**
	 * Selecting photoid by userid
	 * @param userid
	 * @return photoId
 	 * @throws SQLException
	 */
	public List<Integer> selectPhotoIdByUserId(int userid) throws SQLException {
		String sql = "select photoid from photo where userid=" + userid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<Integer> photoId = new ArrayList<Integer>();
		if (result.next()) {
			do {
				photoId.add(result.getInt("photoid"));
			} while (result.next());
		}
		return photoId;
	}

	/**
	 * Selecting photo description by userid
	 * @param userid
	 * @return description
	 * @throws SQLException
	 */
	public List<String> selectDescriptionByUserId(int userid) throws SQLException {
		String sql = "select description from photo where userid=" + userid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<String> description = new ArrayList<String>();
		if (result.next()) {
			do {
				description.add(result.getString("description"));
			} while (result.next());
		}
		return description;
	}

	/**
	 * Selecting photo keyword by photoid
	 * @param photoid
	 * @return keywords
	 * @throws SQLException
	 */
	public List<String> selectKeywordByPhotoId(int photoid) throws SQLException {
		String sql = "select k.keyword from tagged_keywords k inner join photo_keyword_map map on map.keywordid=k.keywordid where map.photoid="
				+ photoid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<String> keywords = new ArrayList<String>();
		if (result.next()) {
			do {
				keywords.add(result.getString("keyword"));
			} while (result.next());
		}
		return keywords;
	}

	/**
	 * Selecting photo data by photoid
	 * @param photoid
	 * @return photo
	 * @throws SQLException
	 */
	public Photo selectPhotoDetailByPhotoId(int photoid) throws SQLException {
		String sql = "select description,privacy from photo where photoid=" + photoid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		Photo photo = new Photo();
		result.next();
		photo.setDescription(result.getString("description"));
		photo.setPrivacy(result.getString("privacy"));
		return photo;
	}

	/**
	 * Selecting photo description
	 * @param userid
	 * @return description
	 * @throws SQLException
	 */
	public List<String> selectTagPhotoDes(int userid) throws SQLException {
		String sql = "select p.description from photo p inner join tagged_people tp on tp.photoid=p.photoid where tp.userid2="
				+ userid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<String> description = new ArrayList<String>();
		while (result.next()) {
			description.add(result.getString("description"));
		}

		return description;
	}

	/**
	 * Select photoid for tagged people photo
	 * @param userid
	 * @return photoId
	 * @throws SQLException
	 */
	public List<Integer> selectTagPhotoId(int userid) throws SQLException {
		String sql = "select p.photoid from photo p inner join tagged_people tp on tp.photoid=p.photoid where tp.userid2="
				+ userid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<Integer> photoId = new ArrayList<Integer>();
		while (result.next()) {
			photoId.add(result.getInt("photoid"));
		}

		return photoId;
	}

	/**
	 * Select photo keyword for tagged people photo
	 * @param photoid
	 * @return
	 * @throws SQLException
	 */
	public List<String> selectTagPhotoKeywords(int photoid) throws SQLException {
		String sql = "select k.keyword from tagged_keywords k inner join photo_keyword_map map on map.keywordid=k.keywordid where map.photoid="
				+ photoid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<String> keywords = new ArrayList<String>();
		while (result.next()) {
			keywords.add(result.getString("keyword"));
		}
		return keywords;
	}
}
