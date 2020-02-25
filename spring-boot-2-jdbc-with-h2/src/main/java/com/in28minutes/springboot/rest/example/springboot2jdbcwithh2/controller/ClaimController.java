package com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.controller;

import java.text.DateFormat.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.sql.rowset.serial.SerialArray;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.model.Claim;
import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.model.Employee;
import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.exception.ResourceNotFoundException;
import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.repository.ClaimRepository;
import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.repository.EmployeeRepository;

@RestController
@RequestMapping(value ="company/api/v1")
public class ClaimController {
	@Autowired
	private ClaimRepository  claimRepository;
	@Autowired
	private EmployeeRepository  employeeRepository;

    
	@GetMapping("/claims")
	public ModelAndView getClaims() {
		List <Claim> list_claims = claimRepository.findAll();		
		return new ModelAndView("show-claims", "listclaims", list_claims);
	}
	
    @GetMapping("/claims/{name}")
    public ModelAndView getClaimsByName(@PathVariable(value = "name") String name){
    	List<Claim> list_claims = claimRepository.findClaimByName(name);
    	return new ModelAndView("show-claims", "listclaims", list_claims);
    }
	
    @GetMapping("/claims/{name}/{id}")
    public ModelAndView getClaimsByNameAndIdEmployee(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long employeeId){
    	List<Claim> list_claims = claimRepository.findClimByNameAndIdEmployee(name, employeeId);
    	return new ModelAndView("show-claims", "listclaims", list_claims);
    }
    
    @PostMapping(path="/create-claims")
    public ModelAndView createClaim(@ModelAttribute  Claim claimForm, Model model) {
    	Claim claimToSave= new Claim();
        	
    	claimToSave.setCode(claimForm.getCode());
    	claimToSave.setName(claimForm.getName());
    	claimToSave.setIdEmployee(claimForm.getIdEmployee());
      	
    	model.addAttribute("claim", claimToSave);
    	claimRepository.save(claimToSave);
    	List <Claim> list_claims = claimRepository.findAll();
    	return new ModelAndView("show-claims", "listclaims", list_claims);
    }
    
    @GetMapping("/create-claims")
    public ModelAndView createClaims() throws ResourceNotFoundException {
    	List <Employee> list_employee = employeeRepository.findAll();
    	return new ModelAndView("create-claims", "employeesList", list_employee);
    }
    
   
    @GetMapping("/claims/employee/{id}")
    public ModelAndView getClaimByEmployeeId(@PathVariable(value = "id") Long employeeId)
    	    throws ResourceNotFoundException {
    	List <Claim> list_claims = claimRepository.findClaimByIdEmployee(employeeId);
    	return new ModelAndView("show-claims", "listclaims", list_claims);
    }
}
