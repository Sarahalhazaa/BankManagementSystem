package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ProfileCustomer {
    @Id
    private Integer id;

    @Pattern(regexp = "05\\d{8}")
    @Column(columnDefinition = "varchar(20) not null")
    private String phoneNumber;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Customer customer;


}
