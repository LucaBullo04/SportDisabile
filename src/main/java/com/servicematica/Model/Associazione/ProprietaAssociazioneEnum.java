package com.servicematica.Model.Associazione;

public enum ProprietaAssociazioneEnum {
    ACRONIMO("Acronimo"),
    ANNO_DI_FONDAZIONE("Anno di fondazione"),
    PROVINCIA("Provincia"),
    CITTA("Città"),
    INDIRIZZO("Indirizzo"),
    NUMERO_CIVICO("Numero civico"),
    NUMERO_DI_TELEFONO("Numero di telefono"),
    EMAIL("Email"),
    PEC("PEC"),
    SITO_WEB("Sito web");
    
    private String key; 

    private ProprietaAssociazioneEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
}
