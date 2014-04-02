package com.ticketbooking.business.core.dao;

import java.io.Serializable;
import java.util.List;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月27日 下午8:31:41 
 */
public interface IGeneralDao {
	
	/**
	 * general saveOrUpdate
	 * @param object
	 */
	public abstract Boolean saveOrUpdate(Object object);
	
	/**
	 * general saveOrUpdate
	 * @param List<Object> list
	 */
	public abstract Boolean saveOrUpdate(List<Object> list);
	
	/**
	 * general get
	 * @param forClass
	 * @param id
	 * @return
	 */
	public abstract <T> Object get(Class<T> forClass, Serializable id);
	
	/**
	 * general delete
	 * @param object
	 * @return
	 */
	public abstract Boolean delete(Object object);
	
	/**
	 * general delete
	 * @param List<Object> list
	 * @return
	 */
	public abstract Boolean delete(List<Object> list);
	
}
