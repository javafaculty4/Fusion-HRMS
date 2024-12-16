package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DuplicateEmployeeIdException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	/*
	 * public Employee save(Employee emp) { return employeeRepository.save(emp); }
	 */
	
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

	@Override
	public Employee findEmployeeByUsername(String username) {
		return employeeRepository.findByUsername(username);
	}

	@Override
	public Employee signUp(Employee emp, int role_id) {
		Optional<Employee> employee = employeeRepository.findById(emp.getEmpId());
		if(employee.isPresent()) {
			throw new DuplicateEmployeeIdException("Duplicate Employee ID : "+emp.getEmpId());
		}
		Optional<Role> role = roleService.getRoleById(role_id);
		emp.setRole(role.get());
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		return employeeRepository.save(emp);
	}

}



