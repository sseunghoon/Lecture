package post;

public class PostDTO {
	int postID;
	int boardID;
	String userID;
	String postDate;
	String postTitle;
	String postContent;
	String postDivide;
	String postFile;
	String postRealFile;
	int agreeCount;
	int postHit;
	int postGroup;
	int postSequence;
	int postLevel;
	int postAvailable;

	public int getPostID() {
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
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostDivide() {
		return postDivide;
	}
	public void setPostDivide(String postDivide) {
		this.postDivide = postDivide;
	}
	public String getPostFile() {
		return postFile;
	}
	public void setPostFile(String postFile) {
		this.postFile = postFile;
	}
	public String getPostRealFile() {
		return postRealFile;
	}
	public void setPostRealFile(String postRealFile) {
		this.postRealFile = postRealFile;
	}
	public int getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(int agreeCount) {
		this.agreeCount = agreeCount;
	}
	public int getPostHit() {
		return postHit;
	}
	public void setPostHit(int postHit) {
		this.postHit = postHit;
	}
	public int getPostGroup() {
		return postGroup;
	}
	public void setPostGroup(int postGroup) {
		this.postGroup = postGroup;
	}
	public int getPostSequence() {
		return postSequence;
	}
	public void setPostSequence(int postSequence) {
		this.postSequence = postSequence;
	}
	public int getPostLevel() {
		return postLevel;
	}
	public void setPostLevel(int postLevel) {
		this.postLevel = postLevel;
	}
	public int getPostAvailable() {
		return postAvailable;
	}
	public void setPostAvailable(int postAvailable) {
		this.postAvailable = postAvailable;
	}
	public PostDTO() {

	}
	public PostDTO(int postID, int boardID, String userID, String postDate, String postTitle, String postContent,
			String postDivide, String postFile, String postRealFile, int agreeCount, int postHit, int postGroup,
			int postSequence, int postLevel, int postAvailable) {
		this.postID = postID;
		this.boardID = boardID;
		this.userID = userID;
		this.postDate = postDate;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postDivide = postDivide;
		this.postFile = postFile;
		this.postRealFile = postRealFile;
		this.agreeCount = agreeCount;
		this.postHit = postHit;
		this.postGroup = postGroup;
		this.postSequence = postSequence;
		this.postLevel = postLevel;
		this.postAvailable = postAvailable;
	}
	
}
