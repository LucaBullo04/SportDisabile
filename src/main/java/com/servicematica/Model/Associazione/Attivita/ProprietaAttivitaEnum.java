package com.servicematica.Model.Associazione.Attivita;

public enum ProprietaAttivitaEnum {
    GIORNI("Giorni"),
    ORA("Ora"),
    DURATA("Durata");
    
    private String key;
    
    ProprietaAttivitaEnum(String key) {
        this.key = key;
    }
    
    public String getKey() {
        return key;
    }
}
