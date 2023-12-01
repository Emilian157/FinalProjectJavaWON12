package org.fasttrack.service;

import org.fasttrack.exception.ResourceNotFoundException;
import org.fasttrack.model.Item;
import org.fasttrack.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> listAll() {
        return itemRepository.findAll();
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItem(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public Item updateItem(Long id, Item itemDetails) {
        return itemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(itemDetails.getName());
                    existingItem.setLocation(itemDetails.getLocation());
                    return itemRepository.save(existingItem);
                }).orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        itemRepository.delete(item);
    }
}