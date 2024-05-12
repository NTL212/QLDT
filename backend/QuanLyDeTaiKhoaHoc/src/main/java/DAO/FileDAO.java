package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.time.LocalDateTime;
import Models.FileDTO;
import Util.HandleException;
import Util.JDBCUtil;

public class FileDAO {
	private static final String SELECT_FILE_BY_PROJ_CODE_UPLOADED_BY_LECT = "select * from file join user on nguoiup = username having madetai = ? and role = 'ROLE_LECT';";
	private static final String SELECT_FILE_BY_PROJ_CODE_UPLOADED_BY_MANAGER = "select * from file join user on nguoiup = username having madetai = ? and role = 'ROLE_MGT_STAFF';";
	private static final String INSERT_FILE = "insert into file (data, nguoiup, madetai) values (?, ?, ?)";
	private static final String DELETE_FILE = "delete from file where id = ?";
	
	private static final String SELECT_FILE_BY_PROJ_CODE_AND_LECT_CODE = "select * from file where madetai = ? and nguoiup = ?";
	
	public FileDTO getFileByLectIdAndProId(String projCode, String lectCode) {
	    Connection connection = JDBCUtil.getConnection();
	    FileDTO file = null;
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILE_BY_PROJ_CODE_AND_LECT_CODE);
	        preparedStatement.setString(1, projCode);
	        preparedStatement.setString(2, lectCode);
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            int fileId = rs.getInt("id");
	            byte[] data = rs.getBytes("data");
	            LocalDateTime uploadTime = rs.getTimestamp("thoigianup").toLocalDateTime();
	            String uploaderId = rs.getString("nguoiup");
	            file = new FileDTO(fileId, data, uploadTime, uploaderId, projCode);
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    } finally {
	        JDBCUtil.closeConnection(connection);
	    }
	    return file;
	}
	
	public List<FileDTO> getFilesUploadedByLecturerForProject(String projCode) {
		Connection connection = JDBCUtil.getConnection();
		List<FileDTO> lstFile = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(SELECT_FILE_BY_PROJ_CODE_UPLOADED_BY_LECT);
			preparedStatement.setString(1, projCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int fileId = rs.getInt("id");
				byte[] data = rs.getBytes("data");
				LocalDateTime uploadTime = rs.getTimestamp("thoigianup").toLocalDateTime();
				String uploaderId = rs.getString("nguoiup");
				FileDTO file = new FileDTO(fileId, data, uploadTime, uploaderId, projCode);
				lstFile.add(file);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return lstFile;
	}

	public List<FileDTO> getFilesUploadedByManagerForProject(String projCode) {
		Connection connection = JDBCUtil.getConnection();
		List<FileDTO> lstFile = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(SELECT_FILE_BY_PROJ_CODE_UPLOADED_BY_MANAGER);
			preparedStatement.setString(1, projCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int fileId = rs.getInt("id");
				byte[] data = rs.getBytes("data");
				LocalDateTime uploadTime = rs.getTimestamp("thoigianup").toLocalDateTime();
				String uploaderId = rs.getString("nguoiup");
				FileDTO file = new FileDTO(fileId, data, uploadTime, uploaderId, projCode);
				lstFile.add(file);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return lstFile;
	}
	
	public boolean insertFile(FileDTO file) {
		Connection connection = JDBCUtil.getConnection();
		boolean success = false;
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT_FILE);
			statement.setBytes(1, file.getData());
			statement.setString(2, file.getUploader().getUsername());
			statement.setString(3, file.getProj().getProjectCode());
			success = statement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return success;
	}

	public boolean deleteFile(FileDTO file) {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_FILE);
			statement.setInt(1, file.getFileId());
			rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
}
