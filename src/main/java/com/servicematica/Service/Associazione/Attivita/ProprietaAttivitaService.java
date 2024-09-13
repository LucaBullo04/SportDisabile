package com.servicematica.Service.Associazione.Attivita;

import com.servicematica.Model.Associazione.Attivita.ProprietaAttivita;
import com.servicematica.Model.Associazione.Attivita.ProprietaAttivitaEnum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProprietaAttivitaService {

    public List<ProprietaAttivita> inserisciListaProprieta(String giorni, String ora, String durata) {
        List<ProprietaAttivita> listaProprieta = new ArrayList<>();
        listaProprieta.add(inserisciProprieta(ProprietaAttivitaEnum.GIORNI, giorni));
        listaProprieta.add(inserisciProprieta(ProprietaAttivitaEnum.ORA, ora));
        listaProprieta.add(inserisciProprieta(ProprietaAttivitaEnum.DURATA, durata));
        
        return listaProprieta;
    }

    private ProprietaAttivita inserisciProprieta(ProprietaAttivitaEnum chiaveProprieta, String valoreProprieta) {
        ProprietaAttivita proprietaAttivita = new ProprietaAttivita();
        proprietaAttivita.setChiaveProprieta(chiaveProprieta);
        proprietaAttivita.setValoreProprieta(valoreProprieta);

        return proprietaAttivita;
    }

}
