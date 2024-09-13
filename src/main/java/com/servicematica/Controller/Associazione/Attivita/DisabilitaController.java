package com.servicematica.Controller.Associazione.Attivita;

import com.servicematica.Model.Associazione.Attivita.Disabilita;
import com.servicematica.Service.Associazione.Attivita.DisabilitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DisabilitaController {
    
    @Autowired
    private DisabilitaService service;
    
    @GetMapping("/attivita/listaDisabilita")
    public ResponseEntity listaDisabilita() {
        List<Disabilita> listaDisabilita = service.getDisabilita();
        return new ResponseEntity(listaDisabilita, HttpStatus.OK);
    }
    
    /*@PostMapping("/gestioneAttivita/inserisciDisabilita")
    public ResponseEntity insertDisabilita(@RequestParam String nomeDisabilita) {
        boolean checkInsert = service.inserisciDisabilita(nomeDisabilita);
        
        if(checkInsert) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/gestioneAttivita/eliminaDisabilita")
    public ResponseEntity eliminaDisabilita(@RequestParam String nomeDisabilita) {
        boolean checkDelete = service.eliminaDisabilita(nomeDisabilita);
        
        if(checkDelete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }*/
    
}
