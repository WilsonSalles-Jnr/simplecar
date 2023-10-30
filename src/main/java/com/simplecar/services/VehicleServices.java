package com.simplecar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simplecar.models.Customer;
import com.simplecar.models.Model;
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
	private final ModelRepository modelRepository;
	
	public List<Vehicle> listVehicles() {
		return vehicleRepository.findAll();
	}
	
	public List<Vehicle> findVehicles(Long id) {
		Customer customer = new Customer();
		customer.setId(id);
		return vehicleRepository.findByCustomer(customer);
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
	
	public void deleteCustomer(Long id) {
		vehicleRepository.deleteById(id);
	}
	
	private void validateCustomerAndModel(Vehicle vehicle) {
		Optional<Customer> customer = customerRepository.findById(vehicle.getCustomer().getId());
		Optional<Model> model = modelRepository.findById(vehicle.getModel().getId());
		if(customer.isEmpty()) {
			throw new EntityNotFoundException("Customer not found");
		}
		if(model.isEmpty()) {
			throw new EntityNotFoundException("Model not found");
		}
		customer.ifPresent(c -> vehicle.setCustomer(c));
		model.ifPresent(m -> vehicle.setModel(m));
		
	}
}
