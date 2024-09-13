package com.servicematica.Service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class AssistenzaService {
    
    public boolean inviaMessaggio(String nomeAssociazione, String messaggio) {
        if (messaggio.trim().replaceAll("", "") != "") {
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
                message.setSubject("[ASSISTENZA] SportDisabile - Messaggio inviato da \"" + nomeAssociazione + "\"");
                message.setText(messaggio);
                //Address reply = new InternetAddress(email, "dsfsdf");
                //Address[] arra = new Address[1];
                //arra[0] = reply;
                //message.setReplyTo(arra);

                transport.sendMessage(message, message.getAllRecipients());
                System.out.println("Mail inviata correttamente");
                return true;
            } catch (Exception mex) {
                // System.out.println("Errore durante l'invio della mail");
                mex.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
}
