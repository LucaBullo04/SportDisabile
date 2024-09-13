package com.servicematica.Model;

import org.springframework.web.multipart.MultipartFile;

public class FormAssociazione {
    
    private MultipartFile logo = null;
    private String nome;
    private String acronimo;
    private String annoDiFondazione;
    private String provincia;
    private String citta;
    private String indirizzo;
    private String numeroCivico;
    private String numeroDiTelefono;
    private String email;
    private String pec;
    private String sitoWeb;

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getAnnoDiFondazione() {
        return annoDiFondazione;
    }

    public void setAnnoDiFondazione(String annoDiFondazione) {
        this.annoDiFondazione = annoDiFondazione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getSitoWeb() {
        return sitoWeb;
    }

    public void setSitoWeb(String sitoWeb) {
        this.sitoWeb = sitoWeb;
    }

    @Override
    public String toString() {
        return "FormAssociazione{" + "logo=" + logo + ", nome=" + nome + ", acronimo=" + acronimo + ", annoDiFondazione=" + annoDiFondazione + ", provincia=" + provincia + ", citta=" + citta + ", indirizzo=" + indirizzo + ", numeroCivico=" + numeroCivico + ", numeroDiTelefono=" + numeroDiTelefono + ", email=" + email + ", pec=" + pec + ", sitoWeb=" + sitoWeb + '}';
    }
    
}
