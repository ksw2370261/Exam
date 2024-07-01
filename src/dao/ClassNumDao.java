package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassNumDao extends Dao {

	private String baseSql = "select * from class_num where class_num=?";

	public List<String> filter(String school) throws Exception {
		List<String> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String condition = "and class_num=?";
		String order = " order by no asc";

		try {
			statement = connection.prepareStatement(baseSql + condition + order);
			statement.setString(1, school);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}
}