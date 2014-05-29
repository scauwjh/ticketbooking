package com.ticketbooking.business.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketbooking.business.core.constant.Constant;

/**
 * @author wjh E-mail: 472174314@qq.com
 * @version 创建时间：2014年3月27日 下午7:31:39
 * 全局过滤器，登录时设置session，检查到没有session就过滤掉请求
 */
public class LoginFilter implements Filter {

	protected FilterConfig config;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String user = (String) req.getSession().getAttribute(Constant.USER);
		String contextPath = req.getServletContext().getContextPath();
		if (user == null) {
			System.out.println("no login status");
			// System.out.println(contextPath);
			res.sendRedirect(contextPath + "/login.jsp?ret=-1");
			return;
		}
		String userId = req.getParameter("userId");
		Long sessionId = (Long)req.getSession().getAttribute(Constant.USER_ID);
		if (userId != null) {
			if (!userId.equals(sessionId.toString())) {
				System.out.println("illegal request: userId is not conform");
				res.sendRedirect(contextPath + "/index.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}
}