package com.kh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.BoardDao;
import com.kh.vo.BoardVo;

public class BoardReplyPro implements IBoardService {
	
	BoardDao boardDao = BoardDao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String passwd = request.getParameter("passwd");
		String ip = request.getRemoteAddr();
		
		BoardVo boardVo = new BoardVo();
		boardVo.setRef(ref);
		boardVo.setRe_step(re_step);
		boardVo.setRe_level(re_level);
		boardVo.setWriter(writer);
		boardVo.setEmail(email);
		boardVo.setSubject(subject);
		boardVo.setContent(content);
		boardVo.setPasswd(passwd);
		boardVo.setIp(ip);
		
		boardDao.reply(boardVo);
		
		return IBoardService.STR_REDIRECT + "list.board";
	}

}
