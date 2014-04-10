package com.ticketbooking.business.privilege.service;

import java.util.Date;

import com.ticketbooking.business.privilege.dao.IUserDao;
import com.ticketbooking.business.privilege.dao.UserDao;
import com.ticketbooking.domain.privilege.User;
import com.ticketbooking.util.SHA1;
import com.ticketbooking.util.StringUtil;

/**
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月27日 下午9:46:25
 */
public class LoginService {

	private IUserDao userDao = UserDao.getInstance();

	/**
	 * add user
	 * 
	 * @param userId
	 * @param md5Password
	 * @return
	 */
	public Boolean addUser(String userId, String md5Password) {
		User user = userDao.queryByUserId(userId);
		if (user == null) {
			user = new User();
			user.setUserId(userId);
			user.setCreateDate(new Date());
			String token = StringUtil.randString(32);
			String saltPass = addSalt(md5Password, token); // md5密码加盐再写入数据库
			user.setPassword(saltPass);
			user.setToken(token);
			userDao.saveOrUpdate(user);
			System.out.println("add user succeed");
			return true;
		}
		System.out.println("exists user");
		return false;
	}

	/**
	 * update password
	 * @param userId
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	public Boolean updatePassword(String userId, String oldPass, String newPass) {
		User user = userDao.queryByUserId(userId);
		if (user == null) {
			System.out.println("no such user");
			return false;
		}
		String saltPass = this.addSalt(oldPass, user.getToken());
		if (!user.getPassword().equals(saltPass)) {
			System.out.println("wrong old passwrod");
			return false;
		}
		String token = StringUtil.randString(32);
		saltPass = this.addSalt(newPass, token);
		user.setPassword(saltPass);
		user.setToken(token);
		userDao.saveOrUpdate(user);
		System.out.println("update succeed");
		return true;
	}

	/**
	 * login service
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public User login(String userId, String md5Pass) {
		User user = userDao.queryByUserId(userId);
		if (user == null) {
			System.out.println("no such user");
			return null;
		}
		// 密码加盐匹配
		String saltPass = this.addSalt(md5Pass, user.getToken());
		if (user.getPassword().equals(saltPass))
			return user;
		System.out.println("wrong password");
		return null;
	}

	/**
	 * md5密码加盐
	 * 
	 * @param md5Pass
	 * @param token
	 * @return
	 */
	public String addSalt(String md5Pass, String token) {
		String secretKey = "";
		Integer point = 0;// 插入的位置
		try {
			// 由密码的最后一位定义初始的point
			point = Integer.parseInt(md5Pass.substring(md5Pass.length() - 1));
		} catch (Exception e) {
			// 非数字parseInt出错，modKey为默认的0
			System.out.println("password is not end with number, set point as 0");
		}
		// 加盐，将盐按顺序一个个插入到密码中去
		StringBuffer sb = new StringBuffer(md5Pass);
		for (int i = 0; i < token.length(); i++) {
			point++;// 防止point为0
			point = (point + point * point) % md5Pass.length();// 加盐位置计算公式
			sb.insert(point, token.substring(i, i + 1));// 将盐插入到密码中
		}
		secretKey = SHA1.SHA(sb.toString()).toLowerCase();// SHA-1散列并转成小写
		return secretKey;
	}

	/* main for test */
	public static void main(String[] args) {
		LoginService login = new LoginService();
		String md5Pass = "JSDJP3V0EVPKOJ8WFXG0IIZKMOHCT2AN";// StringUtil.randString(32);
		String token = "JSDJP3V0EVPKOJ8WFXG0IIZKMOHCT2AN";// StringUtil.randString(32);
		// String ret = "3196ebf2376cf6b6b0628fb94abdf8b406fc227d";
		System.out.println("pass: " + md5Pass);
		System.out.println("token: " + token);
		String saltPass = login.addSalt(md5Pass, token);
		System.out.println("saltPass: " + saltPass);
		System.out.println("saltPass length: " + saltPass.length());
	}

}
