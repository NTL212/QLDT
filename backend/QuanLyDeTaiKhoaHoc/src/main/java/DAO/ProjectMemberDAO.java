package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Models.ProjectMember;
import Models.Topic;
import Util.HandleException;
import Util.JDBCUtil;

public class ProjectMemberDAO {
	private static final String SELECT_PROJECT_MEMBERS_BY_PROJECT_CODE = "select * from thamgiadetai where madetai =?";
	private static final String INSERT_PROJECT_MEMBER= "insert into thamgiadetai (sinhvien, madetai) values (?, ?)";
	private static final String DELETE_PROJECT_MEMBER= "delete from thamgiadetai where sinhvien = ? and madetai = ?";

    public List <ProjectMember> selectProjectMemberByProjectCode(String projCode) {
    	Connection connection = JDBCUtil.getConnection();
    	List <ProjectMember> lstProjMems = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_MEMBERS_BY_PROJECT_CODE);
            preparedStatement.setString(1, projCode);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	String studentCode = rs.getString("sinhvien");
            	ProjectMember projMem = new ProjectMember(studentCode, projCode);
            	lstProjMems.add(projMem);
            }
        } catch (SQLException exception) {
            HandleException.printSQLException(exception);
        }
		JDBCUtil.closeConnection(connection);
		return lstProjMems;
	}
    
	public boolean insertProjectMember (ProjectMember projMem) {
		Connection connection = JDBCUtil.getConnection();
		boolean rowUpdated = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_MEMBER);
			preparedStatement.setString(1, projMem.getStudent().getStudentCode());
			preparedStatement.setString(2, projMem.getProject().getProjectCode());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
	
	public boolean deleteProjectMember(ProjectMember projMem) throws SQLException {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJECT_MEMBER);
			preparedStatement.setString(1, projMem.getStudent().getStudentCode());
			preparedStatement.setString(2, projMem.getProject().getProjectCode());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
}
