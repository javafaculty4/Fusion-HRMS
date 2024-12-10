package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	public Employee getById(String id) {
		return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","employee_id", ""+id));
	}
	
	public Employee getByName(String firstName, String lastName) {
		Employee emp =  employeeRepository.getByName(firstName, lastName);
		if(emp == null) {
			throw new ResourceNotFoundException("Employee", "name", firstName+" "+lastName);
		}
		return emp;
	}
	
	public List<Employee> getByStatus(String status){
		List<Employee> empList = employeeRepository.getEmployeesByStatus(status);
		if(empList == null) {
			throw new ResourceNotFoundException("Employee", "status", status);
		}
		return empList;
	}
	
	public List<Employee> getByFirstName(String firstName) {
		List<Employee> emp = employeeRepository.findByFirstName(firstName);
		if(emp == null) {
			throw new ResourceNotFoundException("Employee", "first_name", firstName);
		}
		return emp;
	}
	
	public Employee update(Employee emp,String id) {
		getById(id);
		emp.setEmpId(id);
		return employeeRepository.save(emp);
	}
	
	public String deleteById(String id) {
		getById(id);
		employeeRepository.deleteById(id);
		return "Employee Details deleted successfully with id : "+id;
	}
	
	public void deleteByName(String firstName, String lastName) {
		employeeRepository.deleteByName(firstName, lastName);
	}
	
	public List<Employee> getAll(){
		return employeeRepository.findAll();
	}
	
	public long getCount() {
		return employeeRepository.count();
	}

}



