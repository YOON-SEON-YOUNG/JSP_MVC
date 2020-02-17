package com.kh.vo;

import com.kh.dao.BoardDao;

public class PagingDto {
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	private int totalPage;
	private int perPage = 10;
	private int nowPage = 1;
	private final int PAGE_BLOCK = 10;
	
	public PagingDto() {
		super();
	}

	public PagingDto (int nowPage, SearchDto searchDto, int perPage) {
		this.nowPage = nowPage;
		this.perPage = perPage; // private perPage = int perPage
		BoardDao boardDao = BoardDao.getInstance();
		int count = boardDao.getCount(searchDto);
		
		// 총 페이지 수
		// count	totalPage
		// 500		50
		// 501		51
		// 502		51
		totalPage = (int)Math.ceil((double)count / 10); // 석인
		
		// nowPage 	startRow	endRow
		// 1		1			10
		// 2		11			20
		// 3		21			30 
		startRow = (nowPage * perPage) - (perPage - 1); // 진광
		endRow = nowPage * perPage;
		
		// nowPage	startPage	endPage
		// 1		1			10
		// 5		1			10
		// 10		1			10
		// 11		11			20
		startPage = ((nowPage - 1) / PAGE_BLOCK) * PAGE_BLOCK + 1; // 진광
		endPage = startPage + 9;
		if (endPage > totalPage) {
			endPage = totalPage;
		} // if
	}
	
	
	public PagingDto(int startRow, int endRow) {
		super();
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		if(startRow < 1) {
			return;
		}
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	@Override
	public String toString() {
		return "PagingDto [startRow=" + startRow + ", endRow=" + endRow + ", startPage=" + startPage + ", endPage="
				+ endPage + ", totalPage=" + totalPage + ", perPage=" + perPage + ", nowPage=" + nowPage
				+ ", PAGE_BLOCK=" + PAGE_BLOCK + "]";
	}

	
	
}
