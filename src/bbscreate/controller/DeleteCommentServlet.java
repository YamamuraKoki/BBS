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

import bbscreate.beans.Comment;
import bbscreate.service.CommentService;

@WebServlet(urlPatterns = { "/deleteComment" })
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		System.out.println(Integer.valueOf(request.getParameter("id")));
		Comment comments = new Comment();
		comments.setId(Integer.valueOf(request.getParameter("id")));
		new CommentService().CommentDelete(comments);

		messages.add("削除は正常に行われました");
		session.setAttribute("trueMessages", messages);

		response.sendRedirect("home");
	}
}