package com.ticketbooking.util;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
	 * @param className
	 * @param id
	 * @return
	 */
	public static <T> Object get(Class<T> className, Serializable id) {
		return threadSession.get().get(className, id);
	}
	/**
	 * 保存或者更新对象
	 * @param o
	 */
	public static void saveOrUpdate(Object o) {
		threadSession.get().saveOrUpdate(o);
	}

}
