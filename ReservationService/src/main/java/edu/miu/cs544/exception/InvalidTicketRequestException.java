package edu.miu.cs544.exception;

import java.util.Collection;

import edu.miu.cs544.domain.Ticket;
import edu.miu.cs544.service.response.TicketResponse;

public class InvalidTicketRequestException extends RuntimeException {
	
	private Collection<TicketResponse> tickets;
	
	public InvalidTicketRequestException(String message, Throwable cause, Collection<TicketResponse> alternate) {
		super(message, cause);
		this.tickets = alternate;
	}

	public Collection<TicketResponse> getTickets() {
		return tickets;
	}
	

}
