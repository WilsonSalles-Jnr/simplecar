package com.simplecar.models;

import java.util.Set;

import com.simplecar.enums.WorkorderStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "workorder", name="tb_workorder")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Workorder extends GenericEntityId {
	@Enumerated(EnumType.STRING)
	private WorkorderStatusEnum status;
	
	@ManyToMany
	@JoinTable(schema = "workorder", name = "tb_workorder_item",
	joinColumns = @JoinColumn(table = "tb_workorder_item", name = "workorder_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(table = "tb_workorder_item", name = "item_id", referencedColumnName = "id"))
	private Set<Item> items;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;
	
	@Column(name = "samshed")
	private Boolean samshed;

	@Column(name = "scratrched")
	private Boolean scratrched;

	@Column(name = "broken_glass")
	private Boolean brokenGlass;

	@Column(name = "hole")
	private Boolean hole;

	@Column(name = "observation")
	private String observation;
}
