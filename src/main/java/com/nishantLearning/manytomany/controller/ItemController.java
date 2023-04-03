package com.nishantLearning.manytomany.controller;

import com.nishantLearning.manytomany.model.Item;
import com.nishantLearning.manytomany.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //Get all items
    @GetMapping("/")
    public List<Item> getAllItemsController(){
        return itemService.getAllItems();
    }

    //Get item by ID
    @GetMapping("/{id}")
    public Item getItemByIdController(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    //Create an item
    @PostMapping("/")
    public void createItemController(@RequestBody Item newItem){
        itemService.createItem(newItem);
    }

    //Update an item
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItemController(@PathVariable Long id, @RequestBody Item updateItem){
        return itemService.updateItem(id, updateItem);
    }

    //Delete an item
    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItemController(@PathVariable Long id){
        return itemService.deleteItem(id);
    }
}
