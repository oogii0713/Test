package edu.miu.cs544.service.aggregator;

import java.util.List;

import edu.miu.cs544.service.aggregator.response.PassengerResponse;
import edu.miu.cs544.service.aggregator.request.PassengerRequest;

public interface PassengerService {
	PassengerResponse getById(Integer id);
	List<PassengerResponse> getAll();
	PassengerResponse save(PassengerRequest passengerRequest);
	PassengerResponse putPassenger(PassengerRequest passengerRequest, Integer id);
	PassengerResponse deleteById(Integer id);
}
