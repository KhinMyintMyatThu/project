package service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.Dbconnection;

public class DisplayImageService {
	Dbconnection dbConnect = new Dbconnection();
	Connection con = dbConnect.DBconnect();

	/**
	 * Select specific photo to display
	 * 
	 * @param photoId
	 * @return InputStream
	 * @throws SQLException
	 */
	public InputStream selectPhoto(int photoId) throws SQLException {
		String sql = "select photo from photo where photoid=" + photoId;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		InputStream inputstream = null;
		if (result.next()) {
			inputstream = result.getBinaryStream(1);
		}
		return inputstream;
	}

}
