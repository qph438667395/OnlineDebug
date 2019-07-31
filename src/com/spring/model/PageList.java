package com.spring.model;

import java.util.List;

public class PageList<T> implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	private List<T> resultList;
	
	private PageBean pageBean;

	public PageList(List<T> resultList, PageBean pageBean){
		this.resultList = resultList;
		this.pageBean = pageBean;
	}
	
	
	public PageList(){}
	
	public T get(int index){
		return resultList.get(index);
	}
	
	public boolean isEmpty(){
		if(this.resultList == null || this.resultList.isEmpty()){
			return true;
		}
		return false;
	}

	public List<T> getResultList() {
		return resultList;
	}
	
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
	public PageBean getPageBean() {
		return pageBean;
	}
	
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
}
