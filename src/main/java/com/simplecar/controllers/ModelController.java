package com.simplecar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<List<Model>> listModels() {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(modelServices.listModels());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Model>> getModel(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(modelServices.getModel(id));
	}

	@PostMapping("/")
	public ResponseEntity<Model> createModel(@RequestBody Model model) {
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
				.body(modelServices.createModel(model));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Model> editModel(@PathVariable Long id, @RequestBody Model model) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
				.body(modelServices.editModel(id, model));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> editModel(@PathVariable Long id) {
		modelServices.deleteModel(id);
		return ResponseEntity.noContent().build();
	}
}
