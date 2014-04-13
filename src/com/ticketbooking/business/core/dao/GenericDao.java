package com.ticketbooking.business.core.dao;

import java.io.Serializable;
import java.util.List;

import com.ticketbooking.util.HibernateUtil;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月27日 下午8:36:22 
 * 通用数据库操作方法
 */
public class GenericDao implements IGenericDao {

	// Google工程师BobLee发明的饱（懒）汉单例模式。解决了饱汉单例模式两大难题。
	private static class SingletonInstance {
		private static final GenericDao instance = 
				new GenericDao();
	}
	
	public static GenericDao getInstance() {
		return SingletonInstance.instance;
	}
	
	@Override
	public void saveOrUpdate(Object object) {
		HibernateUtil.saveOrUpdate(object);
	}
	
	@Override
	public void saveOrUpdate(List<Object> list) {
		for (int i = 0; i < list.size(); i++)
			HibernateUtil.saveOrUpdate(list.get(i));
	}

	@Override
	public <T> Object get(Class<T> forClass, Serializable id) {
		return HibernateUtil.get(forClass, id);
	}

	@Override
	public void delete(Object object) {
		HibernateUtil.delete(object);
	}
	
	@Override
	public void delete(List<Object> list) {
		for (int i = 0; i < list.size(); i++)
			HibernateUtil.delete(list.get(i));
	}
}
