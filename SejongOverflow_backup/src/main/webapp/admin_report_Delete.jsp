<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="report.ReportDAO"%>
<%@ page import="report.ReportDTO"%>
<%@ page import="user.UserDAO" %>
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
	int reportID = 0;
	if (request.getParameter("reportID") != null) {
		reportID = Integer.parseInt(request.getParameter("reportID"));
	}
	ReportDAO reportDAO = new ReportDAO();
	int result=reportDAO.delete(reportID);
	if(result==1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('게시판이 삭제되었습니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 삭제가 되었습니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	}
%>