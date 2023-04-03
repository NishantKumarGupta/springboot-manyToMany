package com.nishantLearning.manytomany.service;

import com.nishantLearning.manytomany.DTO.UserDTO;
import com.nishantLearning.manytomany.exception.ResourceNotFoundException;
import com.nishantLearning.manytomany.mapper.UserMapper;
import com.nishantLearning.manytomany.model.Cart;
import com.nishantLearning.manytomany.repo.CartRepository;
import com.nishantLearning.manytomany.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.nishantLearning.manytomany.model.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartRepository cartRepository;

    public List<UserDTO> getAllUsers(){
        List<User> allFoundUsers = userRepository.findAll();
        List<UserDTO> foundDTO = allFoundUsers.stream()
                .map(foundUser -> userMapper.toDTO(foundUser))
                .collect(Collectors.toList());
        return foundDTO;
    }

    public UserDTO getUserById(Long id){
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user found having id" + id));
        return userMapper.toDTO(foundUser);
    }

    public void createUser(User newUser){
        Cart cart = new Cart();
        cart.setCartName(newUser.getCart().getCartName());
         cart.setUser(newUser);
        newUser.setCart(cart);

        cartRepository.save(cart);
        userRepository.save(newUser);
    }

    public ResponseEntity<User> updateUser(Long id, User newUser){
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user found having id" + id));

        if(newUser.getId() != null) oldUser.setId(newUser.getId());
        oldUser.setUserName(newUser.getUserName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setMobileNo(newUser.getMobileNo());
        oldUser.setCart(newUser.getCart());
        userRepository.save(oldUser);
        return ResponseEntity.ok(oldUser);
    }

    public ResponseEntity<User> deleteUser(Long id){
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user found having id" + id));
        userRepository.delete(foundUser);
        return ResponseEntity.ok(foundUser);
    }

}

