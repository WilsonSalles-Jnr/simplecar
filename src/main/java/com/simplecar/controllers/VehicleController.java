package com.simplecar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplecar.models.Vehicle;
import com.simplecar.services.VehicleServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {
	private final VehicleServices vehicleServices;
	
	@GetMapping("/vehicles")
	public List<Vehicle> listVehicles() {
		return vehicleServices.listVehicles();
	}
	
	@GetMapping("/{id}")
	public Optional<Vehicle> listVehicles(@PathVariable Long id) {
		return vehicleServices.getVehicle(id);
	}
	
	@PostMapping("/")
	public Vehicle createVehicle(@RequestBody Vehicle vehicle) throws EntityNotFoundException {
		return vehicleServices.createVehicle(vehicle);
	}
	

	@PutMapping("/{id}")
	public Vehicle listVehicles(@PathVariable Long id, @RequestBody Vehicle vehicle) {
		return vehicleServices.editVehicle(id, vehicle);
	}
}
