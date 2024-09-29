package com.taverna.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Amizade {
    @EmbeddedId
    private AmizadeID amizadeID;

    @Column
    private boolean aceita;

    public boolean isAceita() {
        return aceita;
    }

    public void setAceita(boolean aceita) {
        this.aceita = aceita;
    }
}
