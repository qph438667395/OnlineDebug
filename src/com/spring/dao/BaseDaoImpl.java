package com.spring.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.PageBean;
import com.spring.model.PageList;

@Repository("baseDao")
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(T t) throws Exception {
		sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public void update(T t) throws Exception {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public void delete(T t) throws Exception {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public void deleteById(Serializable id) throws Exception {
		String hql = "delete " + getEntityClassName() + " where "
				+ getPkColunmName() + "=?";
		executeUpdate(hql, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() throws Exception {
		String hql = "from " + this.getEntityClassName();
		Query createQuery = createQuery(sessionFactory.getCurrentSession(), hql);
		List<T> list = null;
		list = createQuery.list();
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageList<T> findAll(PageBean pageBean) throws Exception {
		String hql = "from " + this.getEntityClassName();
		Query createQuery = createQuery(sessionFactory.getCurrentSession(), hql);
		List<T> list = null;
		if (pageBean != null) {
			createQuery.setFirstResult(pageBean.getLimit());
			createQuery.setMaxResults(pageBean.getOffset());
			list = createQuery.list();
			String countHql = null;
			if (StringUtils.containsIgnoreCase(hql, "from")) {
				countHql = "select count(0) "
						+ StringUtils.substring(hql,
								StringUtils.indexOfIgnoreCase(hql, "from"));
			}
			Query countQuery = createQuery(sessionFactory.getCurrentSession(),
					countHql);
			List<Long> countList = countQuery.list();
			Long totalCount = 0L;
			if (countList != null && countList.size() > 0) {
				totalCount = countList.get(0);
			}
			pageBean.setTotalCount(totalCount);
			;
		} else {
			list = createQuery.list();
		}
		return new PageList<T>(list, pageBean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) throws Exception {
		return (T) sessionFactory.getCurrentSession().get(getEntityClassName(),
				id);

	}

	public int executeUpdate(final String hql, final Object... objects) {
		Query createQuery = createQuery(sessionFactory.getCurrentSession(),
				hql, objects);
		return createQuery.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByPropertys(final String hql, final Object... objects) {
				Query createQuery = createQuery(sessionFactory.getCurrentSession(), hql, objects);
				return createQuery.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public PageList<T> findbyProps(PageBean pageBean,final String hql, final Object... objects) throws Exception {
		Query createQuery = createQuery(sessionFactory.getCurrentSession(), hql,objects);
		List<T> list = null;
		if (pageBean != null) {
			createQuery.setFirstResult(pageBean.getLimit());
			createQuery.setMaxResults(pageBean.getOffset());
			list = createQuery.list();
			String countHql = null;
			if (StringUtils.containsIgnoreCase(hql, "from")) {
				countHql = "select count(0) "
						+ StringUtils.substring(hql,
								StringUtils.indexOfIgnoreCase(hql, "from"));
			}
			Query countQuery = createQuery(sessionFactory.getCurrentSession(),
					countHql,objects);
			List<Long> countList = countQuery.list();
			Long totalCount = 0L;
			if (countList != null && countList.size() > 0) {
				totalCount = countList.get(0);
			}
			pageBean.setTotalCount(totalCount);
			;
		} else {
			list = createQuery.list();
		}
		return new PageList<T>(list, pageBean);
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		// 获取超类泛型参数的实际类
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return entityClass;
	}

	// 获取主键类型
	public Type getPkType() {
		ClassMetadata meta = sessionFactory
				.getClassMetadata(getEntityClass());
		return meta.getIdentifierType();
	}

	// 获取主键名称
	public String getPkColunmName() {
		ClassMetadata meta = sessionFactory
				.getClassMetadata(getEntityClass());
		return meta.getIdentifierPropertyName();
	}

	// 获取类名
	protected String getEntityClassName() {
		ClassMetadata meta = sessionFactory
				.getClassMetadata(getEntityClass());
		return meta.getEntityName();
	}

	private Query createQuery(Session session, String hql, Object... objects) {
		System.out.println(hql);
		Query query = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query;
	}

}
