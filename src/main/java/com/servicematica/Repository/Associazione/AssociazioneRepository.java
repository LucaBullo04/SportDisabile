package com.servicematica.Repository.Associazione;

import com.servicematica.Model.Associazione.Associazione;
import com.servicematica.Model.Associazione.FileLogo;
import com.servicematica.Model.UtentiAutenticazione.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociazioneRepository extends JpaRepository<Associazione, Integer> {
    Associazione findAssociazioneByLogo(FileLogo logo);
    Associazione findAssociazioneByUtente(Utente utente);
}
