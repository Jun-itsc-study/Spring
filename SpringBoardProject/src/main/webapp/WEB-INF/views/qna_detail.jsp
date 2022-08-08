<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
<style type="text/css">
	#container{
		/* background-color: lime; */
		width:1200px;
		margin:0 auto;
	}
	#container table{
		width:600px;
		border-collapse: collapse;
		margin:0 auto;
	}
	#container th{
		width:150px !important;
		text-align: right;
		padding:5px;
	}
	#container td{
		width: 500px;
		padding:5px;
		height: 40px;
	}
	#container input{
		width: 100%;
		height:40px;
		box-sizing:border-box;
		border-width : 1px;
		border-radius: 5px;
	}
	h2{
		text-align: center;
	}
	.btn{
		text-decoration: none;
		background-color: #e8e8e8;
		width: 80px;
		display: inline-block;
		padding:5px 0px;
		font-weight:normal;
		border : 1px solid #585858;
		text-align:center;
		color : black;
		font-size:14px;
		box-sizing: border-box;
	}
	.btn:hover{
		background-color: #282828;
		color:#FFFFFF
	}
	.hate{
		transform: rotate(0.5turn);
	}
	.text_center{
		text-align: center;
	}
	.btn_like{
		width:64px;
		display: inline-block;
		color:black;
		text-decoration: none;
	}
	.comment_form{
		width:100%;
		border :1px solid black;
	}
	.writer{
		width: 100%;
		display: inline-block;
		font-weight: bold;
		padding-left: 30px;
	}
	.comment_form textarea{
		width:100%;
		height: 80px;
		margin-top:10px;
		font-size: 18px;
		padding-left: 30px;
		padding-right: 30px;
		outline:none;
		border: none;
		resize: none;
		box-sizing: border-box;
		
	}
	.comment_form button{
		width: 100px;
		height: 40px;
		font-weight: bold;
		background-color: #ffff00;	
		outline:none;
		border: none;
	}
	.comment_form button:hover{
		background-color: #dfdf25;	
	}
	.length{
		text-align: right;
		padding:10px 30px;
	}
	hr{
	margin:0;
	}
	.comment_form p {
		margin:0;
	}
	.f_img {
		width:300px;
		height: 100px;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$(".comment_form button").click(function(){
			var data = $("#comment").serialize();
			$.ajax({
				url : "insertResponse.do",
				data : data,
				success:function(d){
					if(d == 1){
						alert("답변 등록 성공");
					}else{
						alert("답변 등록 실패");
					}
					location.reload();
				}
			});
		});
	});
</script>
</head>
<body>
	<c:if test="${sessionScope.login == null || sessionScope.login == false  }">
		<c:set var="page" target="${sessionScope }" value="${pageContext.request.requestURI}${pageContext.request.queryString }" property="resultPage" scope="session"/>
		${pageContext.request.requestURI}${pageContext.request.queryString }
		<script>
			alert("로그인을 하셔야 이용할수 있습니다.");
			location.href="loginView.do";
		</script>
	</c:if>

	<jsp:include page="template/header.jsp" flush="false"></jsp:include>
	<div id="container">
		<h2>문의글 조회 페이지</h2>
			<table>
				<tr>
					<th>제목</th>
					<td>
						<!-- 조회한 내용 -->
						${dto.title }
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						${dto.writer }			
					</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>
						${dto.wdate }
					</td>
				</tr>
				<tr>
					<th style="vertical-align: top;">내용</th>
					<td>
						${dto.content }
					</td>
				</tr>
				<c:if test="${sessionScope.gradeNo >= 6}">
				<tr>
					<td colspan="2">
						<div class="comment_form">
							<form id="comment">
							<input type="hidden" name="qno" value="${dto.qno }">
							<textarea name="response" maxlength="500"></textarea>
							<p style="text-align: right;"><button type="button">등록</button></p>
							</form>							
						</div>
					</td>
				</tr>
					</c:if>
				<tr>
					<th><a href="qnaAdminView.do?pageNo=${param.pageNo }" class="btn">목록보기</a></th>
					<td style="text-align: right;">
					<c:if test="${sessionScope.gradeNo >= 6 }">
						<a href="deleteQna.do?qno=${dto.qno }" class="btn">삭제</a>
					</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:if test="${sessionScope.gradeNo >=6 && dto.response != null}">
							<a href="#.do?qno=${dto.qno }">삭제</a>
						</c:if>
						<p>
							${dto.response }
						</p>
					</td>
				</tr>
			</table>
	</div>
	<jsp:include page="template/footer.jsp" flush="false"></jsp:include>
</body>
</html>