package de.bentigorlich.batrikaserver.Entities.Messages;

import org.json.JSONObject;



public class ControlMessage extends MessageBase {
	private String username = "";
	private String password = ""; //as SHA256 Hash
	private String email    = "";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public JSONObject construct() {
		JSONObject erg = new JSONObject();
		erg
				.put("username", username)
				.put("password", password)
				.put("email", email)
				.put("type", getType().toInt())
		;
		return erg;
	}

	@Override
	public String toString() {
		return "[username=" + username + ", email=" + email + ", password=" + password + ", type=" + getType() + "]";
	}
}
