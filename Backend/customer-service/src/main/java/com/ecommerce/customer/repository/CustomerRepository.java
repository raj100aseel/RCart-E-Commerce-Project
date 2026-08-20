package com.ecommerce.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
	Optional<Customer> findByEmail(String email);
	Optional<Customer> findByEmailAndPassword(String email, String password);
}
