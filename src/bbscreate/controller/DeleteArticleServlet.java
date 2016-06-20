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

import bbscreate.beans.Article;
import bbscreate.service.ArticleService;
import bbscreate.service.CommentService;

@WebServlet(urlPatterns = { "/deleteArticle" })
public class DeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		Article message = new Article();
		message.setId(Integer.valueOf(request.getParameter("id")));
		new ArticleService().ArticleDelete(message);
		new CommentService().ArticleCommentDelete(message.getId());

		messages.add("削除は正常に行われました");
		session.setAttribute("trueHomeMessages", messages);

		response.sendRedirect("home");
	}
}