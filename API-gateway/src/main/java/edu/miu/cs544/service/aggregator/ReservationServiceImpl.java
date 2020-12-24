package edu.miu.cs544.service.aggregator;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import edu.miu.cs544.service.aggregator.response.FlightResponse;
import edu.miu.cs544.service.aggregator.response.PassengerResponse;
import edu.miu.cs544.service.aggregator.response.ReservationDetailResponse;
import edu.miu.cs544.service.aggregator.response.ReservationResponse;
import edu.miu.cs544.service.aggregator.response.ScheduleEmailRequest;
import edu.miu.cs544.service.aggregator.response.ScheduleEmailResponse;
import edu.miu.cs544.service.aggregator.response.TicketResponseAndEmailScheduleRequest;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private RestTemplate restTemplate;

	@Qualifier("eurekaClient")
	@Autowired
    private EurekaClient eurekaClient;
	
	@Autowired
	private FlightService flightService;

    @Value("${reservation_service}")
    private String reservationServiceName;
    
    @Value("${quartz_email_service}")
    private String quartzEmailService;
    
    private String lookupUrlFor(String appName) {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(appName, false);
        return instanceInfo.getHomePageUrl();
    }
	
	@Override
	public ReservationResponse getByCode(String code) {
		return restTemplate.getForObject(lookupUrlFor(reservationServiceName) + "/reservations?code="+code, ReservationResponse.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationResponse> getAll() {
		return restTemplate.getForObject(lookupUrlFor(reservationServiceName) + "/reservations", List.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationResponse> getAllByPassengerId(Integer passenger_id) {
		return restTemplate.getForObject(lookupUrlFor(reservationServiceName) + "/reservations?passenger_id="+passenger_id, List.class);
	}

	@Override
	public ScheduleEmailResponse finalizeReservation(String code) {
		TicketResponseAndEmailScheduleRequest ticketsAndEmailRequest = restTemplate
				.postForObject(lookupUrlFor(reservationServiceName) + "/reservations/" 
						+ code + "/tickets", new HttpEntity<>(""), TicketResponseAndEmailScheduleRequest.class);
		ScheduleEmailRequest request = buildEmailRequest(ticketsAndEmailRequest);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject json = new JSONObject(request);
		HttpEntity<String> postRequest = new HttpEntity<>(json.toString(), headers);
		return restTemplate.postForObject(lookupUrlFor(quartzEmailService) + "/schedule-email", postRequest, ScheduleEmailResponse.class);

	}
	
	private ScheduleEmailRequest buildEmailRequest(TicketResponseAndEmailScheduleRequest ticketsAndEmailScheduleRequest) {
		PassengerResponse passenger = ticketsAndEmailScheduleRequest.getPassenger();
		List<ReservationDetailResponse> details = ticketsAndEmailScheduleRequest
					.getTickets().stream()
					.map(t -> t.getReservationDetail())
					.collect(Collectors.toList());
		ReservationDetailResponse detail = details.get(0);
		FlightResponse flight = flightService.getByNumber(detail.getFlightNumber());
		String pattern = "yyyy-MM-dd hh-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String departureDate = simpleDateFormat.format(flight.getDepartureDate());
		String arrivalDate = simpleDateFormat.format(flight.getArrivalDate());
		return new ScheduleEmailRequest(passenger.getEmail(), passenger.getFirstName(), passenger.getLastName(), flight.getNumber(), flight.getAirline().getName(),
				flight.getDepartureAirport().getName(), flight.getArrivalAirport().getName(), departureDate, arrivalDate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationResponse> getAllByUserEmail(String user_email) {
		return restTemplate.getForObject(lookupUrlFor(reservationServiceName) + "/reservations?user_email="+user_email, List.class);
	}
	
	@Override
	public List<Integer> getAllFlightsByReservationCode(String code) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationResponse> getAllByUserEmailAndPassengerId(String user_email, Integer passenger_id) {
		return restTemplate.getForObject(
				lookupUrlFor(reservationServiceName) + "/reservations?user_email="+user_email+"&passenger_id="+passenger_id, List.class);
	}

	@Override
	public ReservationResponse getByCodeAndPassengerId(String code, Integer passenger_id) {
		return restTemplate.getForObject(lookupUrlFor(reservationServiceName) 
				+ "/reservations?code="+code+"&passenger_id="+passenger_id, ReservationResponse.class);
	}

	@Override
	public ReservationResponse getByCodeAndUserEmail(String code, String user_email) {
		return restTemplate.getForObject(lookupUrlFor(reservationServiceName) 
				+ "/reservations?code="+code+"&user_email="+user_email, ReservationResponse.class);
	}
	
	@Override
	public ReservationResponse cancelReservation(String code) {
		return restTemplate.postForObject(lookupUrlFor(reservationServiceName) 
				+ "/reservations/" + code, new HttpEntity<>("", getHeaders()), ReservationResponse.class);
	}

	@Override
	public ReservationResponse makeReservation(Integer id, List<Integer> flightNumbers) {
		HttpEntity<Object> request = new HttpEntity<>(flightNumbers, getHeaders());
		return restTemplate.postForObject(lookupUrlFor(reservationServiceName) 
				+ "/reservations/passenger/" + id, request, ReservationResponse.class);
	}
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
