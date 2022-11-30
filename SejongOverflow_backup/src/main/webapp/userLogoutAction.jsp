<%@ page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	session.invalidate();
%>
<script>
	history.back();
	loaction.reload(true);
</script>