package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{
	
	@Query("select bank from Bank bank where bank.employee.empId = :empId")
	public Bank getByEmpId(String empId);

}
