package edu.miu.cs544.service;

import java.util.Collection;
import java.util.List;

import edu.miu.cs544.service.request.AirportRequest;
import edu.miu.cs544.service.response.AirportResponse;

public interface AirportService {
	AirportResponse getById(Integer id);
	AirportResponse getByCode(String code);
	List<AirportResponse> getAll();
	Collection<AirportResponse> saveAll(Collection<AirportRequest> airports);
	AirportResponse put(AirportRequest airportRequest, String code);
	void deleteAirport(String code);
}
