package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Booking;
import dao.BookingDao;

/**
 * Servlet implementation class PrintBill
 */
@WebServlet("/PrintBill")
public class PrintBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
	System.Logger logger = System.getLogger("error");
	private String home = System.getProperty("user.home");

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = (String) request.getParameter("bookingid");
		String billno = request.getParameter("billno");
		BookingDao bd = new BookingDao();
		HttpSession session = request.getSession(false);
		
		try {
			Booking obj = bd.getBookingDetails(bid);
			String filepath = home + "/Downloads/" + billno + ".txt";
			FileWriter writer = new FileWriter(filepath);
			String senderName = obj.getSenderName();
			String senderEmail = obj.getSenderEmail();
			String senderMobile = obj.getSenderMobile();
			String recName = obj.getReceiverName();
			double cost = obj.getCost();
			writer.append("Bill Details\n\n" 
					+ "Bill No: " + billno + "\n" 
					+ "Booking Id: " + bid + "\n" 
					+ "Sender Name: " + senderName + "\n" 
					+ "Sender Email: " + senderEmail + "\n" 
					+ "Sender Mobile: " + senderMobile
					+ "\n" + "Receiver Name: " + recName + "\n" 
					+ "\nCost: " + cost + "\n");
			writer.close();
			String resp = "<script>alert('Bill stored at: "+filepath+".');</script>";
			response.getWriter().print(resp);
			session.setAttribute("bookingid", null);
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		} catch (Exception e) {
			logger.log(System.Logger.Level.ERROR, e.getMessage());
		}

	}

}
