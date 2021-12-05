package com.project.bookmyseat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookmyseat.models.Employee;
import com.project.bookmyseat.repository.Employeerepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class Employeecontroller {
	
	@Autowired
	Employeerepository employeerepository;
	
	 @GetMapping(path="/login/{email}/{Password}")
	    public Employee Loginuser(@PathVariable String email,@PathVariable String Password) 
	    {
	        
	        Employee userobj = null;
	        
	            userobj =  employeerepository.findByEmailAndPassword(email, Password);

	        
	        
	        return userobj;
	    }

	 @GetMapping(path="/getmanagerid/{id}")
	 public Employee getmanagerid(@PathVariable int id)
	 {
       return employeerepository.findById(id);        		 
	 }
	 
	 @GetMapping(path="/getemployee/{id}")
	 public Employee getemployee(@PathVariable int id)
	 {
       return employeerepository.findById(id);        		 
	 }
}
