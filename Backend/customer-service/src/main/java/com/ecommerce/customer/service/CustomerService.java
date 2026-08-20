package com.ecommerce.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer register(Customer customer) {
		if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
			throw new RuntimeException("Email already registered:"+customer.getEmail());
		}
		return customerRepository.save(customer);
	}
	
	public Customer login(String email, String password) {
		return customerRepository.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new RuntimeException("Invalid email or password"));
	}
	
	public Optional<Customer> getById(Long id) {
		return customerRepository.findById(id);
	}
	
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}
	
	public Customer update(Long id, Customer updated) {
		Customer existing = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: "+id));
		existing.setName(updated.getName());
		existing.setPhone(updated.getPhone());
		existing.setAddress(updated.getAddress());
		return customerRepository.save(existing);
	}
}

















