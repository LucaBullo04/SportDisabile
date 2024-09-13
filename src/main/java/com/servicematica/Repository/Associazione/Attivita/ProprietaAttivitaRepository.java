package com.servicematica.Repository.Associazione.Attivita;

import com.servicematica.Model.Associazione.Attivita.ProprietaAttivita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietaAttivitaRepository extends JpaRepository<ProprietaAttivita, Integer> {
    
}
