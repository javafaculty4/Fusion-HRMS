package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
	
	@Id
	@Column(name = "emp_id")
	private String empId;
	
	private String firstName;
	private String lastName;
	private long phone;
	private String email;
	private String address;
	private LocalDate dob;
	private String jobTitle;
	private String department;
	private LocalDate hireDate;
	private String gender;
	private double salary;
	private String branch;
	private String qualification;
	private String bloodGroup;
	private String maritalStatus;
	private String panNo;
	private long aadhar;
	private String pfNumber;
	private String emp_status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_id",referencedColumnName = "id")
	private Bank bank;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Attendance> attendanceList;
	
	public Employee() {
		
	}

	public Employee(String empId, String firstName, String lastName, long phone, String email, String address,
			LocalDate dob, String jobTitle, String department, LocalDate hireDate, String gender, double salary,
			String branch, String qualification, String bloodGroup, String maritalStatus, String panNo, long aadhar,
			String pfNumber, Bank bank, String emp_status) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.jobTitle = jobTitle;
		this.department = department;
		this.hireDate = hireDate;
		this.gender = gender;
		this.salary = salary;
		this.branch = branch;
		this.qualification = qualification;
		this.bloodGroup = bloodGroup;
		this.maritalStatus = maritalStatus;
		this.panNo = panNo;
		this.aadhar = aadhar;
		this.pfNumber = pfNumber;
		this.bank = bank;
		this.emp_status = emp_status;
	}

	
	

	public String getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public long getAadhar() {
		return aadhar;
	}

	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}

	public String getPfNumber() {
		return pfNumber;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	

}
