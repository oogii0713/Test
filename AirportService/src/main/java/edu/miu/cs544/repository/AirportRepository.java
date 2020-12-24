package edu.miu.cs544.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.cs544.domain.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
	Airport findByCode(String code);
}
