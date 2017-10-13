package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.MakeFriendshipService;

/**
 * Servlet implementation class MakeFriendship
 */
@WebServlet("/MakeFriendship")
public class MakeFriendship extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user = new User();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userid2 = Integer.parseInt(request.getParameter("id"));
		String isfollow = request.getParameter("isfollow");
		user = (User) request.getSession(false).getAttribute("user");

		MakeFriendshipService service=new MakeFriendshipService();
		
		if (isfollow.equals("follow")) {
			try {
				service.follow(user.getUserId(), userid2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (isfollow.equals("following")) {
			try {
				service.unfollow(user.getUserId(), userid2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		response.sendRedirect("ExploreFriends");
	}

	
}
