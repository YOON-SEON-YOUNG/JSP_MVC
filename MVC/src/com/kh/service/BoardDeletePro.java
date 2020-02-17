package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.BoardDao;
import com.kh.vo.BoardVo;

public class BoardDeletePro implements IBoardService {
	
	BoardDao boardDao = BoardDao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		BoardVo boardVo = new BoardVo();
		boardVo.setNum(num);
		boardVo.setPasswd(passwd);
		
		boardDao.deleteArticle(num, passwd);
		
		return IBoardService.STR_REDIRECT + "list.board";
	}

}
