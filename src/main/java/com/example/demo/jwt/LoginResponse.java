package com.example.demo.jwt;

public class LoginResponse {

	private String jwtToken;
	
	private String username;
	
	private String role;

	public LoginResponse(String username, String role, String jwtToken) {
		super();
		this.jwtToken = jwtToken;
		this.username = username;
		this.role = role;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
}
