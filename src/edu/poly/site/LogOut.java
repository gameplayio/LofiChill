package edu.poly.site;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class LogOut extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CookieUtils.add("username", null, 0, response);
		SessionUtils.invalidate(request);
		request.setAttribute("isLogin", false);
		PageInfo.prepareAndForward(request, response, PageType.SITE_LOGIN_PAGE);
	}

}
