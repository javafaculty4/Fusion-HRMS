package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Bank;

public interface BankService {
	
	public Bank save(Bank bank);
	
	public Bank getById(int id);
	
	public Bank update(Bank bank, int id);
	
	public String delete(int id);
	
	public List<Bank> getAll();
	
	public Bank getByEmpId(String empId);

}
