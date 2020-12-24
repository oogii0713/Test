package edu.miu.cs544.service;

import java.util.List;

import edu.miu.cs544.service.request.PassengerRequest;
import edu.miu.cs544.service.response.PassengerResponse;

public interface PassengerService {
	PassengerResponse getById(Integer id);
	List<PassengerResponse> getAll();
	PassengerResponse save(PassengerRequest passengerRequest);
	PassengerResponse deleteById(Integer id);
	PassengerResponse putPassenger(PassengerRequest passengerRequest, Integer id);
}
