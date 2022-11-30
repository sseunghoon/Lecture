<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="complaints.ComplaintsDAO"%>
<%@ page import="complaints.ComplaintsDTO"%>
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
      script.println("location.href='userLogin.jsp';");
      script.println("</script>");
      script.close();
      return;
   }
   
   String cmpTitle=null;
   String cmpContent=null;
   String cmpDivide=null;
   String cmpDate=null;
	
   if(request.getParameter("cmpTitle")!=null){
      cmpTitle=request.getParameter("cmpTitle");
   }
   if(request.getParameter("cmpContent")!=null){
      cmpContent=request.getParameter("cmpContent");
   }
   if(request.getParameter("cmpDivide")!=null){
      cmpDivide=request.getParameter("cmpDivide");
   }
   if(request.getParameter("cmpDate")!=null){
      cmpDate=request.getParameter("cmpDate");
   }
   if(cmpTitle==null ||cmpContent==null||cmpTitle.equals("")||cmpContent.equals("")){
      PrintWriter script =response.getWriter();
      script.println("<script>");
      script.println("alert('입력이 안 된 사항이 있습니다..');");
      script.println("history.back();");
      script.println("</script>");
      script.close();
      return;
   }
	int cmpID = 0;
	if (request.getParameter("cmpID") != null) {
		cmpID = Integer.parseInt(request.getParameter("cmpID"));
	}
	int isStudent = -1;
	if (request.getParameter("isStudent") != null) {
		isStudent = Integer.parseInt(request.getParameter("isStudent"));
	}
	ComplaintsDAO complaintsDAO = new ComplaintsDAO();
	UserDAO userDAO = new UserDAO();
	ComplaintsDTO parent = complaintsDAO.getCmp(cmpID, isStudent);
	int result = complaintsDAO.reply(
			new ComplaintsDTO(0,userID,"",cmpTitle,cmpContent,"","","",0,0,0,0,0,1,1),parent, isStudent);
	complaintsDAO.replyUpdate(parent, isStudent);
	if (result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('답변 등록 실패했습니다.');");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	} else {//답변등록 성공 후 이메일 발송 부분
		String host = "http://localhost:8080/SnS/";
		String from = "sjswsns@gmail.com";
		String to = userDAO.getUserEmail(userID);
		String subject = "[세종소융]민원의 답변이 등록되었습니다." + complaintsDAO.getDate();
		String content = "제목: " + cmpTitle + "<br>답변날짜: " + complaintsDAO.getDate() + "<br>" + cmpContent
				+ "\n<a href='" + host + "cmp_View.jsp?isStudent="+isStudent+"&cmpID=" + parent.getCmpID()
				+ "'><br>민원 바로가기</a>";

		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF8");
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("history.go(-2)");
		script.println("</script>");
		script.close();
		return;
	}
%>