package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingDao;

/**
 * Servlet implementation class CancelPayment
 */
@WebServlet("/CancelPayment")
public class CancelPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	System.Logger logger = System.getLogger("error");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingId = request.getParameter("bookingId");
		System.out.println(bookingId);
		PrintWriter out = response.getWriter();
		BookingDao bd = new BookingDao();
		
		try {
			if(bd.deleteBooking(bookingId)) {
				logger.log(System.Logger.Level.INFO, "Deleted the booking");
				out.println("<script>alert('Payment Cancelled. Redirecting to homepage.')</script>");
				response.sendRedirect("./index.jsp");
			}
			else {
				logger.log(System.Logger.Level.WARNING, "Problem with deleting booking entry");
				out.println("<script>alert('Unexpected Error. Redirecting to homepage.')</script>");
				response.sendRedirect("./index.jsp");
			}
		} catch (Exception e) {
			
			
		}
	}

}
