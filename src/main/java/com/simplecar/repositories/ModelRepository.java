package com.simplecar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplecar.models.Model;


@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
	List<Model> findByName(String name);
}
