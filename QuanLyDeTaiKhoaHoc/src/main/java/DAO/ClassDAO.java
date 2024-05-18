package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Class;
import Models.Major;
import Util.HandleException;
import Util.JDBCUtil;

public class ClassDAO {
	private static final String SELECT_CLASS_BY_CLASS_CODE = "select * from lop where malop =?";
	private static final String SELECT_CLASS_BY_MAJOR_CODE = "select * from lop where manganh = ?";

	public Class selectClassByClassCode(String classCode) {
		Connection connection = JDBCUtil.getConnection();
		Class classDTO = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_CLASS_CODE);
			preparedStatement.setString(1, classCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("tenlop");
				String majorCode = rs.getString("manganh");
				classDTO = new Class(classCode, name, majorCode);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return classDTO;
	}
	public List<Class> selectClassByMajorCode(String majorCode) {
		Connection connection = JDBCUtil.getConnection();
		List<Class> classes = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_MAJOR_CODE);
			preparedStatement.setString(1, majorCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String malop = rs.getString("malop");
				String tenlop = rs.getString("tenlop");
				Class class1 = new Class(malop, tenlop, majorCode);
				classes.add(class1);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return classes;
	}
}