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

import com.simplecar.models.Workorder;
import com.simplecar.models.requests.WorkorderRequest;
import com.simplecar.services.WorkorderServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workorder")
public class WorkorderController {
	private final WorkorderServices workorderServices;

	@GetMapping("/workorders")
	public List<Workorder> listWorkorder() {
		return workorderServices.listWorkorder();
	}

	@GetMapping("/{id}")
	public Optional<Workorder> getWorkorder(@PathVariable Long id) {
		return workorderServices.getWorkorder(id);
	}

	@PostMapping("/")
	public Workorder createWorkorder(@RequestBody WorkorderRequest workorder) {
		return workorderServices.createWorkorder(workorder);
	}

	@PutMapping("/{id}")
	public Workorder editWorkorder(@PathVariable Long id, @RequestBody WorkorderRequest workorder) {
		return workorderServices.editRepository(id, workorder);
	}

}
