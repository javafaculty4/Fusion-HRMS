package com.example.demo.service;

import java.time.LocalTime;
import java.util.List;

import com.example.demo.model.Shift;

public interface ShiftService {
	
	public Shift save(Shift shift);
	
	public List<Shift> getAll();
	
	public Shift getById(int id);
	
	public Shift getByLoginTime(LocalTime loginTime);
	
	public String deleteById(int id);

}
