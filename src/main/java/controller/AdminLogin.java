package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import dao.AdminDao;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		Admin obj = new Admin();
		obj.setEmailId(username);
		obj.setPassword(password);
		System.out.println(username+password);
		AdminDao ad = new AdminDao();
		
		try {
			RequestDispatcher rd = request.getRequestDispatcher("./admin/Login/index.jsp");
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			int result = ad.adminLogin(obj);
			switch(result) {
			case 0:
				request.setAttribute("errorMessage", "Password Mismatch");
				rd.forward(request, response);
				break;
			case 1:
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(30*60);
				session.setAttribute("username", username);
				session.setAttribute("role", "admin");
				System.out.println("Session created for admin: "+session.getAttribute("username"));
				response.sendRedirect("./admin/Home/index.jsp");
			case -1:
				request.setAttribute("errorMessage", "User Not found. Please Register");
				rd.forward(request, response);
				break;
			default:
				request.setAttribute("errorMessage", "Unknown Error");
				rd.forward(request, response);
				break;
			}
		}
		catch (Exception e) {
			System.getLogger(e.getMessage());
		}
		finally {
			
		}
		
	}

}
