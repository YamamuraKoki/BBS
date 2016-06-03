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

public class PositionDao {

	public List<User> getPositionUser() {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM positions ;";

			Connection connection = getConnection();
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> positionList = toPositionList(rs);
			if (positionList.isEmpty() == true) {
				return null;
			} else {
				return positionList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toPositionList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("position_name");

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