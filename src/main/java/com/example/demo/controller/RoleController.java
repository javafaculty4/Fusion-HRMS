package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/api/vi/role")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@PostMapping
	public Role saveRole(@RequestBody Role role) {
		return roleService.saveRole(role);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@GetMapping("/{id}")
	public Optional<Role> getRoleById(@PathVariable int id){
		return roleService.getRoleById(id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@GetMapping
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
	@DeleteMapping("/{id}")
	public String deleteRole(@PathVariable int id) {
		return roleService.deleteRole(id);
	}

}
