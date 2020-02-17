<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxForm.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function () {
	var v = 1;
	$("#myButton").click(function () {
		// get - $.get(url, data, funciton(rData) {}), post - $.post()
// 		$.get("ajaxSend.jsp", {"v" : v}, function (rData) {
		$.getJSON("ajaxSend.jsp", { }, function (rData) {
			console.log(rData);
			$.each(rData, function () { // rData : 배열
				var name = this.name;
				var age = this.age;
				var tr = "<tr>";
					tr += "<td>" + name + "</td>";
					tr += "<td>" + age + "</td>";
					tr += "</tr>";	
				$("#myTable").append(tr);
			});

// 			var td = "<td>" + rData.trim() + "</td>";	//trim() : 공백제거
// 			$("#myTable tr").append(td);
// 			v++;
		});
	});
});
</script>
</head>
<body>
	<input type="button" value="데이터 가져오기" id="myButton">
	<table border="1" id="myTable">
		<tr></tr>
	</table>
</body>
</html>