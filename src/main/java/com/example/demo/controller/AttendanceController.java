package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Attendance;
import com.example.demo.service.AttendanceService;

@RestController
@RequestMapping("/api/vi/attendance")
public class AttendanceController {
	
	@Autowired
	private AttendanceService service;
	
	@PostMapping
	public Attendance save(@RequestBody Attendance attendance) {
		
		return service.save(attendance);
	}
	
	@GetMapping("/{id}")
	public Attendance getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@GetMapping
	public List<Attendance> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getByEmpId/{empId}")
	public List<Attendance> getByEmpId(@PathVariable String empId){
		return service.getByEmpId(empId);
	}
	
	@PutMapping("/update/{id}")
	public Attendance update(@PathVariable int id, @RequestBody Attendance attendance) {
		return service.update(id, attendance);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		return service.delete(id);
	}

}




