package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.ExploreFriendsService;

@WebServlet("/ExploreFriends")
public class ExploreFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HashMap<Integer, String> followers = new HashMap<Integer, String>();
		HashMap<Integer, String> followings = new HashMap<Integer, String>();
		HashMap<Integer, String> newFriends = new HashMap<Integer, String>();
		
		User user = (User) request.getSession(false).getAttribute("user");
		ExploreFriendsService service=new ExploreFriendsService();
		try {
			followers=service.selectFollowers(user.getUserId());
			followings=service.selectFollowings(user.getUserId());
			newFriends=service.selectNewFriends(user.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		request.getSession(false).setAttribute("followers", followers);
		request.getSession(false).setAttribute("followings", followings);
		request.getSession(false).setAttribute("newfriends", newFriends);
		response.sendRedirect("explorefriends.jsp");
	}

}
