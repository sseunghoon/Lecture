package likey;

public class LikeyDTO {
	String userID;
	int postID;
	int boardID;
	String userIP;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getpostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public int getBoardID() {
		return boardID;
	}
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}
	public int getPostID() {
		return postID;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	
	public LikeyDTO() {
	}
	
	public LikeyDTO(String userID, int postID, int boardID, String userIP) {
		super();
		this.userID = userID;
		this.postID = postID;
		this.boardID = boardID;
		this.userIP = userIP;
	}
	
}
