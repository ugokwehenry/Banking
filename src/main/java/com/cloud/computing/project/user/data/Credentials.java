package com.cloud.computing.project.user.data;

public class Credentials {
	private String email = null;
	private String password = null;
	
	public Credentials() {}
	
	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
