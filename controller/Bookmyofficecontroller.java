package com.project.bookmyseat.controller;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookmyseat.models.Bookmyoffice;
import com.project.bookmyseat.repository.Bookmyofficerepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class Bookmyofficecontroller {
	
	@Autowired
	Bookmyofficerepository bookmyofficerepository;
	
	@PostMapping("/addslot")
	@ResponseStatus(HttpStatus.CREATED)
	public Bookmyoffice addslot(@RequestBody Bookmyoffice bookmyoffice){
		return bookmyofficerepository.savebookingdetails(bookmyoffice);
	}
	
	/*@PutMapping("/updateslot")
	@ResponseStatus(HttpStatus.CREATED)
	public Bookmyoffice updatelot(@RequestBody Bookmyoffice bookmyoffice){
		return bookmyofficerepository.update(bookmyoffice);
	}*/
	
	@DeleteMapping("/deleteslot/{id}")
	public void deleteslot(@PathVariable int id ){
		 bookmyofficerepository.deleteById(id);
	}
	
	@GetMapping("/details/{id}")
	public void getdetails(@PathVariable int id ){
		 bookmyofficerepository.deleteById(id);
	}

}
