package com.ticketbooking.business.privilege.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.business.privilege.service.LoginService;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月30日 下午9:46:58 
 * 
 *
 */
@WebServlet("/registe")
public class Registe extends HttpServlet {

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
		String userId = req.getParameter(Constant.USER);
		String password = req.getParameter(Constant.PASSWORD); // md5 password
		Boolean flag = loginService.addUser(userId, password);
		if (flag) {
			System.out.println(userId + " registe succeed");
			out.println(Constant.I_SUCCESS);
			return;
		}
		System.out.println(userId + " registe failed");
		out.println(Constant.I_ERROR);
	}
}
