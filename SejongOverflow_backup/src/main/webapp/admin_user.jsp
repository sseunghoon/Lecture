<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="user.UserDAO" %>
<%@ page import="user.UserDTO" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1">
<title>세종소융 관리자페이지</title>
<link rel="shortcut icon" type="image/x-icon" href="imgs/favicon.ico">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->
<link rel="stylesheet" href="css/bootstrap1.css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		UserDAO userDAO=new UserDAO();
		int userLevel=userDAO.getUserEmailChecked(userID);
		if (userLevel<2) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("location.href='admin.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
	%>
	
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
			$('messageModal').modal("show");
		</script>
	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		}
	%>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<a class="navbar-brand" href="admin.jsp">세종소융 관리자</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>
  		<div class="collapse navbar-collapse" id="navbarNavDropdown">
    		<ul class="navbar-nav">
      			<li class="nav-item active">
        			<a class="nav-link" href="admin_user.jsp">User <span class="sr-only">(current)</span></a>
      			</li>
      			<li class="nav-item">
       	 		<a class="nav-link" href="admin_board.jsp">Board</a>
     	 		</li>
     	 		<li class="nav-item">
       	 		<a class="nav-link" href="admin_customizing.jsp">Customizing</a>
     	 		</li>
      			<li class="nav-item dropdown" style="float:right !important;">
        			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         	 		SJSW Link
        			</a>
       				 <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
         				 <a class="dropdown-item" href="index.jsp">Main</a>
         				 <a class="dropdown-item" href="cmp_to_student_council.jsp">Complaints</a>
         				 <a class="dropdown-item" href="student_council_photo.jsp">Gallery</a>
       				 </div>
      			</li>
				<li class="nav-item"><a class="nav-link" href="userLogoutAction.jsp">Logout</a></li>
			</ul>
		</div>
	</nav>

	<div id="modal-login" class="modal fade">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">관리자 로그인</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
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
							<label for="keep_signed" onclick="jQuery('#modal-login input[name=\'keep_signed\']').click();">
							<input type="checkbox" name="keep_signed" value="Y"
								onclick="if(this.checked) return confirm('브라우저를 닫더라도 로그인이 계속 유지될 수 있습니다.\n\n로그인 유지 기능을 사용할 경우 다음 접속부터는 로그인할 필요가 없습니다.\n\n단, 게임방, 학교 등 공공장소에서 이용 시 개인정보가 유출될 수 있으니 꼭 로그아웃을 해주세요.');">
							로그인 유지</label>
						</div>
						<button type="submit" id="btnLogin" class="btn btn-block">
							<i class="fa fa-sign-in" aria-hidden="true"></i> 로그인
						</button>
						<br>
					</form>
				</div>
			</div>
		</div>
	</div>

 	<div class="col-md-12">
    	<div class="card card-plain table-plain-bg">
			<div class="card-header ">
				<h4 class="card-title">회원 관리</h4>
				<p class="card-category">메세지를 보내거나 게시글을 삭제, 회원을 강퇴시킬 수 있습니다.</p>
			</div>
			<div class="card-body table-full-width table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
						<th>Profile</th>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Message</th>
						<th>Authority</th>
						<th>Kick</th>
						</tr>
					</thead>
					<tbody>
						<%
							ArrayList<UserDTO> list = userDAO.getList();
							for (int i = 0; i < list.size(); i++) {
						%>
						<tr>
							<td><img class="media-object img-circle" style="width:30px;height:30px;" src="<%=userDAO.getProfile(list.get(i).getUserID())%>"></td>
							<td><%=list.get(i).getUserID()%></td>
							<td><%=list.get(i).getUserName()%></td>
							<td><%=list.get(i).getUserEmail()%></td>
							<td><a href="#" class="btn btn-primary">쪽지</a></td>
							<td><a onclick="return confirm('관리자로 승격시키겠습니까?')" href="userPromotion.jsp?promotionID=<%=list.get(i).getUserID() %>" class="btn btn-info">승격</a></td>
							<td><a onclick="return confirm('정말로 강퇴하시겠습니까?')" href="userKick.jsp?kickID=<%=list.get(i).getUserID() %>" class="btn btn-danger">강퇴</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

 <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>

</html>
