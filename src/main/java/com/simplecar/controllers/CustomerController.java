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

import com.simplecar.models.Customer;
import com.simplecar.services.CustomerServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerServices customerServices;

	@GetMapping("/customers")
	public List<Customer> customers() {
		return customerServices.listCustomers();
	}

	@GetMapping("/{id}")
	public Optional<Customer> customer(@PathVariable Long id) {
		return customerServices.getCustomer(id);
	}

	@PostMapping("/")
	public Customer createCustomer(@RequestBody Customer params) throws Exception {
		return customerServices.createCustomer(params);
	}

	@PutMapping("/{id}")
	public Customer editCustomer(@PathVariable Long id, @RequestBody Customer customer) throws Exception {
		return customerServices.editCustomer(id, customer);
	}
}
