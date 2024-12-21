package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@JsonBackReference
	private Employee employee;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	private Shift shift;

	private LocalDate date;
	
	private String month;
	
	private int year;

	private LocalTime loginTime;

	private LocalTime logoutTime;

	private LocalTime workingHours;

	private int lateMark;

	private int halfDay;

	private int emp_leave;
	
	private LocalTime lateTime;

	public Attendance() {

	}
	
	public LocalTime getLateTime() {
		return lateTime;
	}

	public void setLateTime(LocalTime lateTime) {
		this.lateTime = lateTime;
	}

	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
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
