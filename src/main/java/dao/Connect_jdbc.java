package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;

public class Connect_jdbc {
	private Connection activeConnection = null;
	public Connection connected() throws Exception{
		System.Logger logger = System.getLogger("error");
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		String url = "jdbc:derby:C:\\Users\\alexc\\MyDB;create=true";
		if (activeConnection != null) {
            // Return the already established connection
            return activeConnection;
        }
		
		
		try {
			activeConnection = DriverManager.getConnection(url);
			if(activeConnection!=null) {
				logger.log(System.Logger.Level.INFO,"Connection Established!!");
			}
		}
		catch(Exception e){
			logger.log(System.Logger.Level.WARNING,"Unable to connect: " + e.getMessage());
			throw new ServletException(e);
		}
		return activeConnection;
	}
	
	// Close the active connection
    public void closeConnection() {
        if (activeConnection != null) {
            try {
                activeConnection.close();
                System.getLogger("error").log(System.Logger.Level.INFO, "Connection Closed");
            } catch (SQLException e) {
                System.getLogger("error").log(System.Logger.Level.WARNING, "Failed to close connection: " + e.getMessage());
            }
            activeConnection = null;  // Clear the connection after closing
        }
    }
    public void shutdownDatabase() {
        try {
            // This command will shutdown Derby
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            // This exception is expected if the shutdown is successful
            if ("XJ015".equals(e.getSQLState())) {
                System.out.println("Database shutdown successfully.");
            } else {
                e.printStackTrace(); // Handle other exceptions
            }
        }
    }
}
