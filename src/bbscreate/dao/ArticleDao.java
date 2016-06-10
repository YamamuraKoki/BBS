package bbscreate.dao;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbscreate.beans.Article;
import bbscreate.beans.ArticleView;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class ArticleDao {

	public void insert(Connection connection, Article message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO articles ( ");
			sql.append("title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append("?"); //titile
			sql.append(", ?"); //text
			sql.append(", ?"); //category
			sql.append(", CURRENT_TIMESTAMP"); //insert_date
			sql.append(", CURRENT_TIMESTAMP"); //update_date
			sql.append(", ?"); //user_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, message.getTitle());
			ps.setString(2, message.getText());
			ps.setString(3, message.getCategory());
			ps.setInt(4, message.getUserId());

			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void articleDelete(Connection connection, Article message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM articles");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, message.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<ArticleView> categorySearch(Connection connection, String category) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_article");
			sql.append(" WHERE");
			sql.append(" category");
			sql.append(" LIKE");
			sql.append(" ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, category);

			ResultSet rs = ps.executeQuery();
			List<ArticleView> ret = toShowUserArticle(rs);
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<ArticleView> getBBSArticle(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_article ORDER BY insert_date DESC ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<ArticleView> ret = toShowUserArticle(rs);
			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<ArticleView> toShowUserArticle(ResultSet rs) throws SQLException {

		List<ArticleView> ret = new ArrayList<ArticleView>();
		try {
			while(rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String title = rs.getString("title");
				String category = rs.getString("category");
				String text = rs.getString("text");
				Date insertDate = rs.getTimestamp("insert_date");
				int branch = rs.getInt("branch");
				int position = rs.getInt("position");

				ArticleView message = new ArticleView();
				message.setName(name);
				message.setId(id);
				message.setUserId(userId);
				message.setTitle(title);
				message.setCategory(category);
				message.setText(text);
				message.setInsertDate(insertDate);
				message.setBranch(branch);
				message.setPosition(position);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public List<Article> getCategoryData() {

		PreparedStatement ps = null;
		try {
			String sql = "select category from bbs.user_article group by category;";

			Connection connection = getConnection();
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Article> categoryList = toCategoryList(rs);
			if (categoryList.isEmpty() == true) {
				return null;
			} else {
				return categoryList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Article> toCategoryList(ResultSet rs) throws SQLException {

		List<Article> ret = new ArrayList<Article>();
		try {
			while (rs.next()) {
				String category = rs.getString("category");

				Article categoryData = new Article();
				categoryData.setCategory(category);

				ret.add(categoryData);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<ArticleView> dayCategorySearch
	(Connection connection, String startDay, String finishDay, String category) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_article");
			sql.append(" WHERE 1 ");

			if (!StringUtils.isEmpty(category)) {
				sql.append("AND category = ? ");
			}
			if (!StringUtils.isEmpty(startDay)) {
				sql.append("AND insert_date >= ? ");
			}
			if (!StringUtils.isEmpty(finishDay)) {
				sql.append("AND insert_date <= ? ");
			}
			sql.append("ORDER BY insert_date DESC ");

			ps = connection.prepareStatement(sql.toString());

			int i = 1;
			if (!StringUtils.isEmpty(category)) {
				ps.setString(i++, category);
			}
			if (!StringUtils.isEmpty(startDay)) {
				ps.setString(i++, startDay + " " + "00:00:00");
			}
			if (!StringUtils.isEmpty(finishDay)) {
				ps.setString(i++, finishDay  + " " + "23:59:59");
			}

			ResultSet rs = ps.executeQuery();
			List<ArticleView> ret = toShowUserArticle(rs);
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<ArticleView> getFinishDayData(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT MAX(insert_date) FROM user_article ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<ArticleView> finishDayList = toFinishDayList(rs);
			if (finishDayList.isEmpty()) {
				return null;
			} else {
				return finishDayList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<ArticleView> toFinishDayList(ResultSet rs) throws SQLException {

		List<ArticleView> ret = new ArrayList<ArticleView>();
		try {
			while (rs.next()) {
				Date finishDay = rs.getDate("MAX(insert_date)");
				ArticleView finishDays = new ArticleView();
				finishDays.setInsertDate(finishDay);

				ret.add(finishDays);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<ArticleView> getStartDayData(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT MIN(insert_date) FROM user_article ORDER BY insert_date ASC ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<ArticleView> startDayList = toStartDayList(rs);
			if (startDayList.isEmpty()) {
				return null;
			} else {
				return startDayList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<ArticleView> toStartDayList(ResultSet rs) throws SQLException {

		List<ArticleView> ret = new ArrayList<ArticleView>();
		try {
			while (rs.next()) {
				Date startDay = rs.getDate("MIN(insert_date)");

				ArticleView finishDays = new ArticleView();
				finishDays.setInsertDate(startDay);

				ret.add(finishDays);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}