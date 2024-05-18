package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Topic;
import Util.JDBCUtil;
import Util.HandleException;
import java.util.*;
import DAO.ProjectDAO;

public class TopicDAO {
	private static final String SELECT_TOPIC_BY_TOPIC_CODE = "select * from chude where maso = ?";
	private static final String SELECT_ALL_TOPIC = "select * from chude";
	private static final String SELECT_ACTIVE_TOPIC = "select * from chude where status = 1";
	private static final String INSERT_TOPIC = "insert into chude(maso, ten) values(?, ?)";
	private static final String UPDATE_TOPIC = "update chude set ten = ?, status = ? where maso = ?";

	public Topic selectTopicByTopicCode(String topicCode) {
		Connection connection = JDBCUtil.getConnection();
		Topic topic = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOPIC_BY_TOPIC_CODE);
			preparedStatement.setString(1, topicCode);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("ten");
				boolean isEnabled = rs.getBoolean("status");
				topic = new Topic(topicCode, name, isEnabled);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return topic;
	}

	public List<Topic> selectAllTopic() {
		Connection connection = JDBCUtil.getConnection();
		List<Topic> listtopic = new ArrayList<Topic>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TOPIC);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Topic topic = new Topic();
				String ms = rs.getString("maso");
				String name = rs.getString("ten");
				boolean isEnabled = rs.getBoolean("status");
				
				topic = new Topic(ms, name, isEnabled);
				listtopic.add(topic);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return listtopic;
	}

	public List<Topic> selectActiveTopic() {
		Connection connection = JDBCUtil.getConnection();
		List<Topic> listtopic = new ArrayList<Topic>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACTIVE_TOPIC);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Topic topic = new Topic();
				String ms = rs.getString("maso");
				String name = rs.getString("ten");
				boolean isEnabled = rs.getBoolean("status");
				System.out.print(isEnabled);
				topic = new Topic(ms, name, isEnabled);
				listtopic.add(topic);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return listtopic;
	}

	public void insertTopic(Topic topic) throws SQLException {
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOPIC);
			preparedStatement.setString(1, topic.getTopicCode());
			preparedStatement.setString(2, topic.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
	}

	public boolean updateTopic(Topic topic) throws SQLException {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_TOPIC);
			statement.setString(1, topic.getName());
			statement.setBoolean(2, topic.isEnabled());
			statement.setString(3, topic.getTopicCode());
			rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}

}
