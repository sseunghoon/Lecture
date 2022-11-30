package post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DatabaseUtil;
import page.PageDAO;

public class PostDAO {

	public String getDate() {
		String SQL="SELECT NOW()";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		try {
			conn=DatabaseUtil.getConnection();
			pstmt =conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return "";
	}
	public String getBoardName(int boardID) {
		String SQL = "SELECT boardName FROM board WHERE boardID = ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		try {
			conn=DatabaseUtil.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1, boardID);
			rs=pstmt.executeQuery();
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return "";
	}
	
	public int getNext(int boardID) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		try {
			SQL = "SELECT postID FROM post WHERE boardID = ? ORDER BY postID DESC";
			conn=DatabaseUtil.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1, boardID);
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
	public int countPost(int boardID) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL = "SELECT COUNT(*) FROM post WHERE boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1, boardID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1; 
	}
	
	public String getFile(int boardID,int postID) {//파일을 불러오는 함수
		String SQL = "";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			SQL = "SELECT postFile FROM post WHERE postID=? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,postID);
			pstmt.setInt(2, boardID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("postFile").equals("")) {
					return "";
				}
				return rs.getString("postFile");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return "";
	}
	
	public String getRealFile(int boardID, int postID) {//파일을 불러오는 함수
		String SQL = "";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			SQL = "SELECT postRealFile FROM post WHERE postID=? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,postID);
			pstmt.setInt(2, boardID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("postRealFile").equals("")) {
					return "";
				}
				return rs.getString("postRealFile");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return "";
	}
	
	public ArrayList<PostDTO> getList(int pageNumber,int boardID){
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<PostDTO> list =new ArrayList<PostDTO>();
		try {
			SQL ="SELECT * FROM post WHERE postGroup >  (SELECT MAX(postGroup) FROM post) - ? AND postGroup <= (SELECT MAX(postGroup) FROM post) - ? AND boardID = ? AND postLevel=0 AND postAvailable=1 ORDER BY postGroup DESC";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*10);
			pstmt.setInt(2, (pageNumber-1)*10);
			pstmt.setInt(3, boardID);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				PostDTO postDTO =new PostDTO();
				postDTO.setPostID(rs.getInt(1));
				postDTO.setBoardID(rs.getInt(2));
				postDTO.setUserID(rs.getString(3));
				postDTO.setPostDate(rs.getString(4));
				postDTO.setPostTitle(rs.getString(5).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostContent(rs.getString(6).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostDivide(rs.getString(7));
				postDTO.setPostFile(rs.getString(8));
				postDTO.setPostRealFile(rs.getString(9));
				postDTO.setAgreeCount(rs.getInt(10));
				postDTO.setPostHit(rs.getInt(11));
				postDTO.setPostGroup(rs.getInt(12));
				postDTO.setPostSequence(rs.getInt(13));
				postDTO.setPostLevel(rs.getInt(14));
				postDTO.setPostAvailable(rs.getInt(15));
				list.add(postDTO);
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
	
	public ArrayList<PostDTO> getHotList(int pageNumber){
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<PostDTO> list =new ArrayList<PostDTO>();
		try {
			SQL ="SELECT * FROM post WHERE postGroup >  (SELECT MAX(postGroup) FROM post) - ? AND postGroup <= (SELECT MAX(postGroup) FROM post) - ? AND agreeCount>= ? AND postLevel=0 AND postAvailable=1 ORDER BY postGroup DESC";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*50);
			pstmt.setInt(2, (pageNumber-1)*50);
			pstmt.setInt(3, 10);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				PostDTO postDTO =new PostDTO();
				postDTO.setPostID(rs.getInt(1));
				postDTO.setBoardID(rs.getInt(2));
				postDTO.setUserID(rs.getString(3));
				postDTO.setPostDate(rs.getString(4));
				postDTO.setPostTitle(rs.getString(5).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostContent(rs.getString(6).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostDivide(rs.getString(7));
				postDTO.setPostFile(rs.getString(8));
				postDTO.setPostRealFile(rs.getString(9));
				postDTO.setAgreeCount(rs.getInt(10));
				postDTO.setPostHit(rs.getInt(11));
				postDTO.setPostGroup(rs.getInt(12));
				postDTO.setPostSequence(rs.getInt(13));
				postDTO.setPostLevel(rs.getInt(14));
				postDTO.setPostAvailable(rs.getInt(15));
				list.add(postDTO);
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
	
	public ArrayList<PostDTO> getSearch(String searchType, String search, int pageNumber,int boardID){
		String SQL="";
		ArrayList<PostDTO> searchList=null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			if(searchType.equals("최신순")) {
				SQL ="SELECT * FROM post WHERE postGroup >  (SELECT MAX(postGroup) FROM post) - ? AND postGroup <= (SELECT MAX(postGroup) FROM post) - ? AND boardID = ? AND CONCAT(postTitle,postContent,userID) LIKE ? AND postLevel=0 AND postAvailable=1 ORDER BY postGroup DESC";
			}
			else {
				SQL ="SELECT * FROM post WHERE postGroup >  (SELECT MAX(postGroup) FROM post) - ? AND postGroup <= (SELECT MAX(postGroup) FROM post) - ? AND boardID = ? AND CONCAT(postTitle,postContent,userID) LIKE ? AND postLevel=0 AND postAvailable=1 ORDER BY agreeCount DESC";
			}
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*200);
			pstmt.setInt(2, (pageNumber-1)*200);
			pstmt.setInt(3, boardID);
			pstmt.setString(4, "%" + search + "%");
			rs=pstmt.executeQuery();
			searchList= new ArrayList<PostDTO>();
			while(rs.next()) {
				PostDTO postDTO= new PostDTO();
				postDTO.setPostID(rs.getInt(1));
				postDTO.setBoardID(rs.getInt(2));
				postDTO.setUserID(rs.getString(3));
				postDTO.setPostDate(rs.getString(4));
				postDTO.setPostTitle(rs.getString(5).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostContent(rs.getString(6).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostDivide(rs.getString(7));
				postDTO.setPostFile(rs.getString(8));
				postDTO.setPostRealFile(rs.getString(9));
				postDTO.setAgreeCount(rs.getInt(10));
				postDTO.setPostHit(rs.getInt(11));
				postDTO.setPostGroup(rs.getInt(12));
				postDTO.setPostSequence(rs.getInt(13));
				postDTO.setPostLevel(rs.getInt(14));
				postDTO.setPostAvailable(rs.getInt(15));
				searchList.add(postDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return searchList;
	}
	
	public ArrayList<PostDTO> getHotSearch(String searchType, String search, int pageNumber){
		String SQL="";
		ArrayList<PostDTO> searchList=null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			if(searchType.equals("최신순")) {
				SQL ="SELECT * FROM post WHERE postGroup >  (SELECT MAX(postGroup) FROM post) - ? AND postGroup <= (SELECT MAX(postGroup) FROM post) - ? AND agreeCount >= ? AND CONCAT(postTitle,postContent,userID) LIKE ? AND postLevel=0 AND postAvailable=1 ORDER BY postGroup DESC";
			}
			else {
				SQL ="SELECT * FROM post WHERE postGroup >  (SELECT MAX(postGroup) FROM post) - ? AND postGroup <= (SELECT MAX(postGroup) FROM post) - ? AND agreeCount >= ? AND CONCAT(postTitle,postContent,userID) LIKE ? AND postLevel=0 AND postAvailable=1 ORDER BY agreeCount DESC";
			}
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*200);
			pstmt.setInt(2, (pageNumber-1)*200);
			pstmt.setInt(3, 10);
			pstmt.setString(4, "%" + search + "%");
			rs=pstmt.executeQuery();
			searchList= new ArrayList<PostDTO>();
			while(rs.next()) {
				PostDTO postDTO= new PostDTO();
				postDTO.setPostID(rs.getInt(1));
				postDTO.setBoardID(rs.getInt(2));
				postDTO.setUserID(rs.getString(3));
				postDTO.setPostDate(rs.getString(4));
				postDTO.setPostTitle(rs.getString(5).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostContent(rs.getString(6).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostDivide(rs.getString(7));
				postDTO.setPostFile(rs.getString(8));
				postDTO.setPostRealFile(rs.getString(9));
				postDTO.setAgreeCount(rs.getInt(10));
				postDTO.setPostHit(rs.getInt(11));
				postDTO.setPostGroup(rs.getInt(12));
				postDTO.setPostSequence(rs.getInt(13));
				postDTO.setPostLevel(rs.getInt(14));
				postDTO.setPostAvailable(rs.getInt(15));
				searchList.add(postDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return searchList;
	}
	
	public ArrayList<PostDTO> getReply(int postGroup,int boardID){
		String SQL="";
		ArrayList<PostDTO> list=null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
	
		list =new ArrayList<PostDTO>();
		try {
			SQL ="SELECT * FROM post WHERE postGroup = ? AND boardID = ? AND postLevel != 0 AND postAvailable=1 ORDER BY postSequence ASC";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, postGroup);
			pstmt.setInt(2, boardID);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				PostDTO postDTO =new PostDTO();
				postDTO.setPostID(rs.getInt(1));
				postDTO.setBoardID(rs.getInt(2));
				postDTO.setUserID(rs.getString(3));
				postDTO.setPostDate(rs.getString(4));
				postDTO.setPostTitle(rs.getString(5).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostContent(rs.getString(6).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostDivide(rs.getString(7));
				postDTO.setPostFile(rs.getString(8));
				postDTO.setPostRealFile(rs.getString(9));
				postDTO.setAgreeCount(rs.getInt(10));
				postDTO.setPostHit(rs.getInt(11));
				postDTO.setPostGroup(rs.getInt(12));
				postDTO.setPostSequence(rs.getInt(13));
				postDTO.setPostLevel(rs.getInt(14));
				postDTO.setPostAvailable(rs.getInt(15));
				list.add(postDTO);
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
	public boolean nextPage(int pageNumber,int boardID) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL ="SELECT * FROM post WHERE postGroup >= ? AND boardID = ? AND postAvailable=1";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*10);
			pstmt.setInt(2, boardID);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return false;
	}
	public int targetPage(int pageNumber,int boardID) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL ="SELECT COUNT(postGroup) FROM post WHERE postGroup > ? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, (pageNumber-1)*10);
			pstmt.setInt(2, boardID);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)/10;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return 0;
	}
	
	public int hotTargetPage(int pageNumber) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL ="SELECT COUNT(postGroup) FROM post WHERE postGroup > ? AND agreeCount >= ?";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, (pageNumber-1)*10);
			pstmt.setInt(2, 10);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)/10;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return 0;
	}
	
	public PostDTO getPost(int postID,int boardID) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL = "SELECT * FROM post WHERE postID = ? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, postID);
			pstmt.setInt(2, boardID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				PostDTO postDTO = new PostDTO();
				postDTO.setPostID(rs.getInt(1));
				postDTO.setBoardID(rs.getInt(2));
				postDTO.setUserID(rs.getString(3));
				postDTO.setPostDate(rs.getString(4));
				postDTO.setPostTitle(rs.getString(5).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostContent(rs.getString(6).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				postDTO.setPostDivide(rs.getString(7));
				postDTO.setPostFile(rs.getString(8));
				postDTO.setPostRealFile(rs.getString(9));
				postDTO.setAgreeCount(rs.getInt(10));
				postDTO.setPostHit(rs.getInt(11));
				postDTO.setPostGroup(rs.getInt(12));
				postDTO.setPostSequence(rs.getInt(13));
				postDTO.setPostLevel(rs.getInt(14));
				postDTO.setPostAvailable(rs.getInt(15));
				return postDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return null;
	}
	public int agree(int postID, int boardID) { 
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL = "UPDATE post SET agreeCount = agreeCount + 1 WHERE postID = ? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, postID);
			pstmt.setInt(2, boardID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1; //데이터베이스 오류
	}
	public int hit(int postID,int boardID) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL ="UPDATE post SET postHit = postHit + 1 WHERE postID=? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,postID);
			pstmt.setInt(2,boardID);
			return pstmt.executeUpdate();
			}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	public int write(PostDTO postDTO, int boardID) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL ="INSERT INTO post VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, IFNULL((SELECT MAX(postGroup)+1 FROM post a),0),0,0,1)";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext(boardID));
			pstmt.setInt(2,boardID);
			pstmt.setString(3,postDTO.getUserID());
			pstmt.setString(4,getDate());
			pstmt.setString(5,postDTO.getPostTitle());
			pstmt.setString(6,postDTO.getPostContent());
			pstmt.setString(7,postDTO.getPostDivide());
			pstmt.setString(8,postDTO.getPostFile());
			pstmt.setString(9,postDTO.getPostRealFile());
			return pstmt.executeUpdate();
			}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	public int update(int postID, String postTitle, String postContent,int boardID) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL = "UPDATE post SET postTitle = ?, postContent = ? WHERE postID = ? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, postTitle);
			pstmt.setString(2, postContent);
			pstmt.setInt(3, postID);
			pstmt.setInt(4, boardID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1; //데이터베이스 오류
	}
	public int delete(int postID, int boardID) { 
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL = "UPDATE post SET postAvailable =0 WHERE postID=? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, postID);
			pstmt.setInt(2, boardID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1; //데이터베이스 오류
	}
	public int reply(PostDTO postDTO,PostDTO parent, int boardID) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {			
			SQL ="INSERT INTO post VALUES(?, ?, ?, ?, ?, ?, ?,?,?, 0,0,?,?,?,1)";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext(boardID));
			pstmt.setInt(2,boardID);
			pstmt.setString(3,postDTO.getUserID());
			pstmt.setString(4,getDate());
			pstmt.setString(5,postDTO.getPostTitle());			
			pstmt.setString(6,postDTO.getPostContent());
			pstmt.setString(7,postDTO.getPostDivide());
			pstmt.setString(8,"");
			pstmt.setString(9,"");
			pstmt.setInt(10,parent.getPostGroup());
			pstmt.setInt(11,parent.getPostSequence()+1);
			pstmt.setInt(12,parent.getPostLevel()+1);
			return pstmt.executeUpdate();
			}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	public int replyUpdate(PostDTO parent,int boardID) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
			SQL = "UPDATE post SET postSequence = postSequence + 1 WHERE postGroup = ? AND boardID = ? AND postSequence > ?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, parent.getPostGroup());
			pstmt.setInt(2, boardID);
			pstmt.setInt(3, parent.getPostSequence());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}	
		}
		return -1; //데이터베이스 오류
	}
	public String getUserID(int postID,int boardID) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL="SELECT userID FROM post WHERE postID=? AND boardID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, postID);
			pstmt.setInt(2, boardID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return null; //데이터베이스 오류
	}
}