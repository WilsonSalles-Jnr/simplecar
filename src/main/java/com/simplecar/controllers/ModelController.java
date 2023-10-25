package com.simplecar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplecar.models.Model;
import com.simplecar.services.ModelServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelController {
	private final ModelServices modelServices;

	@GetMapping("/models")
	public List<Model> listModels() {
		return modelServices.listModels();
	}

	@GetMapping("/{id}")
	public Optional<Model> getModel(@PathVariable Long id) {
		return modelServices.getModel(id);
	}

	@PostMapping("/")
	public Model createModel(@RequestBody Model model) {
		return modelServices.createModel(model);
	}

	@PutMapping("/{id}")
	public Model editModel(@PathVariable Long id, @RequestBody Model model) {
		return modelServices.editModel(id, model);
	}
}
