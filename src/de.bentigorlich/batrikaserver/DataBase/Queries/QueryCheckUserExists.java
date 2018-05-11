package de.bentigorlich.batrikaserver.DataBase.Queries;

import de.bentigorlich.batrikaserver.DataBase.QueryBase;

import java.sql.ResultSet;
import java.sql.SQLException;



public class QueryCheckUserExists extends QueryBase {
	private String  username = "";
	private boolean exists   = false;

	public QueryCheckUserExists(String username) {
		this.username = username;
	}

	@Override
	public String createSQLstatement() {
		return "SELECT * FROM user WHERE username='" + username + "'";
	}

	@Override
	public void processResults(ResultSet rs) {
		try {
			if(rs.next()) {
				exists = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean exists() {
		return exists;
	}
}
