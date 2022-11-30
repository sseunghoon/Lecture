package board;

public class BoardDTO {
	int boardID;
	String boardName;
	String boardURL;
	int boardAvailable;
	int boardLevel;
	
	public int getBoardID() {
		return boardID;
	}
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardURL() {
		return boardURL;
	}
	public void setBoardURL(String boardURL) {
		this.boardURL = boardURL;
	}
	public int getBoardAvailable() {
		return boardAvailable;
	}
	public void setBoardAvailable(int boardAvailable) {
		this.boardAvailable = boardAvailable;
	}
	public int getBoardLevel() {
		return boardLevel;
	}
	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
	}
	public BoardDTO() {

	}
	public BoardDTO(int boardID, String boardName, String boardURL, int boardAvailable, int boardLevel) {
		super();
		this.boardID = boardID;
		this.boardName = boardName;
		this.boardURL = boardURL;
		this.boardAvailable = boardAvailable;
		this.boardLevel = boardLevel;
	}
	
	
}
