package com.ticketbooking.business.privilege.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.business.privilege.service.LoginService;
import com.ticketbooking.domain.privilege.User;
import com.ticketbooking.util.HibernateUtil;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月30日 下午9:46:58 
 * 
 *
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LoginService loginService = new LoginService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException {
		this.doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException {
		req.setCharacterEncoding(Constant.ENCODER);
		res.setCharacterEncoding(Constant.ENCODER);
		res.setContentType(Constant.HTML_TYPE);
		PrintWriter out = res.getWriter();
		HttpSession sessiong = req.getSession();
		String userId = req.getParameter(Constant.USER);
		String password = req.getParameter(Constant.PASSWORD); // md5 password
		//事务开始
		HibernateUtil.begin();
		User user = loginService.login(userId, password);
		if (user != null) {
			// set session
			String redirect = user.getRole().getRedirection();
			sessiong.setAttribute(Constant.USER, user.getUserId());
			sessiong.setAttribute(Constant.POWER, user.getRole().getPower());
			System.out.println(userId + " login succeed");
			out.println(redirect);
			HibernateUtil.close();
			return;
		}
		System.out.println(userId + " login failed");
		out.println(Constant.LOGIN_ERROR);
		HibernateUtil.close();
	}
}
