package com.google.spreadsheet.facebook.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.model.Metrics;
import com.google.spreadsheet.facebook.repository.MetricsRepository;
import com.google.spreadsheet.facebook.services.isservice.MetricService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.data.MetricsUntil;
import com.google.spreadsheet.facebook.util.googleapi.SheetsServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MetricServiceImpl implements MetricService {

    @Autowired
    private MetricsRepository metricsRepository;
    @Autowired
    SendEmailService sendEmailService;

    Sheets sheetsService;

    public MetricServiceImpl() throws GeneralSecurityException, IOException {
        this.sheetsService = SheetsServiceUtil.getSheetsService();
    }

    @Override
    public void importData(String driveId, List<String> sheetNames) throws IOException {

    }

    @Override
    public void importData(String spreadsheetId, String sheetName) throws IOException {

    }

    @Override
    public void importData(FileImported fileImported) throws QTTTException {
        List<Metrics> metricsList = MetricsUntil.getAllDataFromFile(sheetsService, fileImported,sendEmailService);
        if (metricsList == null) {
            throw new QTTTException("Post metric" + fileImported.getFileName() + "is not have record");
        } else {
            log.info("Have " + String.valueOf(metricsList.size()) + " recorde to created by: " + fileImported.getFileName());
            save(metricsList);

            log.info("Have " + String.valueOf(metricsList.size()) + " recorde save to Database from " + fileImported.getFileName());
        }
    }

    @Override
    public void save(Metrics object){
        metricsRepository.save(object);
    }

    @Override
    public void save(List<Metrics> objects) {
         metricsRepository.saveAll(objects);
    }

    @Override
    public Metrics findById(Long aLong) {
        return null;
    }

    @Override
    public List<Metrics> findAllBySpreadsheet(String spreadsheet) {
        return metricsRepository.findAllBySpreadsheet(spreadsheet);
    }

    @Override
    public void deleteAllBySpreadsheet(String spreadsheet) {
        metricsRepository.deleteAllBySpreadsheet(spreadsheet);
    }

    @Override
    public List<Metrics> findAll() {
        return null;
    }


//    public void saveNewFile(FileImported newFile, List<String> sheetNames) {
//        String fileId = newFile.getFileId();
//        String driveId = newFile.getDriveId();
//        log.info("Post: Importing file: " + newFile.getFileName());
//
//
//        for (String sheetName : sheetNames) {
//            try {
//                int dataType = getTypeConstants(driveId);
//                if (dataType < 0) {
//                    return;
//                }
//
//                importData(fileId, sheetName, dataType);
//            } catch (IOException e) {
//                log.error("C3B: Import file: " + newFile.getFileName() + " get errors: " + e.getMessage());
//                return;
//            } catch (QTTTException e) {
//                log.info(e.getMessage());
//                return;
//            }
//        }
//    }
//    public void importData(String spreadsheetId, String sheetName, int typeOfData)
//            throws IOException, QTTTException {
//        List<C3B> c3BList = C3BUtil
//                .getAllDataFromFile(sheetsService, spreadsheetId, sheetName, typeOfData);
//
//        this.save(c3BList);
//    }
}
