package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.Booking;
import java.util.*;
import java.sql.SQLException;


public class BookingDao {
	Random rd = new Random();
	
	private String generateBooking() {
    	String key ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    	
    	StringBuilder username = new StringBuilder("BKNO_");
    	for(int i=0;i<8;i++) {
    		int index = rd.nextInt(key.length());
    		username.append(key.charAt(index));
    	}    	
    	return username.toString();
    }
	public ArrayList<Booking> getPreviousBooking(String email) throws ClassNotFoundException, SQLException {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		ArrayList<Booking> arr=new ArrayList<>();
		String  sql = "select * from tbl_Booking where Sender_Email='"+email+"'";
		
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			int rowcount = 0;
			
			while(rs.next()) {
				Booking match1=new Booking();
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
			if(rowcount > 0) {
				System.out.println("Fetched Previous Booking data: " + rowcount);
			}
			else {
				arr = null;
				System.out.println("Error in fetching Previous Booking data");
			}
			
			conn.close();
			st.close();
		}catch(Exception e){
			System.getLogger(e.getMessage());
		}
		
		
		return arr;
	}
	
	public Booking getTracking(String bid) throws ClassNotFoundException {
		Connect_jdbc cj = new Connect_jdbc();
		Connection conn = cj.connected();
		Booking match = new Booking();
		String sql = "select Par_Status,Par_DropoffTime,Par_BookingTime from tbl_Booking where Booking_ID = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, bid);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				match.setStatus(rs.getString("Par_Status"));
				match.setDropoffDate(rs.getDate("Par_DropoffTime"));
				match.setBookingDate(rs.getDate("Par_BookingTime"));
				match.setBookingId(bid);
			}
			else {
				match = null;
			}
			conn.close();
			st.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
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
				
	    Map<String,Double> costSpeed = Map.of(
	    		 "standard", 1.0,
	 	        "express", 1.5,
	 	        "business", 2.0);
	        
	    Map<String,Integer> costPackage = Map.of(
	    		"standard", 10,
		        "eco", 25,
		        "custom", 40,
		        "fragile", 50);
	    // Basic cost factors (these can be adjusted as needed)
	    int costPerKg = 50; // base cost per kg
	    double costPerCubicCm = 0.02; // base cost per cubic cm
	    // Total cost calculation
	    double estimatedCost = ((weight * costPerKg) + (volume * costPerCubicCm))*costSpeed.get(speed) + costPackage.get(packageType);
		try {
			String bid = generateBooking();
			PreparedStatement chk = conn.prepareStatement(check);
			chk.setString(1,bid);
			ResultSet chkrs = chk.executeQuery();
			while(chkrs.next()) {
				bid = generateBooking();
				chk.setString(1,bid);
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
            
            ps.setString(1,bid);
            ps.setString(2, obj.getSenderEmail());                      // Sender_Email VARCHAR(50)
            ps.setString(3, obj.getReceiverName());                          // Rec_Name VARCHAR(50)
            ps.setString(4, obj.getReceiverAddress());                       // Rec_Address VARCHAR(100)
            ps.setString(5, obj.getReceiverMobile());                        // Rec_Mobile VARCHAR(20)
            ps.setString(6, obj.getReceiverEmail());                         // Rec_Email VARCHAR(40)
            ps.setDouble(7, obj.getLength());                           // Par_Length DECIMAL(10,2)
            ps.setDouble(8, obj.getHeight());                           // Par_Height DECIMAL(10,2)
            ps.setDouble(9, obj.getWidth());                          // Par_Width DECIMAL(10,2)
            ps.setDouble(10, obj.getWeight());                           // Par_Weight_Gram DECIMAL(10,2)
            ps.setString(11, obj.getContentDescription());                     // Par_Contents_Description VARCHAR(200)
            ps.setString(12, obj.getShippingSpeed());                  // Par_Shipping_Speed VARCHAR(20)
            ps.setString(13, obj.getPackingType());                   // Par_Packing_Type VARCHAR(20)
            ps.setDate(14, sqlPickupDate);  // Par_PickupTime DATE
            ps.setDate(15, sqlDropDate); // Par_DropoffTime DATE
            ps.setDate(16, sqlBookingDate); // Par_BookingTime DATE
            ps.setDouble(17, estimatedCost);                            // Par_Cost DECIMAL(10,2)
            ps.setString(18, obj.getStatus());                          // Par_Status VARCHAR(20)
            ps.execute();
            int rowcount = ps.getUpdateCount();
            if(rowcount > 0) {
            	rbid = bid;
            }
            else {
            	System.out.println("Booking data not inserted");
            }
            conn.close();
            ps.close();
            chk.close();
            chkrs.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return rbid;
		
	}
}
