package com.ticketbooking.domain.privilege;

import java.io.Serializable;

/** 
 * @author wjh E-mail: 472174314@qq.com
 */
public class User implements Serializable {

	private static final long serialVersionUID = -6573181306739082338L;
	
	private Long userId;
	
	private String password;

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
