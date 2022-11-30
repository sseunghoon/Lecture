<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>세종대학교 소프트웨어융합대학 :: 과별 게시판</title>
    <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/departments.css">
  </head>
  <body>
  
  <%
  	String userID =null;
  	if(session.getAttribute("userID")!=null){
  		userID=(String)session.getAttribute("userID");
  	}
  %>  
       <header>
      <nav id='first_area'>
        <a href='index.jsp'><img src="imgs/software_convergence_logo.PNG" id='logo' alt="소융대 로고"></a>
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

            <li>취업&amp;졸업 <!-- 메뉴바 네번째 - 취업&졸업 카테고리 -->
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
        
        <h1 id='language'>한국어 / EN </h1> <!--영어, 한글 버전 바꾸는 버튼-->
        
        <%
        	if(userID==null){
        %>
        <h2 id='login'><a href="userLogin.jsp" style="text-decoration:none; color:#000000">LOGIN</a></h2>
        <%
        	}else{
        %>
      	<h2 id='login'><a href="userLogoutAction.jsp" style="text-decoration:none; color:#000000">LOGOUT</a></h2>
        <%
        	}
        %>
        
      </nav>
    </header>
    
    <section id='departments_board'>
      <header>
        <h2>과별 게시판</h2>
      </header>
    </section>
    
    </div>
    
    <section id='departments_area'>
    	 <div id='computer_science'><!--컴퓨터공학과 게시판-->
    		<h2><a href='post.jsp?boardID=4' id='cs_content'>컴퓨터공학과</a></h2>
    		<a href='post.jsp?boardID=4'><img src='imgs/computer_science_logo.jpg' id='cs_logo'></a>
    	</div>
    
    	<div id='information_security'><!--정보보호학과 게시판-->
    		<h2><a href='post.jsp?boardID=6' id='is_content'>정보보호학과</a></h2>
    		<a href='post.jsp?boardID=6'><img src='imgs/information_security_logo.jpg' id='is_logo'></a>
    	</div>
    	<br/>
    
    	<div id='software'><!--소프트웨어학과 게시판-->
    		<h2><a href='post.jsp?boardID=8' id='soft_content'>소프트웨어학과</a></h2>
    		<a href='post.jsp?boardID=8'><img src='imgs/software_logo.jpg' id='soft_logo'></a>
    	</div>
    
    	<div id='data_science'><!--데이터사이언스학과 게시판-->
    		<h2><a href='post.jsp?boardID=10'  id='ds_content'>데이터사이언스학과</a></h2>
    		<a href='post.jsp?boardID=10'><img src='imgs/data_science_logo.jpg' id='ds_logo'></a>
    	</div>
    
    	<div id='intelligent_mechanics_engineering'><!--지능기전공학부 게시판-->
    		<h2><a href='post.jsp?boardID=12'  id='ime_content'>지능기전공학부</a></h2>
    		<a href='post.jsp?boardID=12'><img src='imgs/mechanical_engineering_logo.jpg' id='ime_logo'></a>
    	</div>
    	<br/>
    
   		<div id='design_innovation'><!--디자인이노베이션학과 게시판-->
    		<h2><a href='post.jsp?boardID=14' id='di_content'>디자인이노베이션학과</a></h2>
    		<a href='post.jsp?boardID=14'><img src='imgs/design_innovation_logo.jpg' id='di_logo'></a>
    	</div>
    
    	<div id='cartoon_animation'><!--만화애니메이션학과 게시판-->
    		<h2><a href='post.jsp?boardID=16' id='ca_content'>만화애니메이션텍학과</a></h2>
    		<a href='post.jsp?boardID=16'><img src='imgs/cartoon_animation_logo.jpg' id='ca_logo'></a>
    	</div>
    </section>
    
    <footer>
   		<p id='footer_content'> 010-0000-0000 | sejongsc3@gmail.com | 학생회관 409호 <br>
   		COPYRIGHT &copy 2019 세종대학교 소프트웨어융합대학 데단한 사람들 All rights reserved.</p>
    </footer>
    
    <script src="js/bootstrap.js"></script>
  </body>
</html>
