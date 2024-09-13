package com.servicematica.Service.Associazione;

import com.servicematica.Model.Associazione.ProprietaAssociazione;
import com.servicematica.Model.Associazione.ProprietaAssociazioneEnum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProprietaAssociazioneService {

    public List<ProprietaAssociazione> inserisciListaProprieta(String acronimo, String annoDiFondazione, String provincia, String citta, String indirizzo, String numeroCivico, String numeroDiTelefono, String email, String pec, String sitoWeb) { 
        List<ProprietaAssociazione> listaProprieta = new ArrayList<>();

        
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.ACRONIMO, acronimo));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.ANNO_DI_FONDAZIONE, annoDiFondazione));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.PROVINCIA, provincia));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.CITTA, citta));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.INDIRIZZO, indirizzo));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.NUMERO_CIVICO, numeroCivico));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.NUMERO_DI_TELEFONO, numeroDiTelefono));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.EMAIL, email));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.PEC, pec));
        listaProprieta.add(creaProprieta(ProprietaAssociazioneEnum.SITO_WEB, sitoWeb));
        

        return listaProprieta;
    }

    private ProprietaAssociazione creaProprieta(ProprietaAssociazioneEnum chiaveProprieta, String valoreProprieta) {
        ProprietaAssociazione proprietaAssociazione = new ProprietaAssociazione();
        proprietaAssociazione.setChiaveProprieta(chiaveProprieta);
        if (!valoreProprieta.equals("") && !valoreProprieta.equals("0")) {
            proprietaAssociazione.setValoreProprieta(valoreProprieta);
        } else {
            proprietaAssociazione.setValoreProprieta(null);
        }

        return proprietaAssociazione;
    }

}
