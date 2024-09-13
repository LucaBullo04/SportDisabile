package com.servicematica.Repository.Associazione;

import com.servicematica.Model.Associazione.FileLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileLogoRepository extends JpaRepository<FileLogo, Integer> {
    public FileLogo findByDataBase64(String dataBase64);
}
