package com.ticketbooking.business.debug.init;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ticketbooking.business.privilege.service.LoginService;
import com.ticketbooking.util.ExecuteSQL;
import com.ticketbooking.util.MD5;
import com.ticketbooking.util.StringUtil;

/** 
 * 初始化项目，创建数据库和数据库表
 * @author wjh E-mail: 472174314@qq.com
 */
public class InitProject {
	
	private LoginService loginService = new LoginService();
	
	public InitProject() {}
	
	public void initDatabase() {
		try {
			ExecuteSQL exec = new ExecuteSQL();
			String databaseUrl = "jdbc:mysql://localhost:3306";
			String sqlFilePath = System.getProperty("user.dir");
			sqlFilePath = sqlFilePath.replace("\\", "/");
			if (!sqlFilePath.endsWith("/")) sqlFilePath += "/";
			sqlFilePath += "src/com/ticketbooking/business/debug/init/initDatabase.sql";
			String user = "root";
			String password = "root";
			exec.exceSQLFile(databaseUrl, sqlFilePath, user, password);
			// insert root account
			String sql = "insert into `p_user` value(1, 'root', '[PASSWORD]', '[TOKEN]', 1, '[DATE]')";
			String token = StringUtil.randString(32);
			String pass = loginService.addSalt(MD5.getMD5("root"), token);
			// need to add salt(token)
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:MM:dd");
			String date = sf.format(new Date());
			sql = sql.replace("[PASSWORD]", pass)
					.replace("[TOKEN]", token)
					.replace("[DATE]", date);
			// System.out.println(sql);
			databaseUrl += "/ticketbooking";
			exec.execSQL(sql, databaseUrl, user, password);
			System.out.println("init successed!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("init failed!");
		}
	}
	
	public static void main(String[] args) {
		InitProject init = new InitProject();
		init.initDatabase();
	}
}
