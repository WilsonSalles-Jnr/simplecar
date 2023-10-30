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

import com.simplecar.models.GenericEntityId;
import com.simplecar.models.Item;
import com.simplecar.models.requests.ItemRequest;
import com.simplecar.services.ItemServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
	private final ItemServices itemServices;

	@GetMapping("/items")
	public ResponseEntity<List<Item>> listItems() {
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(itemServices.listItems());
	}
	
	@GetMapping("/items/filter")
	public ResponseEntity<List<Item>> filterItems(@RequestBody GenericEntityId id) {
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(itemServices.findByModelId(id.getId()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Item>> getItem(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(itemServices.getItem(id));
	}

	@PostMapping("/")
	public ResponseEntity<Item> createItem(@RequestBody ItemRequest item) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(itemServices.createItem(item));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> editItem(@PathVariable Long id, @RequestBody ItemRequest item) {
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(itemServices.editItem(id, item));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
		itemServices.deleteItem(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON).build();
	}

}
