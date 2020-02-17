package com.kh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.sql.DataSource;
import javax.swing.text.AbstractDocument.Content;

import com.kh.vo.BoardVo;
import com.kh.vo.PagingDto;
import com.kh.vo.SearchDto;

import javafx.css.PseudoClass;

public class BoardDao {
	
	private static BoardDao instance;
	
	private BoardDao() { /* singleton */ }
	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/basicjsp"); 
			Connection conn = ds.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) 	try { rs.close(); } 	catch (Exception e) { }
		if (pstmt != null) 	try { pstmt.close(); } 	catch (Exception e) { }
		if (conn != null) 	try { conn.close(); } 	catch (Exception e) { }
	}
	
	
	// 글목록
	public List<BoardVo> getArticles(PagingDto pagingDto, SearchDto searchDto){				// List : ArrayList의 상위타입
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from" + 
					"        (select rownum rnum, a.* from" + 
					"            (select * from board";
			
			if (!searchDto.getSearchType().equals("") &&
					!searchDto.getKeyword().equals("")) {
				sql += "          where " + searchDto.getSearchType() +
					   "          like '%" + searchDto.getKeyword() + "%'";
			}
			
			sql +=  "             order by ref desc, re_step asc) a)" + 
					"     where rnum >= ? and rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pagingDto.getStartRow());
			pstmt.setInt(2, pagingDto.getEndRow());
			rs = pstmt.executeQuery();
			// java.util.List - interface
			List<BoardVo> list = new ArrayList<>();
			while (rs.next()) {
				int 		num 		= rs.getInt("num");
				String	 	subject 	= rs.getString("subject");
				String		 writer 	= rs.getString("writer");
				Timestamp 	reg_date 	= rs.getTimestamp("reg_date");
				int 		readcount 	= rs.getInt("readcount");
				int 		ref 		= rs.getInt("ref");
				int 		re_step 	= rs.getInt("re_step");
				int 		re_level 	= rs.getInt("re_level");
				String 		ip 			= rs.getString("ip");
				String 		file_name 	= rs.getString("file1");
				
				BoardVo vo = new BoardVo();
				vo.setNum(num);
				vo.setSubject(subject);
				vo.setWriter(writer);
				vo.setReg_date(reg_date);
				vo.setReadcount(readcount);
				vo.setRef(ref);
				vo.setRe_step(re_step);
				vo.setRe_level(re_level);
				vo.setIp(ip);
				vo.setFile_name(file_name);
				
				list.add(vo);
				
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}// 글목록
	
	
	// 글쓰기
	public void insertArticle(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into board"
					+ "		(num, writer, email, subject, content,"
					+ "		passwd, ref, re_step, re_level, ip, file_name)"
					+ "		values "
					+ "			(seq_board.nextval, ?, ?, ?, ?,"
					+ "			?, seq_board.nextval, 0, 0, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, vo.getWriter());
			pstmt.setString	(2, vo.getEmail());
			pstmt.setString	(3, vo.getSubject());
			pstmt.setString	(4, vo.getContent());
			pstmt.setString	(5, vo.getPasswd());
			pstmt.setString	(6, vo.getIp());
			pstmt.setString	(7, vo.getFile_name());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}// 글쓰기
	
	
	// 내용 상세보기
	public BoardVo getArticleByNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql2 = "update board set"
					+ "			readcount = readcount + 1"
					+ "		where num = ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, num);
			pstmt2.executeUpdate();
			
			String sql = "select * from board"
					+"		where num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String writer = rs.getString("writer");
				String email = rs.getString("email");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				Timestamp reg_date = rs.getTimestamp("reg_date");
				int readcount = rs.getInt("readcount");
				int ref = rs.getInt("ref");
				int re_step = rs.getInt("re_step");
				int re_level = rs.getInt("re_level");
				String ip = rs.getString("ip");
				
				BoardVo vo = new BoardVo();
				vo.setNum(num);
				vo.setWriter(writer);
				vo.setEmail(email);
				vo.setSubject(subject);
				vo.setContent(content);
				vo.setReg_date(reg_date);
				vo.setReadcount(readcount);
				vo.setRef(ref);
				vo.setRe_step(re_step);
				vo.setRe_level(re_level);
				vo.setIp(ip);
				
				return vo;
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt2, null);
			closeAll(conn, pstmt, null);
		}	
		return null;
	}// 내용 상세보기
	
	
	// 글 수정하기
	public void updateArticle(BoardVo boardVo) {
//		System.out.println(boardVo);
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update board set"
					+ "		subject = ?,"
					+ "		content = ? "
					+ "   where num = ? and passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getSubject());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getNum());
			pstmt.setString(4, boardVo.getPasswd());
			int count = pstmt.executeUpdate();
//			System.out.println("count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return;
	}// 글 수정하기
	
	
	// 글 삭제
	public void deleteArticle(int num, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "delete from board"
					+ "   where num = ? and passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, passwd);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}// 글 삭제

	
	// 답글달기
	public void reply(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			conn = getConnection();
			// Transaction 설정
			conn.setAutoCommit(false);
			String sql2 = "update board set"
					+ "			re_step = re_step + 1"
					+ "	   where ref = ? and re_step > ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, vo.getRef());
			pstmt2.setInt(2, vo.getRe_step());
			pstmt2.executeUpdate();
			
			String sql = "insert into board"
					+ "		(num, writer, email, subject, content,"
					+ "		passwd, ref, re_step, re_level, ip)"
					+ "		values "
					+ "			(seq_board.nextval, ?, ?, ?, ?,"
					+ "			?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, vo.getWriter());
			pstmt.setString	(2, vo.getEmail());
			pstmt.setString	(3, vo.getSubject());
			pstmt.setString	(4, vo.getContent());
			pstmt.setString	(5, vo.getPasswd());
			pstmt.setInt	(6, vo.getRef());
			pstmt.setInt	(7, vo.getRe_step() + 1);
			pstmt.setInt	(8, vo.getRe_level() + 1);
			pstmt.setString	(9, vo.getIp());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			closeAll(null, pstmt2, null);
			closeAll(conn, pstmt, null);
		}

	}// 답글달기
	
	
	// 조회수
	public int getCount(SearchDto searchDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select count(*) from board";
			if (!searchDto.getSearchType().equals("") &&
					!searchDto.getKeyword().equals("")) {
				sql += "	where " + searchDto.getSearchType() +
						"	like '%" + searchDto.getKeyword() + "%'";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				return cnt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return 0;
	}
	
	
}
