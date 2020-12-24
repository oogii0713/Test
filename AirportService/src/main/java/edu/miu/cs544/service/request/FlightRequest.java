package edu.miu.cs544.service.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.miu.cs544.domain.Flight;

public class FlightRequest {
	private Integer number;
	private Integer capacity;
	private String airlineCode;
	private String departureAirportCode;
	private String arrivalAirportCode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date departureDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date arrivalDate;
	
	public FlightRequest() {
		super();
	}
	
	public FlightRequest(Flight flight) {
		super();
		if(flight != null) {
			this.number = flight.getNumber();
			this.capacity = flight.getCapacity();
			this.airlineCode = flight.getAirline().getCode();
			this.departureAirportCode = flight.getDepartureAirport().getCode();
			this.arrivalAirportCode = flight.getArrivalAirport().getCode();
			this.departureDate = flight.getDepartureDate();
			this.arrivalDate = flight.getArrivalDate();
		}
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
	
	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}

	public void setArrivalAirportCode(String arrivalAirportCode) {
		this.arrivalAirportCode = arrivalAirportCode;
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
