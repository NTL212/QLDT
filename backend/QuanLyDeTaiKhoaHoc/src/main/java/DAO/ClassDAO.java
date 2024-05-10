package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Class;
import Util.HandleException;
import Util.JDBCUtil;

public class ClassDAO {
	private static final String SELECT_CLASS_BY_CLASS_CODE = "select * from lop where malop =?";

	public Class selectClassByClassCode(String classCode) {
		Connection connection = JDBCUtil.getConnection();
		Class classDTO = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_CLASS_CODE);
			preparedStatement.setString(1, classCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("tenlop");
				String majorCode = rs.getString("khoa");
				classDTO = new Class(classCode, name, majorCode);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return classDTO;
	}
}
