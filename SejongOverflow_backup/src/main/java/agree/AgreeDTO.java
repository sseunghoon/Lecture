package agree;

public class AgreeDTO {
	String userID;
	int cmpID;
	String userIP;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getCmpID() {
		return cmpID;
	}
	public void setCmpID(int cmpID) {
		this.cmpID = cmpID;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	
	public AgreeDTO() {
	}
	
	public AgreeDTO(String userID, int cmpID, String userIP) {
		super();
		this.userID = userID;
		this.cmpID = cmpID;
		this.userIP = userIP;
	}
	
	
}
