package com.simplecar.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.simplecar.models.Customer;
import com.simplecar.models.dto.CustomerDTO;
import com.simplecar.repositories.CustomerRepository;
import com.simplecar.utils.ModelMapperConverter;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServices {
	private final CustomerRepository customerRepository;
	private final ModelMapperConverter entityConverter;

	public List<CustomerDTO> listCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return entityConverter.toDTOList(customers, CustomerDTO.class);
	}

	public CustomerDTO getCustomer(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isPresent()) {
			return entityConverter.toDTO(customer.get(), CustomerDTO.class);
		} else {
			throw new EntityNotFoundException("Customer not found");
		}
		
	}

	public CustomerDTO createCustomer(Customer customer) throws Exception {
		validatePhone(customer, 0L);
		Customer created = customerRepository.save(customer);
		return entityConverter.toDTO(created, CustomerDTO.class);
	}

	public CustomerDTO editCustomer(Long customerId, Customer customer) throws Exception {
		if(!customerRepository.existsById(customerId)) {
			throw new EntityNotFoundException("Customer not found");
		}
		if (Objects.isNull(customer.getName()) || customer.getName().isBlank()) {
			throw new Exception("name is required");
		}
		validatePhone(customer, customerId);
		customer.setId(customerId);
		Customer edited = customerRepository.save(customer);
		return entityConverter.toDTO(edited, CustomerDTO.class);
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
