package com.springboot.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapi.entity.Employee;
import com.springboot.restapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String upsert(Employee employee) {
		employeeRepository.save(employee);
		return "Success";
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList = employeeRepository.findAll();
		return empList;
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		Optional<Employee> empData = employeeRepository.findById(id);
		
		if(empData.isPresent()) {
			return empData.get();
		}
		return null;
	}

	@Override
	public String deleteEmployeeById(Integer id) {
		if(employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			return "Deleted Successfully";
		} else {
			return "No Record Found";
		}
		
	}
	
//	@GetMapping("/getAllEmployees")
//	public List<Employee> getAllEmployees() {
//		List<Employee> employeeList = employeeRepository.findAll();
//		return employeeList;
//	}
//	
//	@GetMapping("/getEmployeeById/{id}")
//	public Employee getEmployeeById(@PathVariable int id) {
//		Employee employeeData = employeeRepository.findById(id).get();
//		return employeeData;
//	}
//	
//	@PostMapping("/createEmployee")
//	public Employee createEmployee(@RequestBody Employee employee) {
//		return employeeRepository.save(employee);
//	}
//	
//	@DeleteMapping("/deleteEmployee/{id}")
//	public void deleteEmployee(@PathVariable int id) {
//		employeeRepository.deleteById(id);
//	}

}
