package com.servicematica.Controller;

import com.servicematica.Service.AssistenzaService;
import com.servicematica.Service.Associazione.AssociazioneService;
import com.servicematica.Service.AutenticazioneService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AssistenzaController {
    
    @Autowired
    private AssistenzaService assistenzaService;
    
    @Autowired
    private AssociazioneService associazioneService;
    
    @Autowired
    private AutenticazioneService autenticazioneService;
    
    @GetMapping("/assistenza")
    public ModelAndView assistenzaPage(HttpServletRequest request) {
        ModelAndView assistenzaPage = new ModelAndView("Associazione/assistenza");
        assistenzaPage.addObject("titoloDellaPagina", "Assistenza");
        assistenzaPage.addObject("assistenzaClass", "active");
        
        return assistenzaPage;
    }
    
    @PostMapping("/assistenza/inviaMessaggio")
    public ResponseEntity inviaMessaggio(@RequestParam String messaggio) {
        boolean checkInvioMessaggio;
        
        try {
            String nomeAssociazione = associazioneService.getAssociazioneUtenteLoggato(autenticazioneService.getUtenteLoggato()).getNome();
            checkInvioMessaggio = assistenzaService.inviaMessaggio(nomeAssociazione, messaggio);
        } catch(Exception e) {
            checkInvioMessaggio = false;
        } 
        
        if(checkInvioMessaggio) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
    }  
}
