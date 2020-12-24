package edu.miu.cs544.service.response;

import edu.miu.cs544.domain.Reservation;
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
	public ReservationResponse(Reservation reservation) {
		super();
		if(reservation != null) {
			this.id = reservation.getId();
			this.code = reservation.getCode();
			this.reservationStatus = reservation.getReservationStatus();
			this.passengerId = reservation.getPassenger().getId();
			this.userEmail = reservation.getUserEmail();
		}
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

	public void setPassenger(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
