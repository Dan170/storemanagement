package com.inghubs.storemanagement.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;

    private String name;
}
