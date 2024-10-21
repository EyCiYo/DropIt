package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Booking;
import dao.BookingDao;

/**
 * Servlet implementation class ShowAllBookings
 */
@WebServlet("/ShowAllBookings")
public class ShowAllBookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	System.Logger logger = System.getLogger("error");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Booking> al = new ArrayList<Booking>();
		BookingDao bd = new BookingDao();
		
		try {
			al = bd.getAllBooking();
			if(al != null || al.size() != 0) {
				
			}
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
		}
		
	}

}
