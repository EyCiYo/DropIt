package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Connect_jdbc;

/**
 * Servlet implementation class HandleError
 */
@WebServlet("/HandleError")
public class HandleError extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connect_jdbc jdbcConnection = new Connect_jdbc(); // Create instance of Connect_jdbc

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the active connection (if any) using the Connect_jdbc class
        Connection conn = null;
        try {
            conn = jdbcConnection.connected();  // Get active connection
        } catch (Exception e) {
            e.printStackTrace();  // Handle ClassNotFoundException
        }

        if (conn != null) {
            // Close the active connection using the Connect_jdbc method
            jdbcConnection.closeConnection();  // Close the connection
            jdbcConnection.shutdownDatabase();
        }

        // Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Redirect to the home page
        response.sendRedirect("/DropIt/");
    }

}
