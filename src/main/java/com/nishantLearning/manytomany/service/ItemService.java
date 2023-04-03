package com.nishantLearning.manytomany.service;


import com.nishantLearning.manytomany.exception.ResourceNotFoundException;
import com.nishantLearning.manytomany.model.Item;
import com.nishantLearning.manytomany.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    //Get all items
    @Cacheable(value = "item", key = "'all'")
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    //Get Items by ID
    @Cacheable(value = "item", key = "#p0")
    public Item getItemById(Long id){
        Item foundItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No item found with id : " + id));
        return foundItem;
    }

    //Create an item
    public void createItem(Item newItem){
        itemRepository.save(newItem);
    }

    //Update an item
    @CachePut(value = "item")
    public ResponseEntity<Item> updateItem(Long id, Item newItem){
        Item oldItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No item found with id : " + id));

        if(newItem.getId() != null) oldItem.setId(newItem.getId());
        oldItem.setItemName(newItem.getItemName());
        oldItem.setItemPrice(newItem.getItemPrice());
        oldItem.setCarts(newItem.getCarts());

        itemRepository.save(oldItem);
        return ResponseEntity.ok(oldItem);
    }

    //Delete an item
    @CacheEvict(value = "item")
    public ResponseEntity<Item> deleteItem(Long id){
        Item foundItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No item found with id : " + id));
        itemRepository.delete(foundItem);
        return ResponseEntity.ok(foundItem);
    }
}
