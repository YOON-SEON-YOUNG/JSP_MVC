<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete_form.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function() {
	$("#deleteForm").submit(function() {
		if(!confirm("삭제하시겠습니까?")){
			return false; // 폼 전송하지 않겠다.
		}
	});
});
</script>
</head>
<body>
<h1>삭제 폼</h1>
<form action="delete-pro.board" method="post" id="deleteForm">
<input type="hidden" name="num" value="${num}">
 	<table border="1">
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="passwd" placeholder="비밀번호 입력" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="삭제하기">
			</td>
		</tr>
	</table>
</form>
</body>
</html>