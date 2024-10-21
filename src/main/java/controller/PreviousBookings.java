package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Booking;
import dao.BookingDao;

/**
 * Servlet implementation class PreviousBookings
 */
@WebServlet("/PreviousBookings")
public class PreviousBookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	System.Logger logger = System.getLogger("error");
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BookingDao bd = new BookingDao();
		ArrayList<Booking> al = new ArrayList<Booking>();
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = sdf.parse(fromdate);
			toDate = sdf.parse(todate);
			al = bd.getPreviousBooking(email, fromDate, toDate);
			request.setAttribute("bookingList", al);
			request.setAttribute("email", email);
			request.getRequestDispatcher("./admin/PreviousBooking/index.jsp").forward(request, response);
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
		}
		
	}

}
