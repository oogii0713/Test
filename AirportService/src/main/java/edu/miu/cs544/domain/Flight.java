package edu.miu.cs544.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer number;
  
	private Integer capacity;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	private Airline airline;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	private Airport departureAirport;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	private Airport arrivalAirport;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDate;
	
	public Flight() {
		super();
	}
	
	public Flight(Integer number, Integer capacity, Airline airline, Airport departureAirport, Airport arrivalAirport, Date departureDate, Date arrivalDate) {
		super();
		this.number = number;
		this.capacity = capacity;
		this.airline = airline;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	public Airport getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}
	public Airport getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	
}
