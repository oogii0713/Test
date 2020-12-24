package edu.miu.cs544.service.response;

import edu.miu.cs544.domain.ReservationDetail;

public class ReservationDetailResponse {
	private Integer id;
	private String reservationCode;
	private Integer flightNumber;
	
	public ReservationDetailResponse() {
		super();
	}
	public ReservationDetailResponse(ReservationDetail reservationDetail) {
		super();
		if(reservationDetail != null) {
			this.id = reservationDetail.getId();
			this.reservationCode = reservationDetail.getReservation().getCode();
			this.flightNumber = reservationDetail.getFlightNumber();
		}
	}
	
	public ReservationDetailResponse(Integer id, String reservation, Integer flightNumber) {
		super();
		this.id = id;
		this.reservationCode = reservation;
		this.flightNumber = flightNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReservationCode() {
		return reservationCode;
	}
	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}
	public Integer getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}	
}
