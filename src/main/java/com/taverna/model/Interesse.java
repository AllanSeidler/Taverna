package com.taverna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author AllanSeidler
 *
 * @implNote
 * Provavelmente haverá apenas dados fixos,
 * por isso a falta de setters.
 * */
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