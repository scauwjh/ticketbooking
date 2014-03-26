package com.ticketbooking.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * 说明：直接用java application运行就可以了
 */
public class ExecuteSQL {
	
	public ExecuteSQL() {}
	
	
	/**
	 * 执行sql
	 * @param path
	 * @param user
	 * @param password
	 */
	public void updateDatebase(String databaseUrl, String sqlFilePath, String user, String password){
		File sqlFile = new File(sqlFilePath);
		BufferedReader br = null;
		Connection con = null;
		Statement sta = null;
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			con = DriverManager.getConnection(databaseUrl, user, password);
			sta = con.createStatement();
			// 读取文件
			br = new BufferedReader(new FileReader(sqlFile));
			StringBuffer sb = new StringBuffer();
			String str = null;
			while((str = br.readLine()) != null) {
				sb.append(str);
				if (str.endsWith(";")) {
					sta.addBatch(sb.toString());
					sb.delete(0, sb.length());
				}
			}
			sta.executeBatch();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}	
		catch (Exception e) {
			e.printStackTrace();
			try {
				if(!con.isClosed())
					con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			br = null;
			con = null;
			sta = null;
		}
	}
	
	public static void main(String[] args) {
		ExecuteSQL exec = new ExecuteSQL();
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入数据库备份文件（*.sql）的绝对路径：");
		String path = scanner.next();
		// System.out.println("输入数据库帐号：");
		String user = "root";//scanner.next();
		// System.out.println("输入密码：");
		String password = "root";//scanner.next();
		String url = "jdbc:mysql://localhost:3306/ticketbooking";
		exec.updateDatebase(url, path, user, password);
		scanner.close();
	}
}

