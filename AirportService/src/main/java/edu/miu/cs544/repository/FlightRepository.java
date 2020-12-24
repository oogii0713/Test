package edu.miu.cs544.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import edu.miu.cs544.domain.Airline;
import edu.miu.cs544.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.miu.cs544.domain.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
	Flight findByNumber(Integer number);
	List<Flight> findByDepartureAirportCode(String code);
	
	@Query( "select f from Flight f where Number in :numbers" )
	List<Flight> findAllByNumbers(List<Integer> numbers);
//	List<Flight> findByDepartureAirportCodeAndArrivalAirportCodeAndDepartureDate(String departureAirportCode, String arrivalAirportCode, Date departureDate);

	Flight findFirstByArrivalAirportOrDepartureAirport(Airport arrivalAirport, Airport departureAirport);
	Flight findFirstByAirline(Airline airline);
	List<Flight> findByDepartureAirportCodeAndArrivalAirportCodeAndDepartureDateAfter(String departureAirportCode,
																					  String arrivalAirportCode,
																					  Date departure_date);

}
