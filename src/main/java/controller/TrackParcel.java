package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import bean.Booking;
import dao.BookingDao;

/**
 * Servlet implementation class TrackParcel
 */
@WebServlet("/TrackParcel")
public class TrackParcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String bid = request.getParameter("tracking-id");
			BookingDao bd = new BookingDao();
			
			try {
				Booking obj = bd.getTracking(bid);
				if(obj != null) {
					request.setAttribute("bookingid", obj.getBookingId());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String deliveryDate = sdf.format(obj.getDropoffDate());
					String bookingDate = sdf.format(obj.getBookingDate());
					request.setAttribute("deliverydate", deliveryDate);
					request.setAttribute("bookingdate", bookingDate);
					request.setAttribute("status", obj.getStatus());
					RequestDispatcher rd = request.getRequestDispatcher("./user/Tracking/index.jsp");
					rd.forward(request, response);
				}
				else {
					request.setAttribute("errorMessage", "No booking found with this ID");
					request.setAttribute("bookingid", bid);
					RequestDispatcher rd = request.getRequestDispatcher("./user/Tracking/index.jsp");
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
