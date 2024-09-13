package com.servicematica.Service;

import com.servicematica.Model.Associazione.Associazione;
import com.servicematica.Model.UtentiAutenticazione.Ruoli;
import com.servicematica.Model.UtentiAutenticazione.Utente;
import com.servicematica.Repository.Associazione.AssociazioneRepository;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.servicematica.Repository.AutenticazioneRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class AutenticazioneService implements UserDetailsService {

    @Autowired
    private AutenticazioneRepository autenticazioneRepository;

    @Autowired
    private AssociazioneRepository associazioneRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Utente utente = autenticazioneRepository.findByUsername(username);
            return User.builder()
                    .username(utente.getUsername())
                    .password(utente.getPassword())
                    .roles(getRoles(utente))
                    .build();
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(Utente utente) {
        if (utente.getRuolo().equals(Ruoli.Associazione.name()) || utente.getRuolo().equals(Ruoli.AssociazioneDisabilitata.name())) {
            String[] ruoloDefault;
            Date dataDiOggi = new Date();
            if (!dataDiOggi.after(utente.getScadenzaAccount())) {
                ruoloDefault = new String[]{Ruoli.Associazione.name()};
            } else {
                ruoloDefault = new String[]{Ruoli.AssociazioneDisabilitata.name()};
            }

            return ruoloDefault;
        } else {
            String[] ruoliAdmin = {Ruoli.Associazione.name(), Ruoli.Admin.name()};
            return ruoliAdmin;
        }
    }

    public boolean verificaRuoloAdmin(HttpServletRequest request) {
        return request.isUserInRole(Ruoli.Admin.name());
    }

    public String getRuoloUtenteLoggato() {
        return getUtenteLoggato().getRuolo();
    }

    public void cambiaRuolo(Utente utenteLoggato, String ruoloNuovo) {
        utenteLoggato.setRuolo(ruoloNuovo);
        autenticazioneRepository.save(utenteLoggato);
    }

    public Utente getUtenteByUsername(String username) {
        return autenticazioneRepository.findByUsername(username);
    }

    public Utente getUtenteLoggato() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utenteLoggato = autenticazioneRepository.findByUsername(userDetails.getUsername());
        return utenteLoggato;
    }

    public boolean creaUtente(String username, String partitaIva, String passwordTemporanea, Date scadenzaAccount) {
        Utente nuovoUtente = new Utente();
        Associazione associazione = new Associazione();

        try {
            nuovoUtente.setUsername(username);
            nuovoUtente.setPassword(passwordTemporanea);
            nuovoUtente.setPasswordTemporanea(true);
            nuovoUtente.setRuolo(Ruoli.Associazione.name());
            nuovoUtente.setScadenzaAccount(scadenzaAccount);
            autenticazioneRepository.save(nuovoUtente);

            associazione.setPartitaIva(partitaIva);
            associazione.setUtente(nuovoUtente);
            associazioneRepository.save(associazione);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminaAssociazione(int associazioneId) {
        try {
            Associazione associazioneDaEliminare = associazioneRepository.findById(associazioneId).get();
            associazioneRepository.delete(associazioneDaEliminare);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificaAssociazione(int associazioneId, String nuovoUsername, String nuovaPartitaIva, Date nuovaScadenzaAccount) {
        try {
            Associazione associazioneDaModificare = associazioneRepository.findById(associazioneId).get();
            Utente utenteAssociazione = associazioneDaModificare.getUtente();

            utenteAssociazione.setUsername(nuovoUsername);
            if (nuovaScadenzaAccount != null) {
                utenteAssociazione.setScadenzaAccount(nuovaScadenzaAccount);
            }
            if(utenteAssociazione.getRuolo().equals(Ruoli.AssociazioneDisabilitata.name())) {
                utenteAssociazione.setRuolo(Ruoli.Associazione.name());
            }
            autenticazioneRepository.save(utenteAssociazione);

            associazioneDaModificare.setPartitaIva(nuovaPartitaIva);
            associazioneRepository.save(associazioneDaModificare);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Associazione> getListaAssociazioni(boolean isAttiva) {
        String ruolo = Ruoli.AssociazioneDisabilitata.name();
        if(isAttiva) {
            ruolo = Ruoli.Associazione.name();
        }
        
        List<Utente> utenti = autenticazioneRepository.findByRuolo(ruolo);
        List<Associazione> associazioni = new ArrayList<>();
        for(Utente utente : utenti) {
            associazioni.add(associazioneRepository.findAssociazioneByUtente(utente));
        }
        
        return associazioni;
    }
    
    public List<Utente> getUtenteByRuolo(String ruolo) {
        return autenticazioneRepository.findByRuolo(ruolo);
    }

    public boolean impostaPassword(String passwordNuova) {
        Utente utente = getUtenteLoggato();

        try {
            utente.setPassword(passwordNuova);
            utente.setPasswordTemporanea(false);

            autenticazioneRepository.save(utente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void inviaEmailPasswordTemporanea(String passwordTemporanea) {
        try {
            String to = "lbullo@servicematica.com";
            String fromUsername = "lbullo@servicematica.com";
            String fromPassword = "Marzo2024!";
            String host = "authsmtp.securemail.pro";

            Properties properties = System.getProperties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.transport.protocol", "smtps");
            properties.put("mail.debug", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.checkserveridentity", "false");
            properties.put("mail.smtp.ssl.trust", "*");
            properties.put("mail.smtp.connectiontimeout", "10000");

            Session session = Session.getDefaultInstance(properties);
            Transport transport = session.getTransport();
            transport.connect(host, fromUsername, fromPassword);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromUsername));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("SportDisabile - Password temporanea dell'account");
            message.setContent("""

                                        <h1 style='display: flex; justify-content: center;'>Sport<span style='color: #18A2B8'>Disabile</span></h1>
                                        <p style='display: flex; justify-content: center;'>Le diamo il benvenuto nel nostro gestionale. Per accedere al sito dovrà inserire la seguente password:</p>
                                        <h4 style='display: flex; justify-content: center;'>%s</h4>
                                        <p style='display: flex; justify-content: center;'>Non appena avverrà l'accesso, la preghiamo di cambiare la password con una personale.</p>
                                   
                      """.formatted(passwordTemporanea), "text/html; charset=utf-8");

            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail inviata correttamente");
        } catch (Exception mex) {
            // System.out.println("Errore durante l'invio della mail per la password temporanea");
            mex.printStackTrace();
        }
    }

    public boolean inviaEmailPasswordSmarrita(String email, String nomeAssociazione, String passwordTemporanea) {
        try {
            String to = /*email*/ "lbullo@servicematica.com";
            String fromUsername = "lbullo@servicematica.com";
            String fromPassword = "Marzo2024!";
            String host = "authsmtp.securemail.pro";

            Properties properties = System.getProperties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.transport.protocol", "smtps");
            properties.put("mail.debug", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.checkserveridentity", "false");
            properties.put("mail.smtp.ssl.trust", "*");
            properties.put("mail.smtp.connectiontimeout", "10000");

            Session session = Session.getDefaultInstance(properties);
            Transport transport = session.getTransport();
            transport.connect(host, fromUsername, fromPassword);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromUsername));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("SportDisabile - Cambiare password dell'account");
            /*message.setContent("""

                                        <h1 style='display: flex; justify-content: center;'>Sport<span style='color: #18A2B8'>Disabile</span></h1>
                                        <p style='display: flex; justify-content: center;'>Salve %s! Ci è arrivata una richiesta per il cambio di password del tuo account.</p>
                                        <p style='display: flex; justify-content: center;'>Se hai cambiato idea sulla tua richiesta, puoi tranquillamente ignorare questa email, altrimenti puoi cliccare sul bottone per completare il processo.</p>
                                        <button onclick='/impostaPassword' style='display: flex; justify-content: center; background-color: #18A2B8'>Cambia password</button>
                                   
                      """.formatted(nomeAssociazione), "text/html; charset=utf-8");*/

            message.setContent("""

                                        <h1 style='display: flex; justify-content: center;'>Sport<span style='color: #18A2B8'>Disabile</span></h1>
                                        <p style='display: flex; justify-content: center;'>Salve %s! Ci è arrivata una richiesta per il cambio di password del tuo account.</p>
                                        <p style='display: flex; justify-content: center;'>Se ha cambiato idea sulla sua richiesta, può tranquillamente ignorare questa email, altrimenti può inserire la seguente password per accedere:</p>
                                        <h4 style='display: flex; justify-content: center;'>%s</h4>
                                        <p style='display: flex; justify-content: center;'>Non appena avverrà l'accesso, la preghiamo di cambiare la password con una personale.</p>
                                   
                      """.formatted(nomeAssociazione, passwordTemporanea), "text/html; charset=utf-8");

            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail inviata correttamente");
            return true;
        } catch (Exception mex) {
            // System.out.println("Errore durante l'invio della mail per la password temporanea");
            mex.printStackTrace();
            return false;
        }
    }

    public String generaPasswordTemporanea() {
        Random random = new Random();
        String valori = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password = "";

        //Lunghezza password: 8
        for (int i = 0; i <= 7; i++) {
            password += valori.charAt(random.nextInt(valori.length()));
        }

        return password;
    }

    public boolean passwordSmarritaModificaAssociazione(String username, String passwordTemporanea) {
        try {
            Utente utente = autenticazioneRepository.findByUsername(username);
            utente.setPassword(passwordTemporanea);
            utente.setPasswordTemporanea(true);
            autenticazioneRepository.save(utente);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
