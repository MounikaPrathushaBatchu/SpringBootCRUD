package com.springboot.restapi.service;

import java.util.List;

import com.springboot.restapi.entity.Employee;

public interface EmployeeService {
	
	String saveOrUpdateEmployee(Employee employee);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(Integer id);
	
	String deleteEmployeeById(Integer id);
	
	List<Employee> saveAllEmployees(List<Employee> employees);
	
	List<Employee> searchEmployeesByName(String name);
	
	List<Employee> searchEmployees(String name, String department, Integer active);

}
