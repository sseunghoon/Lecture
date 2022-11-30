<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="complaints.ComplaintsDTO"%>
<%@ page import="complaints.ComplaintsDAO"%>
<%@ page import="agree.AgreeDAO"%>
<%@ page import="agree.AgreeDTO"%>
<%@ page import="java.io.PrintWriter"%>

<%!
	public static String getClientIP(HttpServletRequest request){
	String ip=request.getHeader("X-FORWARDED-FOR");
	if(ip==null||ip.length()==0){
		ip=request.getHeader("Proxy-Client-IP");
	}
	if(ip==null||ip.length()==0){
		ip=request.getHeader("WL-Proxy-Client_IP");
	}
	if(ip==null||ip.length()==0){
		ip=request.getRemoteAddr();
	}
	return ip;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>세종대학교 소프트웨어융합대학 :: 민원 :: 학생회 건의사항</title>
</head>
<body>
<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int cmpID=0;
		if (request.getParameter("cmpID") != null) { 
			cmpID =Integer.parseInt(request.getParameter("cmpID"));
		}
		String userID = null;
		if (session.getAttribute("userID") != null) { 
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요.')");
			script.println("history.back()");
			script.println("</script>");
			script.close();
			return;
		}
		int isStudent = -1;
		if (request.getParameter("isStudent") != null) {
			isStudent = Integer.parseInt(request.getParameter("isStudent"));
		}
		ComplaintsDAO cmpDAO = new ComplaintsDAO();
		AgreeDAO agreeDAO=new AgreeDAO();
		
		int result=agreeDAO.agree(userID, cmpID, isStudent, getClientIP(request));
		if(result==1){
			result=cmpDAO.agree(cmpID,isStudent);
			if(result==1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('해당 민원의 동의가 완료되었습니다.')");
				script.println("history.back()");
				script.println("</script>");	
				script.close();
				return;
			}else{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('데이터베이스 오류가 발생했습니다.')");
				script.println("history.back()");
				script.println("</script>");
				script.close();
				return;
			}
		}
		else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 동의한 게시글입니다.')");
			script.println("history.back()");
			script.println("</script>");
			script.close();
		}
%>
	<script src="js/bootstrap.js"></script>
</body>

</html>