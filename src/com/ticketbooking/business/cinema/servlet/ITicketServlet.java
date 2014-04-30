package com.ticketbooking.business.cinema.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ticketbooking.business.cinema.service.TicketService;
import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.util.HibernateUtil;
import com.ticketbooking.util.JSONConfig;
import com.ticketbooking.domain.ticket.Ticket;

/**
 * outward ticket servlet
 * 
 * @author wjh
 */
@WebServlet("/inner/ticket")
public class ITicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private HttpSession session;
	private TicketService ticketService = new TicketService();

	public ITicketServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constant.ENCODER);
		response.setContentType(Constant.HTML_TYPE);
		response.setCharacterEncoding(Constant.ENCODER);
		out = response.getWriter();
		session = request.getSession();
		// begin transaction
		HibernateUtil.begin();
		try {
			// get method
			String method = (String) request.getParameter(Constant.METHOD);
			System.out.println(method);
			// do something
			if (method == null || method.equals("queryList")) {
				// get parameters
				String tmp = request.getParameter("start");
				Integer start = null;
				if (tmp == null)
					start = 0;
				else
					start = Integer.parseInt(tmp);
				Integer limit = null;
				tmp = request.getParameter("limit");
				if (tmp == null)
					limit = 0;
				else
					limit = Integer.parseInt(tmp);
				Long userId = (Long) session.getAttribute(Constant.USER_ID);
				System.out.println(userId + "," + start + "," + limit);
				List<Ticket> ticketList = ticketService.queryTicketList(userId,
						start, limit);
				JSONArray json = JSONArray.fromObject(ticketList, JSONConfig.getInstance());
				out.println(json);
			} else if (method.equals("saveOrUpdaet")) {
				String ticketId = request.getParameter("ticketId");
				Integer type = ticketId.equals("0") ? 0 : 1;
				if (ticketService.saveOrUpdateTicket(request, type)) {
					// save success then commit
					HibernateUtil.commit();
					out.println("保存成功");
				} else {
					// save error so rollback
					HibernateUtil.rollback();
					out.println("保存失败");
				}
			} else if (method.equals("query")) {
				// get parameters
				Long ticketId = Long
						.parseLong(request.getParameter("ticketId"));
				Long userId = (Long) session.getAttribute(Constant.USER_ID);
				Ticket ticket = ticketService.queryTicket(ticketId, userId);
				JSONObject json = JSONObject.fromObject(ticket, JSONConfig.getInstance());
				out.println(json);
			} else if(method.equals("delete")) {
				Long ticketId = Long
						.parseLong(request.getParameter("ticketId"));
				Boolean flag = ticketService.deleteTicket(ticketId);
				if (flag) out.println("删除成功");
				else out.println("删除失败");
				HibernateUtil.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close session
			System.out.println("close session");
			HibernateUtil.close();
		}
	}

}
