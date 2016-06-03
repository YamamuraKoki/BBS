package bbscreate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbscreate.beans.ArticleView;
import bbscreate.beans.UserComment;
import bbscreate.service.ArticleService;
import bbscreate.service.CommentService;

@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
ServletException {


		List<ArticleView> messages = new ArticleService().getMessage();
		List<UserComment> comments = new CommentService().getComment();

		request.setAttribute("messages", messages);
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}