package com.simplecar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simplecar.models.Model;
import com.simplecar.repositories.ModelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServices {
	private final ModelRepository modelRepository;

	public List<Model> listModels() {
		return modelRepository.findAll();
	}

	public Optional<Model> getModel(Long id) {
		return modelRepository.findById(id);
	}

	public Model createModel(Model model) {
		return modelRepository.save(model);
	}

	public Model editModel(Long id, Model model) {
		model.setId(id);
		return modelRepository.save(model);
	}
}
