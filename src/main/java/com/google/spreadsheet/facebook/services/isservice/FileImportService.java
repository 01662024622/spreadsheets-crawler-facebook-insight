package com.google.spreadsheet.facebook.services.isservice;

import com.google.spreadsheet.facebook.model.FileImported;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileImportService {
    List<FileImported> findByDriveID(String driveId);

    FileImported findByFileID(String fileId);

    void saveOrUpdate(FileImported fileImported);


}
