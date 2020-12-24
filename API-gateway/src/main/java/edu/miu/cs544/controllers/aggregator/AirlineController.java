package edu.miu.cs544.controllers.aggregator;

import java.util.Collection;
import java.util.List;

import edu.miu.cs544.service.aggregator.request.AirlineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.miu.cs544.service.aggregator.AirlineService;
import edu.miu.cs544.service.aggregator.response.AirlineResponse;

@RestController
@RequestMapping("/airlines")
public class AirlineController {
	@Autowired
	private AirlineService airlineService;
	
	@GetMapping
	public List<AirlineResponse> getAll() {
		return airlineService.getAll();
	}
	
	@GetMapping(params = {"code"})
	public AirlineResponse getByCode(@RequestParam String code) {
		return airlineService.getByCode(code);
	}
	
	@GetMapping(params = {"departure_airport_code"})
	public List<AirlineResponse> getByDepartureAirportCode(@RequestParam String departure_airport_code) {
		return airlineService.getAllByDepartureAirportCode(departure_airport_code);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public Collection<AirlineResponse> saveAirlines(@RequestBody Collection<AirlineRequest> airlines) {
		return airlineService.saveAll(airlines);
	}

	@PutMapping("/{code}")
	@PreAuthorize("hasRole('ADMIN')")
	public AirlineResponse putAirline(@RequestBody AirlineRequest airlineRequest, @PathVariable String code) {
		return airlineService.put(airlineRequest, code);
	}

	@DeleteMapping("/{code}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAirline(@PathVariable String code) {
		airlineService.deleteAirline(code);
	}
}
