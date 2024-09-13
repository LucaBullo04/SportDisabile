package com.servicematica.Model.Associazione.Attivita;

import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewAttivita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sport {
    
    @JsonView(value = {ViewAttivita.ModificaAttivitaAssociazione.class, ViewAttivita.ModificaAttivitaAdmin.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @JsonView(value = {ViewAttivita.RowAttivitaAssociazione.class, ViewAttivita.RowAttivitaAdmin.class})
    @Column(unique = true, nullable = false)
    private String nomeSport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeSport() {
        return nomeSport;
    }

    public void setNomeSport(String nomeSport) {
        this.nomeSport = nomeSport;
    }

    @Override
    public String toString() {
        return "Sport{" + "id=" + id + ", nomeSport=" + nomeSport + '}';
    }
    
}
