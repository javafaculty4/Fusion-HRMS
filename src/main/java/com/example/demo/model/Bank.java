package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Bank {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private long accNo;
	private String ifsc_code;
	private String branch;
	private String salary_mode; 
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "bank")
	private Employee employee;
	
	public Bank() {
		
	}

	public Bank(int id, String name, long accNo, String ifsc_code, String branch, String salary_mode) {
		super();
		this.id = id;
		this.name = name;
		this.accNo = accNo;
		this.ifsc_code = ifsc_code;
		this.branch = branch;
		this.salary_mode = salary_mode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSalary_mode() {
		return salary_mode;
	}

	public void setSalary_mode(String salary_mode) {
		this.salary_mode = salary_mode;
	}
	
	
	

}
