package com.project.bookmyseat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	 @PostMapping(path="/login")
	    public Employee Loginuser(@RequestBody Employee user) throws Exception
	    {
	        String tempemailid = user.getEmail();
	        String temppassword = user.getPassword();
	        Employee userobj = null;
	        if(tempemailid != null && temppassword != null)
	        {
	            userobj =  employeerepository.findByEmailAndPassword(tempemailid, temppassword);

	        }
	        if(userobj == null)
	        {
	            throw new Exception("Enter correct EmailId and Password");
	        }
	        return userobj;
	    }

}
