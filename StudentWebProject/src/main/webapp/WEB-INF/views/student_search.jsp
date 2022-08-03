<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
function all_select(){
	$.ajax({
		url:"all.do",
		dataType:"json",
		success:function(r){
			var tag="";
			if(r.code == 200){
				var arr = r.result;
				for(i=0;i<arr.length;i++){
					tag += "<tr>";
					tag += "<td>"+arr[i].sno+"</td>";
					tag += "<td>"+arr[i].name+"</td>";
					tag += "<td>"+arr[i].major+"</td>";
					tag += "<td>"+arr[i].score+"</td>";
					tag += "</tr>";
				}
			} else {
				tag = r.message;
			}
			$(".container").html(tag);
		}//success
	})//ajax
}
$(function(){
	//----
	all_select();
	//----
	$(".btn").click(function(){
		var d = $("#frm").serialize();
		$.ajax({
			url:"search.do",
			data:d,
			dataType:"json",
			success:function(r){
				var tag="";
				if(r.code == 200){
					var arr = r.result;
					for(i=0;i<arr.length;i++){
						tag += "<tr>";
						tag += "<td>"+arr[i].sno+"</td>";
						tag += "<td>"+arr[i].name+"</td>";
						tag += "<td>"+arr[i].major+"</td>";
						tag += "<td>"+arr[i].score+"</td>";
						tag += "</tr>";
					}
				} else {
					tag = r.message;
				}
				$(".container").html(tag);
			}//success
		})//ajax
	})//btn
	$(".reset").click(function(){
		all_select();
	});//reset
})	
</script>
<style type="text/css">
	td {
		padding: 5px;
		text-align: center;
		width:100px;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="frm">
		<select name="kind">
			<option value="sno">학번</option>
			<option value="name">이름</option>
			<option value="major">학과</option>
		</select>
		<input type="text" name="search"><button type="button" class="btn">검색</button>
		<button type="button" class="reset">리셋</button>
	</form>
	<hr>
	<div class="container">
	<table>
	</table>
	</div>
</body>
</html>