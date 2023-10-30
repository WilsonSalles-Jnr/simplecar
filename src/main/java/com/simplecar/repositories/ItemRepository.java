package com.simplecar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.simplecar.models.GenericEntityId;
import com.simplecar.models.Item;
import java.util.Set;
import com.simplecar.models.Model;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	@Query(value = "SELECT tia.item_id AS id FROM workorder.tb_item_application tia\r\n"
			+ "LEFT JOIN people.tb_model tm ON tm.id = tia.model_id\r\n"
			+ "WHERE tm.id = :modelId", nativeQuery = true)
	List<GenericEntityId> listItemByModelId(Long modelId);
}
