package com.taverna.model;

import com.taverna.model.AmizadeID;
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

    public void confirmar() {
        this.aceita = true;
    }

    public AmizadeID getAmizadeID() {
        return amizadeID;
    }
}
