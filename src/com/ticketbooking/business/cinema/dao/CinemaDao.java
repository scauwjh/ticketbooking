package com.ticketbooking.business.cinema.dao;

import java.util.List;

import com.ticketbooking.business.core.dao.GenericDao;
import com.ticketbooking.domain.ticket.Ticket;
import com.ticketbooking.domain.ticket.TicketRecord;
import com.ticketbooking.util.HibernateUtil;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月28日 上午8:08:08 
 * 
 *
 */
public class CinemaDao extends GenericDao implements ICinemaDao {
	
	// 单例设计模式
	private static class SingletonInstance {
		private static final CinemaDao instance = 
				new CinemaDao();
	}
	// 获取实例
	public static CinemaDao getInstance() {
		return SingletonInstance.instance;
	}

	@Override
	public Ticket queryByTicketId(Long ticketId, Long userId) {
		String hql = "from Ticket t where t.ticketId = ? and t.userId = ?";
		Ticket ticket = null;
		@SuppressWarnings("unchecked")
		List<Ticket> list = (List<Ticket>) HibernateUtil.createHQLQuery(hql, ticketId, userId);
		if (list.size() > 0)
			ticket = list.get(0);
		return ticket;
	}
	
	@Override
	public Ticket queryByTicketId(Long ticketId) {
		String hql = "from Ticket t where t.ticketId = ?";
		Ticket ticket = null;
		@SuppressWarnings("unchecked")
		List<Ticket> list = (List<Ticket>) HibernateUtil.createHQLQuery(hql, ticketId);
		if (list.size() > 0)
			ticket = list.get(0);
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> queryTicketListByUserId(Long userId, 
			Integer start, Integer limit) {
		String hql = "from Ticket t where t.userId = ?";
		return (List<Ticket>) HibernateUtil.createHQLQuery(hql,
				userId, start, limit);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> queryTicketListByUserId(Integer start, Integer limit) {
		String hql = "from Ticket t";
		return (List<Ticket>) HibernateUtil.createHQLQuery(hql, null, start, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketRecord> queryTicketRecordByUserId(Long userId, Integer start, Integer limit) {
		String hql = "from TicketRecord tr where tr.userId = ?";
		return (List<TicketRecord>) HibernateUtil.createHQLQuery(hql, userId, start, limit);
	}

	@Override
	public TicketRecord queryTicketRecordByUserId(Long userId,
			Long ticketId) {
		String hql = "from TicketRecord tr where tr.userId = ? and ticketId = ?";
		@SuppressWarnings("unchecked")
		List<TicketRecord> list = (List<TicketRecord>) HibernateUtil.createHQLQuery(hql, userId, ticketId);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
}
