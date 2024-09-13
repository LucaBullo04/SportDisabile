package com.servicematica.Model.Associazione.Attivita;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewAttivita;
import com.servicematica.Model.Associazione.Associazione;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Attivita {

    @JsonView(value = {ViewAttivita.RowAttivitaAssociazione.class, ViewAttivita.RowAttivitaAdmin.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(value = {ViewAttivita.RowAttivitaAdmin.class, ViewAttivita.ModificaAttivitaAdmin.class})
    //L'annotation @JsonManagedReference serve per inidcare che questo campo è il parent del bean
    @JsonManagedReference
    @ManyToOne
    private Associazione associazione;
    
    @JsonView(value = {ViewAttivita.RowAttivitaAssociazione.class, ViewAttivita.RowAttivitaAdmin.class, ViewAttivita.ModificaAttivitaAssociazione.class, ViewAttivita.ModificaAttivitaAdmin.class})
    @JsonManagedReference
    @ManyToOne
    private Sport sport;
    
    @JsonView(value = {ViewAttivita.RowAttivitaAssociazione.class, ViewAttivita.RowAttivitaAdmin.class, ViewAttivita.ModificaAttivitaAssociazione.class, ViewAttivita.ModificaAttivitaAdmin.class})
    @JsonManagedReference
    @ManyToOne
    private Disabilita disabilita;
    
    @JsonView(value = {ViewAttivita.RowAttivitaAssociazione.class, ViewAttivita.RowAttivitaAdmin.class, ViewAttivita.ModificaAttivitaAssociazione.class, ViewAttivita.ModificaAttivitaAdmin.class})
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProprietaAttivita> proprieta = new LinkedList<>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Associazione getAssociazione() {
        return associazione;
    }

    public void setAssociazione(Associazione associazione) {
        this.associazione = associazione;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Disabilita getDisabilita() {
        return disabilita;
    }

    public void setDisabilita(Disabilita disabilita) {
        this.disabilita = disabilita;
    }
    
    public List<ProprietaAttivita> getProprieta() {
        return proprieta;
    }

    public void setProprieta(List<ProprietaAttivita> proprieta) {
        this.proprieta = proprieta;
    }

    @Override
    public String toString() {
        return "Attivita{" + "id=" + id + ", associazione=" + associazione + ", proprieta=" + proprieta + '}';
    }
    
}
