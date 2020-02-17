<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.kh.vo.SampleDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<SampleDto> list = new ArrayList<>();
	list.add(new SampleDto("가길동", 20));
	list.add(new SampleDto("나길동", 30));
	list.add(new SampleDto("다길동", 40));
	list.add(new SampleDto("라길동", 50));
	list.add(new SampleDto("마길동", 60));
	
// 	out.print(list);
	
	// JSONArray - [] , JSONObject - {}
	JSONArray jsonArray = new JSONArray();	// []
	for (SampleDto dto : list) {
		JSONObject jsonObject = new JSONObject();	// {}
		jsonObject.put("name", dto.getName());		// {"name" : "가길동"}
		jsonObject.put("age", dto.getAge());		// {"name" : "가길동", "age" : "20"}
		jsonArray.add(jsonObject);	// [{"name" : "가길동", "age" : "20"}, {}, {}]
	}
	
	String data = jsonArray.toJSONString();
	
	out.print(data);
%>