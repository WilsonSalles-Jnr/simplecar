package com.simplecar.models.dto;

import com.simplecar.models.GenericEntityId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO extends GenericEntityId {
	private String licensePlate;
	private String customerName;
	private String modelName;
	
}
