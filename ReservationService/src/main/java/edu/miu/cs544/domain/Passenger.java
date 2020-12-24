package edu.miu.cs544.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import edu.miu.cs544.service.request.PassengerRequest;

@Entity
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn
	private Address address;
	
	@OneToMany(mappedBy = "passenger", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@OrderColumn(name="sequence")
	private List<Reservation> reservations = new ArrayList<>();
  
	public Passenger() {
	}
	
	public Passenger(String firstName, String lastName, Date dateOfBirth, String email, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.address = address;
	}
	
	public Passenger(PassengerRequest passengerRequest) {
		super();
		if(passengerRequest != null)
		{
			this.id = passengerRequest.getId();
			this.firstName = passengerRequest.getFirstName();
			this.lastName = passengerRequest.getLastName();
			this.dateOfBirth = passengerRequest.getDateOfBirth();
			this.email = passengerRequest.getEmail();
			this.address = new Address(passengerRequest.getAddress());
		}
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Reservation> getReservations() {
		return Collections.unmodifiableList(reservations);
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public boolean addReservation(Reservation reservation) {
		return reservations.add(reservation);
	}
	
	public boolean removeReservation(Reservation reservation) {
		return reservations.remove(reservation);
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", email=" + email + "]";
	}
	
	
}
