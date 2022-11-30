<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="post.PostDAO" %>
<%@ page import="post.PostDTO" %>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="javax.mail.Transport"%>
<%@ page import="javax.mail.Message"%>
<%@ page import="javax.mail.Address"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="javax.mail.Session"%>
<%@ page import="javax.mail.Authenticator"%>
<%@ page import="java.util.Properties"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.Gmail"%>
<%@ page import="util.SHA256"%>
<%
   request.setCharacterEncoding("UTF-8");
   String userID = null;
 	if(session.getAttribute("userID")!=null){
 		userID=(String)session.getAttribute("userID");
 	}
   if(userID==null){
      PrintWriter script =response.getWriter();
      script.println("<script>");
      script.println("alert('로그인을 해주세요.');");
      script.println("history.back()");
      script.println("</script>");
      script.close();
      return;
   }
   
   String postTitle=null;
   String postContent=null;
   String postDivide="";
   String postDate=null;
	
   if(request.getParameter("postTitle")!=null){
	   postTitle=request.getParameter("postTitle");
   }
   if(request.getParameter("postContent")!=null){
	   postContent=request.getParameter("postContent");
   }
   if(request.getParameter("postDate")!=null){
	   postDate=request.getParameter("postDate");
   }
   if(postTitle==null ||postContent==null||postTitle.equals("")||postContent.equals("")){
      PrintWriter script =response.getWriter();
      script.println("<script>");
      script.println("alert('입력이 안 된 사항이 있습니다..');");
      script.println("history.back();");
      script.println("</script>");
      script.close();
      return;
   }
	int postID=0;
	if(request.getParameter("postID")!=null){
		postID =Integer.parseInt(request.getParameter("postID"));
	}
	int boardID =0;
	if (request.getParameter("boardID") != null) {
		boardID = Integer.parseInt(request.getParameter("boardID"));
	}
   
   PostDAO postDAO =new PostDAO();
   PostDTO parent = postDAO.getPost(postID, boardID);
   int result=postDAO.reply(new PostDTO(0,0,userID,"", postTitle,postContent,postDivide,"","",0,0,0,0,0,0),parent,boardID);
   postDAO.replyUpdate(parent,boardID);
   if(result==-1){
      PrintWriter script = response.getWriter();
      script.println("<script>");
      script.println("alert('답변 등록 실패했습니다.');");
      script.println("history.back();");
      script.println("</script>");
      script.close();
      return;
   }
      PrintWriter script =response.getWriter();
      script.println("<script>");
      script.println("history.go(-2)");
      script.println("</script>");
      script.close();
      return;
%>