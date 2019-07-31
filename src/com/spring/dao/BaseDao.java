package com.spring.dao;

import java.util.List;

import com.spring.model.PageBean;
import com.spring.model.PageList;

public interface BaseDao<T> {
	public void add(T t) throws Exception;
	public void update(T t) throws Exception;
	public void delete(T t) throws Exception;
	public void deleteById(java.io.Serializable id) throws Exception;
	public List<T> findAll() throws Exception;
	public T findById(java.io.Serializable id) throws Exception;
	public PageList<T> findAll(PageBean pageBean) throws Exception;
	public int executeUpdate(final String hql, final Object... objects) throws Exception;
	public List<T> findByPropertys(final String hql, final Object... objects) throws Exception;
	public PageList<T> findbyProps(PageBean pageBean,final String hql, final Object... objects) throws Exception;
}
