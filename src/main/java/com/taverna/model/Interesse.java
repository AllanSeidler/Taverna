package com.taverna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Interesse {

    @Id
    private int id;

    @Column
    private String interesse;

    public int getId() {
        return id;
    }

    public String getInteresse() {
        return interesse;
    }
}