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
		HttpSession session = req.getSession();
		String userId = req.getParameter(Constant.USER);
		String password = req.getParameter(Constant.PASSWORD); // md5 password
		//事务开始
		HibernateUtil.begin();
		try{
			String method = req.getParameter("method");
			if (method == null) {
				User user = loginService.login(userId, password);
				if (user != null) {
					// set session
					String redirect = user.getRole().getRedirection();
					session.setAttribute(Constant.USER_ID, user.getId());
					session.setAttribute(Constant.USER, user.getUserId());
					session.setAttribute(Constant.POWER, user.getRole().getPower());
					System.out.println(userId + " login succeed");
					out.println(redirect);
				}
				else {
					System.out.println(userId + " login failed");
					out.println(Constant.LOGIN_ERROR);
				}
			}
			else if(method.equals("check")) {
				String user = (String) session.getAttribute(Constant.USER);
				if (user != null) {
					System.out.println(user + " is login");
					out.println(user);
				} else{
					System.out.println("is not login");
					out.println(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(Constant.LOGIN_ERROR);
			HibernateUtil.close();
		}
	}
}
