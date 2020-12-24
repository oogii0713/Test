package edu.miu.cs544.controller;

import edu.miu.cs544.service.FlightService;
import edu.miu.cs544.service.request.FlightRequest;
import edu.miu.cs544.service.response.FlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<FlightResponse> getAll() {
        return flightService.getAll();
    }

    @GetMapping(params = {"number"})
    public FlightResponse getByNumber(@RequestParam Integer number) {
        return flightService.getByNumber(number);
    }

    @GetMapping(params = {"numbers"})
    public List<FlightResponse> getByNumber(@RequestParam List<Integer> numbers) {
        return flightService.getAllByNumbers(numbers);
    }

    @PostMapping
    public Collection<FlightResponse> saveFlights(@RequestBody Collection<FlightRequest> flights) {
        try {
            return flightService.saveAll(flights);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping("/{flightNumber}")
    public FlightResponse putFlights(@RequestBody FlightRequest flightRequest, @PathVariable Integer flightNumber) {
        try {
            return flightService.put(flightRequest, flightNumber);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/{flightNumber}")
    public void deleteFlight(@PathVariable Integer flightNumber) {
        try {
            flightService.deleteFlight(flightNumber);
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/available")
    public List<FlightResponse> getAllflightBetweenDepartureAndDestinationForADate(@RequestParam Date departure_date,
                                                                                   @RequestParam String departure_airport,
                                                                                   @RequestParam String arrival_airport
    ) {
        System.out.println(departure_date);
        return flightService.getDepartureAndDestination(departure_date, departure_airport, arrival_airport);

    }

}
