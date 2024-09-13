package com.servicematica.Model.Associazione;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewAssociazione;
import com.servicematica.Direttive.ViewAttivita;
import com.servicematica.Direttive.ViewUtentiAssociazione;
import com.servicematica.Model.Associazione.Attivita.Attivita;
import com.servicematica.Model.UtentiAutenticazione.Utente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Associazione {
    
    @JsonView(value = {ViewAttivita.RowListaAssociazioni.class, ViewAttivita.ModificaAttivitaAdmin.class, ViewUtentiAssociazione.RowUtentiAssociazione.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @JsonView(value = {ViewUtentiAssociazione.RowUtentiAssociazione.class, ViewAttivita.RowAttivitaAdmin.class, ViewAttivita.RowListaAssociazioni.class, ViewAssociazione.ModificaAssociazione.class})
    @Column(unique = true)
    private String nome;
    
    @JsonView(value = {ViewUtentiAssociazione.GetValoriAssociazione.class, ViewUtentiAssociazione.RowUtentiAssociazione.class})
    @Column(unique = true)
    private String partitaIva;

    @JsonView(value = {ViewAttivita.RowAttivitaAdmin.class, ViewUtentiAssociazione.RowUtentiAssociazione.class, ViewAssociazione.ModificaAssociazione.class})
    @OneToOne(cascade = CascadeType.ALL)
    private FileLogo logo;

    @JsonView(value = {ViewUtentiAssociazione.RowUtentiAssociazione.class, ViewUtentiAssociazione.GetValoriAssociazione.class})
    @OneToOne(cascade = CascadeType.REMOVE)
    private Utente utente;
    
    //L'annotation @JsonBackReference serve per inidcare che questo campo è il children del bean
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attivita> attivita;
    
    @JsonView(value = {ViewAssociazione.ModificaAssociazione.class})
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProprietaAssociazione> proprieta = new LinkedList<>(); // La parte dopo l'uguale serve per evitare il NullPointerException nell'inserimento all'interno del DB che avviene nel service

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }
    
    public FileLogo getLogo() {
        return logo;
    }

    public void setLogo(FileLogo logo) {
        this.logo = logo;
    }
    
    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
    public List<Attivita> getAttivita() {
        return attivita;
    }

    public void setAttivita(List<Attivita> attivita) {
        this.attivita = attivita;
    }
    
    public List<ProprietaAssociazione> getProprieta() {
        return proprieta;
    }

    public void setProprieta(List<ProprietaAssociazione> proprieta) {
        this.proprieta = proprieta;
    }

    @Override
    public String toString() {
        return "Associazione{" + "id=" + id + ", nome=" + nome + ", logo=" + logo + ", utente=" + utente + ", attivita=" + attivita + ", proprieta=" + proprieta + '}';
    }
    
}
