package com.nishantLearning.manytomany.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String itemName;

    @ManyToMany(mappedBy = "items", fetch = FetchType.EAGER)
    Set<Cart> carts;

    public Item() {
    }

    public Item(Long id, String itemName, Set<Cart> carts) {
        this.id = id;
        this.itemName = itemName;
        this.carts = carts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Cart> getCarts() {
//        Set<Long> cartCodes = new HashSet<>();
//        for(Cart cart : carts){
//            cartCodes.add(cart.getId());
//        }
//        return cartCodes;
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", carts=" + carts +
                '}';
    }
}
