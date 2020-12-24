package edu.miu.cs544.service.request;

import java.util.Date;

import edu.miu.cs544.domain.Passenger;

public class PassengerRequest {
	private Integer id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private AddressRequest address;

	public PassengerRequest() {
		super();
	}
	public PassengerRequest(Passenger passenger) {
		super();
		if(passenger != null)
		{
			this.id = passenger.getId();
			this.firstName = passenger.getFirstName();
			this.lastName = passenger.getLastName();
			this.dateOfBirth = passenger.getDateOfBirth();
			this.email = passenger.getEmail();
			this.address = new AddressRequest(passenger.getAddress());
		}
	}
	
	public PassengerRequest(String firstName, String lastName, Date dateOfBirth, String email, AddressRequest address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.address = address;
	}

	public PassengerRequest(Integer id, String firstName, String lastName, Date dateOfBirth, String email, AddressRequest address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AddressRequest getAddress() {
		return address;
	}
	public void setAddress(AddressRequest address) {
		this.address = address;
	}
}
