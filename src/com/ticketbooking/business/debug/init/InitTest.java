package com.ticketbooking.business.debug.init;

import com.ticketbooking.domain.privilege.User;
import com.ticketbooking.util.HibernateUtil;

/** 
 * 测试
 * @author wjh E-mail: 472174314@qq.com
 */
public class InitTest {
	
	public static void write(Long userId, String passwd){
		User temp = new User();
		temp.setUserId(userId);
		temp.setPassword(passwd);
		HibernateUtil.begin();
        HibernateUtil.saveOrUpdate(temp);
        HibernateUtil.commit();
        HibernateUtil.begin();
        User user = (User) HibernateUtil.get(User.class, 123456L);
        System.out.println(user.getPassword());
        HibernateUtil.commit();
	}
	
	public static void main(String[] args) {
		write(123456L, "fuck");
	}
}
