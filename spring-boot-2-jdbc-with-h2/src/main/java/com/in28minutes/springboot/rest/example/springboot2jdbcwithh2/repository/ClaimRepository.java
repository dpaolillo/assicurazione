package com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.model.Claim;

@Repository
public interface ClaimRepository extends  JpaRepository<Claim,Long>{
	List <Claim> findClaimByIdEmployee(long id_employee); 
	List <Claim> findClaimByName(String name);
	List <Claim> findClimByNameAndIdEmployee(String name, Long id_employee);
}
