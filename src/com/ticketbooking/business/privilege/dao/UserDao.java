package com.ticketbooking.business.privilege.dao;

import java.util.List;

import com.ticketbooking.business.core.dao.GenericDao;
import com.ticketbooking.domain.privilege.User;
import com.ticketbooking.domain.privilege.UserInfo;
import com.ticketbooking.util.HibernateUtil;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月28日 上午8:08:08 
 * 
 *
 */
public class UserDao extends GenericDao implements IUserDao {
	
	private static class SingletonInstance {
		private static final UserDao instance = 
				new UserDao();
	}
	
	public static UserDao getInstance() {
		return SingletonInstance.instance;
	}

	@Override
	public User queryByUserId(String userId) {
		String hql = "from User u where u.userId = ?";
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) HibernateUtil.createHQLQuery(hql, userId);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public UserInfo queryUserInfoByUserId(Long userId) {
		String hql = "from UserInfo u where u.userId = ?";
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) HibernateUtil.createHQLQuery(hql, userId);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
}
