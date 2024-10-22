package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Booking;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BookingDao {
	Random rd = new Random();
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	System.Logger logger = System.getLogger("error");
	// Main validation logic
	public String processBooking(Booking obj) {
		// Retrieve and validate sender information
		String senderName = obj.getSenderName();
		String senderAddress = obj.getSenderAddress();
		String senderEmail = obj.getSenderEmail();
		String senderMobile = obj.getSenderMobile();

		// Retrieve and validate receiver information
		String recName = obj.getReceiverName();
		String recAddress = obj.getReceiverAddress();
		String recEmail = obj.getReceiverEmail();
		String recMobile = obj.getReceiverMobile();
		// Validate parcel details
		Double lengthStr = obj.getLength();
		Double breadthStr = obj.getWidth();
		Double heightStr = obj.getHeight();
		Double weightStr = obj.getWeight();
		String description = obj.getContentDescription();

		// Retrieve shipping options
		String shippingOption = obj.getShippingSpeed();
		String packagingType = obj.getPackingType();

		// Validate dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date pickupDate = obj.getPickupDate();
		Date dropDate = obj.getDropoffDate();

		Date currentDate = new Date();
		if (!isValidName(senderName) || !isValidName(recName)) {
			return ("Invalid sender or receiver name");
		}
		if (!isValidEmail(senderEmail) || !isValidEmail(recEmail)) {
			return ("Invalid sender or receiver email");
		}
		if (!isValidMobile(senderMobile) || !isValidMobile(recMobile)) {
			return ("Invalid sender or receiver mobile number");
		}
		// Validate that none of the parcel details are null or empty
		if (!isNotNullOrEmpty(lengthStr.toString()) || !isNotNullOrEmpty(breadthStr.toString())
				|| !isNotNullOrEmpty(heightStr.toString()) || !isNotNullOrEmpty(weightStr.toString())
				|| !isNotNullOrEmpty(description)) {
			return ("Parcel details cannot be null or empty");
		}
		// Validate shipping option and packaging type
		if (!isNotNullOrEmpty(shippingOption) || !isNotNullOrEmpty(packagingType)) {
			return ("Shipping option and packaging type cannot be null or empty");
		}
		if (pickupDate.before(currentDate)) {
			return "Pickup date cannot be in the past.";
		}
		if (dropDate.before(currentDate)) {
			return "Dropoff date cannot be in the past.";
		}
		if (dropDate.before(pickupDate)) {
			return "Dropoff date connot come before pickup date.";
		}
		return "Success";
	}

	public static boolean isValidEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.matches();

	}

	public static boolean isValidMobile(String mobile) {
		String mobileRegex = "^[0-9]{10}$";
		Pattern pattern = Pattern.compile(mobileRegex);
		if (mobile == null) {
			return false;
		}
		return pattern.matcher(mobile).matches();
	}

	public static boolean isValidName(String name) {
		String nameRegex = "^[A-Za-z ]+$";
		Pattern pattern = Pattern.compile(nameRegex);
		if (name == null || name.isEmpty()) {
			return false;
		}
		return pattern.matcher(name).matches();
	}

	public static boolean isNotNullOrEmpty(String value) {
		return value != null && !value.trim().isEmpty();
	}

	private String generateBooking() {
		String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		StringBuilder username = new StringBuilder("BKNO_");
		for (int i = 0; i < 8; i++) {
			int index = rd.nextInt(key.length());
			username.append(key.charAt(index));
		}
		return username.toString();
	}

	public ArrayList<Booking> getPreviousBooking(String email) throws Exception {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		ArrayList<Booking> arr = new ArrayList<>();
		String sql = "select * from tbl_Booking where Sender_Email=?";

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();

			int rowcount = 0;

			while (rs.next()) {
				Booking match1 = new Booking();
				match1.setBookingId(rs.getString("Booking_ID"));
				match1.setBookingDate(rs.getDate("Par_BookingTime"));
				match1.setReceiverName(rs.getString("Rec_Name"));
				match1.setReceiverAddress(rs.getString("Rec_Address"));
				match1.setCost(rs.getDouble("Par_Cost"));
				match1.setDropoffDate(rs.getDate("Par_DropoffTime"));
				match1.setStatus(rs.getString("Par_Status"));
				arr.add(match1);
				rowcount++;
			}
			if (rowcount > 0) {
				logger.log(System.Logger.Level.INFO,"Fetched Previous Booking data: " + rowcount);
			} else {
				arr = null;
				logger.log(System.Logger.Level.ERROR,"Error in fetching Previous Booking data");
			}

			st.close();
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING,e.getMessage());
			throw new ServletException(e);
		}
		finally {
			cj.closeConnection();
		}

		return arr;
	}
	
	public ArrayList<Booking> getPreviousBooking(String email,Date from,Date to) throws Exception {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		ArrayList<Booking> arr = new ArrayList<>();
		System.out.println(email+from+to);
		String sql = "select * from tbl_Booking where Sender_Email=? AND par_bookingtime BETWEEN ? AND ?";

		try {
			java.sql.Date sqlFromDate = new java.sql.Date(from.getTime());
			java.sql.Date sqlToDate = new java.sql.Date(to.getTime());
			System.out.println(sqlToDate);
			System.out.println(sqlFromDate);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, email);
			st.setDate(2, sqlFromDate);
			st.setDate(3, sqlToDate);
			ResultSet rs = st.executeQuery();
			
			int rowcount = 0;

			while (rs.next()) {
				Booking match1 = new Booking();
				match1.setBookingId(rs.getString("Booking_ID"));
				match1.setBookingDate(rs.getDate("Par_BookingTime"));
				match1.setReceiverName(rs.getString("Rec_Name"));
				match1.setReceiverAddress(rs.getString("Rec_Address"));
				match1.setCost(rs.getDouble("Par_Cost"));
				match1.setStatus(rs.getString("Par_Status"));
				match1.setSenderEmail(rs.getString("Sender_email"));
				match1.setDropoffDate(rs.getDate("Par_Dropofftime"));
				arr.add(match1);
				System.out.println("Fetched data ");
				rowcount++;
			}
			if (rowcount > 0) {
				logger.log(System.Logger.Level.INFO,"Fetched Previous Booking data: " + rowcount);
			} else {
				arr = null;
				logger.log(System.Logger.Level.ERROR,"Error in fetching Previous Booking data");
			}
			st.close();
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING,e.getMessage());
			throw new ServletException(e);
		}
		finally {
			cj.closeConnection();
		}

		return arr;
	}

	public Booking getTracking(String bid, String email) throws Exception {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		Booking match = new Booking();
		System.out.println(bid+email);
		String sql = "select Par_Status,Par_DropoffTime,Par_BookingTime from tbl_Booking where Booking_ID = ? and Sender_Email = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, bid);
			st.setString(2, email);
			ResultSet rs = st.executeQuery();
		 
			if (rs.next()) {
				match.setStatus(rs.getString("Par_Status"));
				match.setDropoffDate(rs.getDate("Par_DropoffTime"));
				match.setBookingDate(rs.getDate("Par_BookingTime"));
				match.setBookingId(bid);
				logger.log(System.Logger.Level.INFO,"Match Found");
			} else {
				match = null;
				logger.log(System.Logger.Level.ERROR,"No match found");
			}
			st.close();
			logger.log(System.Logger.Level.INFO,"Clossed connection");
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING,e.getMessage());
			throw new ServletException(e);
		}
		finally {
			cj.closeConnection();
		}

		return match;
	}

	public String createNewBooking(Booking obj) throws Exception {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		String rbid = null;
		String sql = "INSERT INTO tbl_Booking VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String check = "Select Par_Cost from tbl_booking where booking_id = ?";
		double volume = obj.getLength() * obj.getWidth() * obj.getHeight();
		double weight = obj.getWeight();
		String speed = obj.getShippingSpeed();
		String packageType = obj.getPackingType();

		Map<String, Double> costSpeed = Map.of("standard", 1.0, "express", 1.5, "business", 2.0);

		Map<String, Integer> costPackage = Map.of("standard", 10, "eco", 25, "custom", 40, "fragile", 50);
		// Basic cost factors 
		int costPerKg = 50; // base cost per kg
		double costPerCubicCm = 0.02; // base cost per cubic cm
		// Total cost calculation
		double estimatedCost = ((weight * costPerKg) + (volume * costPerCubicCm)) * costSpeed.get(speed)
				+ costPackage.get(packageType);
		try {
			String bid = generateBooking();
			PreparedStatement chk = conn.prepareStatement(check);
			chk.setString(1, bid);
			ResultSet chkrs = chk.executeQuery();
			while (chkrs.next()) {
				bid = generateBooking();
				chk.setString(1, bid);
				chkrs = chk.executeQuery();
			}

			PreparedStatement ps = conn.prepareStatement(sql);
			Date bookingDate = obj.getBookingDate();
			Date pickupDate = obj.getPickupDate();
			Date dropDate = obj.getDropoffDate();
			java.sql.Date sqlBookingDate = new java.sql.Date(bookingDate.getTime());
			java.sql.Date sqlPickupDate = new java.sql.Date(pickupDate.getTime());
			java.sql.Date sqlDropDate = new java.sql.Date(dropDate.getTime());
			// Set the parameters based on the data types

			ps.setString(1, bid);
			ps.setString(2, obj.getSenderName()); // Sender_Email VARCHAR(50)
			ps.setString(3, obj.getSenderEmail());
			ps.setString(4, obj.getSenderAddress());
			ps.setString(5, obj.getSenderMobile());
			ps.setString(6, obj.getReceiverName()); // Rec_Name VARCHAR(50)
			ps.setString(7, obj.getReceiverAddress()); // Rec_Address VARCHAR(100)
			ps.setString(8, obj.getReceiverMobile()); // Rec_Mobile VARCHAR(20)
			ps.setString(9, obj.getReceiverEmail()); // Rec_Email VARCHAR(40)
			ps.setDouble(10, obj.getLength()); // Par_Length DECIMAL(10,2)
			ps.setDouble(11, obj.getHeight()); // Par_Height DECIMAL(10,2)
			ps.setDouble(12, obj.getWidth()); // Par_Width DECIMAL(10,2)
			ps.setDouble(13, obj.getWeight()); // Par_Weight_Gram DECIMAL(10,2)
			ps.setString(14, obj.getContentDescription()); // Par_Contents_Description VARCHAR(200)
			ps.setString(15, obj.getShippingSpeed()); // Par_Shipping_Speed VARCHAR(20)
			ps.setString(16, obj.getPackingType()); // Par_Packing_Type VARCHAR(20)
			ps.setDate(17, sqlPickupDate); // Par_PickupTime DATE
			ps.setDate(18, sqlDropDate); // Par_DropoffTime DATE
			ps.setDate(19, sqlBookingDate); // Par_BookingTime DATE
			ps.setDouble(20, estimatedCost); // Par_Cost DECIMAL(10,2)
			ps.setString(21, obj.getStatus()); // Par_Status VARCHAR(20)
			ps.execute();
			int rowcount = ps.getUpdateCount();
			if (rowcount > 0) {
				rbid = bid;
				logger.log(System.Logger.Level.INFO,"Booking data inserted");
			} else {
				logger.log(System.Logger.Level.ERROR,"Booking data not inserted");
			}
			ps.close();
			chk.close();
			chkrs.close();
		} catch (Exception e) {
			logger.log(System.Logger.Level.WARNING,e.getMessage());
			throw new ServletException(e);
		}
		finally {
			cj.closeConnection();
		}
		return rbid;
	}
	
	public Booking getBookingDetails(String bookingId) throws Exception {
	    Connect_jdbc cj = new Connect_jdbc();
	    Connection conn = cj.connected();
	    Booking booking = null;
	    
	    String sql = "SELECT Sender_Name,Sender_mobile,Sender_address,Sender_Email, Rec_Name, Rec_Address, Rec_Mobile, Rec_Email, Par_Length, Par_Height, Par_Width, " +
	                 "Par_Weight_Gram, Par_Contents_Description, Par_Shipping_Speed, Par_Packing_Type, Par_PickupTime, " +
	                 "Par_DropoffTime, Par_BookingTime, Par_Cost, Par_Status FROM tbl_Booking WHERE booking_id = ?";
	    
	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, bookingId);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            // If booking is found, populate the Booking object with the data
	            booking = new Booking();
	            booking.setSenderAddress(rs.getString("Sender_address"));
	            booking.setSenderMobile(rs.getString("Sender_mobile"));
	            booking.setSenderName(rs.getString("Sender_name"));
	            booking.setSenderEmail(rs.getString("Sender_Email"));
	            booking.setReceiverName(rs.getString("Rec_Name"));
	            booking.setReceiverAddress(rs.getString("Rec_Address"));
	            booking.setReceiverMobile(rs.getString("Rec_Mobile"));
	            booking.setReceiverEmail(rs.getString("Rec_Email"));
	            booking.setLength(rs.getDouble("Par_Length"));
	            booking.setHeight(rs.getDouble("Par_Height"));
	            booking.setWidth(rs.getDouble("Par_Width"));
	            booking.setWeight(rs.getDouble("Par_Weight_Gram"));
	            booking.setContentDescription(rs.getString("Par_Contents_Description"));
	            booking.setShippingSpeed(rs.getString("Par_Shipping_Speed"));
	            booking.setPackingType(rs.getString("Par_Packing_Type"));
	            
	            // Convert SQL Date to Java Date
	            booking.setPickupDate(rs.getDate("Par_PickupTime"));
	            booking.setDropoffDate(rs.getDate("Par_DropoffTime"));
	            booking.setBookingDate(rs.getDate("Par_BookingTime"));
	            
	            booking.setCost(rs.getDouble("Par_Cost"));
	            booking.setStatus(rs.getString("Par_Status"));
	            booking.setBookingId(bookingId);
	        } else {
	            logger.log(System.Logger.Level.WARNING, "Booking not found with ID: " + bookingId);
	        }

	        // Close resources
	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        logger.log(System.Logger.Level.WARNING, e.getMessage());
	        throw new ServletException(e);
	    }
	    finally {
			cj.closeConnection();
		}
	    
	    return booking;
	}
	
	public double getBookingPrice(String bookingId) throws Exception {
	    Connect_jdbc cj = new Connect_jdbc();
	    Connection conn = cj.connected();
	    double price = -1;  // Initialize with a default value (-1) indicating that the booking is not found
	    
	    String sql = "SELECT Par_Cost FROM tbl_Booking WHERE booking_id = ?";
	    
	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, bookingId);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            price = rs.getDouble("Par_Cost");
	        } else {
	            logger.log(System.Logger.Level.WARNING, "Booking not found with ID: " + bookingId);
	        }

	        // Close resources
	        rs.close();
	        ps.close();

	    } catch (Exception e) {
	        logger.log(System.Logger.Level.WARNING, e.getMessage());
	        throw new ServletException(e);
	    }
	    finally {
	        cj.closeConnection();
		}
	    
	    return price;
	}
	
	public boolean deleteBooking(String bookingId) throws Exception {
		Connect_jdbc cj = new Connect_jdbc();
	    Connection conn = cj.connected();
	    boolean success = false;
	    String sql = "DELETE FROM tbl_Booking WHERE Booking_id = ?";
	    try {
	    	PreparedStatement ps = conn.prepareStatement(sql);
	    	ps.setString(1, bookingId);
	    	int rows = ps.getUpdateCount();
	    	if(rows > 0) {
	    		success = true;
	    		logger.log(System.Logger.Level.INFO , "Deleted the booking entry");
	    	}
	    	ps.close();
	    	
	    }
	    catch (Exception e) {
			logger.log(System.Logger.Level.WARNING , e.getMessage());
			throw new ServletException(e);
		}
	    finally {
	    	cj.closeConnection();
	    }
	    
	    return success;
	}
	
	
	public ArrayList<Booking> getAllBooking() throws Exception {
		ArrayList<Booking> al = new ArrayList<Booking>();
		Connect_jdbc cj = new Connect_jdbc();
	    Connection conn = cj.connected();
	    String sql = "select * from tbl_booking";
	    try{
	    	PreparedStatement ps = conn.prepareStatement(sql);
	    	ResultSet rs = ps.executeQuery();
	    	while(rs.next()) {
	    		Booking obj = new Booking();
	    		obj.setBookingId(rs.getString("booking_id"));
	    		obj.setSenderName(rs.getString("Sender_name"));
	    		obj.setSenderEmail(rs.getString("Sender_email"));
	    		obj.setReceiverName(rs.getString("rec_name"));
	    		obj.setReceiverAddress(rs.getString("rec_address"));
	    		obj.setStatus(rs.getString("par_status"));
	    		obj.setBookingDate(rs.getDate("Par_BookingTime"));
	    		obj.setCost(rs.getDouble("Par_cost"));
	    		al.add(obj);
	    		logger.log(System.Logger.Level.INFO, "Added booking to list");
	    	}
	    }catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
			throw new ServletException(e);
		}
	    finally {
			cj.closeConnection();
		}
		return al;
	}
	
	public boolean schedulePickup(String bookingid, Date newDate) throws Exception {
		boolean result = false;
		Connect_jdbc cj = new Connect_jdbc();
	    Connection conn = cj.connected();
	    java.sql.Date sqlNewDate = new java.sql.Date(newDate.getTime());
	    String sql = "UPDATE tbl_Booking SET Par_PickupTime = ? WHERE Booking_id = ?";
	    try {
	    	PreparedStatement ps = conn.prepareStatement(sql);
	    	ps.setDate(1,sqlNewDate );
	    	ps.setString(2, bookingid);
	    	int rowcount = ps.executeUpdate();
	    	if(rowcount > 0) {
	    		result = true;
	    		logger.log(System.Logger.Level.INFO, "Updated date in table");
	    	}
	    }
	    catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
			throw new ServletException(e);
		}
	    finally {
	    	cj.closeConnection();
		}
	    		
		return result;
	}
	
	public boolean updateStatus(String bookingid,String newStatus) throws Exception {
		Connect_jdbc cj = new Connect_jdbc();
	    Connection conn = cj.connected();
	    boolean result = false;
	    String sql = "UPDATE tbl_Booking SET Par_Status = ? WHERE Booking_id = ?";
	    try {
	    	PreparedStatement ps = conn.prepareStatement(sql);
	    	ps.setString(1, newStatus);
	    	ps.setString(2, bookingid);
	    	int rowcount = ps.executeUpdate();
	    	if(rowcount>0) {
	    		result = true;
	    		logger.log(System.Logger.Level.INFO, "Updated Parcel Status");
	    	}
	    	else {
	    		logger.log(System.Logger.Level.ERROR, "Error Updating Status");
	    	}
	    	ps.close();
	    }
	    catch (Exception e) {
			logger.log(System.Logger.Level.WARNING, e.getMessage());
			throw new ServletException(e);
		}
	    finally {
			cj.closeConnection();
		}
	    return result;
	}

}
