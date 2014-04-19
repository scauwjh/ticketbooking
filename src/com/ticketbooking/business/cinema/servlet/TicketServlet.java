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

import com.ticketbooking.business.cinema.service.TicketService;
import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.util.HibernateUtil;
import com.ticketbooking.domain.ticket.Ticket;


@WebServlet("/Ticket")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private HttpSession session;
	private TicketService ticketService = new TicketService();
	
    public TicketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constant.ENCODER);
		response.setContentType(Constant.HTML_TYPE);
		response.setCharacterEncoding(Constant.ENCODER);
		out = response.getWriter();
		session = request.getSession();
		//begin transaction
		HibernateUtil.begin();
		// get method
		String method = (String) session.getAttribute(Constant.METHOD);
		// do something
		if (method.equals("save")) {
			if (ticketService.saveOrUpdateTicket(request, 0)) {
				// save success then commit
				HibernateUtil.commit();
				out.println("保存成功");
			} else {
				// save error so rollback
				HibernateUtil.rollback();
				out.println("保存失败");
			}
		} else if (method.equals("update")) {
			if (ticketService.saveOrUpdateTicket(request, 1)) {
				// update success then commit
				HibernateUtil.commit();
				out.println("更新成功");
			} else {
				// update success so rollback
				HibernateUtil.rollback();
				out.println("更新失败");
			}
		} else {
			//get parameters
			Integer start = Integer.parseInt(request.getParameter("start"));
			Integer limit = Integer.parseInt(request.getParameter("limit"));
			Long userId = (Long) request.getSession().getAttribute(Constant.USER_ID);
			List<Ticket> ticketList = ticketService.queryTicketList(userId, start, limit);
//			request.setAttribute("ticketList", ticketList);
//			String path = "/page/inner/cinema/ticketList.jsp";
//			request.getRequestDispatcher(path).forward(request, response);
			out.println(ticketList);
		}
		// close session
		HibernateUtil.close();
	}

}
