package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Falculity;
import Util.JDBCUtil;
import Util.HandleException;

public class FalculityDAO {
	private static final String SELECT_FALCULITY_BY_FALC_CODE = "select * from khoa where makhoa =?";
	private static final String SELECT_ALL_FALCULITIES = "select * from khoa";

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
	public List<Falculity> getAllFalculities() {
	    Connection connection = JDBCUtil.getConnection();
	    List<Falculity> falculities = new ArrayList<>();
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FALCULITIES);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String falcCode = rs.getString("makhoa");
	            String name = rs.getString("tenkhoa");
	            Falculity falculity = new Falculity(falcCode, name);
	            falculities.add(falculity);
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    JDBCUtil.closeConnection(connection);
	    return falculities;
	}

	
}
