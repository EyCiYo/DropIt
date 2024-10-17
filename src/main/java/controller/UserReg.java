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
	private String validateUserReg(User obj) {
		
		        String name = obj.getName();
		        String address = obj.getAddress();
		        String email =obj.getEmail();
		        String mobile =obj.getMobile();
		        String password =obj.getPassword();
		        String cpassword = obj.getPassword();
		        

		        // Basic validation
		        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
		            return "Please fill in all the required fields.";
		        }
		        
		        if (name.length() > 30){
		            return "Maximum length of name allowed is 30.";
		        } else if (name.length() < 5) {
		            return "Minimum length of name allowed is 5.";
		        }
		        
		        // Email validation
		        if (!validateEmail(email)) {
		           
		            return("Please enter a valid email address.");
		        }
		        
		        // Password validation
		        if (!pswCheck(password)) {
		           
		            return("Please follow password creation criteria.");
		        } else if (!password.equals(cpassword)) {
		           
		            return("Password and Confirm password need to be the same.");
		        }
		        
		        // Mobile number validation (10 digits)
		        if (!validateMobile(mobile)) {
		           
		            return("Please enter a valid mobile number (10 digits).");
		        }
		        
		        // If validation passes
		        return("Registration successful!");		   
		        }
	private boolean validateEmail(String email) {
	    String emailPattern = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
	    return Pattern.matches(emailPattern, email);
	}

	  private boolean validateMobile(String mobile) {
	        String mobilePattern = "^\\d{10}$";
	        return Pattern.matches(mobilePattern, mobile);
	    }
	  private boolean pswCheck(String password) {
	        String upperCaseLetters = ".*[A-Z].*";
	        String lowerCaseLetters = ".*[a-z].*";
	        String numbers = ".*[0-9].*";
	        
	        if (password.length() < 8 || password.length() > 16) {
	            return false;
	        } else if (!password.matches(numbers)) {
	            return false;
	        } else if (!password.matches(upperCaseLetters)) {
	            return false;
	        } else if (!password.matches(lowerCaseLetters)) {
	            return false;
	        } else {
	            return true;
	        }
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Called Reg servlet Post");
		User obj = new User();
		obj.setUserID(request.getParameter("username"));
		obj.setName(request.getParameter("full_name"));
		obj.setEmail(request.getParameter("email"));
		obj.setAddress(request.getParameter("address"));
		obj.setMobile(request.getParameter("phone"));
		obj.setPassword(request.getParameter("confirm_password"));
		String preferences[] = request.getParameterValues("preferences");
		for (int i = 0; i < preferences.length; i++) {
			if(preferences[i].equals("email")) {
				obj.setEmailNotifications(true);
			}
			if(i==1 && preferences[i].equals("sms")) {
				obj.setSmsNotifications(true);
			}
		}
		UserDao ud = new UserDao();
		try {
			if(ud.createUser(obj)) {
				System.out.println("User Created Successfully");
				response.sendRedirect("./user/Home/index.jsp");
			}else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert(\"Not Success\")</script>");
				RequestDispatcher rd = request.getRequestDispatcher("./user/Registration/index.jsp");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			System.getLogger(e.getMessage());
		}
		
	}

}
