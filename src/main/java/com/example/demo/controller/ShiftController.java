package com.example.demo.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Shift;
import com.example.demo.service.ShiftService;

@RestController
@RequestMapping("/api/v1/shift")
public class ShiftController {
	
	@Autowired
	private ShiftService service;
	
	@PostMapping
	public Shift save(@RequestBody Shift shift) {
		return service.save(shift);
	}
	
	@GetMapping("/getAll")
	public List<Shift> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Shift getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@GetMapping("/getByLoginTime/{loginTime}")
	public Shift getByLoginTime(@PathVariable LocalTime localTime) {
		return service.getByLoginTime(localTime);
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable int id) {
		return service.deleteById(id);
	}

}
