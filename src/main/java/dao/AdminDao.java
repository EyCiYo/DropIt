package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import bean.Admin;

public class AdminDao {
	public int adminLogin(Admin obj) throws Exception {
		int result = 0;
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		String sql = "Select Password from tbl_Admin where Email = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getEmailId());
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
			ps.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServletException(e);
		}
		finally {
			cj.closeConnection();
		}
		return result;
	}
}
