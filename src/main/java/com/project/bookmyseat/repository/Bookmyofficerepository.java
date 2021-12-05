package com.project.bookmyseat.repository;

import java.io.Console;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.bookmyseat.models.Bookmyoffice;
import com.project.bookmyseat.models.Employee;

@Repository
@Transactional
public class Bookmyofficerepository {
	
	//inserting booking details
	@Autowired
	EntityManager em;
	
	public Bookmyoffice findByEmpidAndBookingdate(int empId,String bookingDate) {
		Query query = em.createNativeQuery("Select * from bookmyoffice where emp_id = ? and booking_date LIKE ?  LIMIT 1",Bookmyoffice.class);
		query.setParameter(1, empId);
		query.setParameter(2, bookingDate);
	Bookmyoffice bookmyoffice=null;
		try {
		bookmyoffice= (Bookmyoffice) query.getSingleResult();
		}
		catch (NoResultException nre){}
		return bookmyoffice;
	}
	
	public Bookmyoffice savebookingdetails(Bookmyoffice bookmyoffice) {
		Bookmyoffice bookmyoffice2=findByEmpidAndBookingdate(bookmyoffice.getEmployee().getEmpId(),bookmyoffice.getBookingDate());
		if(bookmyoffice2==null) {
			em.persist(bookmyoffice);
		}
		else {
			return null;
		}
		return bookmyoffice;
	}
/*	
	public Bookmyoffice updateBookingById(Bookmyoffice bookmyoffice) {
		Employee employee1=findByEmpidAndBookingdate(bookmyoffice.getEmployee().getEmpId(),bookmyoffice.getBookingDate());
      
		if(employee1!=null) {
			Query query = em.createNativeQuery("UPDATE bookmyoffice SET bookindId=?,bookingDate = ?,deskId=?,floorId=?,managerId=?,portId=? where empId = ? ",Bookmyoffice.class);
			query.setParameter(1,bookmyoffice.getBookindId());
			query.setParameter(2,bookmyoffice.getBookingDate());
			query.setParameter(3,bookmyoffice.getDeskId());
			query.setParameter(4,bookmyoffice.getFloorId());
			query.setParameter(5,bookmyoffice.getFloorId());
			query.setParameter(6,bookmyoffice.getManagerId());
			query.setParameter(7,bookmyoffice.getPortId());
			if(query.executeUpdate()>0);
			return bookmyoffice;
		}
		else
			return null;
	}
	*/
	/*public void deleteByEmpidandBookingDate(Bookmyoffice bookmyoffice) {
		Employee employee =findByEmpidAndBookingdate(bookmyoffice.getEmployee().getEmpId(),bookmyoffice.getBookingDate()); 
		if(employee!=null)
		{
		em.remove(bookmyoffice);
		}
	}*/
	public Bookmyoffice findByBookingId(int id) {
		return em.find(Bookmyoffice.class, id);
	}
	
	public void deleteById(int id) {
		Bookmyoffice bookmyoffice = findByBookingId(id);
		em.remove(bookmyoffice);
	}
	
	public List<Bookmyoffice> findAll(){
		Query query = em.createNativeQuery("Select * from bookmyoffice",Bookmyoffice.class);
		return query.getResultList();
	}
	
	public List<Bookmyoffice> findByEmpid(int empId) {
		Query query = em.createNativeQuery("Select * from bookmyoffice where emp_id = ?",Bookmyoffice.class);
		query.setParameter(1, empId);
		List<Bookmyoffice> bookmyoffice=null;
		try {
		bookmyoffice=  query.getResultList();
		}
		catch (NoResultException nre){}
		return bookmyoffice;
	}
	
	public List<Bookmyoffice> getEmployeeByManagerId(int id,String bookingdate) {
		Query query = em.createNativeQuery("Select * from bookmyoffice where manager_id=? and booking_date Like ?",Bookmyoffice.class);
		query.setParameter(1,id);
		query.setParameter(2, bookingdate);
		return query.getResultList();
	}
	
	public List<Bookmyoffice> getManagers(String bookingdate) {
		Query query = em.createNativeQuery("Select distinct * from bookmyoffice where booking_date Like ? ",Bookmyoffice.class);
		query.setParameter(1,bookingdate);
		return query.getResultList();
	}

	public void updateSeat(int mgrid,int f,int p,int start,String bookingdate) {
		List<Bookmyoffice> result=getEmployeeByManagerId(mgrid,bookingdate);
		for(Bookmyoffice b:result) {
			Query query = em.createNativeQuery("UPDATE bookmyoffice SET floor_id=?,port_id= ?,desk_id=?,where emp_id = ? ",Bookmyoffice.class);
			query.setParameter(1,f);
			query.setParameter(2,p);
			query.setParameter(3,start);
			query.setParameter(4,b.getEmployee().getEmpId());
			query.executeUpdate();
			start+=1;
		}
		
	}
	
	public int teamsize(int managerid,String bookingdate)
	{
		Query query = em.createNativeQuery("Select count(*) from bookmyoffice where manager_id=? and booking_date Like ?",Bookmyoffice.class);
		query.setParameter(1,managerid);
		query.setParameter(2, bookingdate);
		 return query.getFirstResult();	
	}
	
	
}
