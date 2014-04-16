package com.ticketbooking.business.cinema.dao;

import java.util.List;

import com.ticketbooking.business.core.dao.GenericDao;
import com.ticketbooking.domain.privilege.User;
import com.ticketbooking.util.HibernateUtil;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月28日 上午8:08:08 
 * 
 *
 */
public class CinemaDao extends GenericDao implements ICinemaDao {
	
	private static class SingletonInstance {
		private static final CinemaDao instance = 
				new CinemaDao();
	}
	
	public static CinemaDao getInstance() {
		return SingletonInstance.instance;
	}

	@Override
	public User queryByUserId(String userId) {
		String hql = "from User u where u.userId = ?";
		User user = null;
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) HibernateUtil.createHQLQuery(hql, userId);
		if (list.size() > 0)
			user = list.get(0);
		return user;
	}
	
}
