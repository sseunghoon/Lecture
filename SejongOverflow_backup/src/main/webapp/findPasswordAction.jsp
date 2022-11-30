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
<%
	UserDAO userDAO =new UserDAO();
	String userID =null;
	if(request.getParameter("userID")!=null){
		userID=request.getParameter("userID");
	}
	if(userID==null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('아이디을 입력해주세요.')");
		script.println("location.href='findAccount.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	String userEmail =null;
	if(request.getParameter("userEmail")!=null){
		userEmail=request.getParameter("userEmail");
	}
	if(userEmail==null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이메일을 입력해주세요.')");
		script.println("location.href='findAccount.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	String findPwQuestion =null;
	if(request.getParameter("findPwQuestion")!=null){
		findPwQuestion=request.getParameter("findPwQuestion");
	}
	if(findPwQuestion==null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('질문을 선택해주세요.')");
		script.println("location.href='findAccount.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	String findPwAnswer =null;
	if(request.getParameter("findPwAnswer")!=null){
		findPwAnswer=request.getParameter("findPwAnswer");
	}
	if(findPwAnswer==null || findPwAnswer.equals("")){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('답변을 입력해주세요.')");
		script.println("location.href='findAccount.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	String host="http://localhost:8080/SnS/";
	String from="sjswsns@gmail.com";
	String to=userEmail.split("@")[0]+"@sju.ac.kr";
	String temporary=userDAO.getRamdomPassword(6);
	String subject ="[세종소융]임시비밀번호 발급";
	String content ="[세종소융]임시비밀번호 발급<br><br>"+temporary;
	
	userDAO.setUserPassword(temporary, userID);
	
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
%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>세종대학교 소프트웨어융합대학 :: 비밀번호찾기</title>
    <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/PSB.css">
    <link rel="stylesheet" href="login_page.css">
    <link rel="stylesheet" href="bootstrap.css">
  </head>
  <body>
    <header>
      <nav id='first_area'>
        <img src="software_convergence_logo.PNG" id='logo' alt="소융대 로고"> <!-- 소융대 로고 -->
        <div id="menubar">
          <ul> <!-- 사이트 타이틀 하단 메뉴바 -->
            <li>학생회 <!-- 메뉴바 첫번째 - 학생회 카테고리 -->
              <ul id='submenu'>
                <li><a href='student_council_introduce.jsp'>학생회 소개</a></li>
                <li><a href='student_council_photo.jsp'>갤러리</a></li>
                <li><a href='student_council_events.jsp'>행사</a></li>
                <li><a href='student_council_public_money.jsp'>학생회비 내역</a></li>
                <li><a href='departments.jsp'>과별 게시판</a></li>
              </ul>
            </li>

            <li>민원 <!-- 메뉴바 두번째 - 민원 카테고리 -->
              <ul id='submenu'>
                <li><a href='cmp_to_student_council.jsp'>학생회 건의사항</a></li>
                <li><a href='cmp_to_school.jsp'>학교 건의사항</a></li>
                <li><a href='introduce_cmp.jsp'>민원창구 소개</a></li>
              </ul>
            </li>

            <li>예비 소융인 <!-- 메뉴바 세번째 - 예비 소융인 카테고리 -->
              <ul id='submenu'>
                <li><a href='admission_reviews.jsp'>선배들의 입시 후기</a></li>
                <li><a href='admission_qnas.jsp'>QnA</a></li>
              </ul>
            </li>

            <li>취업&졸업 <!-- 메뉴바 네번째 - 취업&졸업 카테고리 -->
              <ul id='submenu'>
                <li><a href='employ_reviews.jsp'>취창업 후기</a><br></li>
                <li><a href='graduate_interviews.jsp'>졸업생 인터뷰</a><br></li>
                <li><a href='graduate_qnas.jsp'>졸업생 QnA</a><br></li>
              </ul>
            </li>
				
            <li>홍보 <!-- 메뉴바 다섯번째 - 홍보 카테고리 -->
              <ul id='submenu'>
                <li><a href='school_contests.jsp'>교내 공모전</a><br></li>
                <li><a href='not_school_contests.jsp'>교외 공모전</a><br></li>
              </ul>
            </li>

            <li>QnA <!-- 메뉴바 여섯번째 - QnA 카테고리 -->
              <ul id='submenu'>
                <li><a href='chatbot.jsp'>Chatbot</a><br></li>
                <li><a href='qna.jsp'>QnA</a><br></li>
              </ul>
            </li>
          </ul>
        </div>
        
        <h2 id='language'>한국어 / EN </h2> <!--영어, 한글 버전 바꾸는 버튼-->
        <h2 id='login'> LOGIN</h2> <!-- 로그인 버튼-->
      </nav>
    </header>
	
	<section class="container mt-3" style="max-width:560px;">
		<div class="alert alert-success mt-4" role="alert">
			임시비밀번호가 메일로 전송되었습니다. 회원가입시 인증했던 이메일을 확인해주세요.
		</div>
	</section>
	
	



    
  </body>
</html>
