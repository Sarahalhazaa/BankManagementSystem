package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class ProfileEmployee {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    private String position;

    @Positive
    @Column(columnDefinition = "int")
    private Integer salary;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Employee employee;
}
