package com.simplecar.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class ModelMapperConverter {
	private final ModelMapper modelMapper = new ModelMapper();

    public <E, D> D toDTO(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <E, D> List<D> toDTOList(List<E> entities, Class<D> dtoClass) {
    	List<D> dtoList = new ArrayList<>();
    	entities.forEach(e -> dtoList.add(modelMapper.map(e, dtoClass)));
        return dtoList;
    }
}
