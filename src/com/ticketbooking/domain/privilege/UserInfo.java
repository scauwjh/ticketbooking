package com.ticketbooking.domain.privilege;

import java.io.Serializable;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * UserInfo持久化类，对应数据库的p_user_info表
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private String name;
	
	private String telephone;
	
	private String address;
	
	private String IDCard;
	
	private String otherCard;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getOtherCard() {
		return otherCard;
	}

	public void setOtherCard(String otherCard) {
		this.otherCard = otherCard;
	}
}
