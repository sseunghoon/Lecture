package report;

public class ReportDTO {
	int reportID;
	String userID;
	String reportTitle;
	String reportContent;
	String reportDate;
	String reportLink;
	int cmpID;
	int postID;
	int isStudent;
	int boardID;
	int processingStatus;
	
	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public int getCmpID() {
		return cmpID;
	}

	public void setCmpID(int cmpID) {
		this.cmpID = cmpID;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public int getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(int isStudent) {
		this.isStudent = isStudent;
	}

	public int getBoardID() {
		return boardID;
	}

	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}

	public int getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(int processingStatus) {
		this.processingStatus = processingStatus;
	}
	
	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	
	public String getReportLink() {
		return reportLink;
	}

	public void setReportLink(String reportLink) {
		this.reportLink = reportLink;
	}

	public ReportDTO() {
	}

	public ReportDTO(int reportID, String userID, String reportTitle, String reportContent, String reportDate,
			String reportLink, int cmpID, int postID, int isStudent, int boardID, int processingStatus) {
		super();
		this.reportID = reportID;
		this.userID = userID;
		this.reportTitle = reportTitle;
		this.reportContent = reportContent;
		this.reportDate = reportDate;
		this.reportLink = reportLink;
		this.cmpID = cmpID;
		this.postID = postID;
		this.isStudent = isStudent;
		this.boardID = boardID;
		this.processingStatus = processingStatus;
	}
}
