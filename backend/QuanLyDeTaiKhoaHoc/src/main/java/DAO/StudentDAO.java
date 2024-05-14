package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import Models.Student;
import Util.HandleException;
import Util.JDBCUtil;

public class StudentDAO {
	private static final String SELECT_STUDENT_BY_STUDENT_CODE = "select * from sinhiven where mssv =?";

	public Student selectStudentByStudentCode(String studentCode) {
		Connection connection = JDBCUtil.getConnection();
		Student student = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_STUDENT_CODE);
			preparedStatement.setString(1, studentCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("ten");
				LocalDate birthday = rs.getDate("ngaysinh").toLocalDate();
				String address = rs.getString("diachi");
				String idNum = rs.getString("cccd");
				String phoneNum = rs.getString("phoneNum");
				String email = rs.getString("email");
				String sex = rs.getBoolean("gioitinh") ? "Nam" : "Nữ";
				String classCode = rs.getString("malop");
				student = new Student(name, birthday, address, idNum, phoneNum, email, sex, studentCode, classCode);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return student;
	}
}