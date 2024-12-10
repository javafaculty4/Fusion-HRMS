package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;


public interface EmployeeService {

	public Employee save(Employee emp) ;
	
	public Employee getById(String id) ;
	
	public Employee getByName(String firstName, String lastName) ;
	
	public List<Employee> getByStatus(String status);
	
	public List<Employee> getByFirstName(String firstName) ;
	
	public Employee update(Employee emp,String id) ;
	
	public String deleteById(String id) ;
	
	public void deleteByName(String firstName, String lastName) ;
	
	public List<Employee> getAll();
	
	public long getCount();

}
