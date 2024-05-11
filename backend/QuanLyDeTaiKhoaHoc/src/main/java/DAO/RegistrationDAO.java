package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Models.AcceptanceCouncil;
import Models.Project;
import Util.HandleException;
import Util.JDBCUtil;
import java.util.*;
import Models.Registration;

public class RegistrationDAO {
	private static final String SELECT_ALL_REGIS = "select * from dangkydetai";
	private static final String SELECT_APPROVE_REGIS = "select * from dangkydetai where tinhtrang is null";
	private static final String APPROVE_REGIS = "Update dangkydetai set tinhtrang = 1 , nguoiduyet = ? where machunhiem = ? and madetai = ?";
	private static final String DISAGREE_REGIS = "Update dangkydetai set tinhtrang = 0 , nguoiduyet = ? where machunhiem = ? and madetai = ?";
	private static final String INSERT_REG = "insert into dangkydetai(machunhiem, madetai) values (?, ?)";
	private static final String SELECT_ONE_REGI = "select * from dangkydetai where machunhiem = ? and madetai = ?";

	public List <Registration> selectAllRegis() {
		List<Registration> lstRe = new ArrayList<Registration>();
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REGIS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Registration Re = null;
				String lecture = rs.getString("machunhiem");
				String projectCode = rs.getString("madetai");
				LocalDate regisDate = rs.getDate("ngaydangky") != null ? rs.getDate("ngaydangky").toLocalDate() : null;
				LocalDate approveDate = rs.getDate("ngayduyet") != null ? rs.getDate("ngayduyet").toLocalDate() : null;
				boolean status = rs.getBoolean("tinhtrang");
				String approvePerson = rs.getString("nguoiduyet");
				Re = new Registration(lecture, projectCode, regisDate, approveDate, status, approvePerson);
				lstRe.add(Re);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return lstRe;
	}
	
	public List<Registration> selectApproveRegis() {
		List<Registration> lstRe = new ArrayList<Registration>();
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPROVE_REGIS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Registration Re = null;
				String lecture = rs.getString("machunhiem");
				String projectCode = rs.getString("madetai");
				LocalDate regisDate = rs.getDate("ngaydangky") != null ? rs.getDate("ngaydangky").toLocalDate() : null;
				LocalDate approveDate = rs.getDate("ngayduyet") != null ? rs.getDate("ngayduyet").toLocalDate() : null;
				boolean status = rs.getBoolean("tinhtrang");
				String approvePerson = rs.getString("nguoiduyet");
				Re = new Registration(lecture,projectCode, regisDate, approveDate, status, approvePerson);
				lstRe.add(Re);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return lstRe;
	}
	
	public boolean approveProject(String lecCode, String prCode, String MgCode)
	{
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_REGIS);
			preparedStatement.setString(1, MgCode);
			preparedStatement.setString(2, lecCode);
			preparedStatement.setString(3, prCode);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
	
	public boolean disagreeProject(String lecCode, String prCode, String MgCode)
	{
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DISAGREE_REGIS);
			preparedStatement.setString(1, MgCode);
			preparedStatement.setString(2, lecCode);
			preparedStatement.setString(3, prCode);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
	
	public boolean insertRegestration(Registration reg) {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REG);
			preparedStatement.setString(1, reg.getLect().getLecturerCode());
			preparedStatement.setString(2, reg.getProj().getProjectCode());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
	
	public Registration selectOneRegistration(String lectCode, String projCode) {
		Connection connection = JDBCUtil.getConnection();
		Registration Re = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_REGI);
			preparedStatement.setString(1, lectCode);
			preparedStatement.setString(2, projCode);
			System.out.print(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String lecture = rs.getString("machunhiem");
				String projectCode = rs.getString("madetai");	
				LocalDate regisDate = rs.getDate("ngaydangky") != null ? rs.getDate("ngaydangky").toLocalDate() : null;
				LocalDate approveDate = rs.getDate("ngayduyet") != null ? rs.getDate("ngayduyet").toLocalDate() : null;
				Boolean status = rs.getBoolean("tinhtrang");
				if (rs.wasNull()) {
					status = null;
				}
				String approvePerson = rs.getString("nguoiduyet");
				Re = new Registration(lecture,projectCode, regisDate, approveDate, status, approvePerson);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		System.out.println(Re.getLect().getLecturerCode());
		return Re;
	}
}