package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Attendance;

public interface AttendanceService {
	
	public Attendance save(Attendance attendance);
	
	public Attendance getById(int id);
	
	public List<Attendance> getAll();
	
	public List<Attendance> getByEmpId(String empId);
	
	public Attendance update(int id, Attendance attendance);
	
	public String delete(int id);
	
	public List<Attendance> getByDate(LocalDate date);
	
	public List<Attendance> findByMonth(String month);
	
	public List<Attendance> findByYear(int year);
	
	public List<Attendance> findByEmpIdAndMonth(String empId,String month);
	
	public long countOnLeaveByDate(LocalDate date);
	
	public long countByDate(LocalDate date);
	
	public long presentCountByDate(LocalDate date);

}
