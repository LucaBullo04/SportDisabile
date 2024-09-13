package com.servicematica.Repository;

import com.servicematica.Model.UtentiAutenticazione.Utente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticazioneRepository extends JpaRepository<Utente, Integer> {
    public Utente findByUsername(String username);
    public List<Utente> findByRuolo(String ruolo);
}
