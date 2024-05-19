package DAO;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.Account;
import Models.Project;
import Models.Admin;
import Models.ManagementStaff;
import Models.Lecturer;
import Models.ProjectMember;
import Util.HandleException;
import Util.JDBCUtil;

public class AccountDAO {

	private static final String AUTH_ACCOUNT = "select * from user where username = ? and pass = ? ";
	private static final String SELECT_ACCOUNT_BY_USERNAME = "select * from user where username = ?";
	private static final String INSERT_ACCOUNT = "insert into user (username, pass, role) values (?, ?, ?)";
	private static final String SELECT_ALL_ACCOUNT = "select * from user";
	private static final String EDIT_ACCOUNT_BY_USERNAME = "UPDATE user set pass = ?, role = ? where username = ?";
	private static final String DELETE_ACCOUNT_BY_USERNAME = "delete from user where username = ?";
	private static final String SELECT_ACCOUNT_BY_ROLE = "SELECT * FROM user WHERE role = ?";

	public Account validate(Account loginData) throws ClassNotFoundException {
		Account acc = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(AUTH_ACCOUNT);
			preparedStatement.setString(1, loginData.getUsername());
			preparedStatement.setString(2, loginData.getPassword());

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String role = rs.getString("role");
				acc = new Account(loginData.getUsername(), loginData.getPassword(), role);
			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		JDBCUtil.closeConnection(conn);
		return acc;
	}

	public List<Account> selectAllAccount() {
		List<Account> accountList = new ArrayList<Account>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_ACCOUNT);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String role = rs.getString("role");
				Account acc = new Account(username, pass, role);
				accountList.add(acc);
			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		JDBCUtil.closeConnection(conn);
		return accountList;
	}

	public Account selectByUsername(String username) {
		Account acc = null;
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ACCOUNT_BY_USERNAME);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String role = rs.getString("role");
				acc = new Account(username, null, role);
			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		JDBCUtil.closeConnection(conn);
		return acc;
	}

	public List<Account> selectByAdmin(String role) {
		List<Account> accountList = new ArrayList<>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ACCOUNT_BY_ROLE);
			preparedStatement.setString(1, role);
			if (role.equals("ROLE_ADMIN")) {
				preparedStatement = conn.prepareStatement("SELECT * FROM admin");
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("masoadmin");
				String email = rs.getString("email");
				String name = rs.getString("hoten");
				Account acc = new Account(id, email, name);
				accountList.add(acc);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(conn);
		return accountList;
	}

	public List<Account> selectByLecturer(String role) {
		List<Account> accountList = new ArrayList<>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ACCOUNT_BY_ROLE);
			preparedStatement.setString(1, role);
			if (role.equals("ROLE_LECT")) {
				preparedStatement = conn.prepareStatement("SELECT * FROM giangvien");
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("magv");
				String email = rs.getString("email");
				String name = rs.getString("hoten");
				Account acc = new Account(id, email, name);
				accountList.add(acc);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(conn);
		return accountList;
	}

	public List<Account> selectByManager(String role) {
		List<Account> accountList = new ArrayList<>();
		Connection conn = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ACCOUNT_BY_ROLE);
			preparedStatement.setString(1, role);
			if (role.equals("ROLE_MGT_STAFF")) {
				preparedStatement = conn.prepareStatement("SELECT * FROM nhanvienqlkh");
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("manv");
				String email = rs.getString("email");
				String name = rs.getString("hoten");
				Account acc = new Account(id, email, name);
				accountList.add(acc);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(conn);
		return accountList;
	}

	public void insertAccount(Account acc) {
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
			preparedStatement.setString(1, acc.getUsername());
			preparedStatement.setString(2, acc.getPassword());
			preparedStatement.setString(3, acc.getRole());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
	}

	public boolean editAccountByUsername(Account acc) {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(EDIT_ACCOUNT_BY_USERNAME);
			preparedStatement.setString(1, acc.getPassword());
			preparedStatement.setString(2, acc.getRole());
			preparedStatement.setString(3, acc.getUsername());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}

	public boolean deleteAccountByUsername(Account acc) throws SQLException {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_BY_USERNAME);
			preparedStatement.setString(1, acc.getUsername());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
	public Account selectAccountByUsername(String Username) {
		Connection connection = JDBCUtil.getConnection();
		Account account = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
			preparedStatement.setString(1, Username);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String role = rs.getString("role");
				account = new Account(username, pass, role);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return account;
	}
}