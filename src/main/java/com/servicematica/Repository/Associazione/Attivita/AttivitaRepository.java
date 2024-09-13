package com.servicematica.Repository.Associazione.Attivita;

import com.servicematica.Model.Associazione.Associazione;
import com.servicematica.Model.Associazione.Attivita.Attivita;
import com.servicematica.Model.Associazione.Attivita.Disabilita;
import com.servicematica.Model.Associazione.Attivita.Sport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttivitaRepository extends JpaRepository<Attivita, Integer>{
    public List<Attivita> findAttivitaByAssociazione(Associazione associazione);
    public List<Attivita> findAttivitaBySport(Sport sport);
    public List<Attivita> findAttivitaByDisabilita(Disabilita disabilita);
}
