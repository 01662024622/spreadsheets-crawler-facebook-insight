package com.google.spreadsheet.facebook.repository;

import com.google.spreadsheet.facebook.model.FileImported;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FileImportedRepository extends JpaRepository<FileImported,Long> {
    @Query("SELECT file FROM FileImported file where file.driveId = :driveId")
    List<FileImported> findByDriveID(@Param("driveId") String driveId);

    @Query("SELECT file FROM FileImported file where file.fileId = :fileId")
    FileImported findByFileID(@Param("fileId") String fileId);

}
