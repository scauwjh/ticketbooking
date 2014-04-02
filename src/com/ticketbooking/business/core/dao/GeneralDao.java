package com.ticketbooking.business.core.dao;

import java.io.Serializable;
import java.util.List;

import com.ticketbooking.util.HibernateUtil;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月27日 下午8:36:22 
 */
public class GeneralDao implements IGeneralDao {

	// Google工程师BobLee发明的饱（懒）汉单例模式。解决了饱汉单例模式两大难题。
	private static class SingletonInstance {
		private static final GeneralDao instance = 
				new GeneralDao();
	}
	
	public static GeneralDao getInstance() {
		return SingletonInstance.instance;
	}
	
	@Override
	public Boolean saveOrUpdate(Object object) {
		Boolean flag = false;
		HibernateUtil.begin();
		try {
			HibernateUtil.saveOrUpdate(object);
			HibernateUtil.commit();
			flag = true;
		} catch (Exception e) {
			HibernateUtil.rollback();
			e.printStackTrace();
			flag = false;
		}
		HibernateUtil.close();
		return flag;
	}
	
	@Override
	public Boolean saveOrUpdate(List<Object> list) {
		Boolean flag = false;
		HibernateUtil.begin();
		try {
			for (int i = 0; i < list.size(); i++)
				HibernateUtil.saveOrUpdate(list.get(i));
			HibernateUtil.commit();
			flag = true;
		} catch (Exception e) {
			HibernateUtil.rollback();
			e.printStackTrace();
			flag = false;
		}
		HibernateUtil.close();
		return flag;
	}

	@Override
	public <T> Object get(Class<T> forClass, Serializable id) {
		HibernateUtil.begin();
		Object object = null;
		try {
			object = HibernateUtil.get(forClass, id);
			HibernateUtil.commit();
		} catch (Exception e) {
			HibernateUtil.rollback();
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Boolean delete(Object object) {
		Boolean flag = false;
		HibernateUtil.begin();
		try {
			HibernateUtil.delete(object);
			HibernateUtil.commit();
			flag = true;
		} catch (Exception e) {
			HibernateUtil.rollback();
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	@Override
	public Boolean delete(List<Object> list) {
		Boolean flag = false;
		HibernateUtil.begin();
		try {
			for (int i = 0; i < list.size(); i++)
				HibernateUtil.delete(list.get(i));
			HibernateUtil.commit();
			flag = true;
		} catch (Exception e) {
			HibernateUtil.rollback();
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
