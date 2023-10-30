package com.simplecar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplecar.models.Customer;
import com.simplecar.models.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	List<Vehicle> findByCustomer(Customer customer);
}
