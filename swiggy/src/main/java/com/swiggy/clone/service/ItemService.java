package com.swiggy.clone.service;

import com.swiggy.clone.dto.ItemRequest;
import com.swiggy.clone.model.Item;

import java.util.List;

public interface ItemService {

    Item createItem(ItemRequest request);

    Item getItemById(Long id);

    List<Item> getAllItems();

    Item updateItem(Long id, ItemRequest request);

    void deleteItem(Long id);

    Item toggleAvailability(Long id);
}
