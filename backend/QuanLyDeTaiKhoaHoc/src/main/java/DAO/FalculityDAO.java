package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Falculity;
import Util.JDBCUtil;
import Util.HandleException;

public class FalculityDAO {
	private static final String SELECT_FALCULITY_BY_FALC_CODE = "select * from khoa where makhoa =?";

	public Falculity selectFalculityByFalcCode(String falcCode) {
		Connection connection = JDBCUtil.getConnection();
		Falculity falculity = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FALCULITY_BY_FALC_CODE);
			preparedStatement.setString(1, falcCode);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("tenkhoa");
				falculity = new Falculity(falcCode, name);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		JDBCUtil.closeConnection(connection);
		return falculity;
	}
}
