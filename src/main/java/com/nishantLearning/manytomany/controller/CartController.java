package com.nishantLearning.manytomany.controller;


import com.nishantLearning.manytomany.model.Cart;
import com.nishantLearning.manytomany.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //Get all carts
    @GetMapping("/")
    public List<Cart> getAllCartController(){
        return cartService.getAllCarts();
    }

    //Get cart by Id
    @GetMapping("/{id}")
    public Cart getCartByIdController(@PathVariable Long id){
        return cartService.getCartById(id);
    }

    //Create a cart
    @PostMapping("/")
    public void createClassController(@RequestBody Cart cart){
        cartService.createCart(cart);
    }

    //Update a cart
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCartController(@PathVariable Long id, @RequestBody Cart cart){
        return cartService.updateCart(id, cart);
    }

    //Delete a cart
    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteCartController(@PathVariable Long id){
        return cartService.deleteCart(id);
    }
}
