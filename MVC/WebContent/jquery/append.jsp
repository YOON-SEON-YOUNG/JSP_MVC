<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
// 자식, 자손
// a > b, a b
// html > head, html meta
$(function () {
	$("#myButton").click(function () {
		var td = "<td>데이터</td>";
		$("#myTable tr").append(td); // append
	});
	
});
</script>
<meta charset="UTF-8">
<title>append.jsp</title>
</head>
<body>
	<input type="button" value="추가" id="myButton"/>
	<table border="1" id="myTable">
		<tr></tr>
	</table>
</body>
</html>