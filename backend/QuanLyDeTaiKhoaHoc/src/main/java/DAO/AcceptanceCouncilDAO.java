package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.AcceptanceCouncil;
import Util.HandleException;
import Util.JDBCUtil;

public class AcceptanceCouncilDAO {
	private static final String SELECT_ACOUNCIL_BY_ACOUNCIL_CODE = "select * from hoidongnghiemthu where mahoidong =?";

	public AcceptanceCouncil selectACouncilByACouncilCode(String aCouncilCode) {
		Connection connection = JDBCUtil.getConnection();
		AcceptanceCouncil aCouncil = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACOUNCIL_BY_ACOUNCIL_CODE);
			preparedStatement.setString(1, aCouncilCode);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("tennganh");
				String falcCode = rs.getString("khoa");
				aCouncil = new AcceptanceCouncil(aCouncilCode, name, falcCode);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return aCouncil;
	}
}