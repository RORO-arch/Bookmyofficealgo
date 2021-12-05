package com.project.bookmyseat.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.bookmyseat.models.Employee;

@Repository
public class Employeerepository {
	
	@Autowired
	EntityManager em;
	
	public Employee findById(int empId) {
		return em.find(Employee.class, empId);
	}
	
	public Employee findByEmail(String email) {
		Query query = em.createNativeQuery("Select * from employee where email LIKE ? LIMIT 1",Employee.class);
		query.setParameter(1, email);
		Employee employee=null;
		try {
		employee= (Employee) query.getSingleResult();
		}
		catch (NoResultException nre){}
		return employee;
	}
	
	public Employee findByPassword(String password) {
		Query query = em.createNativeQuery("Select * from employee where password LIKE ? LIMIT 1",Employee.class);
		query.setParameter(1, password);
		Employee employee=null;
		try {
		employee= (Employee) query.getSingleResult();
		}
		catch (NoResultException nre){}
		return employee;
	}
	
	
	public Employee findByEmailAndPassword(String email,String password) {
		Query query = em.createNativeQuery("Select * from employee where email LIKE ? and password LIKE ?  LIMIT 1",Employee.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		Employee employee=null;
		try {
		employee= (Employee) query.getSingleResult();
		}
		catch (NoResultException nre){}
		return employee;
	}
	
	

}
