package com.project.bookmyseat.models;


import java.util.*;
import javax.persistence.*;

@Entity
public class Bookmyoffice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@Column(
			nullable=false
			)
	private String bookingDate;
	
	
	private int floorId;
	
	
	private int portId;
	
	
	private int deskId;
	@ManyToOne
    @JoinColumn(name="empId")

	private Employee employee;			
	private int managerId;
	
	
	
	public Bookmyoffice() {
		
	}


	public String getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}


	public int getFloorId() {
		return floorId;
	}


	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}


	public int getPortId() {
		return portId;
	}


	public void setPortId(int portId) {
		this.portId = portId;
	}


	public int getDeskId() {
		return deskId;
	}


	public void setDeskId(int deskId) {
		this.deskId = deskId;
	}
	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

}
