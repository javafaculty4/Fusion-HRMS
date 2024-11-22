package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bank;
import com.example.demo.service.BankService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/bank")
public class BankController {
	
	@Autowired
	private BankService service;
	
	@PostMapping
	public Bank save(@RequestBody Bank bank) {
		return service.save(bank);
	}
	
	@GetMapping("/{id}")
	public Bank getById(@PathVariable int id) {
		return service.getById(id);
	}
	
	@GetMapping("/getByEmpId/{empId}")
	public Bank getByEmpId(@PathVariable String empId) {
		return service.getByEmpId(empId);
	}

	@GetMapping
	public List<Bank> getAll(){
		return service.getAll();
	}
	
	@PutMapping("/update/{id}")
	public Bank update(@PathVariable int id,@RequestBody Bank bank) {
		return service.update(bank, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		return service.delete(id);
	}
}
