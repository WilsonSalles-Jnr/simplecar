package com.simplecar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simplecar.models.Customer;
import com.simplecar.models.Vehicle;
import com.simplecar.repositories.CustomerRepository;
import com.simplecar.repositories.ModelRepository;
import com.simplecar.repositories.VehicleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServices {
	private final VehicleRepository vehicleRepository;
	private final CustomerRepository customerRepository;

	public List<Vehicle> listVehicles() {
		return vehicleRepository.findAll();
	}

	public Optional<Vehicle> getVehicle(Long id) {
		return vehicleRepository.findById(id);
	}

	public Vehicle createVehicle(Vehicle vehicle) throws EntityNotFoundException {
		validateCustomerAndModel(vehicle);
		return vehicleRepository.save(vehicle);
	}

	public Vehicle editVehicle(Long id, Vehicle vehicle) {
		vehicle.setId(id);
		validateCustomerAndModel(vehicle);
		return vehicleRepository.save(vehicle);
	}
	
	private void validateCustomerAndModel(Vehicle vehicle) {
		Optional<Customer> customer = customerRepository.findById(vehicle.getCustomer().getId());
		if(customer.isEmpty()) {
			throw new EntityNotFoundException("Customer not found");
		}
		customer.ifPresent(c -> vehicle.setCustomer(c));
		
	}
}
