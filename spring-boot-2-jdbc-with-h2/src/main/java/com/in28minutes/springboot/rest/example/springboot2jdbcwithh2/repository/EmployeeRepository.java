package com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.model.Employee;

@Repository
public interface EmployeeRepository extends  JpaRepository<Employee,Long>{

}
