package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	
	@PostMapping
	public Employee save(@RequestBody Employee emp) {
		return employeeService.save(emp);
	}
	
	@GetMapping("/{firstName}")
	public List<Employee> getByName(@PathVariable String firstName) {
		return employeeService.getByFirstName(firstName);
	}
	
	@GetMapping("/getById/{id}")
	public Employee getById(@PathVariable String id) {
		return employeeService.getById(id);
	}
	
	@GetMapping("/getAll")
	public List<Employee> getAll(){
		return employeeService.getAll();
	}
	
	@GetMapping("/getByStatus/{status}")
	public List<Employee> getByStatus(@PathVariable String status){
		return employeeService.getByStatus(status);
	}
	
	@GetMapping("/getByName/{firstName}/{lastName}")
	public Employee getByName(@PathVariable String firstName,@PathVariable String lastName) {
		return employeeService.getByName(firstName, lastName);
	}
	
	@PutMapping("/update/{id}")
	public Employee update(@PathVariable String id,@RequestBody Employee emp) {
		return employeeService.update(emp, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		return employeeService.deleteById(id);
	}
	
	@DeleteMapping("/delete/{firstName}/{lastName}")
	public String deleteByName(@PathVariable String firstName, @PathVariable String lastName){
		employeeService.deleteByName(firstName, lastName);
		return "Employee Deleted successfully !!!";
	}
	
	
	
	

}
