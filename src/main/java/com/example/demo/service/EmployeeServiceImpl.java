package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.DuplicateEmployeeIdException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.Role;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PasswordResetTokenRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Autowired
	private EmailService emailService;
	
	public void sendResetToken(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee","emailId",email));

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, employee, LocalDateTime.now().plusMinutes(15));
        passwordResetTokenRepository.save(resetToken);

        String resetLink = "http://localhost:8081/api/v1/employee/reset-password?token=" + token;
        emailService.sendEmail(email, "Password Reset Request", "Click the link below to reset your password:\n" + resetLink);
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token has expired");
        }

        Employee employee = resetToken.getEmployee();
        employee.setPassword(passwordEncoder.encode(newPassword));
        employeeRepository.save(employee);

        passwordResetTokenRepository.delete(resetToken);
    }

	/*
	 * public Employee save(Employee emp) { return employeeRepository.save(emp); }
	 */

	public Employee getById(String id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "employee_id", "" + id));
	}

	public Employee getByName(String firstName, String lastName) {
		Employee emp = employeeRepository.getByName(firstName, lastName);
		if (emp == null) {
			throw new ResourceNotFoundException("Employee", "name", firstName + " " + lastName);
		}
		return emp;
	}

	public List<Employee> getByStatus(String status) {
		List<Employee> empList = employeeRepository.getEmployeesByStatus(status);
		if (empList == null) {
			throw new ResourceNotFoundException("Employee", "status", status);
		}
		return empList;
	}

	public List<Employee> getByFirstName(String firstName) {
		List<Employee> emp = employeeRepository.findByFirstName(firstName);
		if (emp == null) {
			throw new ResourceNotFoundException("Employee", "first_name", firstName);
		}
		return emp;
	}

	public Employee update(Employee emp, String id) {
		Employee employee = getById(id);
		emp.setEmpId(id);
		emp.setUsername(employee.getUsername());
		emp.setPassword(employee.getPassword());
		return employeeRepository.save(emp);
	}

	public String deleteById(String id) {
		getById(id);
		employeeRepository.deleteById(id);
		return "Employee Details deleted successfully with id : " + id;
	}

	public void deleteByName(String firstName, String lastName) {
		employeeRepository.deleteByName(firstName, lastName);
	}

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public long getCount() {
		return employeeRepository.count();
	}

	@Override
	public Employee findEmployeeByUsername(String username) {
		Employee emp = employeeRepository.findByUsername(username);
		if (emp == null) {
			throw new ResourceNotFoundException("Employee", "username", username);
		}
		return emp;
	}

	@Override
	public Employee signUp(Employee emp, int role_id) {
		Optional<Employee> employee = employeeRepository.findById(emp.getEmpId());
		if (employee.isPresent()) {
			throw new DuplicateEmployeeIdException("Duplicate Employee ID : " + emp.getEmpId());
		}
		Optional<Role> role = roleService.getRoleById(role_id);
		emp.setRole(role.get());
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		return employeeRepository.save(emp);
	}

	@Override
	public void changePassword(String id, String oldPassword, String newPassword) {
		Employee emp = getById(id);
		if (!passwordEncoder.matches(oldPassword, emp.getPassword())) {
			throw new IllegalArgumentException("Incorrect old password");
		}

		emp.setPassword(passwordEncoder.encode(newPassword));
		employeeRepository.save(emp);
	}

}
