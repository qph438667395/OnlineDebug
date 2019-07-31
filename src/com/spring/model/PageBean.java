package com.spring.model;

public class PageBean {
	public PageBean(){}
	
	private int page;
	
	private int pageSize;

	private Long totalCount;
	
	public PageBean(int page, int pageSize){
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getOffset(){
		return pageSize;
	}
	
	public int getLimit(){
		return page * pageSize - pageSize;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPage() {
		if(totalCount%pageSize == 0){
			return totalCount / pageSize;
		} else {
			return totalCount / pageSize +1;
		}
	}

	public Long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public static boolean isEmpty(PageBean pageBean){
		if(pageBean == null){
			return true;
		}
		return false;
	}
	
}
