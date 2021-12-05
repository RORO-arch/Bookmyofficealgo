package com.project.bookmyseat.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.bookmyseat.models.Bookmyoffice;
import com.project.bookmyseat.models.Dashboard;

@Repository
@Transactional
public class Dashboardrepository {

	@Autowired
	EntityManager em;
	
	public Dashboard findavailableseats(String bookingdate ){
		Query query = em.createNativeQuery("Select * from dashboard where booking_date Like ? Limit 1",Dashboard.class);
		query.setParameter(1,bookingdate);
		
		return (Dashboard) query.getSingleResult();
	}
	
	public void updateseats(String bookingdate)
	{   Dashboard remseats=findavailableseats(bookingdate);
	Query query = em.createNativeQuery("UPDATE dashboard SET seats_available=? where booking_date Like ? ",Dashboard.class);
	query.setParameter(1,remseats.getSeatsAvailable()-1);
	query.setParameter(2,bookingdate);
	query.executeUpdate();
	     	        
	}

	public void addseats(String bookingdate)
	{   Dashboard remseats=findavailableseats(bookingdate);
	Query query = em.createNativeQuery("UPDATE dashboard SET seats_available=? where booking_date Like ? ",Dashboard.class);
	query.setParameter(1,remseats.getSeatsAvailable()+1);
	query.setParameter(2,bookingdate);
	query.executeUpdate();
	     	        
	}
}
