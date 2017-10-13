package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Photo;
import entity.User;
import service.DetailPhotoService;

/**
 * Servlet implementation class DetailPhoto
 */
@WebServlet("/DetailPhoto")
public class DetailPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Photo photo = new Photo();
		User user = new User();

		DetailPhotoService service = new DetailPhotoService();

		/**
		 * To show tagged people name in photo
		 */
		List<User> tagPeopleList = new ArrayList<User>();
		List<String> tagPeopleName = new ArrayList<String>();

		int photoId = Integer.parseInt(request.getParameter("photoid"));

		try {
			photo = service.selectPhotoDetail(photoId);
			user = service.selectUserDetail(photoId);

			tagPeopleList = service.selectTagPeople(user.getUserId(), photoId);

			/**
			 * Concatenating first name and last name of each user
			 */
			for (User u : tagPeopleList) {
				tagPeopleName.add(u.getFirstName() + " " + u.getLastName());
			}

			request.getSession(false).setAttribute("photodetail", photo);
			request.getSession(false).setAttribute("selectedphotoid", photoId);
			request.getSession(false).setAttribute("username", user.getFirstName() + " " + user.getLastName());
			request.getSession(false).setAttribute("tagpeoplelist", tagPeopleName);
			response.sendRedirect("detailphoto.jsp");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
