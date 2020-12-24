package edu.miu.cs544.service.aggregator;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.miu.cs544.service.aggregator.response.FlightResponse;
import edu.miu.cs544.service.aggregator.request.FlightRequest;

public interface FlightService {
	FlightResponse getByNumber(Integer number);
	List<FlightResponse> getAllByNumbers(List<Integer> numbers);
	List<FlightResponse> getAll();

    void deleteFlight(Integer flightNumber);

    FlightResponse put(FlightRequest flightRequest, Integer flightNumber);

	Collection<FlightResponse> saveAll(Collection<FlightRequest> flights);

	List<FlightResponse> getDepartureAndDestination(Date departure_date, String departure_airport, String arrival_airport);
}
