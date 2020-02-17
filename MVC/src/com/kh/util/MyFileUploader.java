package com.kh.util;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyFileUploader {
	
	
	private static final String SAVE_DIRECTORY = "upload";
	private static final int maxSize = 1024 * 1024 * 10;
	private static final String ENCODING = "UTF-8";

	public static MultipartRequest upload(HttpServletRequest request) throws Exception {
		ServletContext application = request.getServletContext();	// request로 application 받을수있도록
		String contextPath = application.getRealPath("/");	// D:\workspace\JSP\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC\
		String uploadPath = contextPath + SAVE_DIRECTORY;
		System.out.println("uploadPath: " + uploadPath);
		// 폴더 존재 여부 확인 후 생성
		File f = new File(uploadPath);
		if (!f.exists()) {	// 존재하지 않는다면
			f.mkdir();		// make directory - 폴더생성
		}
		MultipartRequest multi = new MultipartRequest(
				request, 
				uploadPath,
				maxSize,
				ENCODING,
				new DefaultFileRenamePolicy());
		return multi;
	}
}
