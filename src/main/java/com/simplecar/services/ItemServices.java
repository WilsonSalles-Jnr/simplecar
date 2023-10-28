package com.simplecar.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simplecar.models.Item;
import com.simplecar.models.Model;
import com.simplecar.models.requests.ItemRequest;
import com.simplecar.repositories.ItemRepository;
import com.simplecar.repositories.ModelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServices {
	private final ItemRepository itemRepository;
	private final ModelRepository modelRepository;

	public List<Item> listItems() {
		return itemRepository.findAll();
	}

	public Optional<Item> getItem(Long id) {
		return itemRepository.findById(id);
	}

	public Item createItem(ItemRequest item) {
		new Item();
		Item newItem = Item.builder().name(item.getName()).description(item.getDescription()).type(item.getType())
				.build();
		List<Model> models = modelRepository.findAllById(item.getModelIds());
		newItem.setModels(new HashSet<>(models));
		return itemRepository.save(newItem);
	}

	public Item editItem(Long id, ItemRequest item) {
		new Item();
		Item newItem = Item.builder().name(item.getName()).description(item.getDescription()).type(item.getType())
				.build();
		List<Model> models = modelRepository.findAllById(item.getModelIds());
		newItem.setModels(new HashSet<>(models));
		newItem.setId(id);
		return itemRepository.save(newItem);
	}

	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}

}