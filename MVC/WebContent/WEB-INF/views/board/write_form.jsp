<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글쓰기 폼</h1>
<form action="write-pro.board" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" required/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="email" name="email"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content"></textarea> </td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="passwd" required/></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><input type="file" name="file1" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" value="완료">
			</td>
		</tr>
	</table>
</form>
</body>
</html>