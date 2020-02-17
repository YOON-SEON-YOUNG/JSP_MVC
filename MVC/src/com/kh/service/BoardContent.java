package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.BoardDao;
import com.kh.vo.BoardVo;

public class BoardContent implements IBoardService {
	
	BoardDao boardDao = BoardDao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardVo boardVo = boardDao.getArticleByNum(num);
		request.setAttribute("boardVo", boardVo);
		
		// DB에서 해당 글번호의 데이터 가져오기
		return "content"; // /WEB-INF/views/board/content.jsp
	}

}
