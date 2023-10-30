package com.simplecar.controllers;

import java.io.FileNotFoundException;
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
import com.simplecar.services.CustomerServices;
import com.simplecar.services.ReportServices;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerServices customerServices;
	private final ReportServices reportServices;

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> customers() {
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(customerServices.listCustomers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Customer>> customer(@PathVariable Long id) {
		return  ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(customerServices.getCustomer(id));
	}
	
	@GetMapping("/report")
	public ResponseEntity<byte[]> customerReport() throws FileNotFoundException, JRException {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_PDF).body(reportServices.customerReport("pdf"));
	}

	@PostMapping("/")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer params) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(customerServices.createCustomer(params));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> editCustomer(@PathVariable Long id, @RequestBody Customer customer) throws Exception {
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(customerServices.editCustomer(id, customer));
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerServices.deleteCustomer(id);
		return  ResponseEntity.noContent().build();
	}
}
