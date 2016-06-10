package bbscreate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbscreate.beans.User;
import bbscreate.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {
		HttpSession session = request.getSession();

		request.getRequestDispatcher("login.jsp").forward(request, response);
		session.removeAttribute("loginId");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		User user = loginService.login(loginId, password);

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();


		if(user != null) {

			session.setAttribute("loginUser", user);
			response.sendRedirect("home");
		} else {

			messages.add("ログインできませんでした。再度入力してください。");
			session.setAttribute("loginId",loginId);
			session.setAttribute("Messages", messages);
			response.sendRedirect("login");
		}
	}
}