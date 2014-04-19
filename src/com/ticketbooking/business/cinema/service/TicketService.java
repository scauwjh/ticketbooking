package com.ticketbooking.business.cinema.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ticketbooking.business.cinema.dao.CinemaDao;
import com.ticketbooking.business.cinema.dao.ICinemaDao;
import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.business.privilege.dao.IUserDao;
import com.ticketbooking.business.privilege.dao.UserDao;
import com.ticketbooking.domain.privilege.User;
import com.ticketbooking.domain.ticket.Ticket;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年4月17日 下午12:14:31 
 * 
 *
 */
public class TicketService {
	private ICinemaDao cinemaDao = CinemaDao.getInstance();
	private IUserDao userDao = UserDao.getInstance();
	
	/**
	 * 保存或更新ticket
	 * @param req
	 * @param type 0 is save, 1 is update
	 * @return
	 */
	public Boolean saveOrUpdateTicket(HttpServletRequest req, Integer type) {
		try {
			Ticket ticket = new Ticket();
			if (type.equals(0)) {
				String ticketId = (String) req.getAttribute("ticketId");
				ticket.setTicketId(Long.parseLong(ticketId));
			}
			ticket.setCountry((String) req.getAttribute("country"));
			ticket.setFilmType((String) req.getAttribute("filmType"));
			ticket.setLanguage((String) req.getAttribute("language"));
			String onTime = (String) req.getAttribute("onTime");
			Date date;
			DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT);
			date = df.parse(onTime);
			ticket.setOnTime(date);
			ticket.setOriginalPrice(Float.parseFloat((String) req.getAttribute("originalPrice")));
			ticket.setPrevue((String) req.getAttribute("prevue"));
			String releaseTime = (String) req.getAttribute("releaseTime");
			date = df.parse(releaseTime);
			ticket.setReleaseTime(date);
			ticket.setTicketImg((String) req.getAttribute("ticketImg"));
			ticket.setTicketIntro((String) req.getAttribute("ticketIntro"));
			ticket.setTicketName((String) req.getAttribute("ticketName"));
			ticket.setTicketPrice(Float.parseFloat((String) req.getAttribute("ticketPrice")));
			String userId = (String) req.getAttribute("userId");
			User user = userDao.queryByUserId(userId);
			ticket.setUserId(user.getId());
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
}
