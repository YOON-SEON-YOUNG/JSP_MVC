package com.kh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.BoardDao;
import com.kh.vo.BoardVo;
import com.kh.vo.PagingDto;
import com.kh.vo.SearchDto;

public class BoardList implements IBoardService {
	
	BoardDao boardDao = BoardDao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int perPage = 10;
		String strPerPage = request.getParameter("perPage");
		if (strPerPage != null) {
			perPage = Integer.parseInt(strPerPage);
		}
//		System.out.println("perPage: " + perPage);
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		SearchDto searchDto = new SearchDto(searchType, keyword);
//		System.out.println("searchDto: " + searchDto);
		
		int nowPage = 1;
		String strPage = request.getParameter("nowPage");
		if (strPage != null && !strPage.equals("")) {
			nowPage = Integer.parseInt(strPage);
		}
		
		PagingDto pagingDto = new PagingDto(nowPage, searchDto, perPage);
		System.out.println("pagingDto: " + pagingDto);
		
		List<BoardVo> list = boardDao.getArticles(pagingDto, searchDto);
		request.setAttribute("list", list);
		request.setAttribute("pagingDto", pagingDto);
		request.setAttribute("searchDto", searchDto);
		return "list"; // 페이징
//		return "ajax_list";	// 더보기
	}

}
