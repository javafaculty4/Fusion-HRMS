package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Optional<Role> getRoleById(int id) {
		return roleRepository.findById(id);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public String deleteRole(int id) {
		roleRepository.deleteById(id);
		return "Role deleted successfully with id : "+id;
	}

}
