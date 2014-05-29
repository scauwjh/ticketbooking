package com.ticketbooking.business.privilege.servlet.outward;

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
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public LogoutServlet() {
        super();
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException {
		this.doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException {
		req.setCharacterEncoding(Constant.ENCODER);
		res.setContentType(Constant.HTML_TYPE);
		res.setCharacterEncoding(Constant.ENCODER);
		req.getSession().removeAttribute(Constant.USER);
		req.getSession().removeAttribute(Constant.USER_ID);
		req.getSession().removeAttribute(Constant.POWER);
		System.out.println("logout succeed");
		res.getWriter().println("/login.jsp?ret=0");
	}
}
