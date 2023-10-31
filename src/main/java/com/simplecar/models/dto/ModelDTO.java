package com.simplecar.models.dto;

import com.simplecar.models.GenericEntityId;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ModelDTO extends GenericEntityId {
	private String name;
}
