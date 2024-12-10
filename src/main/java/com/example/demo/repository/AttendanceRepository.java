package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	
	@Query("select a from Attendance a where a.employee.empId = :empId")
	public List<Attendance> findByEmpId(String empId);
	 
	public List<Attendance> findByDate(LocalDate date);
	
	public List<Attendance> findByMonth(String month);
	
	public List<Attendance> findByYear(int year);
	
	@Query("select a from Attendance a where a.employee.empId = :empId and a.month= :month")
	public List<Attendance> findByEmpIdAndMonth(String empId,String month);
	
	@Query("select COUNT(a) from Attendance a where a.emp_leave=1 and a.date=:date")
	public long countOnLeaveByDate(LocalDate date);
	
	@Query("select COUNT(a) from Attendance a where a.date=:date")
	public long countByDate(LocalDate date);
	
	

}
