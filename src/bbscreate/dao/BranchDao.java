package bbscreate.dao;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbscreate.beans.User;
import exception.SQLRuntimeException;

public class BranchDao {

	public List<User> getBranchUser() {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM branches ;";

			Connection connection = getConnection();
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> branchList = toBranchList(rs);
			if (branchList.isEmpty()) {
				return null;
			} else {
				return branchList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toBranchList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("branch_name");

				User user = new User();
				user.setId(id);
				user.setName(name);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}