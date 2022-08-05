<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<style type="text/css">
	#container{
		/* background-color: lime; */
	}
	.board{
		margin:20px auto;
		border-collapse: collapse;
	}
	.board th, .board td{
		padding: 10px;
		text-align: center;
	}
	.board th{
		border-top : 2px solid black;
		border-bottom : 2px solid black;
	}
	.board td{
		border-top : 1px solid black;
		border-bottom : 1px solid black;
	}
	.board tr:last-child > td {
		border-bottom : 2px solid black;
	}
	.title{
		width:500px;	
	}
	.writer{
		width:150px;	
	}
	.date{
		width:200px;
	}
	.page_bar{
		position:relative;
		text-align: center;
	}
	.page_bar a:link,.page_bar a:visited {
		color:black;
		text-decoration: none;
		font-size : 18px;
		margin-left: 10px;
		margin-right: 10px;
	}
	.page_bar a:hover{
		font-weight: bold;
		color:red;
	}
	.btn_writer{
		position:absolute;
		right:0px;
	}
	.a a:link, .a a:visited {
		text-decoration: none;
		color:black;	
	}
	.a a:hover{
		font-weight: bold;
	}
	.a:hover{
		background-color: rgb(233,233,233);
	}
</style>
</head>
<body>
	<!-- header.jsp를 현재 문서에 포함 -->
	<jsp:include page="template/header.jsp" flush="false"></jsp:include>
	<div id="container">
		<table class="board">
			<tr>
				<th>글번호</th>
				<th class="title">제목</th>
				<th class="writer">작성자</th>
				<th class="date">작성일</th>
				<th>답변상태</th>
			</tr>
	<!-- 게시판 기능 추가 기존 게시판에 있는 내용을 el과 jstl로 표현 -->
		<c:if test="${list ==null}">
			<script>
				location.href="/qnaView.do?pageNo=1";
				
			</script>
		</c:if>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.qno }</td>
				<td class="a"><a href="qnaDetail.do?qno=${dto.qno }"> ${dto.title }</a></td>
				<td>${dto.writer}</td>
				<td>${dto.wdate }</td>
				<td>
					<c:choose>
						<c:when test="${dto.status == 0}">읽지않음</c:when>
						<c:when test="${dto.status == 1}">읽음</c:when>
						<c:otherwise>답변완료</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
			<tr>
			<td colspan="5">
					<div class="page_bar">
						<c:if test="${pagging.previousPageGroup }">
							<a href="/?pageNo=${pagging.startPageOfPageGroup - 1 }">◀</a>
						</c:if>
						<c:forEach var="i" begin="${pagging.startPageOfPageGroup}" 
						end="${pagging.endPageOfPageGroup}">
							<a href="/?pageNo=${i }">${ i}</a>
						</c:forEach>
					
						<c:if test="${pagging.nextPageGroup }">
							<a href="/?pageNo=${pagging.endPageOfPageGroup + 1 }">▶</a>
						</c:if>
						<a href="qnaWriteView.do" class="btn_writer">문의글쓰기</a>
					</div>
			</tr>
		</table>
	</div>
	<jsp:include page="template/footer.jsp" flush="false"></jsp:include>
	
</body>
</html>