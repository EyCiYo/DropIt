package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_jdbc {
	public Connection connected() throws ClassNotFoundException{
		Connection connect = null;
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		String url = "jdbc:derby:C:\\Users\\alexc\\MyDB;create=true";
		try {
			connect = DriverManager.getConnection(url);
			if(connect!=null) {
				System.out.println("Connection Established!!");
			}
		}
		catch(SQLException e){
			System.out.println("Unable to connect: " + e.getMessage());
		}
		return connect;
	}
}
