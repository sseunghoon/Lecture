package complaints;

public class ComplaintsDTO {
	int cmpID;
	String userID;
	String cmpDate;
	String cmpTitle;
	String cmpContent;
	String cmpDivide;
	String cmpFile;
	String cmpRealFile;
	int agreeCount;
	int cmpHit;
	int cmpGroup;
	int cmpSequence;
	int cmpLevel;
	int cmpAvailable;
	int isStudent;
	
	public int getCmpID() {
		return cmpID;
	}
	public void setCmpID(int cmpID) {
		this.cmpID = cmpID;
	}
	public String getCmpTitle() {
		return cmpTitle;
	}
	public void setCmpTitle(String cmpTitle) {
		this.cmpTitle = cmpTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCmpContent() {
		return cmpContent;
	}
	public void setCmpContent(String cmpContent) {
		this.cmpContent = cmpContent;
	}
	public String getCmpDivide() {
		return cmpDivide;
	}
	public void setCmpDivide(String cmpDivide) {
		this.cmpDivide = cmpDivide;
	}
	public String getCmpFile() {
		return cmpFile;
	}
	public void setCmpFile(String cmpFile) {
		this.cmpFile = cmpFile;
	}
	public String getCmpRealFile() {
		return cmpRealFile;
	}
	public void setCmpRealFile(String cmpRealFile) {
		this.cmpRealFile = cmpRealFile;
	}
	public String getCmpDate() {
		return cmpDate;
	}
	public void setCmpDate(String cmpDate) {
		this.cmpDate = cmpDate;
	}
	public int getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(int agreeCount) {
		this.agreeCount = agreeCount;
	}
	public int getCmpHit() {
		return cmpHit;
	}
	public void setCmpHit(int cmpHit) {
		this.cmpHit = cmpHit;
	}
	public int getCmpGroup() {
		return cmpGroup;
	}
	public void setCmpGroup(int cmpGroup) {
		this.cmpGroup = cmpGroup;
	}
	public int getCmpSequence() {
		return cmpSequence;
	}
	public void setCmpSequence(int cmpSequence) {
		this.cmpSequence = cmpSequence;
	}
	public int getCmpLevel() {
		return cmpLevel;
	}
	public void setCmpLevel(int cmpLevel) {
		this.cmpLevel = cmpLevel;
	}
	public int getCmpAvailable() {
		return cmpAvailable;
	}
	public void setCmpAvailable(int cmpAvailable) {
		this.cmpAvailable = cmpAvailable;
	}
	public int getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(int isStudent) {
		this.isStudent = isStudent;
	}
	
	public ComplaintsDTO() {

	}
	public ComplaintsDTO(int cmpID, String userID, String cmpDate, String cmpTitle, String cmpContent, String cmpDivide,
			String cmpFile, String cmpRealFile, int agreeCount, int cmpHit, int cmpGroup, int cmpSequence, int cmpLevel,
			int cmpAvailable, int isStudent) {
		super();
		this.cmpID = cmpID;
		this.userID = userID;
		this.cmpDate = cmpDate;
		this.cmpTitle = cmpTitle;
		this.cmpContent = cmpContent;
		this.cmpDivide = cmpDivide;
		this.cmpFile = cmpFile;
		this.cmpRealFile = cmpRealFile;
		this.agreeCount = agreeCount;
		this.cmpHit = cmpHit;
		this.cmpGroup = cmpGroup;
		this.cmpSequence = cmpSequence;
		this.cmpLevel = cmpLevel;
		this.cmpAvailable = cmpAvailable;
		this.isStudent = isStudent;
	}
	
	
	
}
