package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Booking;
import dao.BookingDao;

/**
 * Servlet implementation class HomeTrack
 */
@WebServlet("/HomeTrack")
public class HomeTrack extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingid = request.getParameter("tracking-id");
		String email = request.getParameter("email-id");
		RequestDispatcher rd = request.getRequestDispatcher("./hometracking.jsp");
		BookingDao bd = new BookingDao();
		
		try {
			Booking obj = bd.getTracking(bookingid,email);
			if(obj != null) {
				request.setAttribute("bookingid", obj.getBookingId());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String deliveryDate = sdf.format(obj.getDropoffDate());
				String bookingDate = sdf.format(obj.getBookingDate());
				request.setAttribute("deliverydate", deliveryDate);
				request.setAttribute("bookingdate", bookingDate);
				request.setAttribute("status", obj.getStatus());
				rd.forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "No booking found with this ID");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.getLogger(e.getMessage());
		}
		finally {
			System.getLogger("Completed tracking");
		}
	}

}
