package com.springboot.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

@Entity
@Table(name="employee", schema = "employeedatabase")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class Employee {
	
	public Employee(Integer id, String name, Long salary, String department, Integer active) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.department = department;
		this.active = active;
	}
	 public Employee() {
	 }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name",length = 100)
	private String name;
	
	@Column(name = "salary")
	private Long salary;
	
	@Column(name = "department",length = 50)
	private String department;
	
	@Column(name="active", nullable = false)
//	@Builder.Default
	private Integer active = 1;
	
	@Column(name = "email", unique = true, nullable = false, length = 150)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Size(max = 150, message = "Email must not exceed 150 characters")
	private String email;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
