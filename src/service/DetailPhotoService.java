package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Dbconnection;
import entity.Photo;
import entity.User;

public class DetailPhotoService {

	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();

	/**
	 * Selecting photo data to show in detail
	 * 
	 * @param photoId
	 * @return Photo
	 * @throws SQLException
	 */
	public Photo selectPhotoDetail(int photoId) throws SQLException {
		String sql = "select p.description,p.created_time,p.privacy from photo p join user u on p.userid=u.userid where p.photoid="
				+ photoId;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		Photo photo = new Photo();
		result.next();
		photo.setDescription(result.getString("description"));
		photo.setCreated_time(result.getDate("created_time"));
		photo.setPrivacy(result.getString("privacy"));
		return photo;
	}

	/**
	 * Selecting the photo uploaded user id and name in order to show in detail
	 * photo page
	 * 
	 * @param photoId
	 * @return User
	 * @throws SQLException
	 */
	public User selectUserDetail(int photoId) throws SQLException {
		String sql = "select u.userid,u.firstname,u.lastname from photo p join user u on p.userid=u.userid where p.photoid="
				+ photoId;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		User user = new User();
		result.next();
		user.setUserId(result.getInt("userid"));
		user.setFirstName(result.getString("firstname"));
		user.setLastName(result.getString("lastname"));
		return user;
	}

	/**
	 * Selecting people who were tagged in the photo
	 * 
	 * @param userId
	 * @param photoId
	 * @return User
	 * @throws SQLException
	 */
	public List<User> selectTagPeople(int userId, int photoId) throws SQLException {
		String sql = "select u.firstname,u.lastname from user u join tagged_people tp on tp.userid2=u.userid where tp.userid1="
				+ userId + " and tp.photoid=" + photoId;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		List<User> tagPeopleList = new ArrayList<User>();
		if (result.next()) {
			do {
				User user = new User();
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				tagPeopleList.add(user);
			} while (result.next());
		}
		return tagPeopleList;
	}

}
