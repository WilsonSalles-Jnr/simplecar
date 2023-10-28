package com.simplecar.models;

import java.util.Set;

import com.simplecar.enums.ItemTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "workorder", name = "tb_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item extends GenericEntityId {
	
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private ItemTypeEnum type;

	@ManyToMany
	@JoinTable(schema = "workorder", name = "tb_item_application",
	joinColumns = @JoinColumn(table = "tb_item_application", name = "item_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(table = "tb_item_application", name = "model_id", referencedColumnName = "id"))
	private Set<Model> models;
}
