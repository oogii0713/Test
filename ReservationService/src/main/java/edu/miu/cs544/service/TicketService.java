package edu.miu.cs544.service;

import java.util.List;

import edu.miu.cs544.service.response.TicketResponse;

public interface TicketService {
	List<TicketResponse> purchaseTickets(String code, Integer passengerId);
	List<TicketResponse> getTickets(String code);
}
