package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			String url = "jdbc:mysql://localhost:3306/qlkh?useSSL=false&allowPublicKeyRetrieval=true";
//			String url = "jdbc:mysql://localhost:3306/qlkh?enabledTLSProtocols=TLSv1.2";
			String username = "root";
			String password = "88648468";

			conn = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			System.out.println("Connection error...");
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}