package com.simplecar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simplecar.models.Customer;
import com.simplecar.models.Model;
import com.simplecar.models.Vehicle;
import com.simplecar.models.dto.VehicleDTO;
import com.simplecar.repositories.CustomerRepository;
import com.simplecar.repositories.ModelRepository;
import com.simplecar.repositories.VehicleRepository;
import com.simplecar.utils.ModelMapperConverter;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServices {
	private final VehicleRepository vehicleRepository;
	private final CustomerRepository customerRepository;
	private final ModelRepository modelRepository;
	private final ModelMapperConverter modelMapperConverter;
	
	public List<VehicleDTO> listVehicles() {
		List<Vehicle> vehicle = vehicleRepository.findAll();
		return modelMapperConverter.toDTOList(vehicle, VehicleDTO.class);
	}
	
	public List<VehicleDTO> findVehicles(Long id) {
		Customer customer = new Customer();
		customer.setId(id);
		List<Vehicle> vehicle = vehicleRepository.findByCustomer(customer);
		return modelMapperConverter.toDTOList(vehicle, VehicleDTO.class);
	}

	public VehicleDTO getVehicle(Long id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		if(vehicle.isPresent()) {
			return modelMapperConverter.toDTO(vehicle.get(), VehicleDTO.class);
		}
		throw new EntityNotFoundException("Vehicle not found");
	}

	public VehicleDTO createVehicle(Vehicle vehicle) throws EntityNotFoundException {
		validateCustomerAndModel(vehicle);
		Vehicle newVehicle = vehicleRepository.save(vehicle);
		return modelMapperConverter.toDTO(newVehicle, VehicleDTO.class);
	}

	public VehicleDTO editVehicle(Long id, Vehicle vehicle) {
		vehicle.setId(id);
		if(vehicleRepository.existsById(id)) {
			validateCustomerAndModel(vehicle);
			Vehicle updateVehicle = vehicleRepository.save(vehicle);
			return modelMapperConverter.toDTO(updateVehicle, VehicleDTO.class);			
		} else {
			throw new EntityNotFoundException("Vehicle Not Found");
		}
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
