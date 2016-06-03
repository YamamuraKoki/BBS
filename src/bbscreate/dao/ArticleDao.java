package bbscreate.dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

	public List<ArticleView> getBBSArticle(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_article ");

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
				Timestamp insertDate = rs.getTimestamp("insert_date");

				ArticleView message = new ArticleView();
				message.setName(name);
				message.setId(id);
				message.setUserId(userId);
				message.setTitle(title);
				message.setCategory(category);
				message.setText(text);
				message.setInsertDate(insertDate);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
