<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="post.PostDAO" %>
<%@ page import="post.PostDTO" %>
<%@ page import="board.BoardDAO" %>
<%@ page import="board.BoardDTO" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>세종대학교 소프트웨어융합대학 :: 취업&졸업 :: 취창업 후기</title>
    <link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/PSB.css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
  </head>
  <body>


	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		int pageNumber = 1;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		String searchType="최신순";
		String search=null;
		if(request.getParameter("searchType")!=null){
			searchType=request.getParameter("searchType");
		}
		if(request.getParameter("search")!=null){
			search=request.getParameter("search");
		}
		int boardID =20;
		if (request.getParameter("boardID") != null) {
			boardID = Integer.parseInt(request.getParameter("boardID"));
		}
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO board = boardDAO.getBoard(boardID);
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
        <%
				if (userID == null) {
			%>
			<h2 id='login'>
				<a data-toggle="modal" href="#modal-login" style="text-decoration: none; color: #000000">LOGIN</a>
			</h2>
			<%
				} else {
			%>
			<h2 id='login'>
				<a href="userLogoutAction.jsp"
					style="text-decoration: none; color: #000000">LOGOUT</a>
			</h2>
			<%
				}
			%>
      </nav>
    </header>
	
	<%
		String messageContent = null;
		if(session.getAttribute("messageContent")!=null){
			messageContent=(String)session.getAttribute("messageContent");
		}
		String messageType = null;
		if(session.getAttribute("messageType")!=null){
			messageType=(String)session.getAttribute("messageType");
		}
		if(messageContent != null){
	%>
		<div id="messageModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div class="modal-content" <%if(messageType.equals("오류 메시지")) out.println("panel-warning");else out.println("panel-success"); %>>
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span>
								<span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								<%=messageType %>
							</h4>
						</div>
						<div class="modal-body">
							<%=messageContent %>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#messageModal').modal("show");
		</script>
	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		}
	%>
	
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
	
    <div id="container">
    <nav>
      <nav>
        <h2>
          <span></span>
            취업 & 졸업
        </h2>
        <ul class="lnb_deps2">
             <li>
               <a href="employ_reviews.jsp" class="jwxe_22350 active">취창업 후기</a>
             </li>
             <li>
               <a href="graduate_interviews.jsp" class="jwxe_22351 ">졸업생 인터뷰</a>
            </li>
            <li>
              <a href="graduate_qnas.jsp" class="jwxe_22351 ">졸업생 QnA</a>
            </li>
        </ul>
      </nav>
    </nav>
    
    <section class="content">
			<header>
				<h1><%=boardDAO.getBoard(boardID).getBoardName() %></h1>
			</header>
			<hr>
			<div class="find_content">
				<form method="get" action="school_contests.jsp" class="form-inline mt-3">
					<select name="searchType" class="form-control mx-1 mt-2">
						<option value="최신순" <% if(searchType.equals("최신순")) out.println("selected"); %>>최신순</option>
						<option value="추천순" <% if(searchType.equals("추천순")) out.println("selected"); %>>추천순</option>
					</select>
					<input type="text" name="search" class="form-control mx-1 mt-2" placeholder="작성자/제목/내용">
					<button type="submit" class="btn mx-1 mt-2">검색</button>
				</form>
			</div>
			<div class="content_table">
				<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>동의 수</th>
						<th>조회 수</th>
					</tr>
				</thead>
				<tbody>
					<%
            PostDAO postDAO = new PostDAO();
			ArrayList<PostDTO> list = null;
			if(search==null){
				list = postDAO.getList(pageNumber,boardID);	
			}else{
				list=postDAO.getSearch(searchType,search,pageNumber,boardID);
			}
            for(int i=0; i<list.size();i++){
         %>
					<tr>
						<td><%=list.get(i).getPostID() %></td>
						<td><a href="post_View.jsp?boardID=<%=boardID %>&postID=<%=list.get(i).getPostID()%>"
							style="text-decoration: none"><%=list.get(i).getPostTitle().replaceAll(" ", "&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt").replaceAll("\n","<br>") %></a></td>
						<td><%=list.get(i).getUserID() %></td>
						<td><%=list.get(i).getPostDate().substring(0,11)+list.get(i).getPostDate().substring(11,13)+"시"+list.get(i).getPostDate().substring(14,16)+"분" %></td>
						<td><%=list.get(i).getAgreeCount() %></td>
						<td><%=list.get(i).getPostHit() %></td>
					</tr>
					<%
            }
         %>
				</tbody>
			</table>
		</div>
		<div class="write_button">
			<a class="btn btn-default pull-right"
				href="post_Write.jsp?boardID=<%=boardID%>">글쓰기</a>
		</div>
		<br><br>	
		<div>
			<%
				if(search==null){
			%>
			<div class="text-center">
				<ul class="pagination" style="margin: 0 auto;">
					<%
          	int startPage=(pageNumber/10)*10+1;
          	if(pageNumber%10==0) startPage-=10;
          	int targetPage =postDAO.targetPage(pageNumber,boardID);
          	if(startPage!=1){
          %>
					<li><a
						href="<%=board.getBoardURL()%>?pageNumber=<%=startPage-1%>"
						class="btn btn-success">이전</a></li>
					<%
          	}else{
          %>
					<li><a href="#" class="btn" style="color: gray;">이전</a></li>
					<%
          	}for(int i = startPage;i<pageNumber;i++){
        	%>
					<li><a href="<%=board.getBoardURL()%>?pageNumber=<%=i %>"><%=i %></a></li>
					<%      			
          		}
          	%>
					<li><a class="active"
						href="<%=board.getBoardURL()%>?pageNumber=<%=pageNumber %>"><%=pageNumber %></a></li>
					<%
				for(int i = pageNumber+1;i<=targetPage+pageNumber;i++){
					if(i<startPage+10){
			%>
					<li><a href="<%=board.getBoardURL()%>?pageNumber=<%=i %>"><%=i %></a></li>
					<%
					}
				}
				if(targetPage+pageNumber>startPage+9){
			%>
					<li><a
						href="<%=board.getBoardURL()%>?pageNumber=<%=startPage+10 %>">다음</a></li>
					<%
				}else{
			%>
					<li><a href="#" class="btn" style="color: gray;">다음</a></li>
					<%
				}
				}
			%>
				</ul>
			</div>
		</div>
			<br>
			<br>
		</section>
 </div>
    <footer>
   		<p id='footer_content'> 010-0000-0000 | sejongsc3@gmail.com | 학생회관 409호 <br>
   		COPYRIGHT &copy 2019 세종대학교 소프트웨어융합대학 데단한 사람들 All rights reserved.</p>
    </footer>
  </body>
</html>
