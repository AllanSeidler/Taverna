package com.taverna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author AllanSeidler
 *
 * @implNote
 * Provavelmente haverá apenas dados fixos,
 * por isso a falta de setters.
 * */
@Entity
public class Interesse implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interesse interesse = (Interesse) o;
        return id == interesse.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}