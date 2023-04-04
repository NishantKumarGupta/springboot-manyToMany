package com.nishantLearning.manytomany.service;


import com.nishantLearning.manytomany.exception.ResourceNotFoundException;
import com.nishantLearning.manytomany.model.Cart;
import com.nishantLearning.manytomany.model.Item;
import com.nishantLearning.manytomany.repo.CartRepository;
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

    @Autowired
    private CartRepository cartRepository;

    //Get all items
//    @Cacheable(value = "item", key = "'all'")
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    //Get Items by ID
//    @Cacheable(value = "item", key = "#p0")
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
//    @CachePut(value = "item")
    public ResponseEntity<Item> updateItem(Long id, Item newItem){
        Item oldItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No item found with id : " + id));

        if(newItem.getId() != null) oldItem.setId(newItem.getId());
        oldItem.setItemName(newItem.getItemName());
        if(newItem.getItemPrice() != null) oldItem.setItemPrice(newItem.getItemPrice());
        if(newItem.getCarts() != null) oldItem.setCarts(newItem.getCarts());

        itemRepository.save(oldItem);
        return ResponseEntity.ok(oldItem);
    }

    public ResponseEntity<Item> addItemToCart(Long itemId, Long cartId){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("No item found with id" + itemId));
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("No cart found with id" + cartId));
        cart.addItem(item);
        cartRepository.save(cart);
        itemRepository.save(item);
        return ResponseEntity.ok(item);
    }

    //Delete an item
//    @CacheEvict(value = "item")
    public ResponseEntity<Item> deleteItem(Long id){
        Item foundItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No item found with id : " + id));
        itemRepository.delete(foundItem);
        return ResponseEntity.ok(foundItem);
    }
}
