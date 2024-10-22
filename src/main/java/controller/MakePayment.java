package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HandlePayment
 */
@WebServlet("/MakePayment")
public class MakePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	System.Logger logger = System.getLogger("error");
	Random rd = new Random();
	private String generateBillNo() {
		String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		StringBuilder username = new StringBuilder("Bill_");
		for (int i = 0; i < 8; i++) {
			int index = rd.nextInt(key.length());
			username.append(key.charAt(index));
		}
		return username.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookingid = (String)request.getParameter("bookingid"); 
		String billNo = generateBillNo();
		String expDateStr = request.getParameter("expiryDate");
		request.setAttribute("bookingid", bookingid);
		request.setAttribute("billNo", billNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Date expDate = sdf.parse(expDateStr);
			if(expDate.before(new Date())) {
				request.setAttribute("errorMessage", "Your Card has Expired");
				request.getRequestDispatcher("./Payment/index.jsp").forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("./Bill/index.jsp");
				rd.forward(request, response);
			}
		} catch (ParseException e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
			throw new ServletException(e);
		} 
		finally {
			
		}
		
	}

}
