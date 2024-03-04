package com.nar.keycloakdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

}
