package gallery;

public class GalleryDTO {

	private int GalID;
	private String GalTitle;
	private String userID;
	private String GalDate;
	private String GalContent;
	private String GalFile;
	private String GalRealFile;
	private int GalHit;
	private int GalAvailable;
	
	public int getGalID() {
		return GalID;
	}
	public void setGalID(int galID) {
		GalID = galID;
	}
	public String getGalTitle() {
		return GalTitle;
	}
	public void setGalTitle(String galTitle) {
		GalTitle = galTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getGalDate() {
		return GalDate;
	}
	public void setGalDate(String galDate) {
		GalDate = galDate;
	}
	public String getGalContent() {
		return GalContent;
	}
	public void setGalContent(String galContent) {
		GalContent = galContent;
	}
	public String getGalFile() {
		return GalFile;
	}
	public void setGalFile(String galFile) {
		GalFile = galFile;
	}
	public String getGalRealFile() {
		return GalRealFile;
	}
	public void setGalRealFile(String galRealFile) {
		GalRealFile = galRealFile;
	}
	public int getGalHit() {
		return GalHit;
	}
	public void setGalHit(int galHit) {
		GalHit = galHit;
	}
	public int getGalAvailable() {
		return GalAvailable;
	}
	public void setGalAvailable(int galAvailable) {
		GalAvailable = galAvailable;
	}
	
	public GalleryDTO() {
		
	}
	public GalleryDTO(int galID, String galTitle, String userID, String galDate, String galContent, String galFile,String galRealFile,
			int galHit, int galAvailable) {
		super();
		GalID = galID;
		GalTitle = galTitle;
		this.userID = userID;
		GalDate = galDate;
		GalContent = galContent;
		GalFile = galFile;
		GalRealFile=galRealFile;
		GalHit = galHit;
		GalAvailable = galAvailable;
	}
	
}



