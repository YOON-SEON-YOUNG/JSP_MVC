package com.kh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.dao.BoardDao;
import com.kh.vo.BoardVo;
import com.kh.vo.PagingDto;
import com.kh.vo.SampleDto;
import com.kh.vo.SearchDto;

public class BoardAjaxList implements IBoardService {

	BoardDao boardDao = BoardDao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int perPage = 10;
		String strPerPage = request.getParameter("perPage");
		if (strPerPage != null) {
			perPage = Integer.parseInt(strPerPage);
		}
//		System.out.println("perPage:" + perPage);
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		SearchDto searchDto = new SearchDto(searchType, keyword);
//		System.out.println("searchDto:" + searchDto);
		
		int nowPage = 1;
		String strPage = request.getParameter("nowPage");
		if (strPage != null && !strPage.equals("")) {
			nowPage = Integer.parseInt(strPage);
		}
		
		PagingDto pagingDto = new PagingDto(nowPage, searchDto, perPage);
		System.out.println("pagingDto:" + pagingDto);
		
		List<BoardVo> list = boardDao.getArticles(pagingDto, searchDto);
		
		// JSONArray - [] , JSONObject - {}
		JSONArray jsonArray = new JSONArray();	// []
		for (BoardVo vo : list) {
			JSONObject jsonObject = new JSONObject();	// {}
			jsonObject.put("num",		vo.getNum());
			jsonObject.put("file_name",	vo.getFile_name());
			jsonObject.put("subject", 	vo.getSubject());
			jsonObject.put("writer", 	vo.getWriter());
			jsonObject.put("reg_date", 	vo.getReg_date().toString());
			jsonObject.put("readcount", vo.getReadcount());
			jsonObject.put("ip", 		vo.getIp());
			
			jsonArray.add(jsonObject);	// [{"name" : "가길동", "age" : "20"}, {}, {}]
		}
		
		String data = jsonArray.toJSONString();
		System.out.println("data: " + data);
		request.setAttribute("data", data);
		
		return "data"; // data.jsp
	}

}
