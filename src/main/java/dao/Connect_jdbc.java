package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_jdbc {
	public Connection connected() throws ClassNotFoundException{
		System.Logger logger = System.getLogger("error");
		Connection connect = null;
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		String url = "jdbc:derby:C:\\Users\\alexc\\MyDB;create=true";
		try {
			connect = DriverManager.getConnection(url);
			if(connect!=null) {
				logger.log(System.Logger.Level.INFO,"Connection Established!!");
			}
		}
		catch(SQLException e){
			logger.log(System.Logger.Level.WARNING,"Unable to connect: " + e.getMessage());
		}
		return connect;
	}
}
