package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bank;
import com.example.demo.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired
	private BankRepository repository;

	@Override
	public Bank save(Bank bank) {
		return repository.save(bank);
	}

	@Override
	public Bank getById(int id) {
		return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Bank","bankId",""+id));
	}

	@Override
	public Bank update(Bank bank, int id) {
		getById(id);
		return repository.save(bank);
	}

	@Override
	public String delete(int id) {
		getById(id);
		repository.deleteById(id);
		return "Bank details deleted successfully with bank_id : "+id;
	}

	@Override
	public List<Bank> getAll() {
		return repository.findAll();
	}

	@Override
	public Bank getByEmpId(String empId) {
		return repository.getByEmpId(empId);
	}

}
