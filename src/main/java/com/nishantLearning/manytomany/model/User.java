package com.nishantLearning.manytomany.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "CartUser")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;
    private String password;
    private String mobileNo;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dob;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;
}
