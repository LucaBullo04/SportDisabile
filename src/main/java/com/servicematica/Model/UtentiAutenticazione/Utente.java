package com.servicematica.Model.UtentiAutenticazione;

import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewUtentiAssociazione;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Utente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @JsonView(value = {ViewUtentiAssociazione.RowUtentiAssociazione.class, ViewUtentiAssociazione.GetValoriAssociazione.class})
    @Column(unique = true, length = 150, nullable = false)
    private String username;
    
    @Column(length = 150, nullable = false)
    private String password;
    
    private boolean passwordTemporanea;
    
    private String ruolo;
    
    @JsonView(value = {ViewUtentiAssociazione.RowUtentiAssociazione.class, ViewUtentiAssociazione.GetValoriAssociazione.class})
    @Temporal(TemporalType.DATE)
    private Date scadenzaAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPasswordTemporanea() {
        return passwordTemporanea;
    }

    public void setPasswordTemporanea(boolean passwordTemporanea) {
        this.passwordTemporanea = passwordTemporanea;
    }
    
    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Date getScadenzaAccount() {
        return scadenzaAccount;
    }

    public void setScadenzaAccount(Date scadenzaAccount) {
        this.scadenzaAccount = scadenzaAccount;
    }

    @Override
    public String toString() {
        return "Utente{" + "id=" + id + ", username=" + username + ", password=" + password + ", passwordTemporanea=" + passwordTemporanea + ", ruolo=" + ruolo + ", scadenzaAccount=" + scadenzaAccount + '}';
    }
    
}
