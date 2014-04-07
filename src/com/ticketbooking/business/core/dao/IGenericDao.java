package com.ticketbooking.business.core.dao;

import java.io.Serializable;
import java.util.List;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月27日 下午8:31:41 
 * 通用数据库操作
 */
public interface IGenericDao {
	
	/**
	 * 根据实体时候有主键id选择保存或者更新实体
	 * general saveOrUpdate
	 * @param object
	 */
	public abstract Boolean saveOrUpdate(Object object);
	
	/**
	 * 同上，不过更新的是一个list，多个实体
	 * general saveOrUpdate
	 * @param List<Object> list
	 */
	public abstract Boolean saveOrUpdate(List<Object> list);
	
	/**
	 * 通过主键id
	 * general get
	 * @param forClass
	 * @param id
	 * @return
	 */
	public abstract <T> Object get(Class<T> forClass, Serializable id);
	
	/**
	 * 删除数据库一条记录，参数为对应的实体
	 * general delete
	 * @param object
	 * @return
	 */
	public abstract Boolean delete(Object object);
	
	/**
	 * 删除一系列记录，list
	 * general delete
	 * @param List<Object> list
	 * @return
	 */
	public abstract Boolean delete(List<Object> list);
	
}
