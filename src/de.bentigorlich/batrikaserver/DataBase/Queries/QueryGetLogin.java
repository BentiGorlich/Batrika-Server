package de.bentigorlich.batrikaserver.DataBase.Queries;

import de.bentigorlich.batrikaserver.DataBase.QueryBase;

import java.sql.ResultSet;
import java.sql.SQLException;



public class QueryGetLogin extends QueryBase {

	private boolean isCorrect = false;
	private String  username;
	private String  password;

	public QueryGetLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String createSQLstatement() {
		String sql = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";
		System.out.println(sql);
		return sql;
	}

	@Override
	public void processResults(ResultSet rs) {
		try {
			if(rs.next()) {
				isCorrect = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isLoginCorrect() {
		return isCorrect;
	}
}
