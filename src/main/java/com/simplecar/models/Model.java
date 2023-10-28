package com.simplecar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "people", name = "tb_model")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Model extends GenericEntityId {
	private String name;
}
