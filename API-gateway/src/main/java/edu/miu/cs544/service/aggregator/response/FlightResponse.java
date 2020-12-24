package edu.miu.cs544.service.aggregator.response;

import java.util.Date;

public class FlightResponse {
	private Integer id;
	private Integer number;
	private Integer capacity;
	private AirlineResponse airline;
	private AirportResponse departureAirport;
	private AirportResponse arrivalAirport;
	private Date departureDate;
	private Date arrivalDate;
	
	public FlightResponse() {
		super();
	}
	
	public FlightResponse(Integer number, Integer capacity, AirlineResponse airline, AirportResponse departureAirport, AirportResponse arrivalAirport,
			Date departureDate, Date arrivalDate) {
		super();
		this.number = number;
		this.capacity = capacity;
		this.airline = airline;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}

	public FlightResponse(Integer id, Integer number, Integer capacity, AirlineResponse airline,
			AirportResponse departureAirport, AirportResponse arrivalAirport, Date departureDate, Date arrivalDate) {
		super();
		this.id = id;
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
	public AirlineResponse getAirline() {
		return airline;
	}
	public void setAirline(AirlineResponse airline) {
		this.airline = airline;
	}
	public AirportResponse getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(AirportResponse departureAirport) {
		this.departureAirport = departureAirport;
	}
	public AirportResponse getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(AirportResponse arrivalAirport) {
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
