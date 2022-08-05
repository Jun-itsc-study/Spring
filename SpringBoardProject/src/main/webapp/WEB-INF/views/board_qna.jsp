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
	form table{
		width:600px;
		border-collapse: collapse;
		margin:0 auto;
	}
	
	form th{
		width:150px !important;
		text-align: right;
		padding:5px;
	}
	form td{
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
	textarea {
		width:100%;
		height: 300px;
		resize: none;
		box-sizing: border-box;
		border-radius: 5px;
	}
	.qna_title ul{
		list-style-type: none;
		font-size:0px;
		padding:0;
	}
	.qna_title li {
		display: inline-block;
		font-size: 16px;
		maring-right:50px;
	}
	#accordion{
		width:800px;
		margin:0 auto;
	}
	.btn_more{
		width:800px;
		border:none;
		border-radius: 5px;
		height: 50px;
		font-size:18px;
		display: block;
		margin: 0 auto;
		background-color:inherit;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<!-- 
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
 -->
<script>
	$(function(){
		var page=1;
		$( "#accordion" ).accordion();
		$("#frm").submit(function(e){
			var title = $("input[name=title]").val();
			var content = $("textarea[name=content]").val();
			var result=true;
			if(title.length==0){
				alert("문의 제목을 입력하세요");
				result = false;
			} else if(title.length > 33) {
				alert("문의 제목은 33자 이하로 입력하세요");
				result = false;
			} else if(content.length == 0) {
				alert("문의 내용을 입력하세요");
				result = false;
			} else if(content.length > 1300) {
				alert("문의 내용은 1300자 이하로 입력하세요");
				result = false;
			}
			return result;
			/* e.preventDefault(); */
		}); //frm
		$(".btn_more").click(function(){
			page += 1;
			$.ajax({
				url:"qnaMore.do",
				data:"page="+page,
				dataType:"json",
				success:function(r){
					if(r.nextPage == 0){
						$(".btn_more").off("click").text("더 이상 불러올 내용이 없습니다.");
					}
					var arr = r.list;
					var tag = "";
					for(i=0;i<arr.length;i++){
						tag += "<h3 class='qna_title'>";
						tag += "<ul>";
						tag += "<li>["+arr[i].qno+"]</li>";
						tag += "<li class='title'>[제목]"+arr[i].title+"</li>";
						tag += "<li class='date'>[작성일]"+arr[i].wdate+"</li>";
						tag += "<li>[답변상태]";
						switch(arr[i].status){
						case 0: "읽지 않음"; break;
						case 1: "읽음"; break;
						case 2: "답변 완료"; break;
						}
						tag += "</li></ul></h3>";
						tag+="<div>";
						tag+="<p class='qna_content'>[문의내용]"+arr[i].content+"</p>";
						tag+="<p class='qna_response'>[답변]"+arr[i].response+"</p>";
						tag+="<hr></div>";
					}
					$("#accordion").append(tag);
					$("#accordion").accordion("refresh");
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
		<h2>문의 페이지</h2>
		<form id="frm" action="boardQnaWrite.do" method="post">
		<input type="hidden" name="writer" value="${sessionScope.id }">
			<table>
				<tr>
					<td><input type="text" name="title" placeholder="문의제목"></td>
				</tr>
				<tr>
					<td><textarea name="content" placeholder="문의내용"></textarea></td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<a href="javascript:history.back();" class="btn">뒤로가기</a>
						<button class="btn">글쓰기</button>
					</td>
				</tr>
			</table>
		</form>
		</div>
	<c:if test="${list.size() != 0}">
		<hr>
		<div id="accordion">
			<c:forEach var="dto" items="${list }">
				<h3 class="qna_title">
					<ul>
						<li>[${dto.qno }]</li>
						<li class="title">[제목]${dto.title }</li>
						<li class="date">[작성일]${dto.wdate }</li>
						<li>[답변상태] <c:choose>
								<c:when test="${dto.status == 0}">읽지않음</c:when>
								<c:when test="${dto.status == 1}">읽음</c:when>
								<c:otherwise>답변완료</c:otherwise>
							</c:choose>
						</li>
					</ul>
				</h3>
				<div>
					<p class="qna_content">[문의내용] ${dto.content }</p>
					<p class="qna_response">[답변]${dto.response }</p>
					<hr>
				</div>
			</c:forEach>
		</div>
			<button class="btn_more">더보기</button>
	</c:if>
	<jsp:include page="template/footer.jsp" flush="false"></jsp:include>
</body>
</html>