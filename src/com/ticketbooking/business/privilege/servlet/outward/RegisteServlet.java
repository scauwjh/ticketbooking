package com.ticketbooking.business.privilege.servlet.outward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.business.privilege.service.LoginService;
import com.ticketbooking.util.HibernateUtil;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月30日 下午9:46:58 
 * 
 *
 */
@WebServlet("/registe")
public class RegisteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LoginService loginService = new LoginService();
	private HttpServletRequest request;
	private PrintWriter out;
	
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
		request = req;
		out = res.getWriter();
		String method = req.getParameter(Constant.METHOD);
		//事务开始
		HibernateUtil.begin();
		try{
			if (method.equals(Constant.REGISTE)) {
				if (this.registe()) {
					HibernateUtil.commit();
					out.println(Constant.I_SUCCESS);
					System.out.println("registe succeed");
				} else {
					HibernateUtil.rollback();
					out.println(Constant.I_ERROR);
					System.out.println("registe failed");
				}
			}
			else this.checkUserId();
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.close();
		}
	}
	
	private Boolean registe() {
		try {
			String account = request.getParameter(Constant.USER_ID);
			String password = request.getParameter(Constant.PASSWORD); // md5 password
			String name = request.getParameter("name");
			String telephone = request.getParameter("telephone");
			String address = request.getParameter("address");
			String IDCard = request.getParameter("IDCard");
			String otherCard = request.getParameter("otherCard");
			Boolean flag = loginService.addUser(account, password, (byte) 2);
			if (flag) {
				loginService.saveUserInfo(account, name, telephone, address, IDCard, otherCard);
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	// 检查userId是否可用
	private void checkUserId() {
		String account = request.getParameter(Constant.USER_ID);
		if (loginService.checkAccount(account)){
			out.println(Constant.CHECK_OK);
			return;
		}
		out.println(Constant.CHECK_FAILED);
	}
}
