package dao;

import java.sql.*;

import bean.User;

public class UserDao {
	
	public int authLogin(User obj) throws ClassNotFoundException {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		
		int result = 0;
		String sql = "select password from tbl_UserProfile where email = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getEmail());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(obj.getPassword().equals(rs.getString(1))) {
					result = 1;
					System.out.println("Password Match");
				}
				else {
					result = 0;
					System.out.println("Password Mismatch");
				}	
			}
			else {
				result = -1;
				System.out.println("No user found");
			}
			conn.close();
		} catch (Exception e) {
			System.getLogger(e.getMessage());
		}
		return result;
	}
	
	public boolean createUser(User obj) throws ClassNotFoundException {
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
			int rows =  ps.getUpdateCount();
			if(rows != -1) {
				System.out.println("Rows Updated: "+rows);
				result = true;
			}
			else {
				result = false;
			}
			conn.close();
		}
		catch (Exception e) {
			System.getLogger(e.getMessage());
		}
		return result;
	}
	
	public User getUser(String email) throws ClassNotFoundException {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		User obj=new User();
		String sql="select * from tbl_UserProfile where email=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				System.out.println("User found. Fetching Details...");
				obj.setName(rs.getString("FullName"));
				obj.setMobile(rs.getString("MobileNumber"));
				obj.setUserID(rs.getString("UserID"));
				obj.setAddress(rs.getString("Address"));
				System.out.println(obj.toString());
			}
			else {
				System.out.println("No such user found");
				obj = null;
			}
			conn.close();
		}
		catch (Exception e) {
			System.getLogger(e.getMessage());
		}
		return obj;
	}

}
