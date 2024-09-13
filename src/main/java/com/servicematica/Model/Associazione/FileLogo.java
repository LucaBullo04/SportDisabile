package com.servicematica.Model.Associazione;

import com.fasterxml.jackson.annotation.JsonView;
import com.servicematica.Direttive.ViewAssociazione;
import com.servicematica.Direttive.ViewAttivita;
import com.servicematica.Direttive.ViewUtentiAssociazione;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FileLogo {
    
    @JsonView(ViewUtentiAssociazione.RowUtentiAssociazione.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @JsonView(value = {ViewAssociazione.ModificaAssociazione.class, ViewAssociazione.GetLogoById.class})
    @Column(nullable = false)
    private String fileName;
    
    @JsonView(value = {ViewAssociazione.GetLogoById.class, ViewAssociazione.ModificaAssociazione.class})
    @Column(length = 100)
    private String fileType;
       
    @JsonView(value = {ViewAttivita.RowAttivitaAdmin.class, ViewUtentiAssociazione.RowUtentiAssociazione.class, ViewAssociazione.GetLogoById.class})
    @Lob
    @Column(columnDefinition = "longtext", unique = true)
    private String dataBase64;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDataBase64() {
        return dataBase64;
    }

    public void setDataBase64(String dataBase64) {
        this.dataBase64 = dataBase64;
    }
    
}

