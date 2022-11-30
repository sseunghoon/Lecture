package complaints;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DatabaseUtil;

public class ComplaintsDAO {

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
	public int getNext(int isStudent) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		try {
			SQL = "SELECT cmpID FROM CMP WHERE isStudent = ? ORDER BY cmpID DESC";
			conn=DatabaseUtil.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1,isStudent);
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
	public int countCmp(int isStudent) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		SQL = "SELECT COUNT(*) FROM CMP WHERE isStudent = ?";
		try {
			conn=DatabaseUtil.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1,isStudent);
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
	
	public String getFile(int cmpID, int isStudent) {//파일을 불러오는 함수
		String SQL = "SELECT cmpFile FROM CMP WHERE cmpID=? AND isStudent = ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,cmpID);
			pstmt.setInt(2,isStudent);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("cmpFile").equals("")) {
					return "";
				}
				return rs.getString("cmpFile");
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
	
	public String getRealFile(int cmpID,int isStudent) {//파일을 불러오는 함수
		String SQL = "SELECT cmpRealFile FROM CMP WHERE cmpID=? AND isStudent = ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,cmpID);
			pstmt.setInt(2,isStudent);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("cmpRealFile").equals("")) {
					return "";
				}
				return rs.getString("cmpRealFile");
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
	
