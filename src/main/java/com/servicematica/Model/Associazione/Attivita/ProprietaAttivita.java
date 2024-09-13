package com.servicematica.Model.Associazione.Attivita;

import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewAttivita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProprietaAttivita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)
    private ProprietaAttivitaEnum chiaveProprieta;
    
    @JsonView(value = {ViewAttivita.RowAttivitaAssociazione.class, ViewAttivita.RowAttivitaAdmin.class, ViewAttivita.ModificaAttivitaAssociazione.class, ViewAttivita.ModificaAttivitaAdmin.class})
    @Column
    private String valoreProprieta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ProprietaAttivitaEnum getChiaveProprieta() {
        return chiaveProprieta;
    }

    public void setChiaveProprieta(ProprietaAttivitaEnum chiaveProprieta) {
        this.chiaveProprieta = chiaveProprieta;
    }

    public String getValoreProprieta() {
        return valoreProprieta;
    }

    public void setValoreProprieta(String valoreProprieta) {
        this.valoreProprieta = valoreProprieta;
    }

    @Override
    public String toString() {
        return "ProprietaAttivita{" + "id=" + id + ", chiaveProprieta=" + chiaveProprieta + ", valoreProprieta=" + valoreProprieta + '}';
    }
    
}
