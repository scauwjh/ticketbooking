package com.ticketbooking.business.cinema.servlet.inner;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ticketbooking.business.cinema.service.TicketService;
import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.domain.ticket.Ticket;
import com.ticketbooking.domain.ticket.TicketRecord;
import com.ticketbooking.util.HibernateUtil;
import com.ticketbooking.util.JSONConfig;

/**
 * Servlet implementation class BuyTicketServlet
 */
@WebServlet("/inner/buyticket")
public class BuyTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TicketService ticketService = new TicketService();
	private HttpServletRequest req;
	private HttpServletResponse res;
	private PrintWriter out;
	private Long userId;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyTicketServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constant.ENCODER);
		response.setCharacterEncoding(Constant.ENCODER);
		response.setContentType(Constant.HTML_TYPE);
		req = request;
		res = response;
		out = res.getWriter();
		userId = (Long) req.getSession().getAttribute(Constant.USER_ID);
		// begin transaction
		HibernateUtil.begin();
		try {
			String method = request.getParameter("method");
			if (method == null || method.equals("buy")) {
				this.buyTicket();
			} 
			else if(method.equals("query")) {
				this.queryTicketRecord();
			}
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollback();
			System.out.println("transaction rollback!");
		} finally {
			HibernateUtil.close();
			System.out.println("transaction close!");
		}
	}
	
	private void buyTicket() throws Exception{
		if (userId == null) return;
		Long ticketId = Long.valueOf(req.getParameter("ticketId"));
		String message = null;
		if (ticketService.buyTicket(userId, ticketId)) {
			HibernateUtil.commit();
			message = "订购成功";
		} else {
			HibernateUtil.rollback();
			message = "订购失败，请确认是否已经订购";
		}
		out.println(message);
	}
	
	private void queryTicketRecord() {
		if (userId == null) return;
		Integer start = Integer.valueOf(req.getParameter("start"));
		Integer limit = Integer.valueOf(req.getParameter("limit"));
		List<TicketRecord> ticketRecord = ticketService.queryTicketRecord(userId, start, limit);
		// commit transaction
		HibernateUtil.commit();
		// 重新开始事务
		HibernateUtil.begin();
		List<Ticket> ticket = new ArrayList<Ticket>();
		for (int i = 0; i < ticketRecord.size(); i++) {
			Ticket tmp = ticketService.queryTicket(ticketRecord.get(i).getTicketId().getTicketId());
			ticket.add(tmp);
		}
		//commit transaction
		HibernateUtil.commit();
		if (ticketRecord == null || ticketRecord.size() <= 0) { 
			out.println("没有购买记录");
			return;
		}
		JSONArray j1 = JSONArray.fromObject(ticketRecord, JSONConfig.getInstance());
		JSONArray j2 = JSONArray.fromObject(ticket, JSONConfig.getInstance());
		JSONObject json = new JSONObject();
		json.put("ticketRecord", j1);
		json.put("ticket", j2);
		out.println(json);
	}

}
