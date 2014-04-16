package com.ticketbooking.business.cinema.dao;

import com.ticketbooking.business.core.dao.IGenericDao;
import com.ticketbooking.domain.privilege.User;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月28日 上午8:05:55 
 * 
 *
 */
public interface ICinemaDao extends IGenericDao {
	
	/**
	 * 通过userId查询获取一个user
	 * @param account
	 * @return
	 */
	public abstract User queryByUserId(String userId);
}
