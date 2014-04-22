package com.ticketbooking.business.core.init;

//import com.ticketbooking.business.core.dao.GenericDao;
//import com.ticketbooking.business.core.dao.IGenericDao;
import com.ticketbooking.domain.privilege.User;
import com.ticketbooking.util.HibernateUtil;

/** 
 * 测试
 * @author wjh E-mail: 472174314@qq.com
 */
public class InitTest {
	
//	private IGenericDao genericDao = GenericDao.getInstance();
	
	
	//写入数据库超简单例子
	public static void write(String userId, String passwd){
		User temp = new User();
		temp.setUserId(userId);
		temp.setPassword(passwd);
//		HibernateUtil.begin();
//        HibernateUtil.saveOrUpdate(temp);
//        HibernateUtil.commit();
        HibernateUtil.begin();
        //User user = (User) HibernateUtil.get(User.class, 123456L);
        HibernateUtil.saveOrUpdate(temp);
//        System.out.println(user.getPassword());
        HibernateUtil.commit();
	}
	
	public static void main(String[] args) {
		write("wjh", "password");
	}
}
