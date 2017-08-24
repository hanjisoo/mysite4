
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("n","\n"); %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap-theme.min.css"></script>
<title>ajax-Mysite</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

	<div id="wrapper">
			<div id="content">
				<div id="user">
					<form id="write-form" action="${pageContext.request.contextPath }/book/add" method="post">
						<table border=1 width=500>
							<tr>
								<td>이름</td>
								<td><input id="name" type="text" name="name"></td>
								<td>비밀번호</td>
								<td><input id="password" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan=4><textarea id="content" name="content" cols=60 rows=5></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
							</tr>
						</table>
					</form>
					<br />
					<ul id="guestbook-list"></ul>
					

<%-- <c:forEach items="${list}" var="vo">
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
</c:forEach> --%>
					
				</div>	<!-- /user -->
			</div>	<!-- /content -->
		</div>	<!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	
	</div>	<!-- /container -->

</body>


<script type="text/javascript">
	$(document).ready(function(){//그리기전에 읽어
		//리스트 출력
		fetchList();
		
		//저장버튼 클릭
		$("#write-form").on("submit",function(){
			//원래 기능 막아
			event.preventDefault();
			console.log("전송버튼클릭");
			//네임값 뽑아와
			var name=$("#name").val();
			var name2=$("[name=name]").val();
			var password=$("[name=password]").val();
			var content=$("[name=content]").val();
			//콘솔창에 뿌려지는지 확인
			/* console.log(name); */
			//자바스크립트 객체
			var guestbookVo={
				
				name:name,
				password: password,
				content:content
			}
			
			
			$.ajax({
				
				url : "${pageContext.request.contextPath }/api/book/add",
				type : "post",
				//제이슨으로 보낼때
				/* contentType : "application/json",
				data:JSON.stringgiry(guestbookVo), */
				
				//파라미터방식처럼 가는 거야 바디에 담아가는거 아니고.
				//date :guestbookVo,//위에 객체에 다 담아 놨으니깐
				data : {name: name, password:password, content:content} ,
				dataType : "json", 
				success : function(guestbookVo){
						
					console.log(guestbookVo);
					
					//화면 추가
					render(guestbookVo,"up");
				},
				error : function(XHR, status, error) {
				console.error(status + " : " + error);
				}
			});
		});
	});
	
	
function fetchList(){
	$.ajax({//비동기식이다.
		
		url : "${pageContext.request.contextPath }/api/book/list",
		type : "post",
		/* contentType : "application/json",	//데이터를 컨트롤러로 보낼때
		data : {name: ”홍길동"},*/
		dataType : "json", //데이터 받아올때 제이슨으로 받아서 별명으로 받아줘
		
		success : function(guestbookList){
			/*성공시 처리해야될 코드 작성*/	
			for(var i=0; i<guestbookList.length; i++){
				render(guestbookList[i],"down");
			}
			
			console.log(guestbookList);
						//별명
				
		/* alert(result); */
		},
		error : function(XHR, status, error) {
		console.error(status + " : " + error);
		}
	});
}

	
//데이터랑 섞어주는애 만듬
function render(guestbookVo,updown){
	
	var str="";
	str+="<li>"
	str+="	<table width='510' border='1'>"
	str+="   	<tr>"
	str+="	   		<td>["+ guestbookVo.no +"]</td>";
	str+="	   		<td>["+ guestbookVo.name +"]</td>";
	str+="	   		<td>["+ guestbookVo.regDate +"]</td>";
	str+="	   		<td><a href=>삭제</a></td>";
	str+="   	</tr>"
	str+="   	<tr>"
	str+="	   		<td colspan=4>["+ guestbookVo.content +"]</td>";
	str+="  	</tr>"
	str+="	</table>"
	str+="	<br/>"
	str+="</li>"
	
	if(updown=="down"){
		$("#guestbook-list").append(str);
	}else if(updown=="up"){
		$("#guestbook-list").prepend(str);
	}
}
	
	

</script>













</html>