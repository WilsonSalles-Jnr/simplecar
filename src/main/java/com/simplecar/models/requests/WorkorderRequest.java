package com.simplecar.models.requests;

import com.simplecar.enums.WorkorderStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkorderRequest {
	private WorkorderStatusEnum status;
	private Long vehicleId;
	private Boolean smashed;
	private Boolean scratrched;
	private Boolean brokenGlass;
	private Boolean hole;
	private String observation;
}
