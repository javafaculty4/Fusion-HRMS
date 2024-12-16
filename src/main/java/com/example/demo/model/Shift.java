package com.example.demo.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Shift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shift_id")
	private int id;

	private LocalTime loginTime;
	private LocalTime logoutTime;
	
	public Shift() {
		
	}

	public Shift(LocalTime loginTime, LocalTime logoutTime) {
		super();
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalTime getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(LocalTime logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	

	
	
}
