package com.simplecar.models.requests;

import java.util.List;

import com.simplecar.enums.ItemTypeEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private ItemTypeEnum type;
	private List<Long> modelIds;
}
