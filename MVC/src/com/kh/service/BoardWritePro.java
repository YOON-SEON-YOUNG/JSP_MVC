package com.kh.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.BoardDao;
import com.kh.util.MyFileUploader;
import com.kh.vo.BoardVo;
import com.oreilly.servlet.MultipartRequest;

public class BoardWritePro implements IBoardService {
	
	BoardDao boardDao = BoardDao.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartRequest multi = MyFileUploader.upload(request);
		
		String writer = multi.getParameter("writer");
		String email = multi.getParameter("email");
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		String passwd = multi.getParameter("passwd");
		String ip = request.getRemoteAddr();
		
		Enumeration<?> enumer = multi.getFileNames();
		String file1 = (String)enumer.nextElement();
		String fileSystemName = multi.getFilesystemName(file1);
		
		BoardVo vo = new BoardVo();
		vo.setWriter(writer);
		vo.setEmail(email);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPasswd(passwd);
		vo.setIp(ip);
		vo.setFile_name(fileSystemName);
		
		
		boardDao.insertArticle(vo);
		
//		System.out.println(writer);
		
		return IBoardService.STR_REDIRECT + "list.board";
	}

}
