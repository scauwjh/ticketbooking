package com.ticketbooking.business.cinema.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ticketbooking.business.cinema.dao.CinemaDao;
import com.ticketbooking.business.cinema.dao.ICinemaDao;
import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.domain.privilege.User;
import com.ticketbooking.domain.ticket.Ticket;
import com.ticketbooking.domain.ticket.TicketRecord;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年4月17日 下午12:14:31 
 * 
 *
 */
public class TicketService {
	private ICinemaDao cinemaDao = CinemaDao.getInstance();
	
	/**
	 * 保存或更新ticket
	 * @param req
	 * @param type 0 is save, 1 is update
	 * @return
	 */
	public Boolean saveOrUpdateTicket(HttpServletRequest req, Integer type) {
		try {
			Ticket ticket = new Ticket();
			DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT_NO_SYMBOL);
			ticket.setTicketId(Long.parseLong(df.format(new Date())));
			if (type.equals(1)) {
				String ticketId = req.getParameter("ticketId");
				ticket.setTicketId(Long.parseLong(ticketId));
				System.out.println("update ticket");
			}
			ticket.setCountry(req.getParameter("country"));
			ticket.setFilmType(req.getParameter("filmType"));
			ticket.setLanguage(req.getParameter("language"));
			String onTime = req.getParameter("onTime");
			Date date;
			df = new SimpleDateFormat(Constant.DATE_FORMAT);
			date = df.parse(onTime);
			ticket.setOnTime(date);
			ticket.setOriginalPrice(Float.parseFloat( req.getParameter("originalPrice")));
			ticket.setPrevue(req.getParameter("prevue"));
			ticket.setReleaseTime(new Date());
			ticket.setTicketImg(req.getParameter("ticketImg"));
			ticket.setTicketIntro(req.getParameter("ticketIntro"));
			ticket.setTicketName(req.getParameter("ticketName"));
			ticket.setTicketPrice(Float.parseFloat(req.getParameter("ticketPrice")));
			Long userId = (Long) req.getSession().getAttribute(Constant.USER_ID);
			ticket.setUserId(userId);
			cinemaDao.saveOrUpdate(ticket);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Ticket> queryTicketList(Long userId, 
			Integer start, Integer limit) {
		return cinemaDao.queryTicketListByUserId(userId, start, limit);
	}
	
	public List<Ticket> queryTicketList(Integer start, Integer limit) {
		return cinemaDao.queryTicketListByUserId(start, limit);
	}
	
	public Ticket queryTicket(Long ticketId, Long userId) {
		return cinemaDao.queryByTicketId(ticketId, userId);
	}
	
	public Ticket queryTicket(Long ticketId) {
		return cinemaDao.queryByTicketId(ticketId);
	}
	
	public List<TicketRecord> queryTicketRecord(Long userId, Integer start, Integer limit) {
		return cinemaDao.queryTicketRecordByUserId(userId, start, limit);
	}
	
	public Boolean deleteTicket(Long ticketId) {
		Ticket ticket = cinemaDao.queryByTicketId(ticketId);
		if (ticket == null) return false;
		cinemaDao.delete(ticket);
		return true;
	}
	
	/**
	 * 预定ticket
	 * @param userId
	 * @param ticketId
	 * @return
	 * @throws Exception
	 */
	public Boolean buyTicket(Long userId, Long ticketId) throws Exception {
		TicketRecord ticketRecord = cinemaDao.queryTicketRecordByUserId(userId, ticketId);
		if (ticketRecord != null) return false;
		ticketRecord = new TicketRecord();
		ticketRecord.setTicketId(new Ticket(ticketId));
		ticketRecord.setUserId(new User(userId));
		ticketRecord.setOrderDate(new Date());
		ticketRecord.setChecked((byte) 0);
		cinemaDao.saveOrUpdate(ticketRecord);
		return true;
	}
	
	/**
	 * 管理员查询记录
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<TicketRecord> queryTicketRecord(Integer start, Integer limit) {
		return cinemaDao.queryTicketRecord(start, limit);
	}
	
	/**
	 * checked 预订
	 * @param id
	 * @param status
	 * @return
	 */
	public Boolean checkedTicketRecord(Long id, Integer status) {
		return cinemaDao.updateTicketRecordStatus(id, status.byteValue());
	}
}
