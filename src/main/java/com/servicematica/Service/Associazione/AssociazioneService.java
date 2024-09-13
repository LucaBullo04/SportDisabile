package com.servicematica.Service.Associazione;

import com.servicematica.Model.Associazione.Associazione;
import com.servicematica.Model.Associazione.FileLogo;
import com.servicematica.Model.Associazione.ProprietaAssociazione;
import com.servicematica.Model.UtentiAutenticazione.Ruoli;
import com.servicematica.Model.UtentiAutenticazione.Utente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.servicematica.Repository.Associazione.FileLogoRepository;
import java.util.Base64;
import com.servicematica.Repository.Associazione.AssociazioneRepository;
import com.servicematica.Repository.AutenticazioneRepository;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AssociazioneService {

    @Autowired
    private AssociazioneRepository associazioneRepository;
    
    @Autowired
    private AutenticazioneRepository autenticazioneRepository;

    @Autowired
    private FileLogoRepository logoRepository;

    @Transactional
    public boolean inserisciAssociazione(MultipartFile fileLogo, String nome, Utente utenteLoggato, List<ProprietaAssociazione> proprieta) {
        Associazione associazione = getAssociazioneUtenteLoggato(utenteLoggato);
        FileLogo logo;

        if (associazione.getLogo() == null) {
            logo = new FileLogo();
        } else {
            logo = associazione.getLogo();
        }

        if (!associazione.getProprieta().isEmpty()) {
            associazione.getProprieta().clear();
        }

        try {
            System.out.println("FILELOGO = " + fileLogo);

            // INIZIO LOGICA INSERIMENTO LOGO NEL DB
            if (fileLogo != null) {
                String dataBase64 = new String(convertiLogoInBase64(fileLogo.getBytes()), "UTF-8");
                String originalFileName = fileLogo.getOriginalFilename();

                logo.setFileName(originalFileName.substring(0, originalFileName.indexOf(".")));
                logo.setFileType(fileLogo.getContentType());
                logo.setDataBase64(dataBase64);

                logoRepository.save(logo);
            }

            // FINE LOGICA INSERIMENTO LOGO NEL DB
            // INIZIO LOGICA INSERIMENTO DATI ASSOCIAZIONE NEL DB
            inserisciNelDB(associazione, nome, logo, proprieta);
            return true;
            // FINE LOGICA INSERIMENTO DATI ASSOCIAZIONE NEL DB
        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println("Errore nell'inserimento dell'associazione");
            return false;
        }
    }

    @Transactional
    public void inserisciNelDB(Associazione associazione, String nome, FileLogo logo, /*Utente utente,*/ List<ProprietaAssociazione> proprieta) {
        associazione.setLogo(logo);
        associazione.setNome(nome);
        //associazione.setUtente(utente);
        //associazione.setProprieta(proprieta);
        associazione.getProprieta().addAll(proprieta);
        associazioneRepository.save(associazione);
    }

    public Associazione getAssociazioneUtenteLoggato(Utente utenteLoggato) {
        Associazione associazioneUtenteLoggato = associazioneRepository.findAssociazioneByUtente(utenteLoggato);
        return associazioneUtenteLoggato;
    }

    public byte[] convertiLogoInBase64(byte[] data) {
        return Base64.getEncoder().encode(data);
    }

    public List<Associazione> getAssociazioniAttive() {
        List<Utente> utenti = autenticazioneRepository.findByRuolo(Ruoli.Associazione.name());
        List<Associazione> associazioni = new ArrayList<>();
        for(Utente utente : utenti) {
            associazioni.add(associazioneRepository.findAssociazioneByUtente(utente));
        }
        return associazioni;
    }

    public Associazione getAssociazioneById(int associazioneId) {
        return associazioneRepository.findById(associazioneId).get();
    }

}
