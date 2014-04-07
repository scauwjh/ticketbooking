package com.ticketbooking.business.privilege.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketbooking.business.core.constant.Constant;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月30日 下午9:46:58
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException {
		this.doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException {
		req.getSession().removeAttribute(Constant.USER);
		String contextPath = req.getServletContext().getContextPath();
		System.out.println("logout succeed");
		res.getWriter().println(contextPath + "/login.jsp?ret=0");
	}
}
