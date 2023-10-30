package com.simplecar.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "people", name = "tb_customer")
@Getter
@Setter
public class Customer extends GenericEntityId {
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "phone", nullable = false, length = 15, unique = true)
	private String phone;
}
