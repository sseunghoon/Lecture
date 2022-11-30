<%@ page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.mail.Transport"%>
<%@ page import="javax.mail.Message"%>
<%@ page import="javax.mail.Address"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="javax.mail.Session"%>
<%@ page import="javax.mail.Authenticator"%>
<%@ page import="java.util.Properties"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="util.Gmail"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="complaints.ComplaintsDAO" %>
<%@ page import="post.PostDAO" %>
<%@ page import="report.ReportDAO" %>
<%@ page import="report.ReportDTO" %>
<%
	UserDAO userDAO =new UserDAO();
	String userID =null;
	if(session.getAttribute("userID")!=null){
		userID=(String) session.getAttribute("userID");
	}
	if(userID==null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.')");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	
	int boardID =0;
	if (request.getParameter("boardID") != null) {
		boardID = Integer.parseInt(request.getParameter("boardID"));
	}
	int postID =0;
	if (request.getParameter("postID") != null) {
		postID = Integer.parseInt(request.getParameter("postID"));
	}
	int cmpID =0;
	if (request.getParameter("cmpID") != null) {
		cmpID = Integer.parseInt(request.getParameter("cmpID"));
	}
	int isStudent =0;
	if (request.getParameter("isStudent") != null) {
		isStudent = Integer.parseInt(request.getParameter("isStudent"));
	}
	String reportTitle =null;
	if (request.getParameter("reportTitle") != null) {
		reportTitle = new String(request.getParameter("reportTitle").getBytes("ISO-8859-1"), "UTF-8");
	}
	String reportContent =null;
	if (request.getParameter("reportContent") != null) {
		reportContent = new String(request.getParameter("reportContent").getBytes("ISO-8859-1"), "UTF-8");
	}
	
	String reportURL=null;
	if(boardID==0){
		reportURL="cmp_View.jsp?isStudent="+isStudent+"&cmpID="+cmpID;
	}else{
		reportURL="post_View.jsp?boardID="+boardID+"&postID="+postID;
	}
	
	ReportDAO reportDAO = new ReportDAO();
	ComplaintsDAO cmpDAO = new ComplaintsDAO();
	PostDAO postDAO = new PostDAO();
	
	int result= reportDAO.write(new ReportDTO(0,userID,reportTitle,reportContent,"",reportURL,cmpID,postID,isStudent,boardID,0));
	
	if(result!=1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('오류가 발생했습니다.')");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	String host="http://localhost:8080/SnS/";
	String from="sjswsns@gmail.com";
	String to=userDAO.getUserEmail(userID);//민원담당자로 변경
	to=to.split("@")[0]+"@sju.ac.kr";
	String subject ="[세종소융]게시글 신고접수";
	String content =null;
	if(boardID==0){
		content ="제목: "+reportTitle+"<br>접수날짜: "+cmpDAO.getDate()+"<br>"+reportContent+
				"\n<a href='" + host + "cmp_View.jsp?isStudent="+isStudent+"&cmpID="+cmpID+
						"'><br>민원 바로가기</a>";
	}else{
		content ="제목: "+reportTitle+"<br>접수날짜: "+postDAO.getDate()+"<br>"+reportContent+
				"\n<a href='" + host + "post_View.jsp?isStudent="+isStudent+"&postID="+postID+
						"'><br>게시글 바로가기</a>";
	}
	
	Properties p = new Properties();
	p.put("mail.smtp.user",from);
	p.put("mail.smtp.host","smtp.googlemail.com");
	p.put("mail.smtp.port","465");
	p.put("mail.smtp.starttls.enable","true");
	p.put("mail.smtp.auth","true");
	p.put("mail.smtp.debug","true");
	p.put("mail.smtp.socketFactory.port","465");
	p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	p.put("mail.smtp.socketFactory.fallback","false");
	
	try{
		Authenticator auth =new Gmail();
		Session ses=Session.getInstance(p,auth);
		ses.setDebug(true);
		MimeMessage msg=new MimeMessage(ses);
		msg.setSubject(subject);
		Address fromAddr=new InternetAddress(from);
		msg.setFrom(fromAddr);
		Address toAddr = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO,toAddr);
		msg.setContent(content,"text/html;charset=UTF8");
		Transport.send(msg);
	}catch(Exception e){
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
	script.println("alert('신고가 접수되었습니다.\\r\\n신고해주셔서 감사합니다.')");
	script.println("history.back();");
	script.println("</script>");
	script.close();
%>