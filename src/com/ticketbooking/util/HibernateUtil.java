package com.ticketbooking.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/** 
 * hibernate dao层封装类
 * @author wjh E-mail: 472174314@qq.com
 * @version 2014年3月25日  11:33:01 
 */
public class HibernateUtil {
	
	private SessionFactory sessionFactory = null;
	
	
	// Google工程师BobLee发明的饱（懒）汉单例模式。解决了饱汉单例模式两大难题。
	private static class SingletonInstance {
		private static final HibernateUtil instance = 
				new HibernateUtil();
	}
	
	public static HibernateUtil getInstance() {
		return SingletonInstance.instance;
	}
	
	/**
	 * 私有的构造方法
	 * hibernate4 的 sesssionFactory 创建方法
	 */
	private HibernateUtil() {
		try {
			Configuration configure = new Configuration()
				.configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configure.getProperties())
				.buildServiceRegistry();
			this.sessionFactory = configure.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}
	
	/**
	 * 管理session的threadLocal
	 */
	private static ThreadLocal<Session> threadSession = new ThreadLocal<Session>(){
		@SuppressWarnings("static-access")
		protected Session initialValue() {
			return SingletonInstance.instance.createSession();
		}
	};
		
	/**
	 * session创建
	 * 由于是现成不安全的，不开放此方法
	 * 请使用下面的getSession
	 * @return
	 */
	private static Session createSession() {
		return SingletonInstance.instance.sessionFactory.openSession();
	}
	
	/**
	 * 获取由threadLocal管理的session
	 * 线程安全的
	 * @return
	 */
	public static Session getSession() {
		return threadSession.get();
	}
	
	
	// Transaction
	/**
	 * 事务的开始
	 */
	public static void begin() {
		if(!threadSession.get().isOpen())
			threadSession.set(createSession());
		if(!threadSession.get().getTransaction().isActive())
			threadSession.get().beginTransaction();
	}
	/**
	 * session的flush操作
	 */
	public static void flush() {
		threadSession.get().flush();
	}
	/**
	 * 事务的提交
	 */
	public static void commit() {
		threadSession.get().getTransaction().commit();
	}
	/**
	 * 事务的回滚
	 */
	public static void rollback() {
		threadSession.get().getTransaction().rollback();
	}
	/**
	 * session的close操作
	 */
	public static void close() {
		if(threadSession.get().isOpen())
			threadSession.get().close();
	}
	
	// Data Access Object
	/**
	 * 通过序列化id查询唯一对象
	 * @param forClass
	 * @param id
	 * @return
	 */
	public static <T> Object get(Class<T> forClass, Serializable id) {
		return threadSession.get().get(forClass, id);
	}
	/**
	 * 保存或者更新对象
	 * @param Object o
	 */
	public static void saveOrUpdate(Object o) {
		threadSession.get().saveOrUpdate(o);
	}
	/**
	 * HQL查询、分页查询
	 * @param hql
	 * @param params
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public static List<?> createHQLQuery(String hql, Object[] params, 
			Integer start, Integer limit) {
		Query query = threadSession.get().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (start != null) query.setFirstResult(start);
		if (limit != null) query.setMaxResults(limit);
		return query.list();
	}
	/**
	 * 简化接口
	 * @param hql
	 * @param params
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public static List<?> createHQLQuery(String hql, Object[] params,
			Integer limit) {
		return createHQLQuery(hql, params, null, limit);
	}
	/**
	 * 简化接口
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static List<?> createHQLQuery(String hql, Object[] params) {
		return createHQLQuery(hql, params, null, null);
	}
	/**
	 * 简化接口
	 * @param hql
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static List<?> createHQLQuery(String hql, Object param) {
		List<Object> list = new ArrayList<Object>(0);
		list.add(param);
		return createHQLQuery(hql, list.toArray());
	}
	/**
	 * SQL查询、分页查询
	 * @param sql
	 * @param params
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public static List<?> createSQLQuery(String sql, Object[] params, 
			Integer start, Integer limit) {
		Query query = threadSession.get().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (start != null) query.setFirstResult(start);
		if (limit != null) query.setMaxResults(limit);
		return query.list();
	}
	/**
	 * 简化接口
	 * @param sql
	 * @param params
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public static List<?> createSQLQuery(String sql, Object[] params, 
			Integer limit) {
		return createSQLQuery(sql, params, null, limit);
	}
	/**
	 * 简化接口
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static List<?> createSQLQuery(String sql, Object[] params) {
		return createSQLQuery(sql, params, null, null);
	}
	/**
	 * delete
	 * @param object
	 * @throws Exception
	 */
	public static void delete(Object object) {
		threadSession.get().delete(object);
	}
	/**
	 * 条件查询、分页查询
	 * @param DetachedCriteria dc
	 * @param Integer start
	 * @param Integer limit
	 * @return
	 */
	public static List<?> queryByDetachedCriteria(DetachedCriteria dc, 
			Integer start, Integer limit) {
		Session sess = threadSession.get();
		Criteria c = dc.getExecutableCriteria(sess);
		if (start != null) c.setFirstResult(start);
		if (limit != null) c.setMaxResults(limit);
		return c.list();
	}
	/**
	 * 条件查询、分页查询
	 * @param DetachedCriteria dc
	 * @param Integer limit
	 * @return
	 */
	public static List<?> queryByDetachedCriteria(DetachedCriteria dc,
			Integer limit) {
		return queryByDetachedCriteria(dc, null, limit);
	}
	/**
	 * 条件查询
	 * @param DetachedCriteria dc
	 * @return
	 */
	public static List<?> queryByDetachedCriteria(DetachedCriteria dc) {
		return queryByDetachedCriteria(dc, null, null);
	}
}
