package com.servicematica.Repository.Associazione.Attivita;

import com.servicematica.Model.Associazione.Attivita.Disabilita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisabilitaRepository extends JpaRepository<Disabilita, Integer>{
    public Disabilita findDisabilitaByNomeDisabilita(String nomeDisabilita);
    public List<Disabilita> findByOrderByNomeDisabilita();
}
