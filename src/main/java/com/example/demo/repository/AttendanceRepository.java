package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	
	@Query("select a from Attendance a where a.employee.empId = : empId")
	public List<Attendance> findByEmpId(String empId);
	 
	public List<Attendance> findByDate(LocalDate date);

}
