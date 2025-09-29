package com.springboot.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.entity.Employee;
import com.springboot.restapi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
//	@Autowired
//	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/saveOrUpdateEmployee")
	public ResponseEntity<String> saveOrUpdateEmployee(@RequestBody Employee employee){
		String status = employeeService.saveOrUpdateEmployee(employee);
		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> empList = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
		String status = employeeService.deleteEmployeeById(id);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
//	@GetMapping("/getAllEmployees")
//	public List<Employee> getAllEmployees() {
//		return employeeService.getAllEmployees();
//		
////		List<Employee> employeeList = employeeRepository.findAll();
////		return employeeList;
//	}
//	
//	@GetMapping("/getEmployeeById/{id}")
//	public Employee getEmployeeById(@PathVariable int id) {
//		return employeeService.getEmployeeById(id);
//		
////		Employee employeeData = employeeRepository.findById(id).get();
////		return employeeData;
//	}
//	
//	@PostMapping("/createEmployee")
//	public Employee createEmployee(@RequestBody Employee employee) {
//		return employeeService.createEmployee(employee);
////		return employeeRepository.save(employee);
//	}
//	
//	@DeleteMapping("/deleteEmployee/{id}")
//	public void deleteEmployee(@PathVariable int id) {
//		employeeService.deleteEmployee(id);
////		employeeRepository.deleteById(id);
//	}

}
