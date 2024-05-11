package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Models.Admin;
import Models.Project;
import Util.HandleException;
import Util.JDBCUtil;

public class AdminDAO {

	private static final String SELECT_AD_BY_AD_CODE = "select * from admin where masoadmin = ?";
	private static final String INSERT_ADMIN = "insert into admin (masoadmin, hoten, ngaysinh, cccd, dienthoai, email, gioitinh, diachi) values (?, ?, ?, ?, ?, ?, ?, ?)";

	public Admin selectByAdCode(String adCode) {
		Connection connection = JDBCUtil.getConnection();
		Admin ad = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AD_BY_AD_CODE);
			preparedStatement.setString(1, adCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("hoten");
				LocalDate birthday = rs.getDate("ngaysinh").toLocalDate();
				String address = rs.getString("diachi");
				String idNum = rs.getString("cccd");
				String phoneNum = rs.getString("dienthoai");
				String email = rs.getString("email");
				String sex = rs.getBoolean("gioitinh") ? "Nam" : "Nữ";
				ad = new Admin(adCode, name, birthday, idNum, phoneNum, email, sex, address);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return ad;
	}
	
	public void insertAdmin (Admin ad) {
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN);
			preparedStatement.setString(1, ad.getAdCode());
			preparedStatement.setString(2, ad.getName());
			preparedStatement.setDate(3, Date.valueOf(ad.getBirthday()));
			preparedStatement.setString(4, ad.getIdNum());
			preparedStatement.setString(5, ad.getPhoneNum());
			preparedStatement.setString(6, ad.getEmail());
			preparedStatement.setBoolean(7, ad.getSex().equals("Nam") ? true : false);
			preparedStatement.setString(8, ad.getAddress());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
	}
}
