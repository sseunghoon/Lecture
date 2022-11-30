package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.BoardDTO;
import util.DatabaseUtil;

public class BoardDAO {
	public int getNext() {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		try {
			SQL = "SELECT boardID FROM board ORDER BY boardID DESC";
			conn=DatabaseUtil.getConnection();
			pstmt =conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1; 
	}
	
	public BoardDTO getBoard(int boardID){
		String SQL ="SELECT * FROM board WHERE boardID =?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		BoardDTO board =new BoardDTO();
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, boardID);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				board.setBoardID(rs.getInt(1));
				board.setBoardName(rs.getString(2));
				board.setBoardURL(rs.getString(3));
				board.setBoardAvailable(rs.getInt(4));
				return board;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return board;
	}
	
	public ArrayList<BoardDTO> getList(){
		String SQL ="SELECT * FROM board ORDER BY boardID ASC";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<BoardDTO> list =new ArrayList<BoardDTO>();
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board =new BoardDTO();
				board.setBoardID(rs.getInt(1));
				board.setBoardName(rs.getString(2));
				board.setBoardURL(rs.getString(3));
				board.setBoardAvailable(rs.getInt(4));
				board.setBoardLevel(rs.getInt(5));
				list.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return list;
	}
	
	public int create(BoardDTO board) {
		String SQL = "INSERT INTO BOARD VALUES(?, ? ,?,1,?)";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,board.getBoardID());
			pstmt.setString(2,board.getBoardName());
			pstmt.setString(3,board.getBoardURL());
			pstmt.setInt(4,board.getBoardLevel());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public int update(BoardDTO board) {
		String SQL = "UPDATE board SET boardName =?, boardURL = ?,boardLevel=?  WHERE boardID= ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,board.getBoardName());
			pstmt.setString(2,board.getBoardURL());
			pstmt.setInt(3,board.getBoardLevel());
			pstmt.setInt(4,board.getBoardID());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public int ban(int boardID) {
		String SQL = "UPDATE board SET boardAvailable = 0 WHERE boardID= ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,boardID);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public int unBan(int boardID) {
		String SQL = "UPDATE board SET boardAvailable = 1 WHERE boardID= ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,boardID);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public int delete(int boardID) {
		String SQL = "DELETE FROM board WHERE boardID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,boardID);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
}
