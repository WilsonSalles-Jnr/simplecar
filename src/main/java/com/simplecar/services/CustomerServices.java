package com.simplecar.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.simplecar.models.Customer;
import com.simplecar.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServices {
	private final CustomerRepository customerRepository;

	public List<Customer> listCustomers() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomer(Long customerId) {
		return customerRepository.findById(customerId);
	}

	public Customer createCustomer(Customer customer) throws Exception {
		validatePhone(customer, 0L);

		return customerRepository.save(customer);
	}

	public Customer editCustomer(Long customerId, Customer customer) throws Exception {
		if (Objects.isNull(customer.getName()) || customer.getName().isBlank()) {
			throw new Exception("name is required");
		}
		validatePhone(customer, customerId);
		customer.setId(customerId);
		return customerRepository.save(customer);
	}
	
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	private void validatePhone(Customer customer, Long customerId) throws Exception {
		if (Objects.isNull(customer.getPhone()) || customer.getPhone().isBlank()) {
			throw new Exception("phone is required");
		}
		if (customer.getPhone().length() < 11 || customer.getPhone().length() > 15) {
			throw new DataIntegrityViolationException("Invalid phone lenght");
		}
		if (!customer.getPhone().matches("\\d+")) {
			throw new Exception("Only numbers allowed");
		}
		Customer findCustomer = customerRepository.findByPhone(customer.getPhone());
		if (Objects.nonNull(findCustomer) && findCustomer.getId() != customerId) {
			throw new Exception("phone already exist");
		}
	}
}
