package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Major;
import Util.HandleException;
import Util.JDBCUtil;

public class MajorDAO {
	private static final String SELECT_MAJOR_BY_MAJOR_CODE = "select * from nganh where manganh =?";
	private static final String SELECT_MAJOR_BY_FALCULITY_CODE = "select * from nganh where khoa =?";

	public Major selectMajorByMajorCode(String majorCode) {
		Connection connection = JDBCUtil.getConnection();
		Major major = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAJOR_BY_MAJOR_CODE);
			preparedStatement.setString(1, majorCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("tennganh");
				String falcCode = rs.getString("khoa");
				major = new Major(majorCode, name, falcCode);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return major;
	}
	public List<Major> selectMajorByFalculityCode(String falculityCode) {
		Connection connection = JDBCUtil.getConnection();
		List<Major> majors = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAJOR_BY_FALCULITY_CODE);
			preparedStatement.setString(1, falculityCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String manganh = rs.getString("manganh");
				String tennganh = rs.getString("tennganh");
				Major major = new Major(manganh, tennganh, falculityCode);
				majors.add(major);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return majors;
	}
}
