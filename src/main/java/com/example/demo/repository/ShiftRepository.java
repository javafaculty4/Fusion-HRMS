package com.example.demo.repository;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Integer>{
	
	public Shift findByLoginTime(LocalTime loginTime);

}
