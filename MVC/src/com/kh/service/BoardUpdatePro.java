package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.BoardDao;
import com.kh.vo.BoardVo;

public class BoardUpdatePro implements IBoardService {
		
		BoardDao boardDao = BoardDao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String passwd = request.getParameter("passwd");
		
		BoardVo boardVo = new BoardVo();
		boardVo.setNum(num);
		boardVo.setSubject(subject);
		boardVo.setContent(content);
		boardVo.setPasswd(passwd);
		
		boardDao.updateArticle(boardVo);
		
		
		// DAO에 업데이트 요청
		return IBoardService.STR_REDIRECT + "content.board?num=" + num;
	}

}
