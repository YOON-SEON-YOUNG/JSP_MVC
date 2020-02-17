<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax_list.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function () {
	 var nowPage = 1;
	
	$("#btnMore").click(function() {
		var url = "list.ajax";
		var perPage = $("input[name=perPage]").val();
			console.log("perPage:"  + perPage);
		var sendData = {
				"nowPage" : ++nowPage,
				"perPage" : $("input[name=perPage]").val(),
				"searchType" : $("select[name=searchType]").val(),
				"keyword" : $("input[name=keyword]").val()
		};
		$.getJSON(url, sendData, function(receivedData) {
			console.log(receivedData); // [{}, {}, {},....]
			$.each(receivedData, function () {  // each:각각의
				var tr = "<tr>";
					tr += "<td>" + this.num + "</td>";
					if (this.file_name == null){
						tr += "<td><img src='images/default.jpg' width='50'/></td>";
					} else {
						tr += "<td><img src='upload/" + this.file_name + "' width='50'/></td>";
					}
				 	tr += "<td>" + this.subject + "</td>";
				 	tr += "<td>" + this.writer + "</td>";
				 	tr += "<td>" + this.reg_date + "</td>";
				 	tr += "<td>" + this.readcount + "</td>";
				 	tr += "<td>" + this.ip + "</td>";
				 	tr += "</tr>";
				$("#board_table").append(tr);
				
			});
		});
	});
});
</script>
</head>
<body>

<h1>글목록</h1>
<a href="write-form.board">글쓰기</a>
<form action="list.board" id="searchForm">
<input type="hidden" name="nowPage" value="${pagingDto.nowPage}"/>
	<select name="searchType">
		<option value="subject"
		<c:if test="${searchDto.searchType == 'subject'}">
		selected
		</c:if>
		 >제목</option>
		<option value="content"
		<c:if test="${searchDto.searchType == 'content'}">
		selected
		</c:if>
		>내용</option>
		<option value="writer"
		<c:if test="${searchDto.searchType == 'writer'}">
		selected
		</c:if>
		>작성자</option>
	</select>
	<input type="text" name="keyword" value="${searchDto.keyword}"/>
	<input type="submit" value="검색"/>
	<!-- 5 ~ 20줄 -->
	<select name="perPage">
		<c:forEach var="i" begin="5" end="20" step="5">
			<option value="${i}"
				<c:if test="${ i == pagingDto.perPage }">selected</c:if>
			>${i}줄씩 보기</option>
		</c:forEach>
	</select>
</form>
<%-- ${list} --%>
<table border="1" id="board_table">
	<tr>
		<th>번호</th>
		<th>이미지</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
		<th>IP</th>
	</tr>
	<%-- for (BoardVo boardVo : list) --%>
	<c:forEach items="${list}" var="boardVo">
	<tr>
		<td>${boardVo.num}</td>	
		<td>
			<c:choose>
				<c:when test="${empty boardVo.file_name}">
					<img src="images/default.jpg" width="50"/>
				</c:when>
				<c:otherwise>
					<img src="upload/${boardVo.file_name}" width="50"/>
				</c:otherwise>
			</c:choose>
		</td>										
		<td>
			<c:if test="${boardVo.re_level > 0}">
				<img src="images/npsp.png" width="${boardVo.re_level * 20}"/>
			</c:if>
			<a href="content.board?num=${boardVo.num}">${boardVo.subject}</a>
		</td>	
		<td>${boardVo.writer}</td>										
		<td>${boardVo.reg_date}</td>									
		<td>${boardVo.readcount}</td>								
		<td>${boardVo.ip}</td>											
	</tr>
	</c:forEach>
</table>
<input type="button" value="더보기" id="btnMore"/>
</body>
</html>