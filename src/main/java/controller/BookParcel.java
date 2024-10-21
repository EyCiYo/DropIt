package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Booking;
import dao.BookingDao;

/**
 * Servlet implementation class BookParcel
 */
@WebServlet("/BookParcel")
public class BookParcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.Logger logger = System.getLogger("error");

		// Retrieve sender information
		String senderName = request.getParameter("sendername");
		request.setAttribute("senderName", senderName);
		String senderAddress = request.getParameter("senderaddress");
		request.setAttribute("senderAddress", senderAddress);
		String senderEmail = request.getParameter("senderemail");
		request.setAttribute("senderEmail", senderEmail);
		String senderMobile = request.getParameter("sendermobile");
		request.setAttribute("senderMobile", senderMobile);

		// Retrieve receiver information
		String recName = request.getParameter("recname");
		request.setAttribute("recname", recName);
		String recAddress = request.getParameter("recaddress");
		request.setAttribute("recaddress", recAddress);
		String recEmail = request.getParameter("recemail");
		request.setAttribute("recemail", recEmail);
		String recMobile = request.getParameter("recmobile");
		request.setAttribute("recmobile", recMobile);

		// Retrieve parcel details
		double length = Double.parseDouble(request.getParameter("length"));
		request.setAttribute("length", length);
		double breadth = Double.parseDouble(request.getParameter("breadth"));
		request.setAttribute("breadth", breadth);
		double height = Double.parseDouble(request.getParameter("height"));
		request.setAttribute("height", height);
		double weight = Double.parseDouble(request.getParameter("weight"));
		request.setAttribute("weight", weight);
		String description = request.getParameter("description");
		request.setAttribute("description", description);

		// Retrieve shipping options
		String shippingOption = request.getParameter("shippingoption");
		
		String packagingType = request.getParameter("packagingtype");

		// Retrieve pickup and dropoff dates

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date pickupDate = null;
		Date dropDate = null;
		try {
			pickupDate = dateFormat.parse(request.getParameter("pickup-date"));
			dropDate = dateFormat.parse(request.getParameter("drop-date")); // For dropoff date
		} catch (ParseException e) {
			logger.log(System.Logger.Level.ERROR,e.getMessage());
		}

		// Status set to "Booked" when a new booking is created
		String status = "Booked";

		// Create and populate the Booking object
		Booking booking = new Booking();
		booking.setSenderName(senderName);
		booking.setSenderAddress(senderAddress);
		booking.setSenderEmail(senderEmail);
		booking.setSenderMobile(senderMobile);

		booking.setReceiverName(recName);
		booking.setReceiverAddress(recAddress);
		booking.setReceiverEmail(recEmail);
		booking.setReceiverMobile(recMobile);

		booking.setLength(length);
		booking.setHeight(height);
		booking.setWidth(breadth); // Assuming "breadth" is width
		booking.setWeight(weight);
		booking.setContentDescription(description);

		booking.setShippingSpeed(shippingOption);
		booking.setPackingType(packagingType);

		booking.setBookingDate(new Date());
		booking.setPickupDate(pickupDate);
		booking.setDropoffDate(dropDate);

		booking.setStatus(status);
		BookingDao bd = new BookingDao();
		String err=bd.processBooking(booking);
		HttpSession session = request.getSession(false);
		String curUser = (String)session.getAttribute("role"); 
		if(!err.equals("Success")) {
			logger.log(System.Logger.Level.ERROR,"Error in validation");
			request.setAttribute("errorMessage", err);
			RequestDispatcher rd = request.getRequestDispatcher("./"+ curUser+"/Booking/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		String bid = null;
		try {
			bid = bd.createNewBooking(booking);
			logger.log(System.Logger.Level.INFO, "Booking created. Redirecting to payment....");
			request.setAttribute("bookingid", bid);
			RequestDispatcher rd = request.getRequestDispatcher("./Payment/index.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			logger.log(System.Logger.Level.INFO,e.getMessage());
		}
	}

}
