package edu.miu.cs544.controllers.aggregator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs544.service.aggregator.ReservationService;
import edu.miu.cs544.domain.ERole;
import edu.miu.cs544.domain.User;
import edu.miu.cs544.service.auth.UserService;
import edu.miu.cs544.service.aggregator.response.ReservationResponse;
import edu.miu.cs544.service.aggregator.response.ScheduleEmailResponse;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<ReservationResponse> getAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByUsername(auth.getName());
		
		if(user.getRole().getName() == ERole.ROLE_PASSENGER)
		{
			return reservationService.getAllByPassengerId(user.getPassengerId());
		}
		if(user.getRole().getName() == ERole.ROLE_AGENT)
		{
			return reservationService.getAllByUserEmail(user.getUsername());
		}
		
		return reservationService.getAll();
	}
	
	@GetMapping(params = {"code"})
	public ReservationResponse getByCode(@RequestParam String code) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByUsername(auth.getName());
		
		if(user.getRole().getName() == ERole.ROLE_PASSENGER)
		{
			return reservationService.getByCodeAndPassengerId(code, user.getPassengerId());
		}
		if(user.getRole().getName() == ERole.ROLE_AGENT)
		{
			return reservationService.getByCodeAndUserEmail(code, user.getUsername());
		}
		
		return reservationService.getByCode(code);
	}
	
	@GetMapping(params = {"passenger_id"})
	@PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
	public List<ReservationResponse> getAllByPassengerId(@RequestParam Integer passenger_id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByUsername(auth.getName());
		
		if(user.getRole().getName() == ERole.ROLE_AGENT)
		{
			return reservationService.getAllByUserEmailAndPassengerId(user.getUsername(), user.getPassengerId());
		}
		return reservationService.getAllByPassengerId(passenger_id);
	}
	
	@GetMapping(params = {"user_email"})
	@PreAuthorize("hasRole('ADMIN')")
	public List<ReservationResponse> getAllByUserEmail(@RequestParam String user_email) {
		return reservationService.getAllByUserEmail(user_email);
	}
	
	@GetMapping(params = {"user_email", "passenger_id"})
	@PreAuthorize("hasRole('ADMIN')")
	public List<ReservationResponse> getAllByUserEmailAndPassengerId(@RequestParam String user_email, @RequestParam Integer passenger_id) {
		return reservationService.getAllByUserEmailAndPassengerId(user_email, passenger_id);
	}
	
	 
    @GetMapping("/{code}/flights")
	public List<Integer> getAllFlightByReservationCode(@PathVariable String code) {
	   return reservationService.getAllFlightsByReservationCode(code);
	}
	
	@PostMapping("/{code}")
    public ReservationResponse cancelReservation(@PathVariable String code) {
        return reservationService.cancelReservation(code);
    }
 
	@PostMapping("/passenger/{id}")
	public ReservationResponse makeReservation(@PathVariable Integer id, @RequestBody List<Integer> flightNumbers){
	    return reservationService.makeReservation(id, flightNumbers);            
	}
	
	@PostMapping("/{code}/tickets")
	public ScheduleEmailResponse finalizeReservation(@PathVariable String code) {
		return reservationService.finalizeReservation(code);
	}
}
