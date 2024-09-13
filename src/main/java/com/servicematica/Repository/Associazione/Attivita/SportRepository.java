package com.servicematica.Repository.Associazione.Attivita;

import com.servicematica.Model.Associazione.Attivita.Sport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Integer> {
    public Sport findSportByNomeSport(String nomeSport);
    public List<Sport> findByOrderByNomeSport();
}
