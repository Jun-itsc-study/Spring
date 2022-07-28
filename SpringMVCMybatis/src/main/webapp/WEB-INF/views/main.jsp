<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td{text-align: center;}
</style>
</head>
<body>
<h2>회원관리</h2>
<hr>
<form action="register.do" method="post">
	<input type="text" name="id" placeholder="아이디"><br>
	<input type="password" name="passwd" placeholder="비밀번호"><br>
	<input type="text" name="name" placeholder="이름"><br>
	<input type="text" name="age" placeholder="나이"><br>
	<input type="radio" name="gender" value="M">남자
	<input type="radio" name="gender" value="F">여자<br>
	<input type="text" name="address" placeholder="주소"><br>
	<button>등록</button>
</form>
<hr>
	<table>
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>나이</th>
		<th>성별</th>
		<th>주소</th>
	</tr>
		<c:forEach var="i" items="${list }">
			<tr>
				<td>${i.id }</td>
				<td>${i.passwd }</td>
				<td>${i.name }</td>
				<td>${i.age }</td>
				<td>${i.gender }</td>
				<td>${i.address }</td>
				<td><a href="delete.do?id=${i.id }">삭제</a> / <a href="updateView.do?id=${i.id}">수정</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>