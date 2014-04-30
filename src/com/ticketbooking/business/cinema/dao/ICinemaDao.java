package com.ticketbooking.business.cinema.dao;

import java.util.List;

import com.ticketbooking.business.core.dao.IGenericDao;
import com.ticketbooking.domain.ticket.Ticket;
import com.ticketbooking.domain.ticket.TicketRecord;

/** 
 * 电影票维护dao
 */
public interface ICinemaDao extends IGenericDao {
	
	/**
	 * 通过ticketId查询获取一个ticket
	 * @param account
	 * @return
	 */
	public abstract Ticket queryByTicketId(Long ticketId, Long userId);
	
	/**
	 * 同上，不带userId
	 * @param ticketId
	 * @return
	 */
	public abstract Ticket queryByTicketId(Long ticketId);
	
	/**
	 * 通过userId和limit查询ticket列表
	 * @param userId
	 * @param limit 限制多少条
	 * @return 返回List<Ticket>
	 */
	public abstract List<Ticket> queryTicketListByUserId(Long userId, 
			Integer start, Integer limit);
	
	/**
	 * 同上不带userId
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<Ticket> queryTicketListByUserId(Integer start, Integer limit);
	
	/**
	 * 通过userId查询订票记录
	 * @param userId
	 * @return
	 */
	public abstract List<TicketRecord> queryTicketRecordByUserId(Long userId,
			Integer start, Integer limit);
	
	/**
	 * 通过userId和ticketId查询
	 * @param userId
	 * @param ticketId
	 * @return
	 */
	public abstract TicketRecord queryTicketRecordByUserId(Long userId, Long ticketId);
}
