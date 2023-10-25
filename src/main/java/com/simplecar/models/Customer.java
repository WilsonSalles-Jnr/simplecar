package com.simplecar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "people", name = "tb_customer")
@Getter
@Setter
public class Customer extends GenericEntityId {
	private String name;
	private String phone;
}
