package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import Models.Project;
import Util.HandleException;
import Util.JDBCUtil;
import java.util.*;
import Models.Topic;

public class ProjectDAO {
	private static final String SELECT_PROJECT_BY_PROJECT_CODE = "select * from detai where maso = ?";
	private static final String SELECT_PROJECTS_BY_LECTURER_CODE = "select * from detai where chunhiem = ?";
	private static final String SELECT_ALL_PROJECT = "select * from detai";
	private static final String SELECT_ALL_PROMOTE_PROJECT = "select * from detai where duocdexuat = 1";
	private static final String INSERT_PROJECT = "INSERT INTO detai (maso, tendetai, motanoidung, chude, sothanhvientoida, ngaymodangky, ngayketthucdangky, ngaybatdauthuchien, "
											   + "					ngayketthucthuchien, ngaynghiemthu, kinhphidukien, chunhiem)"
											   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_PROJECT = "UPDATE detai set tendetai = ?, motanoidung = ?, chude = ?, sothanhvientoida = ?, ngaymodangky = ?, ngayketthucdangky = ?, "
												+ "ngaybatdauthuchien = ?, ngayketthucthuchien = ?, ngaynghiemthu = ?, kinhphidukien = ?, chunhiem = ?, ketqua = ?, nhanxet = ?, hoidongnghiemthu = ? where maso = ?";
	private static final String CALC_PROJ_STATUS = "SELECT\r\n" 
												 + "    CASE\r\n"
												 + "        WHEN CURRENT_DATE < ngaymodangky THEN 'Chưa đăng ký'\r\n"
												 + "        WHEN CURRENT_DATE >= ngaymodangky AND CURRENT_DATE <= ngayketthucdangky THEN 'Đang mở đăng ký'\r\n"
												 + "        WHEN CURRENT_DATE > ngayketthucdangky AND CURRENT_DATE < ngaybatdauthuchien THEN 'Chưa tới thời gian thực hiện'\r\n"
												 + "        WHEN CURRENT_DATE >= ngaybatdauthuchien AND CURRENT_DATE <= ngayketthucthuchien THEN 'Đang thực hiện'\r\n"
												 + "        WHEN CURRENT_DATE > ngayketthucthuchien AND CURRENT_DATE <= ngaynghiemthu THEN 'Đã hết hạn nộp báo cáo'\r\n"
												 + "        ELSE 'Đã được nghiệm thu'\r\n"
												 + "    END AS status\r\n"
												 + "FROM detai\r\n"
												 + "WHERE maso = ?";
	private static final String SELECT_THE_NUMBER_OF_PROJECT_BY_TOPIC = "select count(*) as sl from detai where chude = ?";
	private static final String SELECT_THE_NUMBER_OF_ENABLE_PROJECT_BY_TOPIC = "select count(*) as sl from detai where chude = ? and ngayketthucdangky <= CURDATE()";
	private static final String SELECT_ALL_ACTIVE_PROJECT = "select *\r\n"
			+ "from detai left join dangkydetai\r\n"
			+ "on maso = madetai\r\n"
			+ "where CURDATE() BETWEEN ngaymodangky AND ngayketthucdangky\r\n"
			+ "    and (dangkydetai.machunhiem is null\r\n"
			+ "    or (dangkydetai.machunhiem is not null and tinhtrang = 0))";
	private static final String DELEGATE_TO_LECTURE = "UPDATE detai set chunhiem = ? where maso = ?";
	private static final String DELETE_PROJECT = "delete from detai where maso = ?";
	private static final String PROPOSE_PROJECT = "INSERT INTO detai (maso, tendetai, motanoidung, chude, sothanhvientoida, ngaybatdauthuchien, "
			   + "					ngayketthucthuchien, kinhphidukien, chunhiem, duocdexuat)"
			   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
	private static final String EXIST_PROJECT_CODE = "Select  count(*) as sl from detai where maso = ?";

