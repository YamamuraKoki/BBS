package bbscreate.dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbscreate.beans.UserManagment;
import exception.SQLRuntimeException;

public class UserManagmentDao {

	public List<UserManagment> getUserList(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT users.id, users.login_id, users.name, branches.branch_name, positions.position_name, users.user_state "
					+ "FROM users INNER JOIN positions ON users.position_id = positions.id "
					+ "INNER JOIN branches ON users.branch_id = branches.id ORDER BY users.branch_id ASC, users.position_id ASC ");
			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserManagment> ret = toShowUserList(rs);
				return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserManagment> toShowUserList(ResultSet rs) throws SQLException {

		List<UserManagment> ret = new ArrayList<UserManagment>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String branchName = rs.getString("branch_name");
				String positionName = rs.getString("position_name");
				boolean userState = rs.getBoolean("user_state");

				UserManagment comment = new UserManagment();
				comment.setId(id);
				comment.setLoginId(loginId);
				comment.setName(name);
				comment.setBranchName(branchName);
				comment.setPositionName(positionName);
				comment.setUserState(userState);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
