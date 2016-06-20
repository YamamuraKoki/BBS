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

import bbscreate.beans.Article;
import bbscreate.beans.User;
import bbscreate.service.ArticleService;

@WebServlet(urlPatterns = { "/article" })
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {
		HttpSession session = request.getSession();
		request.getRequestDispatcher("article.jsp").forward(request, response);
		session.removeAttribute("articleData");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {
		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		Article articleData = getArticleData(request);

		if(isValid(request, messages)) {

			Article message = new Article();
			User user = (User) session.getAttribute("loginUser");
			message.setTitle(request.getParameter("title"));
			message.setCategory(request.getParameter("category"));
			message.setText(request.getParameter("text"));
			message.setUserId(user.getId());

			new ArticleService().register(message);
			messages.add("投稿は正常に行われました");
			session.setAttribute("trueHomeMessages", messages);
			response.sendRedirect("home");
		} else {
			session.setAttribute("articleData", articleData);
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("article");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String text = request.getParameter("text");

		if(StringUtils.isEmpty(title)) {
			messages.add("件名を入力してください");
		}
		if(title.length() > 50) {
			messages.add("件名は50文字以内で入力してください");
		}
		if(StringUtils.isEmpty(category)) {
			messages.add("カテゴリー名を入力してください");
		}
		if(category.length() > 10) {
			messages.add("カテゴリー名は10文字以内で入力してください");
		}
		if(StringUtils.isEmpty(text)) {
			messages.add("本文を入力してください");
		}
		if(1000 < text.length()) {
			messages.add("本文は1000文字以内で入力してください");
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	private Article getArticleData(HttpServletRequest request)
			throws IOException, ServletException {

		Article articleData = new Article();

		articleData.setTitle(request.getParameter("title"));
		articleData.setCategory(request.getParameter("category"));
		articleData.setText(request.getParameter("text"));

		return articleData;
	}
}