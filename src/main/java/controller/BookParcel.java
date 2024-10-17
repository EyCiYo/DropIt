package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class BookParcel
 */
@WebServlet("/BookParcel")
public class BookParcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		
		// Retrieve sender information
        String senderName = request.getParameter("sendername");
        String senderAddress = request.getParameter("senderaddress");
        String senderEmail = request.getParameter("senderemail");
        String senderMobile = request.getParameter("sendermobile");
        
        // Retrieve receiver information
        String recName = request.getParameter("recname");
        String recAddress = request.getParameter("recaddress");
        String recEmail = request.getParameter("recemail");
        String recMobile = request.getParameter("recmobile");

        // Retrieve parcel details
        double length = Double.parseDouble(request.getParameter("length"));
        double breadth = Double.parseDouble(request.getParameter("breadth"));
        double height = Double.parseDouble(request.getParameter("height"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        String description = request.getParameter("description");

        // Retrieve shipping options
        String shippingOption = request.getParameter("shippingoption");
        String packagingType = request.getParameter("packagingtype");

        // Retrieve pickup and dropoff dates
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date pickupDate = null;
        Date dropDate = null;
		try {
			pickupDate = dateFormat.parse(request.getParameter("pickup-date"));
			dropDate = dateFormat.parse(request.getParameter("drop-date"));  // For dropoff date
		} catch (ParseException e) {
			System.getLogger(e.getMessage());
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
        booking.setWidth(breadth);  // Assuming "breadth" is width
        booking.setWeight(weight);
        booking.setContentDescription(description);

        booking.setShippingSpeed(shippingOption);
        booking.setPackingType(packagingType);

        booking.setBookingDate(new Date());
        booking.setPickupDate(pickupDate);
        booking.setDropoffDate(dropDate);

        booking.setStatus(status);
        String bid = null;
        BookingDao bd = new BookingDao();
        try {
			 bid = bd.createNewBooking(booking);
		} catch (ClassNotFoundException e) {
			System.getLogger(e.getMessage());
		}
        		

        
        
        
     // Display retrieved data (optional)
        out.println("<h2>Parcel Booking Information</h2>");
        out.println("<h3>Sender Info:</h3>");
        out.println("Name: " + senderName + "<br>");
        out.println("Address: " + senderAddress + "<br>");
        out.println("Email: " + senderEmail + "<br>");
        out.println("Mobile: " + senderMobile + "<br>");

        out.println("<h3>Receiver Info:</h3>");
        out.println("Name: " + recName + "<br>");
        out.println("Address: " + recAddress + "<br>");
        out.println("Email: " + recEmail + "<br>");
        out.println("Mobile: " + recMobile + "<br>");

        out.println("<h3>Parcel Details:</h3>");
        out.println("Length: " + length + " cm<br>");
        out.println("Breadth: " + breadth + " cm<br>");
        out.println("Height: " + height + " cm<br>");
        out.println("Weight: " + weight + " kg<br>");
        out.println("Description: " + description + "<br>");

        out.println("<h3>Shipping Options:</h3>");
        out.println("Shipping Speed: " + shippingOption + "<br>");
        out.println("Packaging Type: " + packagingType + "<br>");
        out.println("Preferred Pickup Date: " + pickupDate + "<br>");
        out.println("Preferred Dropoff Date: " + dropDate + "<br>");

        out.close();
	}

}
