package edu.miu.cs544.service.request;

import edu.miu.cs544.domain.Airport;

public class AirportRequest {
	private String code;
	private String name;
	private AddressRequest address;
	
	public AirportRequest() {
		super();
	}
	
	public AirportRequest(Airport airport) {
		super();
		if(airport != null) {
			this.code = airport.getCode();
			this.name = airport.getName();
			this.address = new AddressRequest(airport.getAddress());
		}
	}
	
	public AirportRequest(String code, String name, AddressRequest address) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
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
	public AddressRequest getAddress() {
		return address;
	}
	public void setAddress(AddressRequest address) {
		this.address = address;
	}
}
