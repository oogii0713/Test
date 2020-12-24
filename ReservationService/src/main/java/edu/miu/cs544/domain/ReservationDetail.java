package edu.miu.cs544.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReservationDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn
	private Reservation reservation;
	
	private Integer flightNumber;
	
	public ReservationDetail() {
		super();
	}
	
	public ReservationDetail(Integer flightNumber) {
		super();
		this.flightNumber = flightNumber;
	}
	
	public ReservationDetail(Reservation reservation, Integer flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Integer getFlightNumber() {
		return flightNumber;
	}
	public void setFlight(Integer flightNumber) {
		this.flightNumber = flightNumber;
	} 
	
	
}
