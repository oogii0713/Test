package edu.miu.cs544.service.aggregator;


import java.util.Collection;
import java.util.List;

import edu.miu.cs544.service.aggregator.response.AirportResponse;
import edu.miu.cs544.service.aggregator.request.AirportRequest;

public interface AirportService {
	AirportResponse getByCode(String code);
	List<AirportResponse> getAll();

    Collection<AirportResponse> saveAll(Collection<AirportRequest> airports);

	AirportResponse put(AirportRequest airportRequest, String code);

	void deleteAirport(String code);
}
