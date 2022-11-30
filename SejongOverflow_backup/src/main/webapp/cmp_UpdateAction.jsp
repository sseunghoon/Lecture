<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="complaints.ComplaintsDAO"%>
<%@ page import="complaints.ComplaintsDTO"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");

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
	int cmpID = 0;
	if (request.getParameter("cmpID") != null) {
		cmpID = Integer.parseInt(request.getParameter("cmpID"));
	}
	if (cmpID == 0) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글입니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	}
	int isStudent=-1;
	if(request.getParameter("isStudent")!=null){
		isStudent=Integer.parseInt(request.getParameter("isStudent"));
	}
	ComplaintsDTO cmp = new ComplaintsDAO().getCmp(cmpID,isStudent);
	if (!userID.equals(cmp.getUserID())) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('권한이 없습니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;		
	} else {
		if (request.getParameter("cmpTitle") == null || request.getParameter("cmpContent") == null
		|| request.getParameter("cmpTitle").equals("")
		|| request.getParameter("cmpContent").equals("")) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입력이 안 된 사항이 있습니다.')");
	script.println("history.back()");
	script.println("</script>");
	script.close();
	return;
		} else {
	ComplaintsDAO cmpDAO = new ComplaintsDAO();
	int result = cmpDAO.update(cmpID, request.getParameter("cmpTitle"),
			request.getParameter("cmpContent"),isStudent);
	if (result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('글 수정에 실패하였습니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("history.go(-2)");
		script.println("</script>");
		script.close();
	}
		}
	}
%>
