<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update_form.jsp</title>
</head>
<body>

<h1>수정 폼</h1>
<form action="update-pro.board" method="post">
	<input type="hidden" name="num" value="${boardVo.num}"/>
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>${boardVo.writer}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${boardVo.email}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${boardVo.subject}"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content">${boardVo.content}</textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="passwd"/></td>
			</tr>
		</table>
	<input type="submit" value="수정완료">
</form>
</body>
</html>