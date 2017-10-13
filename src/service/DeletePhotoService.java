package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbconnection.Dbconnection;

public class DeletePhotoService {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();

	/**
	 * Delete photo by photo id
	 * @param photoId
	 * @throws SQLException
	 */
	public void deletePhoto(int photoId) throws SQLException {
		String sql = "Delete from photo where photoid=" + photoId;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
	}

}
