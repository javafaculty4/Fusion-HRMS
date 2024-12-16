package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;


import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

	public List<Employee> findByFirstName(String firstName);
	
	@Query("select emp from Employee emp where emp.emp_status=:status")
	public List<Employee> getEmployeesByStatus(String status);
	
	@Query("select emp from Employee emp where emp.firstName=:firstName and emp.lastName=:lastName")
	public Employee getByName(String firstName, String lastName);
	
	@Transactional
	@Modifying
	@Query("delete from Employee emp where emp.firstName=:firstName and emp.lastName=:lastName")
	public void deleteByName(String firstName,String lastName);
	
	public Employee findByUsername(String username);
}
