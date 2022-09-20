package com.ultrates.SpringsecurityviaKeycloak.entity;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
@Setter

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double salary;


    public Employee(String haile, double v) {
        this.name=haile;
        this.salary=v;
    }
}
