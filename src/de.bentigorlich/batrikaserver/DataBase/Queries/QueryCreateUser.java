package de.bentigorlich.batrikaserver.DataBase.Queries;

import de.bentigorlich.batrikaserver.DataBase.QueryBase;

import java.sql.ResultSet;



public class QueryCreateUser extends QueryBase {

	private String  username;
	private String  password;
	private String  email;
	private boolean valid = true;

	public QueryCreateUser(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	@Override
	public String createSQLstatement() {
		String erg = "INSERT INTO user(username, password, email) VALUES('" + username + "', '" + password + "', '" + email + "')";
		System.out.println(erg);
		return erg;
	}

	@Override
	public void processResults(ResultSet rs) {

	}
}
