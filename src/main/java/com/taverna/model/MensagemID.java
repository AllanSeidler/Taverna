package com.taverna.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class MensagemID implements Serializable {

    @Column(name = "id_remetente")
    int remetente;

    @Column(name = "id_dentinatario")
    int destinatario;

    @Column
    Date data;

    public MensagemID() {

    }

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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
}
