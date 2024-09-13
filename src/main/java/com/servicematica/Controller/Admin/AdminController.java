package com.servicematica.Controller.Admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewUtentiAssociazione;
import com.servicematica.Model.Associazione.Associazione;
import com.servicematica.Model.UtentiAutenticazione.Utente;
import com.servicematica.Service.Associazione.AssociazioneService;
import com.servicematica.Service.AutenticazioneService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    
    @Autowired
    private AutenticazioneService autenticazioneService;
    
    @Autowired
    private AssociazioneService associazioneService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/inserisciAssociazione")
    public ModelAndView inserisciAssociazionePage() {
        ModelAndView inserisciAssociazionePage = new ModelAndView("Admin/inserisci_associazione");
        
        inserisciAssociazionePage.addObject("associazioneId", 0);
        inserisciAssociazionePage.addObject("labelDurataAccount", "Valido per");
        inserisciAssociazionePage.addObject("titoloDellaPagina", "Inserisci associazione");
        inserisciAssociazionePage.addObject("dropdownAssociazioniGestore", "menu-open");
        inserisciAssociazionePage.addObject("dropdownAssociazioniClass", "active");
        inserisciAssociazionePage.addObject("inserisciAssociazioneClass", "active");
        
        return inserisciAssociazionePage;
    }
    
    @PostMapping("/creaUtenteAssociazione")
    public ResponseEntity creaUtenteAssociazione(@RequestParam("username") String username, @RequestParam("partitaIva") String partitaIva, @RequestParam("scadenzaAccount") Date scadenzaAccount) {
        boolean checkInserimento = false;
        Utente checkUtente = autenticazioneService.getUtenteByUsername(username);
        
        if(partitaIva.replaceAll("_", "").length() == 11 && checkUtente == null) {
            String passwordTemporanea = autenticazioneService.generaPasswordTemporanea();
            checkInserimento = autenticazioneService.creaUtente(username, partitaIva, passwordEncoder.encode(passwordTemporanea), scadenzaAccount);
            autenticazioneService.inviaEmailPasswordTemporanea(passwordTemporanea);
        }
        
        if (checkInserimento) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/listaAssociazioni")
    public ModelAndView listaAssociazioniPage() {
        ModelAndView listaAssociazioniPage = new ModelAndView("Admin/lista_associazioni");
        
        listaAssociazioniPage.addObject("titoloDellaPagina", "Lista associazioni");
        listaAssociazioniPage.addObject("dropdownAssociazioniGestore", "menu-open");
        listaAssociazioniPage.addObject("dropdownAssociazioniClass", "active");
        listaAssociazioniPage.addObject("listaAssociazioniClass", "active");
        
        return listaAssociazioniPage;
    }
    
    @PostMapping("/eliminaAssociazione")
    public ResponseEntity eliminaAssociazione(@RequestParam int associazioneId) {
        boolean checkEliminaAssociazione = autenticazioneService.eliminaAssociazione(associazioneId);
                
        if (checkEliminaAssociazione) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/modificaAssociazione")
    public ModelAndView modificaAssociazionePage(@RequestParam("associazioneId") int associazioneId) {
        ModelAndView modificaAssociazionePage = new ModelAndView("Admin/modifica_associazione_admin");
        System.out.println("ASSOCIAZIONE ID = " + associazioneId);
        modificaAssociazionePage.addObject("associazioneId", associazioneId);
        modificaAssociazionePage.addObject("labelDurataAccount", "Rinnova per");
        modificaAssociazionePage.addObject("titoloDellaPagina", "Modifica associazione");
        modificaAssociazionePage.addObject("dropdownAssociazioniGestore", "menu-open");
        modificaAssociazionePage.addObject("dropdownAssociazioniClass", "active");
        modificaAssociazionePage.addObject("listaAssociazioniClass", "active");

        return modificaAssociazionePage;
    }
    
    @JsonView(ViewUtentiAssociazione.GetValoriAssociazione.class)
    @PostMapping("/getAssociazioneById")
    public ResponseEntity getAssociazioneById(@RequestParam int associazioneId) {
        Associazione associazione = associazioneService.getAssociazioneById(associazioneId);
        
        return new ResponseEntity(associazione, HttpStatus.OK);
    }
    
    @PostMapping("/cambiaValoriAssociazione")
    public ResponseEntity modificaAssociazione(@RequestParam("associazioneId") int associazioneId, @RequestParam("nuovoUsername") String nuovoUsername, @RequestParam("nuovaPartitaIva") String nuovaPartitaIva, @RequestParam(value = "nuovaScadenzaAccount", required = false) Date nuovaScadenzaAccount) {
        boolean checkModificaAssociazione = false;      

        if(nuovaPartitaIva.replaceAll("_", "").length() == 11) {
            checkModificaAssociazione = autenticazioneService.modificaAssociazione(associazioneId, nuovoUsername, nuovaPartitaIva, nuovaScadenzaAccount);
        }
        
        if (checkModificaAssociazione) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @JsonView(ViewUtentiAssociazione.RowUtentiAssociazione.class)
    @PostMapping("/listaRowAssociazioni")
    public ResponseEntity ottieniListaAssociazioni(@RequestParam boolean isAttiva) {
        List<Associazione> listaAssociazioni = autenticazioneService.getListaAssociazioni(isAttiva);
        return new ResponseEntity(listaAssociazioni, HttpStatus.OK);
    }
    
    @PostMapping("/getInfoAssociazione")
    public ModelAndView getInfoAssociazione(@RequestParam int associazioneId, HttpServletRequest request) {
        ModelAndView infoAssociazionePage = new ModelAndView("Associazione/la_mia_associazione");
        Associazione associazione = associazioneService.getAssociazioneById(associazioneId);
        infoAssociazionePage.addObject("titoloDellaPagina", "Associazione info");
        infoAssociazionePage.addObject("dropdownAssociazioniGestore", "menu-open");
        infoAssociazionePage.addObject("dropdownAssociazioniClass", "active");
        infoAssociazionePage.addObject("listaAssociazioniClass", "active");
        infoAssociazionePage.addObject("associazione", associazione);
        infoAssociazionePage.addObject("isAdmin", autenticazioneService.verificaRuoloAdmin(request));
        
        return infoAssociazionePage;
    }
    
    /*@GetMapping("/gestioneListeAttivita")
    public ModelAndView gestioneAttivitaPage() {
        ModelAndView gestioneAttivitaPage = new ModelAndView("Admin/gestione_attivita");
        
        gestioneAttivitaPage.addObject("titoloDellaPagina", "Gestione attività");
        gestioneAttivitaPage.addObject("gestioneAttivitaClass", "active");
        
        return gestioneAttivitaPage;
    }*/
    
}
