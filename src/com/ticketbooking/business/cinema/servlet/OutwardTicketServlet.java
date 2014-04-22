package com.ticketbooking.business.cinema.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ticketbooking.business.cinema.service.TicketService;
import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.util.HibernateUtil;
import com.ticketbooking.domain.ticket.Ticket;


@WebServlet("/outward/ticket")
public class OutwardTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TicketService ticketService = new TicketService();
	
    public OutwardTicketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constant.ENCODER);
		response.setContentType(Constant.HTML_TYPE);
		response.setCharacterEncoding(Constant.ENCODER);
		//begin transaction
		HibernateUtil.begin();
		try {
			// get method
			String method = (String) request.getParameter(Constant.METHOD);
			System.out.println(method);
			// do something
			if (method == null) {
				//get parameters
				Integer start = 0;
				Integer limit = 12;
				List<Ticket> ticketList = ticketService.queryTicketList(start, limit);
				request.setAttribute("ticketList", ticketList);			
				String path = "/outward/ticketlist.jsp";
				request.getRequestDispatcher(path).forward(request, response);
			} else if (method.equals("query")) {
				//get parameters
				Long ticketId = Long.parseLong(request.getParameter("ticketId"));
				Ticket ticket = ticketService.queryTicket(ticketId);
				JSONObject json = JSONObject.fromObject(ticket);
				json.element("onTime", ticket.getOnTime().toString());
				json.element("releaseTime", ticket.getReleaseTime().toString());
				response.getWriter().println(json.toString());
			} else if (method.equals("queryList")){
				//get parameters
				Integer start = Integer.parseInt(request.getParameter("start"));
				Integer limit = Integer.parseInt(request.getParameter("limit"));
				List<Ticket> ticketList = ticketService.queryTicketList(start, limit);
				request.setAttribute("ticketList", ticketList);			
				String path = "/outward/ticketlist.jsp";
				request.getRequestDispatcher(path).forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			// close session
			System.out.println("close session");
			HibernateUtil.close();
		}
	}

}
