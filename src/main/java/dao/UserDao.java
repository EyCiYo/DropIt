package dao;

import java.sql.*;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import bean.User;

public class UserDao {
	
	System.Logger logger = System.getLogger("error");
	public String validateUserReg(User obj) {
		
		String name = obj.getName();
		String address = obj.getAddress();
		String email = obj.getEmail();
		String mobile = obj.getMobile();
		String password = obj.getPassword();
		String cpassword = obj.getPassword();

		// Basic validation
		if (name.isEmpty() || address.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty()
				|| cpassword.isEmpty()) {
			return "Please fill in all the required fields.";
		}

		if (name.length() > 30) {
			return "Maximum length of name allowed is 30.";
		} else if (name.length() < 5) {
			return "Minimum length of name allowed is 5.";
		}

		// Email validation
		if (!validateEmail(email)) {

			return ("Please enter a valid email address.");
		}

		// Password validation
		if (!pswCheck(password)) {

			return ("Please follow password creation criteria.");
		} else if (!password.equals(cpassword)) {

			return ("Password and Confirm password need to be the same.");
		}

		// Mobile number validation (10 digits)
		if (!validateMobile(mobile)) {

			return ("Please enter a valid mobile number (10 digits).");
		}

		// If validation passes
		return ("Success");
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

		if (password.length() < 8 || password.length() > 16 || !password.matches(numbers)
				|| !password.matches(upperCaseLetters) || !password.matches(lowerCaseLetters)) {
			return false;
		} else {
			return true;
		}
	}

	public int authLogin(User obj) throws Exception {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();

		int result = 0;
		String sql = "select password from tbl_UserProfile where email = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getEmail());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (obj.getPassword().equals(rs.getString(1))) {
					result = 1;
					logger.log(System.Logger.Level.INFO,"Password Match");
				} else {
					result = 0;
					logger.log(System.Logger.Level.INFO,"Password Mismatch");
				}
			} else {
				result = -1;
				logger.log(System.Logger.Level.INFO,"No user found");
			}
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING,e.getMessage());
			throw new ServletException(e);
		} finally {
			cj.closeConnection();
		}
		return result;
	}

	public boolean createUser(User obj) throws Exception {
		boolean result = false;
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();

		String sql = "INSERT INTO tbl_UserProfile VALUES(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getUserID());
			ps.setString(2, obj.getName());
			ps.setString(3, obj.getAddress());
			ps.setString(4, obj.getEmail());
			ps.setString(5, obj.getMobile());
			ps.setString(6, obj.getPassword());
			ps.setBoolean(7, obj.getEmailNotifications());
			ps.setBoolean(8, obj.getSmsNotifications());
			ps.execute();
			int rows = ps.getUpdateCount();
			if (rows != -1) {
				logger.log(System.Logger.Level.INFO,"Rows Updated: " + rows);
				result = true;
			}
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING,e.getMessage());
			throw new ServletException(e);
		} finally {
			cj.closeConnection();
			
		}
		return result;
	}

	public User getUser(String email) throws Exception {
		System.out.println(email);
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		User obj = new User();
		String sql = "select * from tbl_UserProfile where email=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				logger.log(System.Logger.Level.INFO,"User found. Fetching Details...");
				obj.setName(rs.getString("FullName"));
				obj.setMobile(rs.getString("MobileNumber"));
				obj.setUserID(rs.getString("UserID"));
				obj.setAddress(rs.getString("Address"));
				logger.log(System.Logger.Level.INFO,obj.toString());
			} else {
				logger.log(System.Logger.Level.INFO,"No such user found");
				obj = null;
			}
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING,e.getMessage());
			throw new ServletException(e);
		} finally {
			cj.closeConnection();
		}
		return obj;
	}

}
