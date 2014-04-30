package com.ticketbooking.domain.ticket;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * ticketRecord持久化类，对应数据库的t_ticket_record表(购买记录)
 */
public class TicketRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long userId;
	
	private Long ticketId;
	
	private Byte checked;// 0 is not check 1 is checked
	
	private Date orderDate;// 订购时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Byte getChecked() {
		return checked;
	}

	public void setChecked(Byte checked) {
		this.checked = checked;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}
