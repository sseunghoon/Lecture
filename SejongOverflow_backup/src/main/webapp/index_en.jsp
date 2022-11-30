<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="user.UserDAO" %>   
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Sejong Univ. College of Software Convergence</title>
    <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/mainpage_eng_ver.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/Backstretch.js" type="text/javascript"></script>
    <script src="js/mainpage.js"></script>
  </head>
  <body>
  
  <%
  	String userID =null;
  	if(session.getAttribute("userID")!=null){
  		userID=(String)session.getAttribute("userID");
  	}
  %>  
    <script>
      $.backstretch(["imgs/drop_7.jpg"],
    	  {duration: 20, fade: 900});
      $(window).on("backstretch.before", function (e, instance, index) {
    	  // If we wanted to stop the slideshow after it reached the end
    	  if (index === instance.images.length - 1) {
    	    instance.pause();
    	  };
    	});
    </script>

    <header>
      <nav id='first_area'>
        <a href= 'index_en.jsp'><img src="imgs/software_convergence_logo.PNG" id='logo' alt="소융대 로고"></a> <!-- 소융대 로고 -->
         <div id="menubar">
          <ul> <!-- 사이트 타이틀 하단 메뉴바 -->
            <li>Student Council <!-- 메뉴바 첫번째 - 학생회 카테고리 -->
              <ul id='submenu'>
                <li><a href='student_council_introduce_en.jsp'>Introduce</a></li>
                <li><a href='student_council_photo_en.jsp'>Gallery</a></li>
                <li><a href='student_council_events_en.jsp'>Events</a></li>
                <li><a href='student_council_public_money_en.jsp'>Public Money</a></li>
                <li><a href='departments_en.jsp'>Departments</a></li>
              </ul>
            </li>

            <li>Claims <!-- 메뉴바 두번째 - 민원 카테고리 -->
              <ul id='submenu'>
                <li><a href='cmp_to_student_council_en.jsp'>To Student Council</a></li>
              </ul>
            </li>

            <li>Pre-students<!-- 메뉴바 세번째 - 예비 소융인 카테고리 -->
              <ul id='submenu'>
                <li><a href='admission_reviews_en.jsp'>Reviews</a></li>
                <li><a href='admission_qnas_en.jsp'>QnA</a></li>
              </ul>
            </li>

            <li>Employments <!-- 메뉴바 네번째 - 취업&졸업 카테고리 -->
              <ul id='submenu'>
                <li><a href='employ_reviews_en.jsp'>Reviews</a><br></li>
                <li><a href='graduate_interviews_en.jsp'>Interviews</a><br></li>
                <li><a href='graduate_qnas_en.jsp'>Graduation QnAs</a><br></li>
              </ul>
            </li>

            <li>Promotions <!-- 메뉴바 다섯번째 - 홍보 카테고리 -->
              <ul id='submenu'>
                <li><a href='school_contests_en.jsp'>School</a><br></li>
                <li><a href='not_school_contests_en.jsp'>Not School</a><br></li>
              </ul>
            </li>

            <li>QnA <!-- 메뉴바 여섯번째 - QnA 카테고리 -->
              <ul id='submenu'>
                <li><a href='chatbot_en.jsp'>Chatbot</a><br></li>
                <li><a href='qna_en.jsp'>QnA</a><br></li>
              </ul>
            </li>
          </ul>
        </div>
        
        <h2 id='language'>
       		<a href='index.jsp' style="text-decoration:none; color:black">KR</a> / <a href="index_en.jsp" style="text-decoration:none; color:black">EN</a>
 		</h2>
        
        <%
        	if(userID==null){
        %>
        <h2 id='login'>
				<a data-toggle="modal" href="#modal-login" style="text-decoration: none; color: black">LOGIN</a>
			</h2>
        <%
        	}else{
        %>
      	<h2 id='login'><a href="userLogoutAction.jsp" style="text-decoration:none; color:black">LOGOUT</a></h2>
        <%
        	}
        %>
      </nav>
      
      </header>

	<div id="modal-login" class="modal fade">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">로그인</h4>
					</div>
					<script>
						$(document).ready(function(){
							$('btnLogin').click(function(){
								var action = $('#frmLogin').attr("action");
								var form_data={
										user_id:$('#userID').val(),
										user_pw:$('#userPassword').val()
								};
								$.ajax({
									type:"POST",
									url:action,
									data:form_data,
									success:function(response){
										if(response.trim()=="success"){
										sessionStorage.setItem("user_id",form_data.user_id);
										$('#msg').html("<p style='color:green;font-weight:bold'>로그인 성공!</p>");
									}else{
										$('#msg').html("<p style='color:red'>아이디 또는 비밀번호가 잘못되었습니다.</p>");
									}
								},
								error:function(){
									$('#msg'),html("<h2>Error</h2>");
								}
								});
							});
						});
					</script>
					<div class="modal-body">
						<form action="userLoginAction.jsp" id="frmLogin" method="post">
							<div class="form-group">
								<input type="text" name="userID" id="uid" value=""
									placeholder="아이디" class="form-control" required="">
							</div>
							<div class="form-group">
								<input type="password" name="userPassword" id="upw" value=""
									placeholder="비밀번호" class="form-control" required="">
							</div>
							<div class="checkbox">
								<label for="keep_signed"
									onclick="jQuery('#modal-login input[name=\'keep_signed\']').click();"><input
									type="checkbox" name="keep_signed" value="Y"
									onclick="if(this.checked) return confirm('브라우저를 닫더라도 로그인이 계속 유지될 수 있습니다.\n\n로그인 유지 기능을 사용할 경우 다음 접속부터는 로그인할 필요가 없습니다.\n\n단, 게임방, 학교 등 공공장소에서 이용 시 개인정보가 유출될 수 있으니 꼭 로그아웃을 해주세요.');">
									로그인 유지</label>
							</div>
							<button type="submit" id="btnLogin" class="btn btn-block">
								<i class="fa fa-sign-in" aria-hidden="true"></i> 로그인
							</button>
							<br>
						</form>
					</div>
					<div class="modal-footer">
						<div class="btn-group btn-group-justified">
							<a
								href="userJoin.jsp"
								class="btn btn-default btn-sm"><i class="fa fa-user-plus"
								aria-hidden="true"></i> 회원가입</a> <a
								href="findAccount.jsp"
								class="btn btn-default btn-sm"><i
								class="fa fa-question-circle" aria-hidden="true"></i> ID/PW 찾기</a>

						</div>
					</div>
				</div>
			</div>
		</div>

    <nav id='title_animation'>
      <h1 id='title'>College of<br>Software &amp; Convergence Technology</h1>
      <h3 id='subtitle'>Cultivate Leaders of the Software Society</h3>
    </nav>

    <div id="notice">
      <h2><a href='#' id='Notice'>Notice</a></h2>
      <br/> 
      <a href='#' id='Notice'>Recent notices</a> <!-- 링크만 남기고 글 지울 것 -->
    </div>

    <div id="promotion">
      <h2><a href='#' id='Promotion'>Promotions</a></h2>
      <br/>
      <a href='#' id='Promotion'>Recent promotions</a> <!-- 링크만 남기고 글 지울 것 -->
    </div>
    
    <div id="claim">
      <h2><a href='#' id='Claims'>Claims</a></h2>
      <br/>
      <a href='#' id='Claims'>Recent claims</a> <!-- 링크만 남기고 글 지울 것 -->
    </div>

    <div id="departments">
      <h2><a href='departments_en.jsp' id='Departments'>Departments</a></h2>
      <a href='computer_science_en.jsp' id='Departments'>Computer Engineering</a><br/>
      <a href='software_en.jsp' id='Departments'>Software</a><br/>
      <a href='data_science_en.jsp' id='Departments'>Data Science</a><br/>
      <a href='information_security_en.jsp' id='Departments'>Information Security</a><br/>
      <a href='intelligent_mechanics_engineering_en.jsp' id='Departments'>Intelligent Mechanics Engineering</a><br/>
      <a href='cartoon_animation_en.jsp' id='Departments'>Cartoon &amp; Animation</a><br/>
      <a href='design_innovation_en.jsp' id='Departments'>Design Innovation</a><br/>
    </div>

    <table>
      <tr>
        <td>Quick Menu</td>
        <td><a href='http://www.sejong.ac.kr/' id='quick_menu'>Sejong</br>University</a></td>
        <td><a href='http://www.sejongstudent.com/xe/' id='quick_menu'>Sejong</br>Student Council</a></td>
        <td><a href='https://ko-kr.facebook.com/sejong1sc/' id='quick_menu'>Software</br>Facebook</a></td>
        <td><a href='https://www.facebook.com/sejongstudent/' id='quick_menu'>Sejong</br>Student Council Facebook</a></td>
      </tr>
    </table>
    
    <footer>
   		<p id='footer_content'> 010-0000-0000 | sejongsc3@gmail.com | 학생회관 409호 <br>
   		COPYRIGHT &copy 2019 세종대학교 소프트웨어융합대학 데단한 사람들 All rights reserved.</p>
    </footer>

  </body>
</html>