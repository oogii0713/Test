package edu.miu.cs544.controllers.aggregator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs544.service.aggregator.PassengerService;
import edu.miu.cs544.domain.ERole;
import edu.miu.cs544.domain.User;
import edu.miu.cs544.service.auth.UserService;
import edu.miu.cs544.service.aggregator.response.PassengerResponse;
import edu.miu.cs544.service.aggregator.request.PassengerRequest;

@RestController
@RequestMapping("/passengers")
public class PassengerController {
	@Autowired
	private PassengerService passengerService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<PassengerResponse> getAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByUsername(auth.getName());
		
		if(user.getRole().getName() == ERole.ROLE_PASSENGER)
		{
			List<PassengerResponse> list = new ArrayList<>();
			list.add(passengerService.getById(user.getPassengerId()));
			return list;
		}
		
		return passengerService.getAll();
	} 
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
	public PassengerResponse getById(@PathVariable Integer id) {
		return passengerService.getById(id);
	}
	
	@PostMapping("/new")
	public PassengerResponse newPassenger(@RequestBody PassengerRequest passengerRequest) {
		return passengerService.save(passengerRequest);
	}
	
	@PutMapping("/{id}")
	public PassengerResponse putPassenger(@RequestBody PassengerRequest passengerRequest, @PathVariable Integer id) {
		return passengerService.putPassenger(passengerRequest, id);		
	}
	@DeleteMapping("/{id}")
	public PassengerResponse deletePassenger(@PathVariable Integer id) {
		return passengerService.deleteById(id);
	}
}
