package edu.miu.cs544.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.miu.cs544.service.request.FlightRequest;
import edu.miu.cs544.service.response.FlightResponse;

public interface FlightService {
	FlightResponse getById(Integer id);
	FlightResponse getByNumber(Integer number);
	List<FlightResponse> getAll();
	List<FlightResponse> getAllByNumbers(List<Integer> numbers);
	List<FlightResponse> getAllByDepartureAirportCode(String code);
	Collection<FlightResponse> saveAll(Collection<FlightRequest> flights);
	FlightResponse put(FlightRequest flightRequest, Integer flightNumber);
	void deleteFlight(Integer flightNumber);
	List<FlightResponse> getDepartureAndDestination(Date departure_date, String departure_airport, String arrival_airport);
}
