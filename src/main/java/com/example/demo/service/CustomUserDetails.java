package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Employee;




public class CustomUserDetails implements UserDetails {
	
	private Employee employee;
	
	public CustomUserDetails(Employee employee) {
		System.out.println("CustomUserDetails : "+employee.getUsername()+"  "+employee.getPassword());
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities =  new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(employee.getRole().getRole()));   
		System.out.println("Authorities : "+authorities);
		return authorities;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getUsername();
	}

}
