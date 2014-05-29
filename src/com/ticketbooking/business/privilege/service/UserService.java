package com.ticketbooking.business.privilege.service;

import com.ticketbooking.business.privilege.dao.IUserDao;
import com.ticketbooking.business.privilege.dao.UserDao;
import com.ticketbooking.domain.privilege.UserInfo;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年5月29日 下午9:25:37 
 * 
 */
public class UserService {
	
	private IUserDao userDao = UserDao.getInstance();
	
	public UserInfo queryUserInfo(Long userId) {
		return userDao.queryUserInfoByUserId(userId);
	}
}