	public ArrayList<ComplaintsDTO> getList(int pageNumber,int isStudent){
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<ComplaintsDTO> list =new ArrayList<ComplaintsDTO>();
		try {
			SQL ="SELECT * FROM CMP WHERE cmpGroup >  (SELECT MAX(cmpGroup) FROM CMP) - ? AND cmpGroup <= (SELECT MAX(cmpGroup) FROM CMP) - ?  AND cmpLevel=0 AND cmpAvailable=1 AND isStudent= ? ORDER BY cmpGroup DESC";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*10);
			pstmt.setInt(2, (pageNumber-1)*10);
			pstmt.setInt(3,isStudent);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				ComplaintsDTO cmpDTO =new ComplaintsDTO();
				cmpDTO.setCmpID(rs.getInt(1));
				cmpDTO.setCmpTitle(rs.getString(2));
				cmpDTO.setUserID(rs.getString(3));
				cmpDTO.setCmpDate(rs.getString(4));
				cmpDTO.setCmpContent(rs.getString(5));
				cmpDTO.setCmpDivide(rs.getString(6));
				cmpDTO.setCmpFile(rs.getString(7));
				cmpDTO.setCmpRealFile(rs.getString(8));
				cmpDTO.setAgreeCount(rs.getInt(9));
				cmpDTO.setCmpHit(rs.getInt(10));
				cmpDTO.setCmpGroup(rs.getInt(11));
				cmpDTO.setCmpSequence(rs.getInt(12));
				cmpDTO.setCmpLevel(rs.getInt(13));
				cmpDTO.setCmpAvailable(rs.getInt(14));
				cmpDTO.setIsStudent(rs.getInt(15));
				list.add(cmpDTO);
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
	
	public ArrayList<ComplaintsDTO> getSearch(String searchType, String search, int pageNumber,int isStudent){
		String SQL="";
		ArrayList<ComplaintsDTO> searchList=null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			if(searchType.equals("최신순")) {
				SQL ="SELECT * FROM CMP WHERE cmpGroup >  (SELECT MAX(cmpGroup) FROM CMP) - ? AND cmpGroup <= (SELECT MAX(cmpGroup) FROM CMP) - ?  AND CONCAT(cmpTitle,cmpContent,userID) LIKE ? AND cmpLevel=0 AND cmpAvailable=1 AND isStudent= ? ORDER BY cmpGroup DESC";
			}
			else {
				SQL ="SELECT * FROM CMP WHERE cmpGroup >  (SELECT MAX(cmpGroup) FROM CMP) - ? AND cmpGroup <= (SELECT MAX(cmpGroup) FROM CMP) - ?  AND CONCAT(cmpTitle,cmpContent,userID) LIKE ? AND cmpLevel=0 AND cmpAvailable=1 AND isStudent= ? ORDER BY agreeCount DESC";
			}
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*200);
			pstmt.setInt(2, (pageNumber-1)*200);
			pstmt.setString(3, "%" + search + "%");
			pstmt.setInt(4,isStudent);
			rs=pstmt.executeQuery();
			searchList= new ArrayList<ComplaintsDTO>();
			while(rs.next()) {
				ComplaintsDTO cmpDTO= new ComplaintsDTO();
				cmpDTO.setCmpID(rs.getInt(1));
				cmpDTO.setCmpTitle(rs.getString(2));
				cmpDTO.setUserID(rs.getString(3));
				cmpDTO.setCmpDate(rs.getString(4));
				cmpDTO.setCmpContent(rs.getString(5));
				cmpDTO.setCmpDivide(rs.getString(6));
				cmpDTO.setCmpFile(rs.getString(7));
				cmpDTO.setCmpRealFile(rs.getString(8));
				cmpDTO.setAgreeCount(rs.getInt(9));
				cmpDTO.setCmpHit(rs.getInt(10));
				cmpDTO.setCmpGroup(rs.getInt(11));
				cmpDTO.setCmpSequence(rs.getInt(12));
				cmpDTO.setCmpLevel(rs.getInt(13));
				cmpDTO.setCmpAvailable(rs.getInt(14));
				cmpDTO.setIsStudent(rs.getInt(15));
				searchList.add(cmpDTO);
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
	public ArrayList<ComplaintsDTO> getReply(int cmpGroup,int isStudent){
		String SQL="";
		ArrayList<ComplaintsDTO> list=null;
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
	
		list =new ArrayList<ComplaintsDTO>();
		try {
			SQL ="SELECT * FROM CMP WHERE cmpGroup = ? AND cmpLevel != 0 AND cmpAvailable=1 AND isStudent= ? ORDER BY cmpSequence ASC";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, cmpGroup);
			pstmt.setInt(2,isStudent);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				ComplaintsDTO cmpDTO =new ComplaintsDTO();
				cmpDTO.setCmpID(rs.getInt(1));
				cmpDTO.setCmpTitle(rs.getString(2));
				cmpDTO.setUserID(rs.getString(3));
				cmpDTO.setCmpDate(rs.getString(4));
				cmpDTO.setCmpContent(rs.getString(5));
				cmpDTO.setCmpDivide(rs.getString(6));
				cmpDTO.setCmpFile(rs.getString(7));
				cmpDTO.setCmpRealFile(rs.getString(8));
				cmpDTO.setAgreeCount(rs.getInt(9));
				cmpDTO.setCmpHit(rs.getInt(10));
				cmpDTO.setCmpGroup(rs.getInt(11));
				cmpDTO.setCmpSequence(rs.getInt(12));
				cmpDTO.setCmpLevel(rs.getInt(13));
				cmpDTO.setCmpAvailable(rs.getInt(14));
				cmpDTO.setIsStudent(rs.getInt(15));
				list.add(cmpDTO);
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
	public boolean nextPage(int pageNumber,int isStudent) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL ="SELECT * FROM CMP WHERE cmpGroup >= ? AND cmpAvailable=1 AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pageNumber*10);
			pstmt.setInt(2,isStudent);
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
	public int targetPage(int pageNumber,int isStudent) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL ="SELECT COUNT(cmpGroup) FROM CMP WHERE cmpGroup > ? AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, (pageNumber-1)*10);
			pstmt.setInt(2,isStudent);
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
	public ComplaintsDTO getCmp(int cmpID,int isStudent) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL = "SELECT * FROM CMP WHERE cmpID = ? AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, cmpID);
			pstmt.setInt(2,isStudent);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ComplaintsDTO cmp = new ComplaintsDTO();
				cmp.setCmpID(rs.getInt(1));
				cmp.setCmpTitle(rs.getString(2).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				cmp.setUserID(rs.getString(3));
				cmp.setCmpDate(rs.getString(4));
				cmp.setCmpContent(rs.getString(5).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				cmp.setCmpDivide(rs.getString(6));
				cmp.setCmpFile(rs.getString(7));
				cmp.setCmpRealFile(rs.getString(8));
				cmp.setAgreeCount(rs.getInt(9));
				cmp.setCmpHit(rs.getInt(10));
				cmp.setCmpGroup(rs.getInt(11));
				cmp.setCmpSequence(rs.getInt(12));
				cmp.setCmpLevel(rs.getInt(13));
				cmp.setCmpAvailable(rs.getInt(14));
				cmp.setIsStudent(rs.getInt(15));
				return cmp;
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
	public int agree(int cmpID, int isStudent) { 
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL = "UPDATE CMP SET agreeCount = agreeCount + 1 WHERE cmpID = ? AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, cmpID);
			pstmt.setInt(2,isStudent);
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
	public static int hit(int cmpID,int isStudent) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL ="UPDATE CMP SET cmpHit = cmpHit + 1 WHERE cmpID=? AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,cmpID);
			pstmt.setInt(2,isStudent);
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
	public int write(ComplaintsDTO complaintsDTO, int isStudent) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL ="INSERT INTO CMP VALUES(?, ?, ?, ?, ?, ?, ?, ?, 0,0,IFNULL((SELECT MAX(cmpGroup)+1 FROM CMP a),0),0,0,1,?)";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext(isStudent));
			pstmt.setString(2,complaintsDTO.getCmpTitle());
			pstmt.setString(3,complaintsDTO.getUserID());
			pstmt.setString(4,getDate());
			pstmt.setString(5,complaintsDTO.getCmpContent());
			pstmt.setString(6,complaintsDTO.getCmpDivide());
			pstmt.setString(7,complaintsDTO.getCmpFile());
			pstmt.setString(8,complaintsDTO.getCmpRealFile());
			pstmt.setInt(9,isStudent);
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
	public int update(int cmpID, String cmpTitle, String cmpContent,int isStudent) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL = "UPDATE CMP SET cmpTitle = ?, cmpContent = ? WHERE cmpID = ? AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, cmpTitle);
			pstmt.setString(2, cmpContent);
			pstmt.setInt(3, cmpID);
			pstmt.setInt(4,isStudent);
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
	public int delete(int cmpID, int isStudent) { 
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL = "UPDATE CMP SET cmpAvailable =0 WHERE cmpID=? AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, cmpID);
			pstmt.setInt(2,isStudent);
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
	public int reply(ComplaintsDTO complaintsDTO,ComplaintsDTO parent, int isStudent) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL ="INSERT INTO CMP VALUES(?, ?, ?, ?, ?, ?,NULL,NULL, 0,0,?,?,?,1,?)";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext(isStudent));
			pstmt.setString(2,complaintsDTO.getCmpTitle());
			pstmt.setString(3,complaintsDTO.getUserID());
			pstmt.setString(4,getDate());
			pstmt.setString(5,complaintsDTO.getCmpContent());
			pstmt.setString(6,complaintsDTO.getCmpDivide());
			pstmt.setInt(7,parent.getCmpGroup());
			pstmt.setInt(8,parent.getCmpSequence()+1);
			pstmt.setInt(9,parent.getCmpLevel()+1);
			pstmt.setInt(10,isStudent);
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
	public int replyUpdate(ComplaintsDTO parent,int isStudent) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		try {
			SQL = "UPDATE CMP SET cmpSequence = cmpSequence + 1 WHERE cmpGroup = ? AND cmpSequence > ? AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, parent.getCmpGroup());
			pstmt.setInt(2, parent.getCmpSequence());
			pstmt.setInt(3,isStudent);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}	
		}
		return -1; //데이터베이스 오류
	}
	public String getUserID(int cmpID,int isStudent) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL="SELECT userID FROM CMP WHERE cmpID=? AND isStudent= ? ";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, cmpID);
			pstmt.setInt(2,isStudent);
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