<%@ page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
	String code= null;
	if(request.getParameter("code")!=null){
		code=request.getParameter("code");
	}
	UserDAO userDAO = new UserDAO();
	
	boolean result=userDAO.setUserEmailChecked(code);
	if(result){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('인증에 성공했습니다.')");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 코드입니다.')");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
%>