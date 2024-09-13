package com.servicematica.Controller.Associazione;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servicematica.Direttive.ViewAssociazione;
import com.servicematica.Model.Associazione.Associazione;
import com.servicematica.Model.Associazione.FileLogo;
import com.servicematica.Model.Associazione.ProprietaAssociazione;
import com.servicematica.Model.Associazione.ProprietaAssociazioneEnum;
import com.servicematica.Model.FormAssociazione;
import com.servicematica.Model.UtentiAutenticazione.Utente;
import com.servicematica.Repository.Associazione.FileLogoRepository;
import com.servicematica.Service.Associazione.AssociazioneService;
import com.servicematica.Service.Associazione.ProprietaAssociazioneService;
import com.servicematica.Service.AutenticazioneService;
import jakarta.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AssociazioneController {

    @Autowired
    private AssociazioneService associazioneService;

    @Autowired
    private ProprietaAssociazioneService proprietaService;
    
    @Autowired
    private FileLogoRepository fileLogoRepository;
    
    @Autowired
    private AutenticazioneService autenticazioneService;

    @GetMapping("/associazione/ilMioAccount")
    public ModelAndView ilMioAccountPage() {
        ModelAndView ilMioAccountPage = new ModelAndView("Associazione/il_mio_account");
        Associazione associazioneUtenteLoggato = associazioneService.getAssociazioneUtenteLoggato(autenticazioneService.getUtenteLoggato());
        int giorniMancanti = calcolaGiorniMancanti(new Date(), associazioneUtenteLoggato.getUtente().getScadenzaAccount());
        String dataScadenzaFormattata = formattaDataScadenza(associazioneUtenteLoggato.getUtente().getScadenzaAccount());
        
        ilMioAccountPage.addObject("giorniMancanti", giorniMancanti);
        ilMioAccountPage.addObject("dataScadenzaFormattata", dataScadenzaFormattata);
        ilMioAccountPage.addObject("associazioneUtenteLoggato", associazioneUtenteLoggato);
        ilMioAccountPage.addObject("titoloDellaPagina", "SportDisabile | Account");
        ilMioAccountPage.addObject("ilMioAccountClass", "active"); 
        
        return ilMioAccountPage;
    }

    @GetMapping("/associazione/laMiaAssociazione")
    public ModelAndView laMiaAssociazione(HttpServletRequest request) {
        ModelAndView laMiaAssociazionePage = new ModelAndView("Associazione/la_mia_associazione");        
        Associazione associazione = associazioneService.getAssociazioneUtenteLoggato(autenticazioneService.getUtenteLoggato());
        String scrittaBottoneInfo;
        
        laMiaAssociazionePage.addObject("titoloDellaPagina", "La mia associazione");
        laMiaAssociazionePage.addObject("laMiaAssociazioneClass", "active");
        laMiaAssociazionePage.addObject("associazione", associazione);
        laMiaAssociazionePage.addObject("isAdmin", autenticazioneService.verificaRuoloAdmin(request));
        if(associazione.getProprieta().isEmpty()) {
            scrittaBottoneInfo = "Inserisci";
        } else {
            scrittaBottoneInfo = "Modifica";
        }
        laMiaAssociazionePage.addObject("scrittaBottoneInfo", scrittaBottoneInfo);

        return laMiaAssociazionePage;
    }

    @GetMapping("/associazione/modificaAssociazione")
    public ModelAndView modificaAssociazionePage() {
        ModelAndView modificaAssociazionePage = new ModelAndView("Associazione/modifica_associazione");
        Associazione associazione = associazioneService.getAssociazioneUtenteLoggato(autenticazioneService.getUtenteLoggato());
        
        ObjectMapper mapper = new ObjectMapper();
        ProprietaAssociazione as = new ProprietaAssociazione(0, ProprietaAssociazioneEnum.ACRONIMO, "Stocazzp");
        
        try {
            modificaAssociazionePage.addObject("regole", mapper.writeValueAsString(as));
        } catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
        
        modificaAssociazionePage.addObject("titoloDellaPagina", "Modifica associazione");
        modificaAssociazionePage.addObject("laMiaAssociazioneClass", "active");
        modificaAssociazionePage.addObject("nomeAssociazione", associazione.getNome());
        
        if(associazione.getLogo() != null) {
            modificaAssociazionePage.addObject("idLogoAssociazione", associazione.getLogo().getId());
            modificaAssociazionePage.addObject("logoClass", "col-md-8");
            modificaAssociazionePage.addObject("isLogoNull", false);
            modificaAssociazionePage.addObject("logoBase64", "data:image/jpg;base64,"+associazione.getLogo().getDataBase64());
        } else {
            modificaAssociazionePage.addObject("idLogoAssociazione", 0);
            modificaAssociazionePage.addObject("logoClass", "col-md-12");
            modificaAssociazionePage.addObject("isLogoNull", true);
        }
        
        
        return modificaAssociazionePage;
    }
    
    @PostMapping("/associazione/inserisciAssociazione")
    public ResponseEntity insertAssociazione(@ModelAttribute FormAssociazione form){
        boolean checkInserimento = false;
        Utente utenteLoggato = autenticazioneService.getUtenteLoggato();
        
        String nome = form.getNome().trim();
        String acronimo = form.getAcronimo().trim().replaceAll("\\s+", "");
        String annoDiFondazione = form.getAnnoDiFondazione();
        String provincia = form.getProvincia();
        String citta = form.getCitta().trim();
        String indirizzo = form.getIndirizzo().trim();
        String numeroCivico = form.getNumeroCivico().trim().replaceAll("\\s+", "");
        String numeroDiTelefono = form.getNumeroDiTelefono();
        String email = form.getEmail();
        String pec = form.getPec();
        String sitoWeb = form.getSitoWeb().trim().replaceAll("\\s+", "");
        
        if (!provincia.equals("") && !nome.replaceAll("\\s+", "").equals("") && !citta.replaceAll("\\s+", "").equals("") && !indirizzo.replaceAll("\\s+", "").equals("") && !numeroCivico.equals("")) {
            
            List<ProprietaAssociazione> listaProprieta = proprietaService.inserisciListaProprieta(acronimo, annoDiFondazione, provincia, citta, indirizzo, numeroCivico, numeroDiTelefono, email, pec, sitoWeb);
            checkInserimento = associazioneService.inserisciAssociazione(form.getLogo(), nome, utenteLoggato, listaProprieta);
        }
        
        if (checkInserimento) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @JsonView(ViewAssociazione.ModificaAssociazione.class)
    @GetMapping("/associazione/getValoriAssociazione")
    public ResponseEntity getValoriAssociazione() {
        Associazione associazione = associazioneService.getAssociazioneUtenteLoggato(autenticazioneService.getUtenteLoggato());
        return new ResponseEntity(associazione, HttpStatus.OK);
    }
    
    @JsonView(ViewAssociazione.GetLogoById.class)
    @PostMapping("/getLogoById")
    public ResponseEntity getLogoById(@RequestParam int idLogoAssociazione) {
        FileLogo checkLogo = fileLogoRepository.findById(idLogoAssociazione).get();
        
        if(checkLogo != null) {
            return new ResponseEntity(checkLogo, HttpStatus.OK);
        } else {
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/getLogoUtenteLoggato")
    public ResponseEntity getLogoUtenteLoggato() {
        FileLogo logoUtenteLoggato = associazioneService.getAssociazioneUtenteLoggato(autenticazioneService.getUtenteLoggato()).getLogo();
        
        if(logoUtenteLoggato != null) {
            return new ResponseEntity(logoUtenteLoggato.getDataBase64(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }   
    }
    
    private int calcolaGiorniMancanti(Date dataDiOggi, Date dataScadenza) {
        long differenza = dataScadenza.getTime() - dataDiOggi.getTime();
        return (int) TimeUnit.DAYS.convert(differenza, TimeUnit.MILLISECONDS) + 1;
    }
    
    private String formattaDataScadenza(Date dataScadenza) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        return formatter.format(dataScadenza);
    }

}
