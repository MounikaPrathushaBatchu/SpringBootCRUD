package com.springboot.restapi.service;

import java.util.List;

import com.springboot.restapi.entity.Employee;

public interface EmployeeService {
	
	public String upsert(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Integer id);
	
	public String deleteEmployeeById(Integer id);
}
