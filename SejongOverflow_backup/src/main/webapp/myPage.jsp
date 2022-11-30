<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="complaints.ComplaintsDAO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="user.UserDTO" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>세종대학교 소프트웨어융합대학 :: 마이 페이지 :: 프로필 수정</title>
    <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/PSB.css">
    <link rel="stylesheet" href="css/light-bootstrap-dashboard.css">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">

    <meta name="viewport" content="device-width, initial-scale=1">
    <style type="text/css">
    	.btn-file{
    		position:relative;
    		overflow:hidden;
    	}
    	.btn-file input[type=file]{
    		position:absolute;
    		top: 0;
    		right:0;
    		min-width:100%;
    		min-height:100%;
    		font-size:100px;
    		text-align:right;
    		filter:alpha(opacity=0);
    		opacity:0;
    		outline:none;
    		background:white;
    		cursor:inherit;
    		display:block;
    	}
    	.file{
    		visibility:hidden;
    		position:absolute;
    	}
    </style>
  </head>
  <body>
  
  <%
		String userID=null;
		if(session.getAttribute("userID")!=null){
			userID=(String)session.getAttribute("userID");
		}
		if(userID==null){
			PrintWriter script =response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요.')");
			script.println("history.back()");
			script.println("</script>"); 
			script.close();
		}
		UserDAO userDAO = new UserDAO();
		UserDTO user = userDAO.getUser(userID);
	 %>
  
    <header>
      <nav id='first_area'>
        <a href='index.jsp'><img src="imgs/software_convergence_logo.PNG" id='logo' alt="소융대 로고"></a> <!-- 소융대 로고 -->
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
        
      	<h2 id='login'><a href="userLogoutAction.jsp" style="text-decoration:none; color:#000000">LOGOUT</a></h2>

      </nav>
    </header>
    
    <div class="content">
                <div class="container-fluid">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">회원정보 관리</h4>
                                </div>
                                <div class="card-body">
                                    <form method="post" action="./userProfile" enctype="multipart/form-data">
                                    	<input type="hidden" class="form-control" name="userID" value="<%=userID%>">
                                        <div class="row">
                                            <div class="col-md-12 px-1">
                                                <div class="form-group">
                                                    <label>User Name</label>
                                                    <input type="text" class="form-control" name="userName" placeholder="UserName" value="<%=user.getUserName()%>">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12 pl-1">
                                                <div class="form-group">
                                                    <label for="exampleInputEmail1">Email address</label>
                                                    <input type="email" class="form-control" name="userEmail" placeholder="@sju.ac.kr" value="<%=user.getUserEmail()%>">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>About Me</label>
                                                    <textarea rows="4" cols="80" class="form-control" name="aboutMe" placeholder="Here can be your description"><%=user.getAboutMe()%></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>Profile Image</label>
                                                    <input type="file" name="userProfile" class="file">
                                                    <input type="text" style="float:left;" class="form-control input-sm"  disabled placeholder="Image Upload">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- <input type="file" name="userProfile" class="file"> -->
               							<div class="input-group col-xs-12">
               								<label for="profileDisclosure">프로필 공개여부</label>
               								<button type="submit" class="btn btn-info btn-fill pull-right" style="margin-left:2%;">프로필 업데이트</button>
											<input type="checkbox" checked data-toggle="toggle">
											<div class="col-md-4 pull-right">
												<!-- <input type="text" style="float:left;" class="form-control input-sm"  disabled placeholder="이미지를 업로드하세요."> -->
												<!-- <input type="button" style="float:right;" class="browse btn btn-primary pull-right" value="업로드">  -->
												<span class="input-group-btn"><button class="browse btn btn-primary pull-right"  type="button"><i class="fa fa-search"></i>프로필 사진 업데이트</button></span>
											</div>
               							</div>

							<div class="clearfix"></div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card card-user">
                                <div class="card-body">
                                    <div class="author">
                                        <br><br><br><br><br><br>
                                        <img class="avatar border-gray" src="<%=userDAO.getProfile(userID) %>" alt="프로필 이미지">
                                        
                                        <p class="description">
                                            <%=user.getUserName() %>
                                        </p>
                                    </div>
                                    <p class="description text-center">
                                        <%=user.getAboutMe()%>
                                    </p>
                                    
                                </div>
                                <br>
                            </div>
                            <%
                            	if(user.getUserEmailChecked()==0){
                            %>
                            <button type="button" onclick="location.href='emailSendAction.jsp'" class="btn btn-danger btn-fill pull-left">이메일 인증하기</button>
                            <%
                            	}
                            %>
                        </div>
                    </div>
                </div>
    
      
      <footer>
   		<p id='footer_content'> 010-0000-0000 | sejongsc3@gmail.com | 학생회관 409호 <br>
   		COPYRIGHT &copy 2019 세종대학교 소프트웨어융합대학 데단한 사람들 All rights reserved.</p>
    </footer>
    
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script type="text/javascript">
    	$(document).on('click','.browse',function(){
    		var file = $(this).parent().parent().parent().parent().find('.file');
    		file.trigger('click');
    	});
    	$(document).on('change','.file',function(){
    		$(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i,''));
    	});
    </script>
  </body>
</html>
