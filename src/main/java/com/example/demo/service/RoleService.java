package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Role;

public interface RoleService {
	
	public Role saveRole(Role role);
	
	public Optional<Role> getRoleById(int id);
	
	public List<Role> getAllRoles();
	
	public String deleteRole(int id);

}
