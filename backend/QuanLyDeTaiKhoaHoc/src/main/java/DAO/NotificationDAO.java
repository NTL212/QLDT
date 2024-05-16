package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import Models.Notification;
import Models.Topic;
import Util.HandleException;
import Util.JDBCUtil;

public class NotificationDAO {
	private static final String SELECT_SENT_NOTIFICATIONS_BY_USER_ID = "select * from thongbao where nguoigui = ?";
	private static final String SELECT_RECEIVED_NOTIFICATIONS_BY_USER_ID = "select * from thongbao where nguoinhan = ?";
	private static final String INSERT_NOTIFICATION = "insert into thongbao (nguoigui, nguoinhan, tieude, noidung) values (?, ?, ?, ?)";

	public List<Notification> selectSentNotificationsByUserId(String userId) {
		Connection connection = JDBCUtil.getConnection();
		List<Notification> lstNots = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SENT_NOTIFICATIONS_BY_USER_ID);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String receiverId = rs.getString("nguoinhan");
				String title = rs.getString("tieude");
				String content = rs.getString("noidung");
				LocalDateTime sentTime = rs.getTimestamp("thoigiangui").toLocalDateTime();
				Notification not = new Notification(id, userId, receiverId, title, content, sentTime);
				lstNots.add(not);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return lstNots;
	}

	public List<Notification> selectNotificationsByUserId(String userId) {
		Connection connection = JDBCUtil.getConnection();
		List<Notification> lstNots = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECEIVED_NOTIFICATIONS_BY_USER_ID);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String senderId = rs.getString("nguoigui");
				String title = rs.getString("tieude");
				String content = rs.getString("noidung");
				LocalDateTime sentTime = rs.getTimestamp("thoigiangui").toLocalDateTime();
				Notification not = new Notification(id, senderId, userId, title, content, sentTime);
				lstNots.add(not);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return lstNots;
	}

	public void insertNotification(Notification noti) throws SQLException {
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTIFICATION);
			preparedStatement.setString(1, noti.getSender().getUsername());
			preparedStatement.setString(2, noti.getReceiver().getUsername());
			preparedStatement.setString(3, noti.getTitle());
			preparedStatement.setString(4, noti.getContent());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
	}
}
