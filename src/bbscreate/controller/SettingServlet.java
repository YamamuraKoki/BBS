package bbscreate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

@WebServlet(urlPatterns = { "/setting" })
@MultipartConfig(maxFileSize = 100000)
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<User> branchs = new ArrayList<User>();
		BranchDao bbsBranchDao = new BranchDao();
		branchs = bbsBranchDao.getBranchUser();
		session.setAttribute("branchs", branchs);

		List<User> positioned = new ArrayList<User>();
		PositionDao bbsPositionDao = new PositionDao();
		positioned = bbsPositionDao.getPositionUser();
		session.setAttribute("positioned", positioned);

		if(session.getAttribute("editUser") == null) {
			User editUser = new UserService().getUser(Integer.valueOf(request.getParameter("id")));
			request.setAttribute("editUser", editUser);
		}

		request.getRequestDispatcher("setting.jsp").forward(request, response);
		session.removeAttribute("editUser");
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		List<User> branchs = new ArrayList<User>();
		BranchDao bbsBranchDao = new BranchDao();
		branchs = bbsBranchDao.getBranchUser();
		session.setAttribute("branchs", branchs);

		List<User> positioned = new ArrayList<User>();
		PositionDao bbsPositionDao = new PositionDao();
		positioned = bbsPositionDao.getPositionUser();
		session.setAttribute("positioned", positioned);

		User editUser = getEditUser(request);
		session.setAttribute("editUser", editUser);

		if (isValid(request, messages)) {


			session.setAttribute("editUser", editUser);
			messages.add("編集は正常に行われました");
			session.setAttribute("trueManagmentMessages", messages);
			new UserService().update(editUser);
			response.sendRedirect("managment");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("setting");
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		User editUser = new User();
		User loginUser = (User) session.getAttribute("loginUser");
		session.setAttribute("loginUser", loginUser);


		System.out.println(loginUser.getBranch());
		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		if((Integer.valueOf(request.getParameter("id"))) == loginUser.getId()) {
			editUser.setBranch(loginUser.getBranch());
			editUser.setPosition(loginUser.getPosition());
		} else {
			editUser.setBranch(Integer.valueOf(request.getParameter("branch")));
			editUser.setPosition(Integer.valueOf(request.getParameter("position")));
		}
		editUser.setId(Integer.valueOf(request.getParameter("id")));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		session.setAttribute("loginUser", loginUser);

		String loginId = request.getParameter("loginId");
		int id = Integer.valueOf(request.getParameter("id"));
		UserService userCheck = new UserService();
		User user = userCheck.checkUser(loginId, id);
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String checkPassword = request.getParameter("checkPassword");

		if(StringUtils.isEmpty(loginId)) {
			messages.add("ログインIDが入力されていません");
		}
		if(StringUtils.isEmpty(name)) {
			messages.add("名前が入力されていません");
		}
		if(user != null){
			messages.add("このログインIDは既に使用されています");
		}
		if(!loginId.matches("^[0-9a-zA-Z]{6,20}$")) {
			messages.add("ログインIDは半角英数字のみで6文字以上20文字以下で入力してください");
		}

		if(!StringUtils.isEmpty(password)) {
			if(!password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}$")) {
				messages.add("パスワードは半角文字のみで6文字以上255文字以下で入力してください");
			}
			if(!password.equals(checkPassword)) {
				messages.add("パスワードが一致しません");
			}
		}

		if(name.length() > 10) {
			messages.add("名前は10文字以内で入力してください");
		}

		if((Integer.valueOf(request.getParameter("id"))) != loginUser.getId()){
			int branch = Integer.valueOf(request.getParameter("branch"));
			int position = Integer.valueOf(request.getParameter("position"));

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
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
