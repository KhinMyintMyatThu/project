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

import dbconnection.Dbconnection;
import entity.User;
import service.SearchService;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dbconnection dbconnect = new Dbconnection();
	User user = new User();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Integer> photoIdByKeyword = new ArrayList<Integer>();
		List<Integer> photoIdByPeople = new ArrayList<Integer>();
		List<Integer> photoIdByTagPeople = new ArrayList<Integer>();
		
		SearchService service=new SearchService();
		String typedString = request.getParameter("search");
		
		try {
			photoIdByKeyword=service.searchByKeyword(typedString);
			photoIdByPeople=service.searchByPeople(typedString);
			photoIdByTagPeople=service.searchByTagPeople(typedString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getSession(false).setAttribute("photoidbykeyword", photoIdByKeyword);
		request.getSession(false).setAttribute("photoidbypeople", photoIdByPeople);
		request.getSession(false).setAttribute("photoidbytagpeople", photoIdByTagPeople);
		response.sendRedirect("search.jsp");

	}

}
