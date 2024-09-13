package com.servicematica.Service.Associazione.Attivita;

import com.servicematica.Model.Associazione.Associazione;
import com.servicematica.Model.Associazione.Attivita.Attivita;
import com.servicematica.Model.Associazione.Attivita.Disabilita;
import com.servicematica.Model.Associazione.Attivita.ProprietaAttivita;
import com.servicematica.Model.Associazione.Attivita.Sport;
import com.servicematica.Model.UtentiAutenticazione.Ruoli;
import com.servicematica.Repository.Associazione.AssociazioneRepository;
import com.servicematica.Repository.Associazione.Attivita.AttivitaRepository;
import com.servicematica.Repository.Associazione.Attivita.DisabilitaRepository;
import com.servicematica.Repository.Associazione.Attivita.SportRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttivitaService {

    @Autowired
    private AttivitaRepository attivitaRepository;

    @Autowired
    private AssociazioneRepository associazioneRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private DisabilitaRepository disabilitaRepository;

    @Autowired
    private ProprietaAttivitaService proprietaAttivitaService;

    @Transactional
    public boolean inserisciAttivita(Associazione associazione, int sportId, int disabilitaId, String giorni, String ora, String durata) {
        boolean checkInsert;

        try {
            Attivita attivita = new Attivita();
            checkInsert = inserisciNelDB(attivita, associazione, sportId, disabilitaId, giorni, ora, durata);
        } catch (Exception e) {
            checkInsert = false;
            System.out.println("Errore nell'inserimento dell'attività");
        }

        return checkInsert;
    }

    @Transactional
    public boolean modificaAttivita(Associazione associazione, int attivitaId, int sportId, int disabilitaId, String nuoviGiorni, String nuovaOra, String nuovaDurata) {
        boolean checkModifica;

        try {
            Attivita attivitaDaModificare = attivitaRepository.findById(attivitaId).get();
            Associazione vecchiaAssociazione = attivitaDaModificare.getAssociazione();

            // DATO CHE C'è L'ANNOTATION @Transactional NON SI PUò MODIFICARE L'ASSOCIAZIONE DELL'ATTIVITà
            // IL PROBLEMA VIENE RISOLTO CREANDO UNA NUOVA ATTIVITà COI VALORI DI QUELLA DA MODIFICARE, ELIMINANDO QUEST'ULTIMA DAL DATABASE
            if (associazione.getId() != vecchiaAssociazione.getId()) {
                Attivita nuovaAttivita = new Attivita();

                nuovaAttivita.setAssociazione(associazione);
                associazione.getAttivita().add(nuovaAttivita);
                nuovaAttivita.setSport(attivitaDaModificare.getSport());
                nuovaAttivita.setDisabilita(attivitaDaModificare.getDisabilita());
                vecchiaAssociazione.getAttivita().remove(attivitaDaModificare);
                attivitaDaModificare.setAssociazione(associazione);

                attivitaDaModificare = nuovaAttivita;
            }

            attivitaDaModificare.getProprieta().clear();

            checkModifica = inserisciNelDB(attivitaDaModificare, associazione, sportId, disabilitaId, nuoviGiorni, nuovaOra, nuovaDurata);
        } catch (Exception e) {
            checkModifica = false;
            System.out.println("Errore nella modifica dell'attività");
        }
        return checkModifica;
    }

    @Transactional
    private boolean inserisciNelDB(Attivita attivita, Associazione associazione, int sportId, int disabilitaId, String giorni, String ora, String durata) {
        try {
            boolean checkOra = controllaOrario(ora);
            boolean checkDurata = controllaDurata(durata);
            giorni = giorni.replaceAll(",", ", ");
            durata = durata.replaceAll("_", "");

            if (!giorni.trim().replaceAll("\\s+", "").equals("") && checkOra && checkDurata) {
                Sport sport = sportRepository.findById(sportId).get();
                Disabilita disabilita = disabilitaRepository.findById(disabilitaId).get();
                List<ProprietaAttivita> proprieta = proprietaAttivitaService.inserisciListaProprieta(giorni, ora, durata);

                // CONTROLLA SE AVVIENE UN INSERT O UN UPDATE
                if (attivita.getAssociazione() == null) {
                    attivita.setAssociazione(associazione);
                    attivita.getAssociazione().getAttivita().add(attivita);
                }

                attivita.setSport(sport);
                attivita.setDisabilita(disabilita);
                attivita.getProprieta().addAll(proprieta);

                attivitaRepository.save(attivita);
                associazioneRepository.save(associazione);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void eliminaAttivita(int attivitaId) {
        Attivita attivitaDaEliminare = attivitaRepository.findById(attivitaId).get();
        attivitaDaEliminare.getAssociazione().getAttivita().remove(attivitaDaEliminare);
        attivitaRepository.delete(attivitaDaEliminare);
    }

    public List<Attivita> getAttivitaAssociazione(Associazione associazione) {
        return attivitaRepository.findAttivitaByAssociazione(associazione);
    }

    public List<Attivita> getAttivitaAdmin() {
        List<Attivita> listaAttivita = attivitaRepository.findAll();
        List<Attivita> attivitaAttive = new ArrayList<>();
        String ruoloUtenteAttivita;
        for(Attivita attivita : listaAttivita) {
            ruoloUtenteAttivita = attivita.getAssociazione().getUtente().getRuolo();
            
            if(ruoloUtenteAttivita.equals(Ruoli.Associazione.name())) {
                attivitaAttive.add(attivita);
            }
        }
        return attivitaAttive;
    }

    private boolean controllaOrario(String ora) {
        if (ora.equals("")) {
            return false;
        } else {
            try {
                String[] orario = ora.split(":");
                int valoreOra = Integer.parseInt(orario[0]);
                int valoreMinuti = Integer.parseInt(orario[1]);

                return !(valoreOra > 23 || valoreMinuti > 59);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("Errore nel controllo dell'orario");
                return false;
            }
        }
    }

    private boolean controllaDurata(String durata) {
        if (durata.equals("")) {
            return false;
        } else {
            try {
                durata = durata.substring(0, 3).trim().replaceAll("_", "");
                int valoreDurata = Integer.parseInt(durata);

                return !(valoreDurata == 0 || valoreDurata > 500);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("Errore nel controllo della durata");
                return false;
            }
        }
    }
    
    public Attivita getAttivitaById(int attivitaId) {
        return attivitaRepository.findById(attivitaId).get();
    }
    
}
