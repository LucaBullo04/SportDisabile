package com.servicematica.Model.Associazione;

import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewAssociazione;
import com.servicematica.Direttive.ViewAttivita;
import com.servicematica.Direttive.ViewUtentiAssociazione;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProprietaAssociazione {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)
    private ProprietaAssociazioneEnum chiaveProprieta;
    
    @JsonView(value = {ViewAttivita.RowAttivitaAdmin.class, ViewUtentiAssociazione.RowUtentiAssociazione.class, ViewAttivita.RowListaAssociazioni.class, ViewAssociazione.ModificaAssociazione.class})
    @Column
    private String valoreProprieta;
    
    public ProprietaAssociazione() {}

    public ProprietaAssociazione(int id, ProprietaAssociazioneEnum chiaveProprieta, String valoreProprieta) {
        this.id = id;
        this.chiaveProprieta = chiaveProprieta;
        this.valoreProprieta = valoreProprieta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProprietaAssociazioneEnum getChiaveProprieta() {
        return chiaveProprieta;
    }

    public void setChiaveProprieta(ProprietaAssociazioneEnum chiaveProprieta) {
        this.chiaveProprieta = chiaveProprieta;
    }

    public String getValoreProprieta() {
        return valoreProprieta;
    }

    public void setValoreProprieta(String valoreProprieta) {
        this.valoreProprieta = valoreProprieta;
    }
    
}
