package com.springboot.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByNameStartingWithIgnoreCase(String namePrefix);
	
	boolean existsByNameIgnoreCase(String name);
	
	@Query("select e from Employee e " +
		       "join e.department d " +
		       "where (:name is null or e.name like concat(:name, '%')) " +
		       "and (:deptId is null or d.id = :deptId) " +
		       "and (:active is null or e.active = :active)")
		List<Employee> searchEmployees(@Param("name") String name, @Param("deptId") Long deptId, @Param("active") Integer active);

}
