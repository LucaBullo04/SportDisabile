package com.servicematica.Service.Associazione.Attivita;

import com.servicematica.Model.Associazione.Attivita.Attivita;
import com.servicematica.Model.Associazione.Attivita.Disabilita;
import com.servicematica.Repository.Associazione.Attivita.AttivitaRepository;
import com.servicematica.Repository.Associazione.Attivita.DisabilitaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisabilitaService {
    
    @Autowired
    private DisabilitaRepository disabilitaRepository;
    
    @Autowired
    private AttivitaRepository attivitaRepository;

    public List<Disabilita> getDisabilita() {
        return disabilitaRepository.findByOrderByNomeDisabilita();
    }

    public boolean inserisciDisabilita(String nomeDisabilita) {
        try {
            Disabilita disabilita = new Disabilita();

            if (checkNomeDisabilita(nomeDisabilita)) {
                disabilita.setNomeDisabilita(nomeDisabilita);
                disabilitaRepository.save(disabilita);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean eliminaDisabilita(String nomeDisabilita) {
        try {
            if (checkNomeDisabilita(nomeDisabilita)) {
                Disabilita disabilitaDaEliminare = disabilitaRepository.findDisabilitaByNomeDisabilita(nomeDisabilita);
                
                List<Attivita> listaAttivita = attivitaRepository.findAttivitaByDisabilita(disabilitaDaEliminare);
                for(Attivita attivita : listaAttivita) {
                    attivita.setDisabilita(null);
                }
                
                disabilitaRepository.delete(disabilitaDaEliminare);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkNomeDisabilita(String nomeDisabilita) {
        //Disabilita checkDisabilita = repository.findDisabilitaByNomeDisabilita(nomeDisabilita);
        
        return /*checkDisabilita == null &&*/ !nomeDisabilita.trim().replaceAll("\\s+", "").equals("") && !Pattern.compile("\\d").matcher(nomeDisabilita).find();
    }
    
}
