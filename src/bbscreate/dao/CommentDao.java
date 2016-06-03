package bbscreate.dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbscreate.beans.Comment;
import bbscreate.beans.UserComment;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("text");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(", user_id");
			sql.append(", article_id");
			sql.append(") VALUES (");
			sql.append("?"); //text
			sql.append(", CURRENT_TIMESTAMP"); //insert_date
			sql.append(", CURRENT_TIMESTAMP"); //update_date
			sql.append(", ?"); //user_id
			sql.append(", ?"); //article_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getArticleId());

			System.out.println(ps);

			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<UserComment> getArticleComment(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT comments.id, users.name, comments.text, comments.insert_date, comments.article_id, comments.user_id"
					+ " FROM users INNER JOIN comments ON users.id = comments.user_id");
			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toShowUserComment(rs);
				return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toShowUserComment(ResultSet rs) throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("name");
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				int userId = rs.getInt("user_id");
				int articleId = rs.getInt("article_id");

				UserComment comment = new UserComment();
				comment.setId(id);
				comment.setUserName(userName);
				comment.setText(text);
				comment.setInsertDate(insertDate);
				comment.setUserId(userId);
				comment.setArticleId(articleId);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public void commentDelete(Connection connection, Comment comments) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comments.getId());

			System.out.println(ps);

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

	public void articleCommentDelete(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments");
			sql.append(" WHERE");
			sql.append(" article_id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id);

			System.out.println(ps);

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
}
