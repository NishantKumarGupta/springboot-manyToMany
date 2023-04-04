package com.nishantLearning.manytomany.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemName;
    private Long itemPrice;

    @ManyToMany(mappedBy = "items", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Cart> carts;

    public void addCart(Cart cart){
        carts.add(cart);
    }
}
