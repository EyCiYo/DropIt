package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;

/**
 * Servlet implementation class UserReg
 */
@WebServlet("/UserReg")
public class UserReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	System.Logger logger = System.getLogger("error");
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User obj = new User();
		obj.setUserID(request.getParameter("username"));
		obj.setName(request.getParameter("full_name"));
		obj.setEmail(request.getParameter("email"));
		obj.setAddress(request.getParameter("address"));
		obj.setMobile(request.getParameter("phone"));
		obj.setPassword(request.getParameter("confirm_password"));
		String[] preferences = request.getParameterValues("preferences");
		for (int i = 0; i < preferences.length; i++) {
			if(preferences[i].equals("email")) {
				obj.setEmailNotifications(true);
			}
			if(i==1 && preferences[i].equals("sms")) {
				obj.setSmsNotifications(true);
			}
		}
		
		UserDao ud = new UserDao();
		String err = ud.validateUserReg(obj);
		if(!err.equals("Success")) {
			request.setAttribute("errorMessage", err);
			System.out.println("Error in validation");
			RequestDispatcher rd = request.getRequestDispatcher("./user/Registration/index.jsp");
			rd.forward(request, response);
		}
		try {
			if(ud.createUser(obj)) {
				System.getLogger("User Created Successfully");
				response.sendRedirect("./user/Home/index.jsp");
			}else {
				request.setAttribute("errorMessage", "Error While creating User");
				RequestDispatcher rd = request.getRequestDispatcher("./user/Registration/index.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.getLogger(e.getMessage());
			throw new ServletException(e);
		}
		
	}

}
