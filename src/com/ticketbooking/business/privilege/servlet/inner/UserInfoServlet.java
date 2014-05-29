package com.ticketbooking.business.privilege.servlet.inner;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ticketbooking.business.core.constant.Constant;
import com.ticketbooking.business.privilege.service.UserService;
import com.ticketbooking.domain.privilege.UserInfo;
import com.ticketbooking.util.HibernateUtil;
import com.ticketbooking.util.JSONConfig;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/inner/userinfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private HttpServletRequest req;
	private HttpServletResponse res;
	private PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
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
		response.setCharacterEncoding(Constant.ENCODER);
		response.setContentType(Constant.HTML_TYPE);
		this.req = request;
		this.res = response;
		this.out = res.getWriter();
		// start transaction
		HibernateUtil.begin();
		try {
			String method = req.getParameter(Constant.METHOD);
			if (method.equals("userInfo")) {
				Long userId = Long.parseLong(req.getParameter("userId"));
				if (!this.queryUserInfo(userId)) {
					out.println(Constant.I_ERROR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(Constant.I_ERROR);
		} finally {
			// close transaction
			HibernateUtil.close();
		}
	}
	
	private Boolean queryUserInfo(Long userId) {
		UserInfo userInfo = userService.queryUserInfo(userId);
		if (userInfo == null) return false;
		try {
			JSONObject json = JSONObject.fromObject(userInfo, JSONConfig.getInstance());
			out.println(json);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
