package edu.miu.cs544.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.cs544.domain.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
	
}
