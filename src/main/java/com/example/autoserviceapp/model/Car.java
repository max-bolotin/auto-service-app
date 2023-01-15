package com.example.autoserviceapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "car_id_seq",
            sequenceName = "car_id_seq",
            allocationSize = 1)
    private Long id;
    private String manufacturer;
    private String model;
    private int makeYear;
    private String registrationNumber;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Client owner;
}
