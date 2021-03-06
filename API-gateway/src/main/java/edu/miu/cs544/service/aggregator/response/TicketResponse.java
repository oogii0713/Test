package edu.miu.cs544.service.aggregator.response;


public class TicketResponse {
	private Integer id;
	private String number;
	private ReservationDetailResponse reservationDetail;
	
	public TicketResponse() {
		super();
	}
	
	public TicketResponse(String number, ReservationDetailResponse reservationDetail) {
		super();
		this.number = number;
		this.reservationDetail = reservationDetail;
	}

	public TicketResponse(Integer id, String number, ReservationDetailResponse reservationDetail) {
		super();
		this.id = id;
		this.number = number;
		this.reservationDetail = reservationDetail;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public ReservationDetailResponse getReservationDetail() {
		return reservationDetail;
	}
	public void setReservationDetail(ReservationDetailResponse reservationDetail) {
		this.reservationDetail = reservationDetail;
	}
}
