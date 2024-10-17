package bean;

import java.util.Date;

public class Booking {
	private String bookingId;
    private String senderEmail;
    private String senderName;
    private String senderAddress;
    private String senderMobile;
    private String receiverName;
    private String receiverAddress;
    private String receiverEmail;
    private String receiverMobile;
    private double length;
    private double height;
    private double width;
    private double weight;
    private double cost;
    private String contentDescription;
    private String shippingSpeed;
    private String packingType;
    private Date bookingDate;
    private Date pickupDate;
    private Date dropoffDate;
    private String currentLocation;
    private String status; // e.g., "Booked", "Picked Up", "In Transit", "Out for Delivery", "Delivered", "Returned"

    public Booking() {}
    public Booking(String senderName, String senderAddress,
        String receiverName, String receiverAddress, double weight, double cost,
        Date bookingDate, String status) {
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.weight = weight;
        this.cost = cost;
        this.bookingDate = bookingDate;
        this.status = status;
    }
	public String getBookingId() {
		return bookingId;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public String getSenderName() {
		return senderName;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public String getSenderMobile() {
		return senderMobile;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public String getReceiverEmail() {
		return receiverEmail;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public double getLength() {
		return length;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
	public double getWeight() {
		return weight;
	}
	public double getCost() {
		return cost;
	}
	public String getContentDescription() {
		return contentDescription;
	}
	public String getShippingSpeed() {
		return shippingSpeed;
	}
	public String getPackingType() {
		return packingType;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public Date getPickupDate() {
		return pickupDate;
	}
	public Date getDropoffDate() {
		return dropoffDate;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public String getStatus() {
		return status;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public void setSenderMobile(String senderMobile) {
		this.senderMobile = senderMobile;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}
	public void setShippingSpeed(String shippingSpeed) {
		this.shippingSpeed = shippingSpeed;
	}
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	public void setDropoffDate(Date dropoffDate) {
		this.dropoffDate = dropoffDate;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    
}
