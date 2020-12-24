package edu.miu.cs544.controller;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.miu.cs544.service.AirportService;
import edu.miu.cs544.service.request.AirportRequest;
import edu.miu.cs544.service.response.AirportResponse;

@RestController
@RequestMapping("/airports")
public class AirportController {
	@Autowired
	private AirportService airportService;
	
	@GetMapping
	public List<AirportResponse> getAll() {
		return airportService.getAll();
	}
	
	@GetMapping(params = {"code"})
	public AirportResponse getByCode(@RequestParam String code) {
		return airportService.getByCode(code);
	}
	
	@PostMapping
	public Collection<AirportResponse> saveAirports(@RequestBody Collection<AirportRequest> airports) {
		return airportService.saveAll(airports);
	}
	
	@PutMapping("/{code}")
	public AirportResponse putAirport(@RequestBody AirportRequest airportRequest, @PathVariable String code) {
		try {
			return airportService.put(airportRequest, code);
		} catch (IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@DeleteMapping("/{code}")
	public void deleteAirport(@PathVariable String code) {
		try {
			airportService.deleteAirport(code);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		} catch (IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
		}
	}
	
}
