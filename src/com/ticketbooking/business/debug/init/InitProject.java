package com.ticketbooking.business.debug.init;

import com.ticketbooking.util.ExecuteSQL;

/** 
 * 初始化项目，创建数据库和数据库表
 * @author wjh E-mail: 472174314@qq.com
 */
public class InitProject {
	
	public InitProject() {}
	
	private void initDatabase() {
		try {
			ExecuteSQL exec = new ExecuteSQL();
			String databaseUrl = "jdbc:mysql://localhost:3306";
			String sqlFilePath = "D:\\Codes\\Java\\eclipse\\ticketbooking"
					+ "\\src\\com\\ticketbooking\\business\\debug\\init\\initDatabase.sql";
			String user = "root";
			String password = "root";
			exec.updateDatebase(databaseUrl, sqlFilePath, user, password);
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
