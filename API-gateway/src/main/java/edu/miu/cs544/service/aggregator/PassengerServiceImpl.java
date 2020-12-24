package edu.miu.cs544.service.aggregator;

import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import edu.miu.cs544.service.aggregator.response.PassengerResponse;
import edu.miu.cs544.service.aggregator.request.PassengerRequest;

@Service
public class PassengerServiceImpl implements PassengerService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Qualifier("eurekaClient")
	@Autowired
    private EurekaClient eurekaClient;

    @Value("${reservation_service}")
    private String reservationServiceName;
    
    private String lookupUrlFor(String appName) {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(appName, false);
        return instanceInfo.getHomePageUrl();
    }
    
	@Override
	public PassengerResponse getById(Integer id) {
		return restTemplate.getForObject(lookupUrlFor(reservationServiceName) + "/passengers/" + id, PassengerResponse.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PassengerResponse> getAll() {
		return restTemplate.getForObject(lookupUrlFor(reservationServiceName) + "/passengers", List.class);
	}

	@Override
	public PassengerResponse save(PassengerRequest passengerRequest) {
		HttpEntity<String> request = prepareHttpRequest(passengerRequest);
		return restTemplate.postForObject(lookupUrlFor(reservationServiceName) + "/passengers", request, PassengerResponse.class);
	}

	@Override
	public PassengerResponse putPassenger(PassengerRequest passengerRequest, Integer id) {
		HttpEntity<String> request = prepareHttpRequest(passengerRequest);
		return restTemplate.exchange(lookupUrlFor(reservationServiceName) +
				"/passengers/" + id, HttpMethod.PUT, request, PassengerResponse.class).getBody();
	}

	@Override
	public PassengerResponse deleteById(Integer id) {
		//return restTemplate.deleteForObject(lookupUrlFor(reservationServiceName) + "/passengers/" + id, PassengerResponse.class);
		return null;
	}
	
	private HttpEntity<String> prepareHttpRequest(Object token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject json = new JSONObject(token);
		return new HttpEntity<>(json.toString(), headers);
	}

}
