package edu.miu.cs544.service.response;

import edu.miu.cs544.domain.Airline;

public class AirlineResponse {
	private Integer id;
	private String code;
	private String name;
	private String history;
	
	public AirlineResponse() {
		super();
	}
	
	public AirlineResponse(Airline airline) {
		super();
		if(airline != null) {
			this.id = airline.getId();
			this.code = airline.getCode();
			this.name = airline.getName();
			this.history = airline.getHistory();
		}	
	}
	
	public AirlineResponse(String code, String name, String history) {
		super();
		this.code = code;
		this.name = name;
		this.history = history;
	}

	public AirlineResponse(Integer id, String code, String name, String history) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.history = history;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
