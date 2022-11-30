package gallery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import complaints.ComplaintsDTO;
import util.DatabaseUtil;

public class GalleryDAO {
	
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
	public int getNext() {
		String SQL = "SELECT galID FROM gallery ORDER BY galID DESC";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		try {	
			conn=DatabaseUtil.getConnection();
			pstmt =conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			rs = pstmt.executeQuery();
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
	
	public int write(String userID, String galTitle, String galContent,String galFile, String galRealFile) { 
		String SQL = "INSERT INTO GALLERY VALUES(?, ?, ?, ?, ?,?,?, 0,1)";
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext());
			pstmt.setString(2, galTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, galContent);
			pstmt.setString(6, galFile);
			pstmt.setString(7, galRealFile);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스오류
	}
	
	public int update(int galID, String galTitle, String galContent) {
		String SQL = "UPDATE gallery SET galTitle = ?, galContent = ? WHERE galID = ?";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, galTitle);
			pstmt.setString(2, galContent);
			pstmt.setInt(3, galID);
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
	
	public int delete(int galID) { 
		String SQL = "UPDATE GALLERY SET galAvailable =0 WHERE galID=?";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, galID);
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
	
	public static int hit(int galID) {
		String SQL ="UPDATE GALLERY SET galHit = galHit + 1 WHERE galID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,galID);
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
	
	public ArrayList<GalleryDTO> getList(int pageNumber){ 
		String SQL = "SELECT * FROM GALLERY WHERE galID < ? AND galAvailable = 1 ORDER BY galID DESC LIMIT 5";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		ArrayList<GalleryDTO> list = new ArrayList<GalleryDTO>();
		try {
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 5);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GalleryDTO gal = new GalleryDTO();
				gal.setGalID(rs.getInt(1));
				gal.setGalTitle(rs.getString(2));
				gal.setUserID(rs.getString(3));
				gal.setGalDate(rs.getString(4));
				gal.setGalContent(rs.getString(5));
				gal.setGalFile(rs.getString(6));
				gal.setGalRealFile(rs.getString(7));
				gal.setGalHit(rs.getInt(8));
				gal.setGalAvailable(rs.getInt(9));
				list.add(gal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return list;
	}
	
	public boolean nextPage(int pageNumber,boolean isStudent) {
		String SQL ="SELECT * FROM USER WHERE galID >= ? AND galAvailable=1";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {			
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*10);
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
	
	public int targetPage(int pageNumber) {
		String SQL ="SELECT COUNT(galID) FROM gallery WHERE galID > ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, (pageNumber-1)*10);
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
	
	public GalleryDTO getGal(int galID) {
		String SQL = "SELECT * FROM gallery WHERE galID = ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, galID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				GalleryDTO gal = new GalleryDTO();
				gal.setGalID(rs.getInt(1));
				gal.setGalTitle(rs.getString(2));
				gal.setUserID(rs.getString(3));
				gal.setGalDate(rs.getString(4));
				gal.setGalContent(rs.getString(5));
				gal.setGalFile(rs.getString(6));
				gal.setGalRealFile(rs.getString(7));
				gal.setGalHit(rs.getInt(8));
				gal.setGalAvailable(rs.getInt(9));
				return gal;
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
	public String getUserID(int galID) {
		String SQL="SELECT userID FROM gallery WHERE galID=?";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, galID);
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
	public int findFile(String galID,String galFile) {
		String SQL = "UPDATE gallery SET galFile = ? WHERE galID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,galFile);
			pstmt.setString(2,galID);
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
	
	public String getFile(String galID) {//파일을 불러오는 함수
		String SQL = "SELECT galFile FROM gallery WHERE galID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,galID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("galFile").equals("")) {
					return "http://localhost:8080/SnS/imgs/userIcon.png";
				}
				return "http://localhost:8080/SnS/upload/"+rs.getString("galFile");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return "http://localhost:8080/SnS/imgs/userIcon.png";
	}
	public String searchFileByID(String galID) {//파일의 이름 불러오는 함수
		String SQL = "SELECT galFile FROM gallery WHERE galID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,galID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return null;
	}
}

