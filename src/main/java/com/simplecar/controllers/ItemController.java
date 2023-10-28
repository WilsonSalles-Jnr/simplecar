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
	public List<Item> listItems() {
		return itemServices.listItems();
	}

	@GetMapping("/{id}")
	public Optional<Item> getItem(@PathVariable Long id) {
		return itemServices.getItem(id);
	}

	@PostMapping("/")
	public Item createItem(@RequestBody ItemRequest item) {
		return itemServices.createItem(item);
	}

	@PutMapping("/{id}")
	public Item editItem(@PathVariable Long id, @RequestBody ItemRequest item) {
		return itemServices.editItem(id, item);
	}

}
