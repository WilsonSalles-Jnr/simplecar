package com.simplecar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplecar.models.Workorder;

@Repository
public interface WorkorderRepository extends JpaRepository<Workorder, Long> {

}
