package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet(description = "Servlet to handle User Login", urlPatterns = { "/UserLogin" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		User obj = new User();
		obj.setEmail(username);
		obj.setPassword(password);
		System.getLogger(username+password);
		UserDao ud = new UserDao();
		try {
			RequestDispatcher rd = request.getRequestDispatcher("./user/Login/index.jsp");
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			int result = ud.authLogin(obj);
			switch(result) {
				case 0:
					request.setAttribute("errorMessage", "Password Mismatch");
					rd.forward(request, response);
					break;
				case 1:
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(30*60);
					session.setAttribute("username", username);
					session.setAttribute("role", "user");
					System.out.println("Session created for user: " + session.getAttribute("username"));
					response.sendRedirect("./user/Home/index.jsp");
					break;
				case -1:
					request.setAttribute("errorMessage", "User Not found. Please Register");
					rd.forward(request, response);
					break;
				default:
					request.setAttribute("errorMessage", "Unknown Error");
					rd.forward(request, response);
					break;
			}			
		} catch (ClassNotFoundException e) {
			
			System.getLogger(e.getMessage());
		}
	}

}
