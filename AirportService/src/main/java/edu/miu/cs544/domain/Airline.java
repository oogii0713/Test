package edu.miu.cs544.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;

import edu.miu.cs544.service.request.AirlineRequest;

@Entity
@SecondaryTable(name = "History")
public class Airline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String code;
	
	private String name;
	
	@Column(table = "History", length = 2000)
	private String history;
	
	public Airline() {
		super();
	}
	
	public Airline(String code, String name, String history) {
		super();
		this.code = code;
		this.name = name;
		this.history = history;
	}
	
	public Airline(AirlineRequest airlineRequest) {
		this.code = airlineRequest.getCode();
		this.name = airlineRequest.getName();
		this.history = airlineRequest.getHistory();
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
