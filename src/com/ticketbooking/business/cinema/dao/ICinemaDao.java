package com.ticketbooking.business.cinema.dao;

import java.util.List;

import com.ticketbooking.business.core.dao.IGenericDao;
import com.ticketbooking.domain.ticket.Ticket;

/** 
 * 电影票维护dao
 */
public interface ICinemaDao extends IGenericDao {
	
	/**
	 * 通过ticketId查询获取一个ticket
	 * @param account
	 * @return
	 */
	public abstract Ticket queryByTicketId(Long TicketId);
	
	/**
	 * 通过userId和limit查询ticket列表
	 * @param userId
	 * @param limit 限制多少条
	 * @return 返回List<Ticket>
	 */
	public abstract List<Ticket> queryTicketListByUserId(Long userId, 
			Integer start, Integer limit);
}
