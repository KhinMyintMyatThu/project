package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.Dbconnection;
import service.DisplayImageService;

/**
 * Display image
 *
 */
@WebServlet("/DisplayImage")
public class DisplayImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dbconnection dbConnect = new Dbconnection();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int photoId;
		BufferedInputStream bin;
		BufferedOutputStream bout;
		ServletOutputStream out;
		InputStream inputstream = null;

		DisplayImageService service=new DisplayImageService();
		response.setContentType("image/jpeg");
		
		out = response.getOutputStream();

		photoId = Integer.parseInt(request.getParameter("photoid"));
		try {
			inputstream = service.selectPhoto(photoId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		bin = new BufferedInputStream(inputstream);
		bout = new BufferedOutputStream(out);
		
		int ch = 0;
		while ((ch = bin.read()) != -1) {
			bout.write(ch);
		}
		bout.flush();

	}

}
