package com.servicematica.Controller.Associazione.Attivita;

import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewAttivita;
import com.servicematica.Model.Associazione.Associazione;
import com.servicematica.Model.Associazione.Attivita.Attivita;
import com.servicematica.Model.Associazione.Attivita.ProprietaAttivitaEnum;
import com.servicematica.Model.UtentiAutenticazione.Ruoli;
import com.servicematica.Service.Associazione.AssociazioneService;
import com.servicematica.Service.Associazione.Attivita.AttivitaService;
import com.servicematica.Service.AutenticazioneService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AttivitaController {

    @Autowired
    private AttivitaService attivitaService;

    @Autowired
    private AutenticazioneService autenticazioneService;
    
    @Autowired
    private AssociazioneService associazioneService;

    @GetMapping("/attivita/inserisciAttivita")
    public ModelAndView inserisciAttivitaPage(HttpServletRequest request) {
        ModelAndView inserisciAttivitaPage = new ModelAndView("Associazione/Attivita/inserisci_attivita");
        
        importaAttributi(inserisciAttivitaPage, request, "Inserisci attività", "inserisciAttivitaClass");
        return inserisciAttivitaPage;
    }
    
    @PostMapping("/attivita/creaAttivita")
    public ResponseEntity inserisciAttivita(HttpServletRequest request, @RequestParam(name = "associazioneId", required = false) Integer associazioneId, @RequestParam("sportId") int sportId, @RequestParam("disabilitaId") int disabilitaId, @RequestParam("giorni") String giorni, @RequestParam("ora") String ora, @RequestParam("durata") String durata) {
        boolean checkInserimento = false;
        Associazione associazione = checkAssociazione(request, associazioneId);
        
        checkInserimento = attivitaService.inserisciAttivita(associazione, sportId, disabilitaId, giorni, ora, durata);

        if (checkInserimento) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/attivita/modificaAttivita")
    public ModelAndView modificaAttivitaPage(HttpServletRequest request, @RequestParam("attivitaId") int attivitaId) {
        ModelAndView modificaAttivitaPage = new ModelAndView("Associazione/Attivita/modifica_attivita");
        
        modificaAttivitaPage.addObject("attivitaId", attivitaId);
        importaAttributi(modificaAttivitaPage, request, "Modifica attività", "listaAttivitaClass");

        return modificaAttivitaPage;
    }
    
    @PostMapping("/attivita/getAttivitaById")
    public ResponseEntity getAttivitaById(@RequestParam int attivitaId, HttpServletRequest request) {
        Attivita attivita = attivitaService.getAttivitaById(attivitaId);
        MappingJacksonValue value = new MappingJacksonValue(attivita);
        
        if(autenticazioneService.verificaRuoloAdmin(request)) {
            value.setSerializationView(ViewAttivita.ModificaAttivitaAdmin.class);
        } else {
            value.setSerializationView(ViewAttivita.ModificaAttivitaAssociazione.class);
        }
        
        return new ResponseEntity(value,HttpStatus.OK);
    }
    
    @PostMapping("/attivita/cambiaValoriAttivita")
    public ResponseEntity modificaAttivita(HttpServletRequest request, @RequestParam(name = "associazioneId", required = false) Integer associazioneId, @RequestParam("attivitaId") int attivitaId, @RequestParam("sportId") int nuovoSportId, @RequestParam("disabilitaId") int nuovaDisabilitaId, @RequestParam("giorni") String nuoviGiorni, @RequestParam("ora") String nuovaOra, @RequestParam("durata") String nuovaDurata) {
        boolean checkModifica = false;
        Associazione associazione = checkAssociazione(request, associazioneId);

        checkModifica = attivitaService.modificaAttivita(associazione, attivitaId, nuovoSportId, nuovaDisabilitaId, nuoviGiorni, nuovaOra, nuovaDurata);
        
        if (checkModifica) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/attivita/eliminaAttivita")
    public ResponseEntity eliminaAttivita(@RequestParam int attivitaId) {
        attivitaService.eliminaAttivita(attivitaId);
        boolean isAdmin = autenticazioneService.getUtenteLoggato().getRuolo().equals(Ruoli.Admin.name());
        return new ResponseEntity(isAdmin, HttpStatus.OK);
    }
    
    @GetMapping("/attivita/listaAttivita")
    public ModelAndView listaAttivitaPage(HttpServletRequest request) {
        ModelAndView listaAttivitaPage = new ModelAndView("Associazione/Attivita/lista_attivita");
        
        importaAttributi(listaAttivitaPage, request, "Lista attività", "listaAttivitaClass");
        return listaAttivitaPage;
    }
    
    @JsonView(ViewAttivita.RowAttivitaAssociazione.class)
    @GetMapping("/listaAttivita/associazione")
    public ResponseEntity getAttivitaAssociazione() {
        Associazione associazione = associazioneService.getAssociazioneUtenteLoggato(autenticazioneService.getUtenteLoggato());
        List<Attivita> attivitaAssociazione = attivitaService.getAttivitaAssociazione(associazione);
        
        return new ResponseEntity(attivitaAssociazione, HttpStatus.OK);
    }
    
    @JsonView(ViewAttivita.RowAttivitaAdmin.class)
    @GetMapping("/listaAttivita/admin")
    public ResponseEntity getAttivitaAdmin() {
        List<Attivita> attivitaAdmin = attivitaService.getAttivitaAdmin();

        return new ResponseEntity(attivitaAdmin, HttpStatus.OK);
    }

    @GetMapping("/attivita/listaProprieta")
    public ResponseEntity listaProprieta() {
        List<String> proprieta = new ArrayList();
        ProprietaAttivitaEnum[] listaProprieta = ProprietaAttivitaEnum.values();
        for (int i = 0; i < listaProprieta.length; i++) {
            proprieta.add(listaProprieta[i].getKey());
        }

        return new ResponseEntity(proprieta, HttpStatus.OK);
    }
    
    @JsonView(ViewAttivita.RowListaAssociazioni.class)
    @GetMapping("/gestioneAttivita/listaAssociazioniAttive")
    public ResponseEntity getListaAssociazioni() {
        List<Associazione> listaAssociazioni = associazioneService.getAssociazioniAttive();
        
        return new ResponseEntity(listaAssociazioni, HttpStatus.OK);
    }
    
    private void importaAttributi(ModelAndView pagina, HttpServletRequest request, String titoloPagina, String nomeClasse) {
        pagina.addObject("titoloDellaPagina", titoloPagina);
        pagina.addObject("dropdownAttivitaGestore", "menu-open");
        pagina.addObject("dropdownAttivitaClass", "active");
        pagina.addObject(nomeClasse, "active");
        
        if (autenticazioneService.verificaRuoloAdmin(request)) {
            pagina.addObject("isAdmin", true);
            pagina.addObject("lunghezzaColonne", "col-md-4");
        } else {
            pagina.addObject("isAdmin", false);
            pagina.addObject("lunghezzaColonne", "col-md-6");
        }
    }
    
    private Associazione checkAssociazione(HttpServletRequest request, Integer associazioneId) {
        if(autenticazioneService.verificaRuoloAdmin(request)) {
            return associazioneService.getAssociazioneById(associazioneId);
        } else {
            return associazioneService.getAssociazioneUtenteLoggato(autenticazioneService.getUtenteLoggato());
        }
    }

}
