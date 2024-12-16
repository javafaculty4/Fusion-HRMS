package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bank;
import com.example.demo.service.BankService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/bank")
@CrossOrigin(origins = "http://localhost:3000")
public class BankController {
	
	@Autowired
	private BankService service;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@PostMapping
	public Bank save(@RequestBody Bank bank) {
		return service.save(bank);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@GetMapping("/{id}")
	public Bank getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@GetMapping("/getByEmpId/{empId}")
	public Bank getByEmpId(@PathVariable String empId) {
		return service.getByEmpId(empId);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@GetMapping
	public List<Bank> getAll(){
		return service.getAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@PutMapping("/update/{id}")
	public Bank update(@PathVariable int id,@RequestBody Bank bank) {
		return service.update(bank, id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		return service.delete(id);
	}
}
