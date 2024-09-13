package com.servicematica.Controller.Associazione.Attivita;

import com.servicematica.Model.Associazione.Attivita.Sport;
import com.servicematica.Service.Associazione.Attivita.SportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportController {
    
    @Autowired
    private SportService service;
    
    @GetMapping("/attivita/listaSport")
    public ResponseEntity listaSport() {
        List<Sport> listaSport = service.getSport();
        return new ResponseEntity(listaSport, HttpStatus.OK);
    }
    
    /*@PostMapping("/gestioneAttivita/inserisciSport")
    public ResponseEntity insertSport(@RequestParam String nomeSport) {
        boolean checkInsert = service.inserisciSport(nomeSport);
        
        if(checkInsert) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/gestioneAttivita/eliminaSport")
    public ResponseEntity eliminaSport(@RequestParam String nomeSport) {
        boolean checkDelete = service.eliminaSport(nomeSport);
        
        if(checkDelete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }*/
    
}
