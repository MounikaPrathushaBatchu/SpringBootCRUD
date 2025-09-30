package com.springboot.restapi.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapi.entity.Employee;
import com.springboot.restapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String saveOrUpdateEmployee(Employee employee) {
		
		String empName = employee.getName().trim();
		
		if(employeeRepository.existsByNameIgnoreCase(empName)) {
			throw new RuntimeException("Employee name already exists : "+empName);
		} else {
			if(employee.getId() != null && employee.getId() > 0) {
				Optional<Employee> employeeData = employeeRepository.findById(employee.getId());
				
				if(employeeData.isPresent()) {
					
					Employee emp = employeeData.get();
					emp.setName(empName);
					emp.setDepartment(employee.getDepartment().trim());
					emp.setSalary(employee.getSalary());
					emp.setActive(employee.getActive());
					emp.setEmail(employee.getEmail().trim());
					employeeRepository.save(emp);
					
				} else {
					throw new RuntimeException("Employee not found with id : "+employee.getId());
				}
			} else {
				employee.setId(null);
				employee.setName(empName);
				employee.setDepartment(employee.getDepartment().trim());
				employee.setSalary(employee.getSalary());
				employee.setActive(employee.getActive());
				employee.setEmail(employee.getEmail().trim());
				employeeRepository.save(employee);
			}
		}
		
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

	@Override
	public List<Employee> searchEmployeesByName(String namePrefix) {
		if(namePrefix != null) {
			namePrefix = namePrefix.trim();
		}
		return employeeRepository.findByNameStartingWithIgnoreCase(namePrefix);
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
