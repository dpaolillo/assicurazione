package com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.model.Employee;
import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.exception.ResourceNotFoundException;


import com.in28minutes.springboot.rest.example.springboot2jdbcwithh2.repository.EmployeeRepository;

@RestController
@RequestMapping(value ="company/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository  employeeRepository;
	
	
	@GetMapping("/employees")
	public ModelAndView getEmployeesView() {
		List <Employee> list_employee = employeeRepository.findAll();
	    return new ModelAndView("show-employees", "employeesList", list_employee);
	}
	
    @GetMapping("/employees/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
    
    @GetMapping("/details_employees/{id}")
    public ModelAndView showEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return new ModelAndView("details-employee", "employee", employee);
    }
    
    @PostMapping(path="/employees")
    public ModelAndView createEmployee(@ModelAttribute  Employee employeeForm, Model model) {
    	Employee employeeToSave= new Employee();
    	employeeToSave.setName(employeeForm.getName());
    	employeeToSave.setSurname(employeeForm.getSurname());
    	employeeToSave.setEmail(employeeForm.getEmail());
    	model.addAttribute("contact", employeeToSave);
    	employeeRepository.save(employeeToSave);
    	List <Employee> list_employee = employeeRepository.findAll();
	    return new ModelAndView("show-employees", "employeesList", list_employee);
    }
    
    @Transactional
    @PutMapping("/employees/{id}")
    public ResponseEntity< Employee > updateEmployee(@PathVariable(value = "id") Long employeeId,@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
    		Employee employee = employeeRepository.findById(employeeId)
    	    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    		
    		employee.setName(employeeDetails.getName());
    		employee.setSurname(employeeDetails.getSurname());

    		final Employee employeeUpdate = employeeRepository.save(employee);
    		
    		return ResponseEntity.ok(employeeUpdate);
    } 
	
    @DeleteMapping("/delete_employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value="id") Long employeeId) throws ResourceNotFoundException {
		
    	Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Nessun dipendente trovato con questo id:: "+ employeeId));
    	
    	employeeRepository.delete(employee);
    	
    	Map<String, Boolean> response = new HashMap<String, Boolean>();
    	response.put("Deleted", true);
    	return response;
    }
    	
}
