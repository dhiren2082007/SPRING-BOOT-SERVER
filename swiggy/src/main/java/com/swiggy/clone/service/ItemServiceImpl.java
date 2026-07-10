package com.swiggy.clone.service;

import com.swiggy.clone.dto.ItemRequest;
import com.swiggy.clone.exception.ResourceNotFoundException;
import com.swiggy.clone.model.Item;
import com.swiggy.clone.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Item createItem(ItemRequest request) {
        Item item = Item.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .available(request.getAvailable() == null ? true : request.getAvailable())
                .build();

        return itemRepository.save(item);
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional
    public Item updateItem(Long id, ItemRequest request) {
        Item item = getItemById(id);

        if (request.getName() != null) {
            item.setName(request.getName());
        }
        if (request.getDescription() != null) {
            item.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            item.setPrice(request.getPrice());
        }
        if (request.getCategory() != null) {
            item.setCategory(request.getCategory());
        }
        if (request.getAvailable() != null) {
            item.setAvailable(request.getAvailable());
        }

        return itemRepository.save(item);
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        Item item = getItemById(id);
        itemRepository.delete(item);
    }

    @Override
    @Transactional
    public Item toggleAvailability(Long id) {
        Item item = getItemById(id);
        item.setAvailable(!Boolean.TRUE.equals(item.getAvailable()));
        return itemRepository.save(item);
    }
}
