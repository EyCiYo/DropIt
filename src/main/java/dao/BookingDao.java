package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Booking;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BookingDao {
	Random rd = new Random();
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

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

	public ArrayList<Booking> getPreviousBooking(String email) throws ClassNotFoundException, SQLException {
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
				System.getLogger("Fetched Previous Booking data: " + rowcount);
			} else {
				arr = null;
				System.getLogger("Error in fetching Previous Booking data");
			}

			conn.close();
			st.close();
		} catch (Exception e) {
			System.getLogger(e.getMessage());
		}

		return arr;
	}

	public Booking getTracking(String bid, String email) throws ClassNotFoundException {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		Booking match = new Booking();
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
			} else {
				match = null;
			}
			conn.close();
			st.close();
			System.getLogger("Clossed connection");
		} catch (Exception e) {
			System.getLogger(e.getMessage());
		}

		return match;
	}

	public String createNewBooking(Booking obj) throws ClassNotFoundException {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		String rbid = null;
		String sql = "INSERT INTO tbl_Booking VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String check = "Select Par_Cost from tbl_booking where booking_id = ?";
		double volume = obj.getLength() * obj.getWidth() * obj.getHeight();
		double weight = obj.getWeight();
		String speed = obj.getShippingSpeed();
		String packageType = obj.getPackingType();

		Map<String, Double> costSpeed = Map.of("standard", 1.0, "express", 1.5, "business", 2.0);

		Map<String, Integer> costPackage = Map.of("standard", 10, "eco", 25, "custom", 40, "fragile", 50);
		// Basic cost factors (these can be adjusted as needed)
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
			ps.setString(2, obj.getSenderEmail()); // Sender_Email VARCHAR(50)
			ps.setString(3, obj.getReceiverName()); // Rec_Name VARCHAR(50)
			ps.setString(4, obj.getReceiverAddress()); // Rec_Address VARCHAR(100)
			ps.setString(5, obj.getReceiverMobile()); // Rec_Mobile VARCHAR(20)
			ps.setString(6, obj.getReceiverEmail()); // Rec_Email VARCHAR(40)
			ps.setDouble(7, obj.getLength()); // Par_Length DECIMAL(10,2)
			ps.setDouble(8, obj.getHeight()); // Par_Height DECIMAL(10,2)
			ps.setDouble(9, obj.getWidth()); // Par_Width DECIMAL(10,2)
			ps.setDouble(10, obj.getWeight()); // Par_Weight_Gram DECIMAL(10,2)
			ps.setString(11, obj.getContentDescription()); // Par_Contents_Description VARCHAR(200)
			ps.setString(12, obj.getShippingSpeed()); // Par_Shipping_Speed VARCHAR(20)
			ps.setString(13, obj.getPackingType()); // Par_Packing_Type VARCHAR(20)
			ps.setDate(14, sqlPickupDate); // Par_PickupTime DATE
			ps.setDate(15, sqlDropDate); // Par_DropoffTime DATE
			ps.setDate(16, sqlBookingDate); // Par_BookingTime DATE
			ps.setDouble(17, estimatedCost); // Par_Cost DECIMAL(10,2)
			ps.setString(18, obj.getStatus()); // Par_Status VARCHAR(20)
			ps.execute();
			int rowcount = ps.getUpdateCount();
			if (rowcount > 0) {
				rbid = bid;
			} else {
				System.getLogger("Booking data not inserted");
			}
			conn.close();
			ps.close();
			chk.close();
			chkrs.close();
		} catch (Exception e) {
			System.getLogger(e.getMessage());
		}

		return rbid;

	}
}
