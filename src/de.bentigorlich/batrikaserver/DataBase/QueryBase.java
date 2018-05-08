package de.bentigorlich.batrikaserver.DataBase;

import de.bentigorlich.batrikaserver.ConfigurationManager;

import java.sql.*;



public abstract class QueryBase {

	public boolean execute() {
		Object[] objekte = {createSQLstatement()};
		try {
			query(objekte);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private Connection conn;
	private String     conString = "jdbc:mariadb://localhost:3306/" + ConfigurationManager.getDBName();

	ResultSet query(Object[] q) throws SQLException {
		conn = connect();
		PreparedStatement ps = conn.prepareStatement((String)q[0]);
		for(int i = 1; i < q.length; i++) {
			if(q[i] instanceof String) {
				ps.setString(i, (String)q[i]);
			}
			else if(q[i] instanceof Integer) {
				ps.setInt(i, (int)q[i]);
			}
			else if(q[i] instanceof Date) {
				ps.setDate(i, (Date)q[i]);
			}
		}
		ResultSet rs = ps.executeQuery();
		ps.close();
		disconnect();
		return rs;
	}


	private Connection connect() throws SQLException {
		return DriverManager.getConnection(
				conString,
				ConfigurationManager.getRdbmsUsername(),
				ConfigurationManager.getRdbmsPassword()
		                                  );
	}

	private void disconnect() throws SQLException {
		conn.close();
	}

	public abstract String createSQLstatement();

	public abstract void processResults(ResultSet rs);
}
