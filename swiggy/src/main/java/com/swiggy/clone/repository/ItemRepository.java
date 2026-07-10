package com.swiggy.clone.repository;

import com.swiggy.clone.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByAvailableTrue();
}
