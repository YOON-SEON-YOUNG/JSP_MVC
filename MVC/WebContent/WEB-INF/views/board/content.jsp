<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>content.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function () {
	// 수정버튼
	$("#btnUpdate").click(function () { 
		location.href = "update-form.board?num=${boardVo.num}";
	});
	// 삭제버튼
	$("#btnDelete").click(function () { 
		location.href = "delete-form.board?num=${boardVo.num}";
	});
	// 답글버튼
	$("#btnReply").click(function () { 
		location.href = "reply-form.board?num=${boardVo.num}";
	});
	// 목록버튼
	$("#btnList").click(function () {
		location.href = "list.board";
	});
})
</script>
</head>
<body>
<%-- boardVo: ${boardVo} --%>
<h1>글 내용 보기</h1>
<table border="1">
	<tr>
		<th>글번호</th>
		<td>${boardVo.num}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${boardVo.writer}(${boardVo.email})</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${boardVo.subject}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${boardVo.content}</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${boardVo.readcount}</td>
	</tr>
	<tr>
		<th>IP</th>
		<td>${boardVo.ip}</td>
	</tr>
</table>
<input type="button" id="btnUpdate" value="수정"/>
<input type="button" id="btnDelete" value="삭제"/>
<input type="button" id="btnReply" 	value="답글달기"/>
<input type="button" id="btnList" 	value="목록으로"/>
</body>
</html>