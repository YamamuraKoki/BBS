package bbscreate.service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbscreate.beans.UserManagment;
import bbscreate.dao.UserManagmentDao;

public class UserManagmentService {

	public List<UserManagment> getManagmentList() {

		 Connection connection = null;
		 try {
			 connection = getConnection();

			 UserManagmentDao userManagmentDao = new UserManagmentDao();
			 List<UserManagment> ret = userManagmentDao.getUserList(connection);

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

	public List<UserManagment> getManagmentData(int userId) {

		 Connection connection = null;
		 try {
			 connection = getConnection();

			 UserManagmentDao userManagmentDao = new UserManagmentDao();
			 List<UserManagment> ret = userManagmentDao.getUserList(connection);

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