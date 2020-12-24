package edu.miu.cs544.service.aggregator.response;

public class AirportResponse {
	private Integer id;
	private String code;
	private String name;
	private AddressResponse address;
	
	public AirportResponse() {
		super();
	}
	
	public AirportResponse(String code, String name, AddressResponse address) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
	}

	public AirportResponse(Integer id, String code, String name, AddressResponse address) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.address = address;
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
	public AddressResponse getAddress() {
		return address;
	}
	public void setAddress(AddressResponse address) {
		this.address = address;
	}
}
