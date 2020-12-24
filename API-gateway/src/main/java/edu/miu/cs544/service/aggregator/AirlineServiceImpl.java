package edu.miu.cs544.service.aggregator;

import java.util.Collection;
import java.util.List;

import edu.miu.cs544.service.aggregator.request.AirlineRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import edu.miu.cs544.service.aggregator.response.AirlineResponse;

@Service
public class AirlineServiceImpl implements AirlineService{
	@Autowired
	private RestTemplate restTemplate;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${airport_service}")
    private String airportServiceName;
    
    private String lookupUrlFor(String appName) {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(appName, false);
        return instanceInfo.getHomePageUrl();
    }
    
    public AirlineResponse getByCode(String code) {
		return restTemplate.getForObject(lookupUrlFor(airportServiceName) + "/airlines?code="+code, AirlineResponse.class);
	}
    
    @SuppressWarnings("unchecked")
	public List<AirlineResponse> getAll() {
        return restTemplate.getForObject(lookupUrlFor(airportServiceName) + "/airlines", List.class);
    }
	
	@SuppressWarnings("unchecked")
	public List<AirlineResponse> getAllByDepartureAirportCode(String departure_airport_code) {
		return restTemplate.getForObject(lookupUrlFor(airportServiceName) + "/airlines?departure_airport_code="+departure_airport_code, List.class);
	}

    @Override
    public Collection<AirlineResponse>  saveAll(Collection<AirlineRequest> airlines) {
        return restTemplate.postForObject(lookupUrlFor(airportServiceName) + "/airlines", airlines, Collection.class);
    }

    @Override
    public AirlineResponse put(AirlineRequest airlineRequest, String code) {
        HttpEntity<String> request = prepareHttpRequest(airlineRequest);
        return restTemplate.exchange(lookupUrlFor(airportServiceName) + "/airlines/" + code, HttpMethod.PUT,request, AirlineResponse.class).getBody();
    }

    @Override
    public void deleteAirline(String code) {
//        return restTemplate.exchange(lookupUrlFor(airportServiceName) + "/airlines/" + code, HttpMethod.DELETE, new HttpEntity<>(""), AirlineResponse.class).getBody();
        restTemplate.delete(lookupUrlFor(airportServiceName) + "/airlines/" + code);
    }

    private HttpEntity<String> prepareHttpRequest(Object token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject json = new JSONObject(token);
        return new HttpEntity<>(json.toString(), headers);
    }

}
