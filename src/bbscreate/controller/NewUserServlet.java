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

import org.apache.commons.lang.StringUtils;

import bbscreate.beans.User;
import bbscreate.dao.BranchDao;
import bbscreate.dao.PositionDao;
import bbscreate.service.UserService;

@WebServlet(urlPatterns = { "/newuser" })
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {

		HttpSession session = request.getSession();

		List<User> branchs = new ArrayList<User>();
		BranchDao bbsBranchDao = new BranchDao();
		branchs = bbsBranchDao.getBranchUser();
		session.setAttribute("branchs", branchs);

		List<User> positioned = new ArrayList<User>();
		PositionDao bbsPositionDao = new PositionDao();
		positioned = bbsPositionDao.getPositionUser();
		session.setAttribute("positioned", positioned);


		request.getRequestDispatcher("newuser.jsp").forward(request, response);
		session.removeAttribute("editUser");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		User editUser = getEditUser(request);

		if(isValid(request, messages)) {

			new UserService().register(editUser);
			session.removeAttribute("editUser");
			messages.add("新規登録は正常に行われました");
			session.setAttribute("Messages", messages);
			response.sendRedirect("managment");
		} else {
			session.setAttribute("editUser", editUser);
			session.setAttribute("Messages", messages);
			response.sendRedirect("newuser");
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		User editUser = new User();
		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranch(Integer.valueOf(request.getParameter("branch")));
		editUser.setPosition(Integer.valueOf(request.getParameter("position")));
		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		UserService userCheck = new UserService();
		User user = userCheck.checkUserId(loginId);
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String checkPassword = request.getParameter("checkPassword");
		int branch = Integer.valueOf(request.getParameter("branch"));
		int position = Integer.valueOf(request.getParameter("position"));

		if(StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDが入力されていません");
		}
		if(StringUtils.isEmpty(password) == true) {
			messages.add("パスワードが入力されていません");
		}
		if(StringUtils.isEmpty(name) == true) {
			messages.add("名前が入力されていません");
		}
		if(!loginId.matches("^[0-9a-zA-Z]{6,20}$")) {
			messages.add("ログインIDは半角英数字のみで6文字以上20文字以下で入力してください");
		}
		if(user != null)  {
			messages.add("このログインIDは既に使用されています");
		}
		if(!password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}$")) {
			messages.add("パスワードは半角文字のみで6文字以上255文字以下で入力してください");
		}
		if(name.length() > 10) {
			messages.add("名前は10文字以内で入力してください");
		}
		if(password.equals(checkPassword) == false) {
			messages.add("パスワードが一致しません");
		}
		if(branch == 1 &&(position != 1)&&(position !=2)) {
			messages.add("本社で登録できる役職は「情報管理部」か「人事総務部」のみです");
		}
		if(branch == 2 &&(position != 3)&&(position !=4)) {
			messages.add("支店Aで登録できる役職は「支店長」か「社員」のみです");
		}
		if(branch == 3 &&(position != 3)&&(position !=4)) {
			messages.add("支店Bで登録できる役職は「支店長」か「社員」のみです");
		}
		if(branch == 4 &&(position != 3)&&(position !=4)) {
			messages.add("支店Cで登録できる役職は「支店長」か「社員」のみです");
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}