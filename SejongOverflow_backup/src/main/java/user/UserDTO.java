package user;

public class UserDTO {
	private String userID;
	private String userPassword;
	private String userName;
	private String userEmail;
	private String userEmailHash;
	private int userEmailChecked;
	private String userProfile;
	private String findPwQuestion;
	private String findPwAnswer;
	private String aboutMe;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmailHash() {
		return userEmailHash;
	}

	public void setUserEmailHash(String userEmailHash) {
		this.userEmailHash = userEmailHash;
	}

	public int getUserEmailChecked() {
		return userEmailChecked;
	}

	public void setUserEmailChecked(int userEmailChecked) {
		this.userEmailChecked = userEmailChecked;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public String getFindPwQuestion() {
		return findPwQuestion;
	}

	public void setFindPwQuestion(String findPwQuestion) {
		this.findPwQuestion = findPwQuestion;
	}

	public String getFindPwAnswer() {
		return findPwAnswer;
	}

	public void setFindPwAnswer(String findPwAnswer) {
		this.findPwAnswer = findPwAnswer;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public UserDTO() {
	}

	public UserDTO(String userID, String userPassword, String userName, String userEmail, String userEmailHash,
			int userEmailChecked, String userProfile, String findPwQuestion, String findPwAnswer, String aboutMe) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userEmailHash = userEmailHash;
		this.userEmailChecked = userEmailChecked;
		this.userProfile = userProfile;
		this.findPwQuestion = findPwQuestion;
		this.findPwAnswer = findPwAnswer;
		this.aboutMe = aboutMe;
	}

	
}
