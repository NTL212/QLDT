package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Models.Admin;
import Models.ManagementStaff;
import Util.HandleException;
import Util.JDBCUtil;

public class ManagementStaffDAO {

	private static final String SELECT_MGT_STAFF_BY_STAFF_CODE = "select * from nhanvienqlkh where manv = ?";
	private static final String INSERT_MANAGEMENT_STAFF = "insert into nhanvienqlkh (manv, hoten, ngaysinh, cccd, dienthoai, email, gioitinh, diachi) values (?, ?, ?, ?, ?, ?, ?, ?)";

	public ManagementStaff selectByEmpCode(String empCode) {
		Connection connection = JDBCUtil.getConnection();
		ManagementStaff emp = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MGT_STAFF_BY_STAFF_CODE);
			preparedStatement.setString(1, empCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("hoten");
				LocalDate birthday = rs.getDate("ngaysinh").toLocalDate();
				String address = rs.getString("diachi");
				String idNum = rs.getString("cccd");
				String phoneNum = rs.getString("dienthoai");
				String email = rs.getString("email");
				String sex = rs.getBoolean("gioitinh") ? "Nam" : "Nữ";
				emp = new ManagementStaff(empCode, name, birthday, idNum, phoneNum, email, sex, address);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return emp;
	}
	
	public void insertManagementStaff (ManagementStaff ms) {
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MANAGEMENT_STAFF);
			preparedStatement.setString(1, ms.getEmpCode());
			preparedStatement.setString(2, ms.getName());
			preparedStatement.setDate(3, Date.valueOf(ms.getBirthday()));
			preparedStatement.setString(4, ms.getIdNum());
			preparedStatement.setString(5, ms.getPhoneNum());
			preparedStatement.setString(6, ms.getEmail());
			preparedStatement.setBoolean(7, ms.getSex().equals("Nam") ? true : false);
			preparedStatement.setString(8, ms.getAddress());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
	}
}
