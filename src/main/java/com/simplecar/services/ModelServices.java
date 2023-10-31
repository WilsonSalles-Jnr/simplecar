package com.simplecar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simplecar.models.Model;
import com.simplecar.models.dto.ModelDTO;
import com.simplecar.repositories.ModelRepository;
import com.simplecar.utils.ModelMapperConverter;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServices {
	private final ModelRepository modelRepository;
	private final ModelMapperConverter modelMapperConverter;

	public List<ModelDTO> listModels() {
		List<Model> models = modelRepository.findAll();
		return modelMapperConverter.toDTOList(models, ModelDTO.class);

	}

	public ModelDTO getModel(Long id) {
		Optional<Model> model = modelRepository.findById(id);

		if (model.isPresent()) {
			return modelMapperConverter.toDTO(model.get(), ModelDTO.class);
		} else {
			throw new EntityNotFoundException("Model not found");
		}
	}

	public ModelDTO createModel(Model model) {
		Model created = modelRepository.save(model);
		return modelMapperConverter.toDTO(created, ModelDTO.class);
	}

	public ModelDTO editModel(Long id, Model model) {
		if(modelRepository.existsById(id)) {
			model.setId(id);
			Model edited = modelRepository.save(model);
			return modelMapperConverter.toDTO(edited, ModelDTO.class);
		} else {
			throw new EntityNotFoundException("Model not found");
		}
	}

	public void deleteModel(Long id) {
		modelRepository.deleteById(id);
	}
}
