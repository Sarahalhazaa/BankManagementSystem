package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Employee {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @OneToOne
    @MapsId
    private User user;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private ProfileEmployee profileEmployee;


}
