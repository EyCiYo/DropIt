package bean;

public class User {
	private String userId;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private String address;
    private boolean emailNotifications;
    private boolean smsNotifications;
    public User() {}
    public User(String name, String email, String address, String mobileNumber, String password,
        boolean emailNotifications, boolean smsNotifications) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.emailNotifications = emailNotifications;
        this.smsNotifications = smsNotifications;
    }
    
    public String getName() {return this.name;}
    public String getEmail() {return this.email;}
    public String getMobile() {return this.mobileNumber;}
    public String getAddress() {return this.address;}
    public String getPassword() {return this.password;}
    public String getUserID() {return this.userId;}
    public boolean getEmailNotifications() {
		return emailNotifications;
	}
	public boolean getSmsNotifications() {
		return smsNotifications;
	}
	
	public void setName(String val) {this.name = val;}
    public void setEmail(String val) {this.email = val;}
    public void setAddress(String val) {this.address = val;}
    public void setMobile(String val) {this.mobileNumber = val;}
    public void setPassword(String val) {this.password = val;}
    public void setUserID(String val) {this.userId = val;}
    public void setEmailNotifications(boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}
	public void setSmsNotifications(boolean smsNotifications) {
		this.smsNotifications = smsNotifications;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", password=" + password + ", address=" + address + ", emailNotifications=" + emailNotifications
				+ ", smsNotifications=" + smsNotifications + "]";
	}
	
}
