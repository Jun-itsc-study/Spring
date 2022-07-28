<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>아이디 : ${requestScope.dto.id }</p>
	<p>비번 : ${requestScope.dto.pass }</p>
	<p>이름 : ${requestScope.dto.name }</p>
	<p>나이 : ${requestScope.dto.age }</p>
</body>
</html>