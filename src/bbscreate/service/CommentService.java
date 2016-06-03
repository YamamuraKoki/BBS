package bbscreate.service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbscreate.beans.Comment;
import bbscreate.beans.UserComment;
import bbscreate.dao.CommentDao;

public class CommentService {
	public void register(Comment comments) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao  commentDao = new CommentDao();
			commentDao.insert(connection, comments);

			commit(connection);
		} catch(RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<UserComment> getComment() {

		 Connection connection = null;
		 try {
			 connection = getConnection();

			 CommentDao commentDao = new CommentDao();
			 List<UserComment> ret = commentDao.getArticleComment(connection);

			 commit(connection);

			 return ret;
		 } catch(RuntimeException e) {
			 rollback(connection);
			 throw e;
		 } catch(Error e) {
			 rollback(connection);
			 throw e;
		 } finally {
			 close(connection);
		 }
	}

	public void CommentDelete(Comment comments) {

		Connection connection = null;
		try {
			connection = getConnection();
			CommentDao commentDao = new CommentDao();
				commentDao.commentDelete(connection, comments);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void ArticleCommentDelete(int id) {

		Connection connection = null;
		try {
			connection = getConnection();
			CommentDao commentDao = new CommentDao();
				commentDao.articleCommentDelete(connection, id);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}