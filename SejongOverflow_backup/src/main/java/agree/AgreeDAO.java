package agree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class AgreeDAO {
	
	public int agree(String userID, int cmpID,int isStudent,String userIP) {
		String SQL="INSERT INTO AGREE VALUES(?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setInt(2, cmpID);
			pstmt.setInt(3, isStudent);
			pstmt.setString(4, userIP);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null) conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null) rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
}
