package bbscreate.service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbscreate.beans.Article;
import bbscreate.beans.ArticleView;
import bbscreate.dao.ArticleDao;

public class ArticleService {
	public void register(Article message) {

		Connection connection = null;
		try {
			connection = getConnection();

			ArticleDao  messageDao = new ArticleDao();
			messageDao.insert(connection, message);

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

	public List<ArticleView> getMessage() {

		 Connection connection = null;
		 try {
			 connection = getConnection();

			 ArticleDao messageDao = new ArticleDao();
			 List<ArticleView> ret = messageDao.getBBSArticle(connection);

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

	public void ArticleDelete(Article message) {

		Connection connection = null;
		try {
			connection = getConnection();
			ArticleDao articleDao = new ArticleDao();
				articleDao.articleDelete(connection, message);

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