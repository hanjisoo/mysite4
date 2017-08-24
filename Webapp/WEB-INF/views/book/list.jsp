
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("n","\n"); %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<title>방명록</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

	<div id="wrapper">
			<div id="content">
				<div id="user">
					<form action="${pageContext.request.contextPath }/book/add" method="post">
						<table border=1 width=500>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name"></td>
								<td>비밀번호</td>
								<td><input type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
							</tr>
						
							
						</table>
					</form>
					<br />

					<c:forEach items="${list}" var="vo">
						<table width="510" border="1">
						<tr>
							<td>${vo.no}</td>
							<td>${vo.name}</td>
							<td>${vo.regDate}</td>
							<td><a href="deleteform/${vo.no}">삭제</a></td>
						</tr>
						
						<tr>
							<td colspan=4>안녕하세요~<br />${fn:replace(vo.content,n,'<br/>')}</td>
														
						</tr>
					</table>
					<br />
					</c:forEach>
					

				</div>	<!-- /user -->
			</div>	<!-- /content -->
		</div>	<!-- /wrapper -->
	

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>	<!-- /container -->


</body>
</html>