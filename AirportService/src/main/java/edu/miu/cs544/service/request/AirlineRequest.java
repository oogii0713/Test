package edu.miu.cs544.service.request;

import edu.miu.cs544.domain.Airline;

public class AirlineRequest {
	private String code;
	private String name;
	private String history;
	
	public AirlineRequest() {
		super();
	}
	
	public AirlineRequest(Airline airline) {
		super();
		if(airline != null) {
			this.code = airline.getCode();
			this.name = airline.getName();
			this.history = airline.getHistory();
		}	
	}
	
	public AirlineRequest(String code, String name, String history) {
		super();
		this.code = code;
		this.name = name;
		this.history = history;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
	
	
}
