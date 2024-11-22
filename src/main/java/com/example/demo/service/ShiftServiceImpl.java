package com.example.demo.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Shift;
import com.example.demo.repository.ShiftRepository;

@Service
public class ShiftServiceImpl implements ShiftService{
	
	@Autowired
	private ShiftRepository repository;

	@Override
	public Shift save(Shift shift) {
		return repository.save(shift);
	}

	@Override
	public Shift getById(int id) {
		return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Shift", "shift_id",""+id));
	}

	@Override
	public Shift getByLoginTime(LocalTime loginTime) {
		return repository.findByLoginTime(loginTime);
	}

	@Override
	public String deleteById(int id) {
		getById(id);
		repository.deleteById(id);
		return "Shift deleted with shift_id : "+id;
	}

	@Override
	public List<Shift> getAll() {
		return repository.findAll();
	}

}
