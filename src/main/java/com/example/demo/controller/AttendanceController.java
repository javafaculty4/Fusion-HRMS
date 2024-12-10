package com.example.demo.controller;

import java.time.LocalDate;
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
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ShiftService;

@RestController
@RequestMapping("/api/vi/attendance")
public class AttendanceController {
	
	@Autowired
	private AttendanceService service;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ShiftService shiftService;
	
	@PostMapping("/{empId}/{shiftId}")
	public Attendance save(@RequestBody Attendance attendance,@PathVariable String empId,@PathVariable int shiftId) {
		attendance.setEmployee(employeeService.getById(empId));
		attendance.setShift(shiftService.getById(shiftId));
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
	
	@GetMapping("/getByDate/{date}")
	public List<Attendance> getByDate(@PathVariable LocalDate date){
		return service.getByDate(date);
	}
	
	@GetMapping("/getByMonth/{month}")
	public List<Attendance> getByMonth(@PathVariable String month){
		return service.findByMonth(month.toUpperCase());
	}
	
	@GetMapping("/getByYear/{year}")
	public List<Attendance> getByYear(@PathVariable int year){
		return service.findByYear(year);
	}
	
	@GetMapping("/getByEmpAndMonth/{empId}/{month}")
	public List<Attendance> getByEmpAndMonth(@PathVariable String empId,@PathVariable String month){
		return service.findByEmpIdAndMonth(empId,month.toUpperCase());
	}
	
	@GetMapping("/countOnLeave/{date}")
	public long countOnLeaveByDate(@PathVariable LocalDate date) {
		return service.countOnLeaveByDate(date);
	}
	
	@GetMapping("/countByDate/{date}")
	public long countByDate(@PathVariable LocalDate date) {
		return service.countByDate(date);
	}
	
	@GetMapping("/presentCount/{date}")
	public long presentCountByDate(@PathVariable LocalDate date) {
		return service.presentCountByDate(date);
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




