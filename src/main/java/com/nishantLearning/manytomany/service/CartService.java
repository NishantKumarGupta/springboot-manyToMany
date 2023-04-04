package com.nishantLearning.manytomany.service;


import com.nishantLearning.manytomany.exception.ResourceNotFoundException;
import com.nishantLearning.manytomany.model.Cart;
import com.nishantLearning.manytomany.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    //Get all carts
//    @Cacheable(value = "cart", key = "'all'")
    public List<Cart> getAllCarts(){
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            System.out.println(e);
        }
        List<Cart> carts = cartRepository.findAll();
        return carts;
    }

    //Get Cart by Id
//    @Cacheable(value = "cart", key = "#p0")
    public Cart getCartById(Long id){
        Cart targetCart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No cart found with the id : " + id));
        return targetCart;
    }

    //Create a cart
    public void createCart(Cart cart){
        cartRepository.save(cart);
    }

    //Update a cart
//    @CachePut(value = "cart")
    public ResponseEntity<Cart> updateCart(Long id, Cart newCart){
        Cart oldCart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No cart found with id : " + newCart.getId()));

        if(newCart.getId() != null) oldCart.setId(newCart.getId());
        oldCart.setCartName(newCart.getCartName());
        oldCart.setItems(newCart.getItems());
        Cart updatedCart = cartRepository.save(oldCart);
        return ResponseEntity.ok(updatedCart);
    }

    //Delete a cart
//    @CacheEvict(value = "cart")
    public ResponseEntity<Cart> deleteCart(Long id){
        Cart oldCart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No cart found with the id : " + id));

        cartRepository.delete(oldCart);
        return ResponseEntity.ok(oldCart);
    }
}
