package com.servicematica.Repository.Associazione;

import com.servicematica.Model.Associazione.ProprietaAssociazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietaAssociazioneRepository extends JpaRepository<ProprietaAssociazione, Integer>{
    
}
