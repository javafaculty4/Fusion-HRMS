package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
	private Employee employee;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	private Shift shift;

	private LocalDate date;

	private LocalTime loginTime;

	private LocalTime logoutTime;

	private LocalTime workingHours;

	private int lateMark;

	private int halfDay;

	private int emp_leave;

	public Attendance() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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

	public LocalTime getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(LocalTime workingHours) {
		this.workingHours = workingHours;
	}

	public int getLateMark() {
		return lateMark;
	}

	public void setLateMark(int lateMark) {
		this.lateMark = lateMark;
	}

	public int getHalfDay() {
		return halfDay;
	}

	public void setHalfDay(int halfDay) {
		this.halfDay = halfDay;
	}

	public int getEmp_leave() {
		return emp_leave;
	}

	public void setEmp_leave(int emp_leave) {
		this.emp_leave = emp_leave;
	}

	

}
