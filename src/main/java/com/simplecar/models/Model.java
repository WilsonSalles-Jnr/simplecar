package com.simplecar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "people", name = "tb_model")
@Getter
@Setter
public class Model extends GenericEntityId {
	public String name;
}
