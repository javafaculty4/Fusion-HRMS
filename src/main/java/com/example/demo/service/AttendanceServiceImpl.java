package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Attendance;
import com.example.demo.repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService{
	
	@Autowired
	private AttendanceRepository repository;

	@Override
	public Attendance save(Attendance attendance) {
		int hours = attendance.getLogoutTime().getHour() - attendance.getLoginTime().getHour();
		int minutes = attendance.getLogoutTime().getMinute() - attendance.getLoginTime().getMinute();
		if(minutes<0) {
			hours--;
			minutes = 60+minutes;
		}
		if(hours<0) {
			hours=12+hours;
		}
		LocalTime workingHours = LocalTime.of(hours, minutes);
		int lateMark = 0;
		int halfDay = 0;
		int leave = 0;
		if(workingHours.getHour()<4) {
			leave = 1;
		}
		else if(workingHours.getHour()>4 && workingHours.getHour()<7) {
			halfDay = 1;
		}
		attendance.setWorkingHours(workingHours);
		attendance.setHalfDay(halfDay);
		attendance.setEmp_leave(leave);
		return repository.save(attendance);
	}

	@Override
	public Attendance getById(int id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Attendance","attendance_id",""+id));
	}

	@Override
	public List<Attendance> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Attendance> getByEmpId(String empId) {
		return repository.findByEmpId(empId);
	}

	@Override
	public Attendance update(int id, Attendance attendance) {
		getById(id);
		return repository.save(attendance);
	}

	@Override
	public String delete(int id) {
		getById(id);
		repository.deleteById(id);
		return "Attendance Deleted with attendance_id : "+id;
	}

	@Override
	public List<Attendance> getByDate(LocalDate date) {
		return repository.findByDate(date);
	}

}
