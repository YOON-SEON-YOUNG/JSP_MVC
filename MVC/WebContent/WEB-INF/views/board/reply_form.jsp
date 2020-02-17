<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reply_form.jsp</title>
</head>
<body>
<%-- ${boardVo} --%>
<h1>답글 폼</h1>
<form action="reply-pro.board" method="post">
	<input type="hidden" name="ref" value="${boardVo.ref}"/>
	<input type="hidden" name="re_step" value="${boardVo.re_step}"/>
	<input type="hidden" name="re_level" value="${boardVo.re_level}"/>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" required /></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="email" name="email" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject" value="ㄴ[Re:]${boardVo.subject}"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content">
----------------------
${boardVo.content}</textarea></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="passwd" required /></td>
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