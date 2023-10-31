package com.simplecar.utils;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

public class ModelMapperConverter {
	private final ModelMapper modelMapper = new ModelMapper();

    public <E, D> D toDTO(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <E, D> List<D> toDTOList(List<E> entities, Class<D> dtoClass) {
        Type listType = new TypeToken<List<D>>() {}.getType();
        return modelMapper.map(entities, listType);
    }
}
