package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Booking;
import dao.BookingDao;

/**
 * Servlet implementation class GetParcel
 */
@WebServlet("/ScheduleParcel")
public class ScheduleParcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	System.Logger logger = System.getLogger("error");
	BookingDao bd = new BookingDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingid = (String)request.getParameter("tracking-id");
		try {
			Booking obj = bd.getBookingDetails(bookingid);
			if(obj!=null) {
				request.setAttribute("bookingid", bookingid);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				request.setAttribute("pickupdate", sdf.format(obj.getPickupDate()));
				logger.log(System.Logger.Level.INFO, "Details fetched and sent");
				request.getRequestDispatcher("./admin/SchedulePickup/index.jsp").forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "No booking found with this id");
				request.getRequestDispatcher("./admin/SchedulePickup/index.jsp").forward(request, response);
			}
		}
		catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
			throw new ServletException(e);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingid = (String)request.getParameter("tracking-id");
		String newdate = (String)request.getParameter("pickup-time");
		request.setAttribute("bookingid", bookingid);
		request.setAttribute("pickupdate", newdate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date pickupDate;
		try {
			pickupDate = sdf.parse(newdate);
			if(bd.schedulePickup(bookingid, pickupDate)) {
				request.setAttribute("errorMessage", "Successfully Updated");
				request.getRequestDispatcher("./admin/SchedulePickup/index.jsp").forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "Error in Scheduling Pickup");
				request.getRequestDispatcher("./admin/SchedulePickup/index.jsp").forward(request, response);
			}
		}
		catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
			throw new ServletException(e);
		}
		
	}

}
