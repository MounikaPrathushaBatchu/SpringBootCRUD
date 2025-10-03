package com.springboot.restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restapi.entity.Department;
import com.springboot.restapi.entity.Employee;
import com.springboot.restapi.repository.DepartmentRepository;
import com.springboot.restapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public String saveOrUpdateEmployee(Employee employee) {
		
		String empName 	= employee.getName().trim();
		long salary 	= employee.getSalary();
		if(employeeRepository.existsByNameIgnoreCase(empName)) {
			throw new RuntimeException("Employee name already exists : "+empName);
		}
		
		if(salary < Employee.MIN_SALARY) {
			throw new RuntimeException("Employee salary minimum is : "+Employee.MIN_SALARY+" but the given salary is : "+salary);
		}
		if(salary > Employee.MAX_SALARY) {
			throw new RuntimeException("Employee salary sholud not execed : "+Employee.MAX_SALARY+" but the given salary is : "+salary);
		}
		
		if(empName.isEmpty()) {
			throw new RuntimeException("Employee name should not be empty : "+empName);
		}
		
		if(employee.getDepartment() == null) {
			throw new RuntimeException("Department details are required!");
		}
		
		Department department = null;
		
		if(employee.getDepartment().getDeptId() != null) {
			
			department = departmentRepository.findById(employee.getDepartment().getDeptId())
					.orElseThrow(() -> new RuntimeException("Department not found with id : "+employee.getDepartment().getDeptId()));
			
		} else if(employee.getDepartment().getDeptName() != null) {
			
			department = departmentRepository.findByDeptNameStartingWithIgnoreCase(employee.getDepartment().getDeptName().trim())
					.orElseGet(() -> departmentRepository.save(employee.getDepartment()));
			
		} else {
			throw new RuntimeException("Either Deprtment name or Depart Id has to provide.");
		}

		if(employee.getId() != null && employee.getId() > 0) {
			Optional<Employee> employeeData = employeeRepository.findById(employee.getId());
			
			if(employeeData.isPresent()) {
				
				Employee emp = employeeData.get();
				emp.setName(empName);
				emp.setDepartment(department);
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
			employee.setDepartment(department);
			employee.setSalary(employee.getSalary());
			employee.setActive(employee.getActive());
			employee.setEmail(employee.getEmail().trim());
			employeeRepository.save(employee);
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
	public List<Employee> saveAllEmployees(List<Employee> employees) {
		
		List<Employee> processedEmployees = new ArrayList<Employee>();
		
		for(Employee emp : employees) {
			
			String empName 	= emp.getName().trim();
			long salary 	= emp.getSalary();
			if(employeeRepository.existsByNameIgnoreCase(empName)) {
				throw new RuntimeException("Employee already exists with the name : "+empName);
			}
			
			if(salary < Employee.MIN_SALARY) {
				throw new RuntimeException("Employee salary minimum is : "+Employee.MIN_SALARY+" but the given salary is : "+salary);
			}
			if(salary > Employee.MAX_SALARY) {
				throw new RuntimeException("Employee salary should not execed : "+Employee.MIN_SALARY+" but the given salary is : "+salary);
			}
			
			if(empName.equals("")) {
				throw new RuntimeException("Employee Name should not be empty");
			}
			
			processedEmployees.add(emp);
		}
		employeeRepository.saveAll(processedEmployees);
		
		return null;
	}

	@Override
	public List<Employee> searchEmployeesByName(String namePrefix) {
		if(namePrefix != null) {
			namePrefix = namePrefix.trim();
		}
		return employeeRepository.findByNameStartingWithIgnoreCase(namePrefix);
	}

	@Override
	public List<Employee> searchEmployees(String name, Long deptId, Integer active) {
		return employeeRepository.searchEmployees(name, deptId, active);
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
