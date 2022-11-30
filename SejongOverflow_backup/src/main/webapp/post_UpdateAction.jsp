<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="post.PostDAO"%>
<%@ page import="post.PostDTO"%>
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
	int boardID =0;
	if (request.getParameter("boardID") != null) {
		boardID = Integer.parseInt(request.getParameter("boardID"));
	}
	int postID = 0;
	if (request.getParameter("postID") != null) {
		postID = Integer.parseInt(request.getParameter("postID"));
	}
	if (postID == 0) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글입니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	}
	PostDTO post = new PostDAO().getPost(postID,boardID);
	if (!userID.equals(post.getUserID())) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('권한이 없습니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;		
	} else {
		if (request.getParameter("postTitle") == null || request.getParameter("cmpContent") == null
		|| request.getParameter("postTitle").equals("")
		|| request.getParameter("postContent").equals("")) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입력이 안 된 사항이 있습니다.')");
	script.println("history.back()");
	script.println("</script>");
	script.close();
	return;
		} else {
	PostDAO postDAO = new PostDAO();
	int result = postDAO.update(postID, request.getParameter("postTitle"),
			request.getParameter("postContent"),boardID);
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
		script.println("history.back()");
		script.println("</script>");
		script.close();
	}
		}
	}
%>
