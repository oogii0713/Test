package edu.miu.cs544.service.aggregator;

import java.util.Collection;
import java.util.List;

import edu.miu.cs544.service.aggregator.response.AirlineResponse;
import edu.miu.cs544.service.aggregator.request.AirlineRequest;

public interface AirlineService {
	AirlineResponse getByCode(String code);
	List<AirlineResponse> getAll();
	List<AirlineResponse> getAllByDepartureAirportCode(String departure_airport_code);

	Collection<AirlineResponse> saveAll(Collection<AirlineRequest> airlines);

	AirlineResponse put(AirlineRequest airlineRequest, String code);

	void deleteAirline(String code);
}
