package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.RegisterService;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user = new User();

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RegisterService service=new RegisterService();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		int isUser = 0;
		try {
			isUser = service.isRegUser(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpSession session = request.getSession();

		if (isUser != 0) {
			session.setAttribute("error", "Email already exists");
			response.sendRedirect("index.jsp");
		} else {
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			try {
				user.setDateOfBirth(format.parse(request.getParameter("dob_year") + '/'
						+ request.getParameter("dob_month") + '/' + request.getParameter("dob_day")));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			user.setGender(request.getParameter("gender"));
			
			try {
				service.register(firstname, lastname, password, email, gender, user.getDateOfBirth());
				int userId=service.lastInsertedUserId();
				user.setUserId(userId);
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("user", user);
			
			response.sendRedirect("photostreams.jsp");
		}

	}

}
