package bean;

public class Admin {
	private String emailId;
	private String password;
	private String name;
	
	public Admin() {}
	public Admin(String name,String emailId,String password) {
		this.name = name;
		this.emailId = emailId;
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
}
