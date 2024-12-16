package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;




@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername Username : "+username);
		Employee employee=this.employeeRepository.findByUsername(username);
		System.out.println("Employee Details : "+employee.getUsername()+"   "+employee.getPassword());
		UserDetails userDetails=new CustomUserDetails(employee);
		return userDetails;
	}

}
