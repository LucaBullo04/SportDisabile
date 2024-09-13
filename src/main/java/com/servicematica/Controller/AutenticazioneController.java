package com.servicematica.Controller;

import com.servicematica.Model.UtentiAutenticazione.Ruoli;
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
public class AutenticazioneController {

    @Autowired
    private AutenticazioneService autenticazioneService;

    @Autowired
    private AssociazioneService associazioneService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView loginPage = new ModelAndView("Autenticazione/login");

        loginPage.addObject("titoloDellaPagina", "SportDisabile | Login");

        return loginPage;
    }

    @GetMapping("/impostaPassword")
    public ModelAndView impostaPasswordPage() {
        ModelAndView impostaPasswordPage = new ModelAndView("Autenticazione/imposta_password");

        impostaPasswordPage.addObject("titoloDellaPagina", "SportDisabile | Imposta password");

        return impostaPasswordPage;
    }

    @PostMapping("/impostaPasswordUtente")
    public ResponseEntity impostaPassword(@RequestParam String passwordNuova) {
        boolean checkImpostaPassword = false;
        passwordNuova = passwordNuova.trim().replaceAll("\\s+", "");

        if (!passwordNuova.equals("")) {
            checkImpostaPassword = autenticazioneService.impostaPassword(passwordEncoder.encode(passwordNuova));
        }

        if (checkImpostaPassword) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/passwordDimenticata")
    public ModelAndView passwordDImenticataPage() {
        ModelAndView passwordDimenticataPage = new ModelAndView("Autenticazione/password_dimenticata");

        passwordDimenticataPage.addObject("titoloDellaPagina", "SportDisabile | Password dimenticata");

        return passwordDimenticataPage;
    }

    @PostMapping("/passwordDimenticata")
    public ResponseEntity passwordDimenticata(@RequestParam String email) {
        boolean checkPasswordDimenticata = false;
        boolean checkAssociazioneModificata = false;
        email = email.trim().replaceAll("\\s+", "");
        Utente checkUtente = autenticazioneService.getUtenteByUsername(email);

        if (!email.equals("") && checkUtente != null) {
            String passwordTemporanea = autenticazioneService.generaPasswordTemporanea();
            checkPasswordDimenticata = autenticazioneService.inviaEmailPasswordSmarrita(email, associazioneService.getAssociazioneUtenteLoggato(checkUtente).getNome(), passwordTemporanea);
            checkAssociazioneModificata = autenticazioneService.passwordSmarritaModificaAssociazione(email, passwordEncoder.encode(passwordTemporanea));
        }

        if (checkPasswordDimenticata && checkAssociazioneModificata) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/associazioneDisabilitata")
    public ModelAndView associazioneDisabilitataPage() {
        ModelAndView associazioneDisabilitataPage = new ModelAndView("Autenticazione/associazione_disabilitata");

        associazioneDisabilitataPage.addObject("titoloDellaPagina", "SportDisabile | Associazione disabilitata");

        return associazioneDisabilitataPage;
    }

    @GetMapping(value = {"", "/", "/gestorePagine"})
    public String gestorePagine(HttpServletRequest request) {
        String path = "";
        Utente utenteLoggato = autenticazioneService.getUtenteLoggato(); 
 
        checkScadenzaAccount();
        //gestoreRuoloUtenteLoggato(utenteLoggato);
        System.out.println("RUOLO = " + utenteLoggato.getRuolo());

        switch (utenteLoggato.getRuolo()) {
            case "Admin":
                path = "redirect:/listaAssociazioni";
                break;
            case "Associazione":
                if (utenteLoggato.isPasswordTemporanea()) {
                    path = "redirect:/impostaPassword";
                } else if (associazioneService.getAssociazioneUtenteLoggato(utenteLoggato).getNome() != null) {
                    path = "redirect:/associazione/laMiaAssociazione";
                } else {
                    path = "redirect:/associazione/modificaAssociazione";
                }
                break;
            case "AssociazioneDisabilitata":
                path = "redirect:/associazioneDisabilitata";
                break;
        }

        return path;
    }

    private void checkScadenzaAccount() {
        List<Utente> utentiAssociazione = autenticazioneService.getUtenteByRuolo(Ruoli.Associazione.name());
        for(Utente utente : utentiAssociazione) {
            System.out.println(utente.toString());
                Date dataDiOggi = new Date();
                Date dataScadenza = utente.getScadenzaAccount();

                if(dataDiOggi.after(dataScadenza)) {
                    System.out.println("Account scaduto");
                    autenticazioneService.cambiaRuolo(utente, Ruoli.AssociazioneDisabilitata.name());
                }
        } 
    }
}
