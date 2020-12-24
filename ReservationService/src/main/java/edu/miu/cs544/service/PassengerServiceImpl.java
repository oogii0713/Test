package edu.miu.cs544.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.cs544.domain.Passenger;
import edu.miu.cs544.repository.PassengerRepository;
import edu.miu.cs544.service.request.PassengerRequest;
import edu.miu.cs544.service.response.PassengerResponse;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;
	
	@Override
	public PassengerResponse getById(Integer id) {
		Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		return new PassengerResponse(passenger);
	}
	
	@Override
	public List<PassengerResponse> getAll() {
		return passengerRepository.findAll()
				.parallelStream()
				.map(PassengerResponse::new)
				.collect(Collectors.toList());
	}

	@Override
	public PassengerResponse save(PassengerRequest passengerRequest) {
		Passenger passenger = new Passenger(passengerRequest);
		passengerRepository.save(passenger);
		return new PassengerResponse(passenger);
	}

	@Override
	public PassengerResponse deleteById(Integer id) {
		Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		passengerRepository.delete(passenger);
		return new PassengerResponse(passenger);
	}

	@Override
	public PassengerResponse putPassenger(PassengerRequest passengerRequest, Integer id) {
		Passenger passenger;
		try {
			passenger = passengerRepository.findById(id).get();
			passenger = new Passenger(passengerRequest);
			passenger.setId(id);
			passengerRepository.save(passenger);
		} catch (NoSuchElementException ex) {
			passenger = passengerRepository.save(new Passenger(passengerRequest));
		}
		
		return new PassengerResponse(passenger);
	}
}
