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

	public List<ArticleView> searchCategory(String category) {

		Connection connection = null;
		try {
			connection = getConnection();
			ArticleDao articleDao = new ArticleDao();
			List<ArticleView> ret = articleDao.categorySearch(connection, category);
			commit(connection);

			return ret;
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

	public List<ArticleView> searchCategoryDay(String startDay, String finishDay, String category) {

		Connection connection = null;
		try {
			connection = getConnection();
			ArticleDao articleDao = new ArticleDao();
			List<ArticleView> ret = articleDao.dayCategorySearch(connection, startDay, finishDay, category);

			commit(connection);

			return ret;
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

	public List<ArticleView> searchDay(String startDay, String finishDay) {

		Connection connection = null;
		try {
			connection = getConnection();
			ArticleDao articleDao = new ArticleDao();
			List<ArticleView> ret = articleDao.daySearch(connection, startDay, finishDay);

			commit(connection);

			return ret;
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


	public List<ArticleView> getFinishDay() {

		Connection connection = null;
		try {
			connection = getConnection();

			ArticleDao finishDao = new ArticleDao();
			List<ArticleView> ret = finishDao.getFinishDayData(connection);
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

	public List<ArticleView> getStartDay() {

		Connection connection = null;
		try {
			connection = getConnection();

			ArticleDao finishDao = new ArticleDao();
			List<ArticleView> ret = finishDao.getStartDayData(connection);
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
}