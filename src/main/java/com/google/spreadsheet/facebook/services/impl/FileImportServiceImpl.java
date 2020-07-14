package com.google.spreadsheet.facebook.services.impl;

import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.repository.FileImportedRepository;
import com.google.spreadsheet.facebook.services.isservice.FileImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileImportServiceImpl implements FileImportService {
    @Autowired
    FileImportedRepository fileImportedRepository;

    @Override
    public List<FileImported> findByDriveID(String driveId) {
        return fileImportedRepository.findByDriveID(driveId);
    }

    @Override
    public FileImported findByFileID(String fileId) {
        return fileImportedRepository.findByFileID(fileId);
    }

    @Override
    public void saveOrUpdate(FileImported fileImported) {
        fileImportedRepository.save(fileImported);
    }

}
