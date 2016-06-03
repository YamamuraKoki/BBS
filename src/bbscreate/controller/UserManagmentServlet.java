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
import bbscreate.beans.UserManagment;
import bbscreate.service.UserManagmentService;
import bbscreate.service.UserService;

@WebServlet(urlPatterns = { "/managment" })
public class UserManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {


		List<UserManagment> lists = new UserManagmentService().getManagmentList();

		request.setAttribute("lists", lists);

		request.getRequestDispatcher("/managment.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		User user = new User();
		user.setId(Integer.valueOf(request.getParameter("id")));
		if(Boolean.valueOf(request.getParameter("userState")) == false) {
		user.setUserState(true);
		}

		if(Boolean.valueOf(request.getParameter("userState")) == true) {
			user.setUserState(false);
		}

		new UserService().userState(user);

		messages.add("変更は正常に行われました");
		session.setAttribute("Messages", messages);
		response.sendRedirect("managment");
	}
}