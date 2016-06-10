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

import bbscreate.beans.Comment;
import bbscreate.beans.User;
import bbscreate.service.CommentService;

@WebServlet(urlPatterns = { "/comments" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException {
			HttpSession session = request.getSession();

			List<String> messages = new ArrayList<String>();

			Comment articleCommentData = getArticleCommentData(request);

			if(isValid(request, messages)) {

				Comment commnets = new Comment();
				User user = (User) session.getAttribute("loginUser");
				commnets.setText(request.getParameter("text"));
				commnets.setUserId(user.getId());
				commnets.setArticleId(Integer.valueOf(request.getParameter("articleId")));


				new CommentService().register(commnets);
				messages.add("コメントは正常に行われました");
				session.setAttribute("trueMessages", messages);
				response.sendRedirect("home");
			} else {
				session.setAttribute("articleCommentData", articleCommentData);
				session.setAttribute("Messages", messages);
				response.sendRedirect("home");
			}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String text = request.getParameter("text");
//		System.out.println(text);

		if(StringUtils.isEmpty(text)) {
			messages.add("コメントを入力してください");
		}
		if(500 < text.length()) {
			messages.add("本文は500文字以内で書いてください");
		}
		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	private Comment getArticleCommentData(HttpServletRequest request)
			throws IOException, ServletException {

		Comment articleCommentData = new Comment();

		articleCommentData.setText(request.getParameter("text"));

		return articleCommentData;
	}
}