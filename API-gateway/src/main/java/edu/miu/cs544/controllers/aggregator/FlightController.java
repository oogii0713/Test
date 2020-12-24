package edu.miu.cs544.controllers.aggregator;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.miu.cs544.service.aggregator.request.FlightRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import edu.miu.cs544.service.aggregator.FlightService;
import edu.miu.cs544.service.aggregator.ReservationDetailService;
import edu.miu.cs544.domain.User;
import edu.miu.cs544.service.auth.UserService;
import edu.miu.cs544.service.aggregator.response.FlightResponse;
import edu.miu.cs544.service.aggregator.response.ReservationDetailResponse;

@RestController
@RequestMapping("/flights")
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private ReservationDetailService reservationDetailService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<FlightResponse> getAll() {
		return flightService.getAll();
	}
	
	@GetMapping(params = {"number"})
	public FlightResponse getByNumber(@RequestParam Integer number) {
		return flightService.getByNumber(number);
	}
	
	@GetMapping(params = {"reservation_code"})
	public List<FlightResponse> getAllByReservationCode(@RequestParam String reservation_code) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByUsername(auth.getName());
		
		ReservationDetailResponse[] details = reservationDetailService.getAllByReservationCodeAndUserRole(reservation_code, user);
		//get Flight info
		List<Integer> flightNumbers = Stream.of(details)
				.parallel().map(detail -> detail.getFlightNumber()).collect(Collectors.toList());
		return flightService.getAllByNumbers(flightNumbers);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public Collection<FlightResponse> saveFlights(@RequestBody Collection<FlightRequest> flights) {
		return flightService.saveAll(flights);
	}

	@PutMapping("/{flightNumber}")
	@PreAuthorize("hasRole('ADMIN')")
	public FlightResponse putFlights(@RequestBody FlightRequest flightRequest, @PathVariable Integer flightNumber) {
		return flightService.put(flightRequest, flightNumber);
	}

	@DeleteMapping("/{flightNumber}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteFlight(@PathVariable Integer flightNumber) {
		flightService.deleteFlight(flightNumber);
	}
	
	@GetMapping("/available")
	public List<FlightResponse> getAllflightBetweenDepartureAndDestinationForADate(@RequestParam Date departure_date,
																				   @RequestParam String departure_airport,
																				   @RequestParam String arrival_airport
	)
	{
		System.out.println(departure_date);
		return flightService.getDepartureAndDestination(departure_date,departure_airport,arrival_airport);

	}
}
