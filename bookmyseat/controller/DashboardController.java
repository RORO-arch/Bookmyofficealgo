package com.project.bookmyseat.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookmyseat.models.Dashboard;
import com.project.bookmyseat.repository.Dashboardrepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class DashboardController {
 
  @Autowired
  Dashboardrepository dashboardrepository;
  
  @GetMapping("/seats/{date}")
  public Dashboard remainingseats(@PathVariable String date)
  {
	  return dashboardrepository.findavailableseats(date);
  }
  
  @PutMapping("/updateseats/{date}")
  public void updateseats(@PathVariable String date)
  {
	   dashboardrepository.updateseats(date);
  }
  
  @PutMapping("/addseats/{date}")
  public void addedseats(@PathVariable String date)
  {
	   dashboardrepository.addseats(date);
  }
	
}
