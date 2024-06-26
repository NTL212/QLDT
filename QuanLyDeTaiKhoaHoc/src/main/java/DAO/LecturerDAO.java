package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Models.Admin;
import Models.Lecturer;
import Util.JDBCUtil;
import Util.HandleException;

public class LecturerDAO {

	private static final String SELECT_LECTURER_BY_LECT_CODE = "select * from giangvien where magv = ?";
	private static final String INSERT_LECTURER = "insert into giangvien (magv, khoa, hoten, ngaysinh, cccd, dienthoai, email, gioitinh, diachi) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_LECTURE= "delete from giangvien where magv=?";
	private static final String UPDATE_LECTURER = "update giangvien set khoa = ?, hoten = ?, ngaysinh = ?, cccd = ?, dienthoai = ?, email = ?, gioitinh = ?, diachi = ? where magv = ?";

	public Lecturer selectLecturerByLectCode(String lectCode) {
		Connection connection = JDBCUtil.getConnection();
		Lecturer lecturer = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LECTURER_BY_LECT_CODE);
			preparedStatement.setString(1, lectCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("hoten");
				LocalDate birthday = rs.getDate("ngaysinh").toLocalDate();
				String address = rs.getString("diachi");
				String idNum = rs.getString("cccd");
				String phoneNum = rs.getString("dienthoai");
				String email = rs.getString("email");
				String sex = rs.getBoolean("gioitinh") ? "Nam" : "Nữ";
				String falculityCode = rs.getString("khoa");
				lecturer = new Lecturer(lectCode, name, birthday, address, idNum, phoneNum, email, sex, falculityCode);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return lecturer;
	}
	
	public void insertLecturer (Lecturer lect) {
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LECTURER);
			preparedStatement.setString(1, lect.getLecturerCode());
			preparedStatement.setString(2, lect.getFalculity().getFalculityCode());
			preparedStatement.setString(3, lect.getName());
			preparedStatement.setDate(4, Date.valueOf(lect.getBirthday()));
			preparedStatement.setString(5, lect.getIdNum());
			preparedStatement.setString(6, lect.getPhoneNum());
			preparedStatement.setString(7, lect.getEmail());
			preparedStatement.setBoolean(8, lect.getSex().equals("Nam") ? true : false);
			preparedStatement.setString(9, lect.getAddress());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
	}
	public void updateLecturer(Lecturer lect) {
	    Connection connection = JDBCUtil.getConnection();
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LECTURER);
	        preparedStatement.setString(1, lect.getFalculity().getFalculityCode());
	        preparedStatement.setString(2, lect.getName());
	        preparedStatement.setDate(3, Date.valueOf(lect.getBirthday()));
	        preparedStatement.setString(4, lect.getIdNum());
	        preparedStatement.setString(5, lect.getPhoneNum());
	        preparedStatement.setString(6, lect.getEmail());
	        preparedStatement.setBoolean(7, lect.getSex().equals("Nam"));
	        preparedStatement.setString(8, lect.getAddress());
	        preparedStatement.setString(9, lect.getLecturerCode()); // Điều kiện cập nhật dựa trên mã giảng viên
	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    JDBCUtil.closeConnection(connection);
	}

	public boolean deleteLectureById(String id) {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LECTURE);
			preparedStatement.setString(1, id);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
}












