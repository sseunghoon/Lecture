package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import complaints.ComplaintsDTO;
import post.PostDTO;
import util.DatabaseUtil;

public class UserDAO {
	public int login(String userID,String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID =?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;
				}else{
					return 0;
				}
			}return -1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -2;
	}
	
	public int join(UserDTO user) {
		String SQL = "INSERT INTO USER VALUES(?,?,?,?,?,false,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,user.getUserID());
			pstmt.setString(2,user.getUserPassword());
			pstmt.setString(3,user.getUserName());
			pstmt.setString(4,user.getUserEmail());
			pstmt.setString(5,user.getUserEmailHash());
			pstmt.setString(6,user.getUserProfile());
			pstmt.setString(7,user.getFindPwQuestion());
			pstmt.setString(8,user.getFindPwAnswer());
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
	
	public int kick(String userID) {
		String SQL = "DELETE FROM USER WHERE userID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
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
	
	public int promotion(String promotionID) {
		String SQL = "UPDATE USER SET userEmailChecked = 2 WHERE userID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,promotionID);
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public int registerCheck(String userID) {
		String SQL = "SELECT * FROM USER WHERE userID = ?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs= pstmt.executeQuery();
			if(rs.next() || userID.equals("")) {
				return 0;
			}else {
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public int getUserEmailChecked(String userID) {
		String SQL = "SELECT userEmailChecked FROM USER WHERE userID =?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public UserDTO getUser(String userID) {
		String SQL="";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			SQL = "SELECT * FROM user WHERE userID= ?";
			conn=DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				UserDTO user = new UserDTO();
				user.setUserID(rs.getString(1));
				user.setUserName(rs.getString(3));
				user.setUserEmail(rs.getString(4));
				user.setUserEmailChecked(rs.getInt(6));
				user.setUserProfile(rs.getString(7));
				user.setAboutMe(rs.getString(10));
				return user;
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
	
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USER WHERE userID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
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
	public String getUserID(String userEmail) {
		String SQL = "SELECT userID FROM USER WHERE userEmail=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userEmail);
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
	public String searchProfileByID(String userID) {//������ �̸� �ҷ����� �Լ�
		String SQL = "SELECT userProfile FROM USER WHERE userID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
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
	public String getProfile(String userID) {//������ �ҷ����� �Լ�
		String SQL = "SELECT userProfile FROM USER WHERE userID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("userProfile").equals("")) {
					return "http://localhost:8080/SnS/imgs/userIcon.png";
				}
				return "http://localhost:8080/SnS/upload/"+rs.getString("userProfile");
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
	public String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		System.out.println("charSet.length :::: "+charSet.length);
		for (int i = 0; i < len; i++){
			idx = (int) (charSet.length * Math.random()); // 36 * ������ ������ Int�� ���� (�Ҽ�������)
			System.out.println("idx :::: "+idx);
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}
	public boolean setUserPassword(String password,String userID) {
		String SQL = "UPDATE USER SET userPassword = ? WHERE userID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,password);
			pstmt.setString(2,userID);
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return false;
	}
	public boolean setUserEmailChecked(String code) {
		String SQL = "UPDATE USER SET userEmailChecked = 1 WHERE userEmailHash=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,code);
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn!=null)conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt!=null)pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs!=null)rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return false;
	}
	public int profile(String userID,String userProfile) {
		String SQL = "UPDATE USER SET userProfile = ? WHERE userID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userProfile);
			pstmt.setString(2,userID);
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
	
	public int update(String userID,String userName, String userEmail, String aboutMe) {
		String SQL = "UPDATE USER SET userName = ?,userEmail = ?,aboutMe = ? WHERE userID=?";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,userName);
			pstmt.setString(2,userEmail);
			pstmt.setString(3,aboutMe);
			pstmt.setString(4,userID);
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
	
	public ArrayList<UserDTO> getList(){
		String SQL ="SELECT * FROM user ORDER BY userID DESC";
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<UserDTO> list =new ArrayList<UserDTO>();
		try {
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				UserDTO userDTO =new UserDTO();
				userDTO.setUserID(rs.getString(1));
				userDTO.setUserName(rs.getString(3));
				userDTO.setUserEmail(rs.getString(4));
				userDTO.setUserProfile(rs.getString(7));
				userDTO.setAboutMe(rs.getString(10));
				list.add(userDTO);
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
}
