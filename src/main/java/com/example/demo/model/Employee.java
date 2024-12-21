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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Employee {
	
	@Id
	@Column(name = "emp_id")
	private String empId;
	
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String email;
	private String address;
	private LocalDate dob;
	private String designation;
	private String department;
	private LocalDate joiningDate;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_role",referencedColumnName = "id")
	private Role role;
		
	@Column(name="password")
	private String password;
	
	@Column(name="username",unique = true)
	private String username;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_id",referencedColumnName = "id")
	private Bank bank;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonManagedReference
	private List<Attendance> attendanceList;
	
	
	public Employee() {
		
	}

	public Employee(String empId, String firstName, String lastName, long phoneNumber, String email, String address,
			LocalDate dob, String designation, String department, LocalDate joiningDate, String gender, double salary,
			String branch, String qualification, String bloodGroup, String maritalStatus, String panNo, long aadhar,
			String pfNumber, String emp_status, Role role, String password, String username, Bank bank,
			List<Attendance> attendanceList) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.designation = designation;
		this.department = department;
		this.joiningDate = joiningDate;
		this.gender = gender;
		this.salary = salary;
		this.branch = branch;
		this.qualification = qualification;
		this.bloodGroup = bloodGroup;
		this.maritalStatus = maritalStatus;
		this.panNo = panNo;
		this.aadhar = aadhar;
		this.pfNumber = pfNumber;
		this.emp_status = emp_status;
		this.role = role;
		this.password = password;
		this.username = username;
		this.bank = bank;
		this.attendanceList = attendanceList;
		
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

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
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

	public String getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}


}
