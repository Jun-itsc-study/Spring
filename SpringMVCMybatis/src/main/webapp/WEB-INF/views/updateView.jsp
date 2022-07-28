<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원수정</h2>
<hr>
<form action="update.do" method="post">
	<input type="text" name="id" readonly="readonly" value="${dto.id }"><br>
	<input type="password" name="passwd" placeholder="비밀번호"><br>
	<input type="text" name="name" value="${dto.name }"><br>
	<input type="text" name="age" value="${dto.age }"><br>
	<input type="radio" name="gender" value="M" <c:if test="${dto.gender == 'M' }">checked</c:if>>남자
	<input type="radio" name="gender" value="F" <c:if test="${dto.gender == 'F' }">checked</c:if>>여자<br>
	<input type="text" name="address" value="${dto.address }"><br>
	<button>수정</button>
	<button onclick="history.back();" type="button">돌아가기</button>
</form>
</body>
</html>