	public Project selectProjectByProjectCode(String projectCode) {
		Project project = null;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_BY_PROJECT_CODE);
			preparedStatement.setString(1, projectCode);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("tendetai");
				LocalDate createDate = rs.getDate("ngaydang").toLocalDate();
				String desc = rs.getString("motanoidung");
				String topicCode = rs.getString("chude");
				int maxMem = rs.getInt("sothanhvientoida");
				LocalDate openRegDate = rs.getDate("ngaymodangky") != null ? rs.getDate("ngaymodangky").toLocalDate() : null;
				LocalDate closeRegDate = rs.getDate("ngayketthucdangky") != null ? rs.getDate("ngayketthucdangky").toLocalDate() : null;;
				LocalDate startDate = rs.getDate("ngaybatdauthuchien").toLocalDate();
				LocalDate endDate = rs.getDate("ngayketthucthuchien").toLocalDate();
				LocalDate acceptanceDate = rs.getDate("ngaynghiemthu") != null ? rs.getDate("ngaynghiemthu").toLocalDate() : null;
				float estBudget = rs.getFloat("kinhphidukien");
				String res = rs.getString("ketqua");
				String comment = rs.getString("nhanxet");
				String lectCode = rs.getString("chunhiem");
				String aCouncilCode = rs.getString("hoidongnghiemthu");
				boolean isProposed = rs.getBoolean("duocdexuat");
				project = new Project(projectCode, name, createDate, desc, maxMem, openRegDate, closeRegDate, startDate,
						endDate, acceptanceDate, estBudget, res, comment, topicCode, lectCode, aCouncilCode, isProposed);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return project;
	}
	
	public List <Project> selectActiveProjectForLecturer() {
		List<Project> lstProj = new ArrayList<Project>();
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTIVE_PROJECT);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Project project = null;
				String projectCode = rs.getString("maso");
				String name = rs.getString("tendetai");
				LocalDate createDate = rs.getDate("ngaydang").toLocalDate();
				String desc = rs.getString("motanoidung");
				String topicCode = rs.getString("chude");
				int maxMem = rs.getInt("sothanhvientoida");
				LocalDate openRegDate = rs.getDate("ngaymodangky").toLocalDate();
				LocalDate closeRegDate = rs.getDate("ngayketthucdangky").toLocalDate();
				LocalDate startDate = rs.getDate("ngaybatdauthuchien").toLocalDate();
				LocalDate endDate = rs.getDate("ngayketthucthuchien").toLocalDate();
				LocalDate acceptanceDate = rs.getDate("ngaynghiemthu") != null ? rs.getDate("ngaynghiemthu").toLocalDate() : null;
				float estBudget = rs.getFloat("kinhphidukien");
				String res = rs.getString("ketqua");
				String comment = rs.getString("nhanxet");
				String lectCode = rs.getString("chunhiem");
				String aCouncilCode = rs.getString("hoidongnghiemthu");
				project = new Project(projectCode, name, createDate, desc, maxMem, openRegDate, closeRegDate, startDate,
						endDate, acceptanceDate, estBudget, res, comment, topicCode, lectCode, aCouncilCode);
				lstProj.add(project);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return lstProj;
	}

	public List<Project> selectAllProject() {
		List<Project> lstProj = new ArrayList<Project>();
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJECT);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Project project = null;
				String projectCode = rs.getString("maso");
				String name = rs.getString("tendetai");
				LocalDate createDate = rs.getDate("ngaydang").toLocalDate();
				String desc = rs.getString("motanoidung");
				String topicCode = rs.getString("chude");
				int maxMem = rs.getInt("sothanhvientoida");
				LocalDate openRegDate = rs.getDate("ngaymodangky") != null ? rs.getDate("ngaymodangky").toLocalDate() : null;
				LocalDate closeRegDate = rs.getDate("ngayketthucdangky") != null ? rs.getDate("ngayketthucdangky").toLocalDate() : null;
				LocalDate startDate = rs.getDate("ngaybatdauthuchien").toLocalDate();
				LocalDate endDate = rs.getDate("ngayketthucthuchien").toLocalDate();
				LocalDate acceptanceDate = rs.getDate("ngaynghiemthu") != null ? rs.getDate("ngaynghiemthu").toLocalDate() : null;
				float estBudget = rs.getFloat("kinhphidukien");
				String res = rs.getString("ketqua");
				String comment = rs.getString("nhanxet");
				String lectCode = rs.getString("chunhiem");
				String aCouncilCode = rs.getString("hoidongnghiemthu");
				Boolean isProposed = rs.getBoolean("duocdexuat");
				project = new Project(projectCode, name, createDate, desc, maxMem, openRegDate, closeRegDate, startDate,
						endDate, acceptanceDate, estBudget, res, comment, topicCode, lectCode, aCouncilCode, isProposed);
				lstProj.add(project);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return lstProj;
	}
	
	public List<Project> selectProjectsByLecturerCode(String lectCode) {
		List<Project> lstProj = new ArrayList<Project>();
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECTS_BY_LECTURER_CODE);
			preparedStatement.setString(1, lectCode);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Project project = null;
				String projectCode = rs.getString("maso");
				String name = rs.getString("tendetai");
				LocalDate createDate = rs.getDate("ngaydang").toLocalDate();
				String desc = rs.getString("motanoidung");
				String topicCode = rs.getString("chude");
				int maxMem = rs.getInt("sothanhvientoida");
				LocalDate openRegDate = rs.getDate("ngaymodangky") != null ? rs.getDate("ngaymodangky").toLocalDate() : null;
				LocalDate closeRegDate = rs.getDate("ngayketthucdangky") != null ? rs.getDate("ngayketthucdangky").toLocalDate() : null;
				LocalDate startDate = rs.getDate("ngaybatdauthuchien").toLocalDate();
				LocalDate endDate = rs.getDate("ngayketthucthuchien").toLocalDate();
				LocalDate acceptanceDate = rs.getDate("ngaynghiemthu") != null ? rs.getDate("ngaynghiemthu").toLocalDate() : null;
				float estBudget = rs.getFloat("kinhphidukien");
				String res = rs.getString("ketqua");
				String comment = rs.getString("nhanxet");
				String aCouncilCode = rs.getString("hoidongnghiemthu");
				Boolean isProposed = rs.getBoolean("duocdexuat");
				project = new Project(projectCode, name, createDate, desc, maxMem, openRegDate, closeRegDate, startDate,
						endDate, acceptanceDate, estBudget, res, comment, topicCode, lectCode, aCouncilCode, isProposed);
				lstProj.add(project);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return lstProj;
	}
	
	public Boolean insertProject (Project proj) {
		Connection connection = JDBCUtil.getConnection();
		Boolean rowUpdated = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT);
			preparedStatement.setString(1, proj.getProjectCode());
			preparedStatement.setString(2, proj.getName());
			preparedStatement.setString(3, proj.getDescription());
			preparedStatement.setString(4, proj.getTopic().getTopicCode());
			preparedStatement.setInt(5, proj.getMaxMember());
			preparedStatement.setDate(6, Date.valueOf(proj.getOpenRegDate()));
			preparedStatement.setDate(7, Date.valueOf(proj.getCloseRegDate()));
			preparedStatement.setDate(8, Date.valueOf(proj.getStartDate()));
			preparedStatement.setDate(9, Date.valueOf(proj.getEndDate()));
			preparedStatement.setDate(10, proj.getAcceptanceDate() != null ? Date.valueOf(proj.getAcceptanceDate()) : null);
			preparedStatement.setFloat(11, proj.getEstBudget());
			preparedStatement.setString(12, (proj.getLecturer() != null) ? proj.getLecturer().getLecturerCode():null);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
	
	public boolean updateProject(Project proj) {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROJECT);
			preparedStatement.setString(1, proj.getName());
			preparedStatement.setString(2, proj.getDescription());
			preparedStatement.setString(3, proj.getTopic().getTopicCode());
			preparedStatement.setInt(4, proj.getMaxMember());
			preparedStatement.setDate(5, Date.valueOf(proj.getOpenRegDate()));
			preparedStatement.setDate(6, Date.valueOf(proj.getCloseRegDate()));
			preparedStatement.setDate(7, Date.valueOf(proj.getStartDate()));
			preparedStatement.setDate(8, Date.valueOf(proj.getEndDate()));
			preparedStatement.setDate(9, proj.getAcceptanceDate() != null ? Date.valueOf(proj.getAcceptanceDate()) : null);
			preparedStatement.setFloat(10, proj.getEstBudget());
			preparedStatement.setString(11, proj.getLecturer() != null ? proj.getLecturer().getLecturerCode():null);
			preparedStatement.setString(12, proj.getResult());
			preparedStatement.setString(13, proj.getComment());
			preparedStatement.setString(14, proj.getaCouncil() !=  null ? proj.getaCouncil().getCouncilCode() : null);
			preparedStatement.setString(15, proj.getProjectCode());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}

	public String calcProjStatus(String projCode) {
		String status = null;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(CALC_PROJ_STATUS);
			preparedStatement.setString(1, projCode);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				status = rs.getString("status");
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return status;
	}
	
	public boolean delegateToLecturer(String lecCode,String projCode) {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELEGATE_TO_LECTURE);
			preparedStatement.setString(1, lecCode);
			preparedStatement.setString(2, projCode);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
	
	public String calProjectByTopic(Topic topic)
	{
		String i = null;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_THE_NUMBER_OF_PROJECT_BY_TOPIC);
			preparedStatement.setString(1, topic.getTopicCode());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				i = rs.getString("sl");
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return i;
	}
	
	public String calEnableProjectByTopic(Topic topic)
	{
		String i = null;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_THE_NUMBER_OF_ENABLE_PROJECT_BY_TOPIC);
			preparedStatement.setString(1, topic.getTopicCode());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				i = rs.getString("sl");
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return i;
	}
	
	public List<Project> selectAllPromoteProject() {
		List<Project> lstProj = new ArrayList<Project>();
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROMOTE_PROJECT);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Project project = null;
				String projectCode = rs.getString("maso");
				String name = rs.getString("tendetai");
				LocalDate createDate = rs.getDate("ngaydang").toLocalDate();
				String desc = rs.getString("motanoidung");
				String topicCode = rs.getString("chude");
				int maxMem = rs.getInt("sothanhvientoida");
				LocalDate openRegDate = rs.getDate("ngaymodangky").toLocalDate();
				LocalDate closeRegDate = rs.getDate("ngayketthucdangky").toLocalDate();
				LocalDate startDate = rs.getDate("ngaybatdauthuchien").toLocalDate();
				LocalDate endDate = rs.getDate("ngayketthucthuchien").toLocalDate();
				LocalDate acceptanceDate = rs.getDate("ngaynghiemthu") != null ? rs.getDate("ngaynghiemthu").toLocalDate() : null;
				float estBudget = rs.getFloat("kinhphidukien");
				String res = rs.getString("ketqua");
				String comment = rs.getString("nhanxet");
				String lectCode = rs.getString("chunhiem");
				String aCouncilCode = rs.getString("hoidongnghiemthu");
				project = new Project(projectCode, name, createDate, desc, maxMem, openRegDate, closeRegDate, startDate,
						endDate, acceptanceDate, estBudget, res, comment, topicCode, lectCode, aCouncilCode);
				lstProj.add(project);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return lstProj;
	}
	
	public boolean deleteProject (Project proj) {
		boolean rowUpdated = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJECT);
			preparedStatement.setString(1, proj.getProjectCode());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return rowUpdated;
	}
	
	public boolean proposeProject (Project proj) {
		boolean success = false;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(PROPOSE_PROJECT);
			preparedStatement.setString(1, proj.getProjectCode());
			preparedStatement.setString(2, proj.getName());
			preparedStatement.setString(3, proj.getDescription());
			preparedStatement.setString(4, proj.getTopic().getTopicCode());
			preparedStatement.setInt(5, proj.getMaxMember());
			preparedStatement.setDate(6, Date.valueOf(proj.getStartDate()));
			preparedStatement.setDate(7, Date.valueOf(proj.getEndDate()));
			preparedStatement.setFloat(8, proj.getEstBudget());
			preparedStatement.setString(9, proj.getLecturer().getLecturerCode());
			success = preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return success;
	}
	
	public boolean checkProjectCode(String code)
	{
		boolean i = true;
		int a = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(EXIST_PROJECT_CODE);
			preparedStatement.setString(1, code);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				a = rs.getInt("sl");
			}
			if (a > 0) i = false;
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return i;
	}
}