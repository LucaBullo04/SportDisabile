package com.servicematica.Service.Associazione.Attivita;

import com.servicematica.Model.Associazione.Attivita.Attivita;
import com.servicematica.Model.Associazione.Attivita.Sport;
import com.servicematica.Repository.Associazione.Attivita.AttivitaRepository;
import com.servicematica.Repository.Associazione.Attivita.SportRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;
    
    @Autowired
    private AttivitaRepository attivitaRepository;

    public List<Sport> getSport() {
        return sportRepository.findByOrderByNomeSport();
    }

    public boolean inserisciSport(String nomeSport) {
        try {
            Sport sport = new Sport();

            if (checkNomeSport(nomeSport)) {
                sport.setNomeSport(nomeSport);
                sportRepository.save(sport);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean eliminaSport(String nomeSport) {
        try {
            if (checkNomeSport(nomeSport)) {
                Sport sportDaEliminare = sportRepository.findSportByNomeSport(nomeSport);
                
                List<Attivita> listaAttivita = attivitaRepository.findAttivitaBySport(sportDaEliminare);
                for(Attivita attivita : listaAttivita) {
                    attivita.setSport(null);
                }
                        
                sportRepository.delete(sportDaEliminare);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean checkNomeSport(String nomeSport) {
        //Sport checkSport = repository.findSportByNomeSport(nomeSport);
        
        return /*checkSport == null &&*/ !nomeSport.trim().replaceAll("\\s+", "").equals("") && !Pattern.compile("\\d").matcher(nomeSport).find();
    }

}
