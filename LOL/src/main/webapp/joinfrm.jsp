<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- <script type="text/javascript"> -->
<%--  	alert('${msg}')  --%>
<!-- </script> -->
<body>
	<h1>joinfrm</h1>
	<form action="join" name="joinfrm">
		ID : <input type="text" name="userId"> <br> 
		PW : <input type="password" name="userPw"> <br>
		NAME : <input type="text" name="name"> <br>
		GENDER : <input type="radio" name="gender" value="남자">남자<br>
<input type="radio" name="gender" value="여자">여자
		<button>회원가입</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>