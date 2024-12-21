package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.JwtUtils;
import com.example.demo.jwt.LoginRequest;
import com.example.demo.jwt.LoginResponse;
import com.example.demo.model.Employee;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import com.example.demo.utility.ChangePasswordRequest;
import com.example.demo.utility.ForgotPasswordRequest;
import com.example.demo.utility.ResetPasswordRequest;


@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        employeeService.sendResetToken(request.getEmail());
        return ResponseEntity.ok("Password reset link sent to your email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request,@RequestParam("token") String token) {
    	request.setToken(token);
        employeeService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Password has been reset successfully.");
    }
    
    @PostMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable String id, @RequestBody ChangePasswordRequest request) {
        try {
            employeeService.changePassword(id,request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok("Password changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save/{id}")
	public Employee save(@RequestBody Employee emp,@PathVariable int id){
		return employeeService.signUp(emp,id);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
		Authentication authentication;
		try {
			System.out.println("Login Request username : "+loginRequest.getUsername());
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
					
		}
		catch(AuthenticationException e) {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "Bad credentials");
			map.put("status", false);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
				
		String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority()).collect(Collectors.toList());
		
		LoginResponse response = new LoginResponse(userDetails.getUsername(),roles.get(0), jwtToken);
		
		return ResponseEntity.ok(response);
	}
	
		
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{firstName}")
	public List<Employee> getByName(@PathVariable String firstName) {
		return employeeService.getByFirstName(firstName);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getById/{id}")
	public Employee getById(@PathVariable String id) {
		return employeeService.getById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getAll")
	public List<Employee> getAll(){
		return employeeService.getAll();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getByStatus/{status}")
	public List<Employee> getByStatus(@PathVariable String status){
		return employeeService.getByStatus(status);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getByName/{firstName}/{lastName}")
	public Employee getByName(@PathVariable String firstName,@PathVariable String lastName) {
		return employeeService.getByName(firstName, lastName);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}")
	public Employee update(@PathVariable String id,@RequestBody Employee emp) {
		return employeeService.update(emp, id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		return employeeService.deleteById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{firstName}/{lastName}")
	public String deleteByName(@PathVariable String firstName, @PathVariable String lastName){
		employeeService.deleteByName(firstName, lastName);
		return "Employee Deleted successfully !!!";
	}
}
