package com.ticketbooking.domain.privilege;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * User持久化类，对应数据库的p_user表
 */
public class User implements Serializable {

	private static final long serialVersionUID = -6573181306739082338L;
	
	private String userId;
	
	private String password;
	
	private String token;
	
	private String redirection;
	
	private Date createDate;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRedirection() {
		return redirection;
	}

	public void setRedirection(String redirection) {
		this.redirection = redirection;
	}
}
