package edu.miu.cs544.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.cs544.domain.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
	Airline findByCode(String code);
}
