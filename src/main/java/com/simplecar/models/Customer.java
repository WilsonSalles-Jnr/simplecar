package com.simplecar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "people", name = "tb_customer")
@Getter
@Setter
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String name;
	public String phone;
}
