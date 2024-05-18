package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.Student;
import Models.Topic;
import Util.HandleException;
import Util.JDBCUtil;

public class StudentDAO {
	private static final String SELECT_STUDENT_BY_STUDENT_CODE = "select * from sinhvien where mssv =?";
	private static final String SELECT_ALL = "select * from sinhvien";
	private static final String INSERT_STUDENT = "insert into sinhvien(mssv, ten, ngaysinh, diachi, cccd, dienthoai, email, gioitinh, malop) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STUDENT = "update sinhvien set ten = ?, ngaysinh = ?, diachi = ?, cccd = ?, dienthoai = ?, email = ?, gioitinh = ?, malop = ? where mssv = ?";
	
    public Student selectStudentByStudentCode(String studentCode) {
    	Connection connection = JDBCUtil.getConnection();
    	Student student = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_STUDENT_CODE);
            preparedStatement.setString(1, studentCode);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	String name = rs.getString("ten");
            	LocalDate birthday = rs.getDate("ngaysinh").toLocalDate();
            	String address = rs.getString("diachi");
            	String idNum = rs.getString("cccd");
            	String phoneNum = rs.getString("dienthoai");
            	String email = rs.getString("email");
            	String sex = rs.getBoolean("gioitinh") ? "Nam" : "Nữ";
            	String classCode = rs.getString("malop");
            	student = new Student(name, birthday, address, idNum, phoneNum, email, sex, studentCode, classCode);
            }
        } catch (SQLException exception) {
            HandleException.printSQLException(exception);
        }
		JDBCUtil.closeConnection(connection);
		return student;
	}
    
    public List<Student> selectAll() {
    	Connection connection = JDBCUtil.getConnection();
    	List<Student> lstStudent = new ArrayList<Student>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	String studentCode = rs.getString("mssv");
            	String name = rs.getString("ten");
            	LocalDate birthday = rs.getDate("ngaysinh").toLocalDate();
            	String address = rs.getString("diachi");
            	String idNum = rs.getString("cccd");
            	String phoneNum = rs.getString("dienthoai");
            	String email = rs.getString("email");
            	String sex = rs.getBoolean("gioitinh") ? "Nam" : "Nữ";
            	String classCode = rs.getString("malop");
            	lstStudent.add(new Student(name, birthday, address, idNum, phoneNum, email, sex, studentCode, classCode));
            }
        } catch (SQLException exception) {
            HandleException.printSQLException(exception);
        }
		JDBCUtil.closeConnection(connection);
		return lstStudent;
	}
    
    public boolean insertStudent(Student student) throws SQLException {
		Connection connection = JDBCUtil.getConnection();
		boolean rowUpdated = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
			preparedStatement.setString(1, student.getStudentCode());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setDate(3, Date.valueOf(student.getBirthday()));
			preparedStatement.setString(4, student.getAddress());
			preparedStatement.setString(5, student.getIdNum());
			preparedStatement.setString(6, student.getPhoneNum());
			preparedStatement.setString(7, student.getEmail());
			boolean sex = student.getSex().equals("Nam");
	        preparedStatement.setBoolean(8, sex);
			preparedStatement.setString(9, student.getClassDTO().getClassCode());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
    
    public boolean updateStudent(Student student) throws SQLException {
		Connection connection = JDBCUtil.getConnection();
		boolean rowUpdated = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT);
			preparedStatement.setString(9, student.getStudentCode());
			preparedStatement.setString(1, student.getName());
			preparedStatement.setDate(2, Date.valueOf(student.getBirthday()));
			preparedStatement.setString(3, student.getAddress());
			preparedStatement.setString(4, student.getIdNum());
			preparedStatement.setString(5, student.getPhoneNum());
			preparedStatement.setString(6, student.getEmail());
			boolean sex = student.getSex().equals("Nam");
		    preparedStatement.setBoolean(7, sex);
			preparedStatement.setString(8, student.getClassDTO().getClassCode());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
    public boolean deleteStudent(String studentCode) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        boolean rowDeleted = false;
        String DELETE_STUDENT = "DELETE FROM sinhvien WHERE mssv = ?"; // Assuming table name is 'Students' and primary key is 'studentCode'
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT);
            preparedStatement.setString(1, studentCode);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            HandleException.printSQLException(exception);
        } finally {
            JDBCUtil.closeConnection(connection);
        }
        return rowDeleted;
    }

}