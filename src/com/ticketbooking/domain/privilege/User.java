package com.ticketbooking.domain.privilege;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * User持久化类，对应数据库的p_user表
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String account;
	
	private String password;
	
	private String token;
	
	private Role role;
	
	private Date createDate;

	
	public User() {}
	
	public User(Long userId) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
