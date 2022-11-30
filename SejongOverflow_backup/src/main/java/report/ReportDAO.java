package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DatabaseUtil;
import page.PageDAO;

public class ReportDAO {

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
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;	
		try {
			SQL = "SELECT reportID FROM report ORDER BY reportID DESC";
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
	
	public ArrayList<ReportDTO> getList(){
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<ReportDTO> list =new ArrayList<ReportDTO>();
		try {
			SQL ="SELECT * FROM report WHERE processingStatus = 0 ORDER BY reportID ASC";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				ReportDTO reportDTO =new ReportDTO();
				reportDTO.setReportID(rs.getInt(1));
				reportDTO.setUserID(rs.getString(2));
				reportDTO.setReportTitle(rs.getString(3).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				reportDTO.setReportContent(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				reportDTO.setReportDate(rs.getString(5));
				reportDTO.setReportLink(rs.getString(6));
				reportDTO.setCmpID(rs.getInt(7));
				reportDTO.setPostID(rs.getInt(8));
				reportDTO.setIsStudent(rs.getInt(9));
				reportDTO.setBoardID(rs.getInt(10));
				reportDTO.setProcessingStatus(rs.getInt(11));
				list.add(reportDTO);
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
	
	public ReportDTO getReport(int reportID) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL = "SELECT * FROM report WHERE reportID = ?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, reportID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ReportDTO reportDTO =new ReportDTO();
				reportDTO.setReportID(rs.getInt(1));
				reportDTO.setUserID(rs.getString(2));
				reportDTO.setReportTitle(rs.getString(3).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				reportDTO.setReportContent(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>"));
				reportDTO.setReportDate(rs.getString(5));
				reportDTO.setReportLink(rs.getString(6));
				reportDTO.setCmpID(rs.getInt(7));
				reportDTO.setPostID(rs.getInt(8));
				reportDTO.setIsStudent(rs.getInt(9));
				reportDTO.setBoardID(rs.getInt(10));
				reportDTO.setProcessingStatus(rs.getInt(11));
				return reportDTO;
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
	
	public int write(ReportDTO reportDTO) {
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL ="INSERT INTO report VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext());
			pstmt.setString(2,reportDTO.getUserID());
			pstmt.setString(3,reportDTO.getReportTitle());
			pstmt.setString(4,reportDTO.getReportContent());
			pstmt.setString(5,getDate());
			pstmt.setString(6,reportDTO.getReportLink());
			pstmt.setInt(7,reportDTO.getCmpID());
			pstmt.setInt(8,reportDTO.getPostID());
			pstmt.setInt(9,reportDTO.getIsStudent());
			pstmt.setInt(10,reportDTO.getBoardID());
			pstmt.setInt(11,reportDTO.getProcessingStatus());
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
	
	public int delete(int reportID) { 
		String SQL="";
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			SQL = "UPDATE report SET processingStatus =0 WHERE reportID=?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, reportID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1; //�����ͺ��̽� ����
	}
}