package com.ticketbooking.util;
/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年4月1日 下午1:38:55 
 * 
 *
 */
public class StringUtil {
	
	public static String randString(Integer length) {
		String base = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			Integer rand = (int) (Math.random() * base.length());
			sb.append(base.charAt(rand));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(randString(32));
	}
}
