package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DeletePhotoService;

/**
 * Delete Photo
 */
@WebServlet("/DeletePhoto")
public class DeletePhoto extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int photoId = Integer.parseInt(request.getParameter("photoid"));
		DeletePhotoService service=new DeletePhotoService();
		
		try {
			service.deletePhoto(photoId);
			response.sendRedirect("photostreams.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
