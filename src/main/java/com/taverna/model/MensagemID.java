package com.taverna.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class MensagemID implements Serializable {

    @Column(name = "id_remetente")
    int remetente;

    @Column(name = "id_destinatario")
    int destinatario;

    @Column
    LocalDateTime data;

    public int getRemetente() {
        return remetente;
    }

    public void setRemetente(int remetente) {
        this.remetente = remetente;
    }

    public int getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) return false;
        if(this == o) return true;
        MensagemID aux = (MensagemID) o;

        return this.remetente==aux.remetente &&
                this.destinatario==aux.destinatario &&
                this.data.equals(aux.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remetente, destinatario, data);
    }

    @Override
    public String toString() {
        return "MensagemID{" +
                "remetente=" + remetente +
                ", destinatario=" + destinatario +
                ", data=" + data +
                '}';
    }
}
