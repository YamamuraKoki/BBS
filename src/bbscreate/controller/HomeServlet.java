package bbscreate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		List<UserComment> comments = new CommentService().getComment();
		List<String> messages = new ArrayList<String>();

		ArticleDao categoryDao = new ArticleDao();
		List<Article> categoryData = categoryDao.getCategoryData();
		request.setAttribute("categoryData", categoryData);

		ArticleService startService = new ArticleService();
		List<ArticleView> startSearch = startService.getStartDay();
		request.setAttribute("startDay", startSearch.get(0).getInsertDate());

		ArticleService finishService = new ArticleService();
		List<ArticleView> finishSearch = finishService.getFinishDay();
		request.setAttribute("finishDay", finishSearch.get(0).getInsertDate());

		String category = request.getParameter("categories");
		String startDay = request.getParameter("startDay");
		String finishDay = request.getParameter("finishDay");

		ArticleService service = new ArticleService();
		List<ArticleView> articles = service.searchCategoryDay(startDay, finishDay, category);

		if(isValid(request, messages)) {
			request.setAttribute("articles", articles);
			request.setAttribute("comments", comments);

		} else {
			request.setAttribute("Messages", messages);
			request.setAttribute("articles", articles);
			request.setAttribute("comments", comments);
		}
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String startDay = request.getParameter("startDay");
		String finishDay = request.getParameter("finishDay");

		if(StringUtils.isEmpty(startDay) && !StringUtils.isEmpty(finishDay)) {
			messages.add("開始日時を選択してください。");
			messages.add("全件表示します。");
		}

		if(!StringUtils.isEmpty(startDay) && StringUtils.isEmpty(finishDay)) {
			messages.add("終了日時を選択してください");
			messages.add("全件表示します。");
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
