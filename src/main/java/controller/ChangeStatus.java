package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Booking;
import dao.BookingDao;

/**
 * Servlet implementation class ChangeStatus
 */
@WebServlet("/ChangeStatus")
public class ChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	System.Logger logger = System.getLogger("error");
	BookingDao bd = new BookingDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingid = (String)request.getParameter("tracking-id");
		try {
			Booking obj = bd.getBookingDetails(bookingid);
			if(obj!=null) {
				request.setAttribute("bookingid", bookingid);
				request.setAttribute("status", obj.getStatus());
				logger.log(System.Logger.Level.INFO, "Details fetched and sent");
				request.getRequestDispatcher("./admin/DeliveryStatus/index.jsp").forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "No booking found with this id");
				request.getRequestDispatcher("./admin/DeliveryStatus/index.jsp").forward(request, response);
			}
		}
		catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newStatus = (String)request.getParameter("status");
		String bookingid = (String)request.getParameter("tracking-id");
		String email = (String)request.getParameter("email");
		request.setAttribute("email", email);
		RequestDispatcher rd = request.getRequestDispatcher("./admin/DeliveryStatus/index.jsp");
		try {
			if(bd.updateStatus(bookingid, newStatus)) {
				request.setAttribute("errorMessage", "Successfully Updated the Delivery Status");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "Error in Updating Delivery Status");
				rd.forward(request, response);
			}
		}
		catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
			throw new ServletException(e);
		}
	}

}
