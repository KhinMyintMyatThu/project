package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.LoginService;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginService service=new LoginService();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User();
		try {
			user = service.isLoginUser(email, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpSession session = request.getSession();

		String message = " ";

		if (user.getFirstName()==null) {
			message = "Invalid user";
		}

		if (!message.equals(" ")) {
			session.setAttribute("message", message);
			response.sendRedirect("index.jsp");
		} else {

			session.setAttribute("user", user);

			response.sendRedirect("photostreams.jsp");
		}

	}

}
