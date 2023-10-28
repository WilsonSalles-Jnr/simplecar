package com.simplecar.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.simplecar.models.Item;
import com.simplecar.models.Vehicle;
import com.simplecar.models.Workorder;
import com.simplecar.models.requests.WorkorderRequest;
import com.simplecar.repositories.ItemRepository;
import com.simplecar.repositories.VehicleRepository;
import com.simplecar.repositories.WorkorderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkorderServices {
	private final WorkorderRepository workorderRepository;
	private final VehicleRepository vehicleRepository;
	private final ItemRepository itemRepository;
	
	public List<Workorder> listWorkorder() {
		return workorderRepository.findAll();
	}
	
	public Optional<Workorder> getWorkorder(Long id) {
		return workorderRepository.findById(id);
	}
	
	public Workorder createWorkorder(WorkorderRequest workorder) {
		Workorder newWorkorder = buildWorkorder(workorder);
		return workorderRepository.save(newWorkorder);
	}
	
	public Workorder editRepository(Long id, WorkorderRequest workorder) {
		Workorder newWorkorder = buildWorkorder(workorder);
		newWorkorder.setId(id);
		return workorderRepository.save(newWorkorder);
	}
	
	private Workorder buildWorkorder(WorkorderRequest workorder) {
		new Workorder();
		Workorder settedWorkorder = Workorder.builder()
				.status(workorder.getStatus())
				.samshed(workorder.getSmashed())
				.scratrched(workorder.getScratrched())
				.brokenGlass(workorder.getBrokenGlass())
				.hole(workorder.getHole())
				.observation(workorder.getObservation())
				.build();
		Optional<Vehicle> vehicle = vehicleRepository.findById(workorder.getVehicleId());
		vehicle.ifPresent(v -> settedWorkorder.setVehicle(v));
		Set<Item> items = new HashSet<>(itemRepository.findAllById(workorder.getItemIds()));
		settedWorkorder.setItems(items);
		return settedWorkorder;
	}
}
