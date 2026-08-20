package com.ecommerce.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name="customers")
public class Customer {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column (unique=true)
	private String email;
	private String password;
	private String phone;
	private String address;
}
