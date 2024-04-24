package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "It must not be empty")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}")
    private String accountNumber;

    @NotEmpty(message ="It must not be empty" )
    @Column(columnDefinition = "int not null")
    @Positive
    private Integer balance;

    private Boolean isActive = false;

    @ManyToOne
    @JsonIgnore
    private Customer customer;


}
