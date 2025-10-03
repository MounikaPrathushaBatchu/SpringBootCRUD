package com.springboot.restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restapi.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	Optional<Department> findByDeptNameStartingWithIgnoreCase(String deptName);

}
