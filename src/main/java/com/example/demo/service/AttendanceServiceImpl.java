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
	
	@Autowired
	private EmployeeService employeeService;

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
		int lateHours = attendance.getLoginTime().getHour()-attendance.getShift().getLoginTime().getHour();
		int lateMinutes = attendance.getLoginTime().getMinute()-attendance.getShift().getLoginTime().getMinute();
		
		if(lateMinutes<0) {				
			lateHours--; 					
			lateMinutes = 60+lateMinutes;				
		}		
		int lateMark = 0;
		if(lateMinutes>30) {
			lateMark = 1;
		}
		else if(lateHours>=1) {
			lateMark=1;
		}
		
		int halfDay = 0;
		int leave = 0;
		
		if(workingHours.getHour()<4) {
			lateMark=0;
			halfDay=0;
			leave = 1;
		}
		else if(workingHours.getHour()>=4 && workingHours.getHour()<7) {
			halfDay = 1;
			lateMark=0;
			leave=0;
		}
		attendance.setWorkingHours(workingHours);
		attendance.setHalfDay(halfDay);
		attendance.setEmp_leave(leave);
		attendance.setLateMark(lateMark);
		attendance.setYear(attendance.getDate().getYear());
		attendance.setMonth(attendance.getDate().getMonth().toString());
		if(lateMark==0) {
			lateHours=0;
			lateMinutes=0;
		}
		attendance.setLateTime(LocalTime.of(lateHours, lateMinutes));
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
		List<Attendance> list =  repository.findByEmpId(empId);
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("Attendance","empId", empId);
		}
		return list;
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
		List<Attendance> list = repository.findByDate(date);
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("Attendance","date", date.toString());
		}
		return list;
	}

	@Override
	public List<Attendance> findByMonth(String month) {
		List<Attendance> list = repository.findByMonth(month);
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("Attendance","Month", month);
		}
		return list;
	}

	@Override
	public List<Attendance> findByYear(int year) {
		List<Attendance> list = repository.findByYear(year);
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("Attendance","Year", ""+year);
		}
		return list;
	}
	
	public List<Attendance> findByEmpIdAndMonth(String empId,String month){
		return repository.findByEmpIdAndMonth(empId, month);
	}

	public long countOnLeaveByDate(LocalDate date) {
		long employeeCount = employeeService.getCount();
		long attendaceCount = countByDate(date);
		long leaveCount = repository.countOnLeaveByDate(date);
		if(employeeCount != attendaceCount) {			
			leaveCount += (employeeCount - attendaceCount);			
		}
		return leaveCount;
	}
	
	public long countByDate(LocalDate date) {
		return repository.countByDate(date);
	}
	
	public long presentCountByDate(LocalDate date) {
		return employeeService.getCount() - countOnLeaveByDate(date);
	}
}
