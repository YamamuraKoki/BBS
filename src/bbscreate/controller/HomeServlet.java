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
import bbscreate.beans.ArticleView;
import bbscreate.beans.UserComment;
import bbscreate.dao.ArticleDao;
import bbscreate.service.ArticleService;
import bbscreate.service.CommentService;

@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {
		HttpSession session = request.getSession();

		List<ArticleView> articles = new ArticleService().getMessage();
		List<UserComment> comments = new CommentService().getComment();
		List<String> messages = new ArrayList<String>();

		List<Article> categoryData = new ArrayList<Article>();
		ArticleDao articleDao = new ArticleDao();
		categoryData = articleDao.getCategoryData();
		session.setAttribute("categoryData", categoryData);

		String category = (request.getParameter("categories"));
		String startTime = (request.getParameter("startDays"));
		String finishTime = (request.getParameter("finishDays"));

		request.setAttribute("articles", articles);

		if(StringUtils.isEmpty(finishTime) == true) {
			messages.add("終了の日付が選択されていません");
		}

		if(StringUtils.isEmpty(startTime) == false && StringUtils.isEmpty(finishTime) == false) {
			List<ArticleView> searchTime = new ArticleService().searchTime(startTime, finishTime);
			System.out.println(startTime + "," +finishTime);
			request.setAttribute("articles", searchTime);
		}

		if(StringUtils.isEmpty(category) == false) {
			List<ArticleView> searchCategory = new ArticleService().searchCategory(category);
			request.setAttribute("articles", searchCategory);
		}

		request.setAttribute("comments", comments);


		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}