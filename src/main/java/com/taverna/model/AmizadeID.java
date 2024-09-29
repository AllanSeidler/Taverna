package com.taverna.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AmizadeID implements Serializable {

    @Column(name = "id_usuario1")
    int usuario1Id;

    @Column(name = "id_usuario2")
    int usuario2Id;


    public int getUsuario1Id() {
        return usuario1Id;
    }

    public void setUsuario1Id(int usuario1Id) {
        this.usuario1Id = usuario1Id;
    }

    public int getUsuario2Id() {
        return usuario2Id;
    }

    public void setUsuario2Id(int usuario2Id) {
        this.usuario2Id = usuario2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) return false;
        if(this == o) return true;
        AmizadeID aux = (AmizadeID) o;

        return this.usuario1Id==aux.usuario1Id &&
                this.usuario2Id==aux.usuario2Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario1Id,usuario2Id);
    }
}
