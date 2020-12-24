package edu.miu.cs544.service.response;

import java.util.List;

public class TicketResponseAndEmailScheduleRequest {
	
	private List<TicketResponse> tickets;
	private PassengerResponse passenger;
	
	public TicketResponseAndEmailScheduleRequest() {
		
	}
	public TicketResponseAndEmailScheduleRequest(List<TicketResponse> tickets, PassengerResponse passenger) {
		super();
		this.tickets = tickets;
		this.passenger = passenger;
	}
	public List<TicketResponse> getTickets() {
		return tickets;
	}
	public void setTickets(List<TicketResponse> tickets) {
		this.tickets = tickets;
	}
	public PassengerResponse getPassenger() {
		return passenger;
	}
	public void setPassengerResponse(PassengerResponse passenger) {
		this.passenger = passenger;
	}
	
}
