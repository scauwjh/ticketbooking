package com.ticketbooking.domain.privilege;

import java.io.Serializable;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * Role持久化类，对应数据库的p_role表
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Byte roleId;
	
	private Byte power;
	
	private String redirection;

	
	public Byte getRoleId() {
		return roleId;
	}

	public void setRoleId(Byte roleId) {
		this.roleId = roleId;
	}

	public Byte getPower() {
		return power;
	}

	public void setPower(Byte power) {
		this.power = power;
	}

	public String getRedirection() {
		return redirection;
	}

	public void setRedirection(String redirection) {
		this.redirection = redirection;
	}
}
