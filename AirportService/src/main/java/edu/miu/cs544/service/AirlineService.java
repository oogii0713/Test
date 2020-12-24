package edu.miu.cs544.service;

import java.util.Collection;
import java.util.List;

import edu.miu.cs544.service.request.AirlineRequest;
import edu.miu.cs544.service.response.AirlineResponse;

public interface AirlineService {
	AirlineResponse getById(Integer id);
	AirlineResponse getByCode(String code);
	List<AirlineResponse> getAll();
	List<AirlineResponse> getAllByDepartureAirportCode(String code);
	Collection<AirlineResponse> saveAll(Collection<AirlineRequest> airlines);
	AirlineResponse put(AirlineRequest airlineRequest, String code);
	void deleteAirline(String code);
}
