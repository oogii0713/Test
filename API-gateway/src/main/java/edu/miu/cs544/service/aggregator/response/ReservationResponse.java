package edu.miu.cs544.service.aggregator.response;

import edu.miu.cs544.util.Constant.ReservationStatus;

public class ReservationResponse {
	private Integer id;
	private String code;
	private ReservationStatus reservationStatus;
	private Integer passengerId;
	private String userEmail;
	
	public ReservationResponse() {
		super();
	}

	public ReservationResponse(Integer id, String code, ReservationStatus reservationStatus,
			Integer passengerId, String userEmail) {
		super();
		this.id = id;
		this.code = code;
		this.reservationStatus = reservationStatus;
		this.passengerId = passengerId;
		this.userEmail = userEmail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passenger) {
		this.passengerId = passenger;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
