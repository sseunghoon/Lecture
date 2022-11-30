<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="board.BoardDAO"%>
<%@ page import="board.BoardDTO"%>
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
	int boardID = 0;
	if (request.getParameter("boardID") != null) {
		boardID = Integer.parseInt(request.getParameter("boardID"));
	}
	BoardDAO boardDAO = new BoardDAO();
	int result=boardDAO.ban(boardID);
	if(result==1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('게시판이 비활성화되었습니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 비활성화가 되어있습니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	}
%>