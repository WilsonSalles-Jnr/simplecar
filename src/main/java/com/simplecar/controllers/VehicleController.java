package com.simplecar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplecar.models.Customer;
import com.simplecar.models.Vehicle;
import com.simplecar.models.dto.VehicleDTO;
import com.simplecar.services.VehicleServices;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {
	private final VehicleServices vehicleServices;

	@GetMapping("/vehicles")
	public ResponseEntity<List<VehicleDTO>> listVehicles() {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(vehicleServices.listVehicles());
	}
	
	@GetMapping("/vehicles/filter")
	public ResponseEntity<List<VehicleDTO>> findVehicles(@RequestBody Customer id) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(vehicleServices.findVehicles(id.getId()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(vehicleServices.getVehicle(id));
	}

	@PostMapping("/")
	public ResponseEntity<VehicleDTO> createVehicle(@RequestBody Vehicle vehicle) throws EntityNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
				.body(vehicleServices.createVehicle(vehicle));
	}

	@PutMapping("/{id}")
	public ResponseEntity<VehicleDTO> listVehicles(@PathVariable Long id, @RequestBody Vehicle vehicle) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(vehicleServices.editVehicle(id, vehicle));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
		vehicleServices.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}
}
