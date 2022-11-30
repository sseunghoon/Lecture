<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	UserDAO userDAO=new UserDAO();
	int userLevel=userDAO.getUserEmailChecked(userID);
	if (userLevel<2) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('권한이 없습니다.')");
		script.println("location.href='admin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	request.setCharacterEncoding("UTF-8");
	String promotionID= null;
	if (request.getParameter("promotionID") != null) {
		promotionID = request.getParameter("promotionID");
	}
	int result=new UserDAO().promotion(promotionID);
	if(result==1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('승격이 완료되었습니다.')");
		script.println("location.href='admin_user.jsp'");
		script.println("</script>");
		script.close();
		return;
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생하였습니다.')");
		script.println("location.href='admin_user.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
%>