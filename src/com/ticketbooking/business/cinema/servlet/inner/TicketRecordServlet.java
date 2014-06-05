package com.ticketbooking.business.cinema.servlet.inner;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.ticketbooking.business.cinema.service.TicketService;
import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.domain.ticket.TicketRecord;
import com.ticketbooking.util.HibernateUtil;
import com.ticketbooking.util.JSONConfig;

/**
 * Servlet implementation class TicketRecordServlet
 */
@WebServlet("/admin/ticketrecord")
public class TicketRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TicketService ticketService = new TicketService();
	private HttpServletRequest req;
	private HttpServletResponse res;
	private PrintWriter out;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		response.setContentType(Constant.HTML_TYPE);
		response.setCharacterEncoding(Constant.ENCODER);
		this.req = request;
		this.res = response;
		this.out = res.getWriter();
		// begin transaction
		HibernateUtil.begin();
		try {
			String method = req.getParameter(Constant.METHOD);
			if (method == null) return;
			else if (method.equals("query")) {
				Integer start = Integer.valueOf(req.getParameter("start"));
				Integer limit = Integer.valueOf(req.getParameter("limit"));
				List<TicketRecord> list = ticketService.queryTicketRecord(start, limit);
				JSONArray json = JSONArray.fromObject(list, JSONConfig.getInstance());
				out.println(json);
			}
			else if (method.equals("checked")) {
				Long id = Long.valueOf(req.getParameter("id"));
				Integer status = Integer.valueOf(req.getParameter("status"));
				if (ticketService.checkedTicketRecord(id, status)) {
					out.println("处理成功");
				}
				else out.println("处理失败");
			}
			HibernateUtil.commit();
		} catch(Exception e) {
			e.printStackTrace();
			HibernateUtil.rollback();
			System.out.println("roll back");
		} finally {
			HibernateUtil.close();
			System.out.println("close session");
		}
	}

}
