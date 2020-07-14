package com.google.spreadsheet.facebook.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.DTO.page.PageMetricsDTO;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.model.page.PageMetrics;
import com.google.spreadsheet.facebook.repository.page.PageMetricRepository;
import com.google.spreadsheet.facebook.services.isservice.PageMetricsService;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.data.page.PageMetricsUtil;
import com.google.spreadsheet.facebook.util.googleapi.SheetsServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Slf4j
@Service
public class PageMetricsServiceImpl implements PageMetricsService {
    @Autowired
    private PageMetricRepository pageMetricRepository;

    @Autowired
    SendEmailService sendEmailService;

    Sheets sheetsService;

    public PageMetricsServiceImpl() throws GeneralSecurityException, IOException {
        this.sheetsService = SheetsServiceUtil.getSheetsService();
    }
    @Override
    public void importData(String driveId, List<String> sheetNames) throws IOException {

    }

    @Override
    public void importData(String spreadsheetId, String sheetName) throws IOException {

    }

    @Override
    public void importData(FileImported fileImported) throws IOException, QTTTException {
        List<PageMetrics> pageMetricsList = PageMetricsUtil.getAllDataFromFile(sheetsService, fileImported,sendEmailService);
        if (pageMetricsList == null) {
            throw new QTTTException("Post metric" + fileImported.getFileName() + "is not have record");
        } else {
            log.info("Have " + String.valueOf(pageMetricsList.size()) + " recorde to created by: " + fileImported.getFileName());

            save(pageMetricsList);
            log.info("Have " + String.valueOf(pageMetricsList.size()) + " recorde save to Database from " + fileImported.getFileName());
        }
    }

    @Override
    public void save(PageMetrics object) {

    }

    @Override
    public void save(List<PageMetrics> objects) {
        pageMetricRepository.saveAll(objects);
    }

    @Override
    public PageMetrics findById(Long aLong) {
        return null;
    }

    @Override
    public List<PageMetrics> findAllBySpreadsheet(String spreadsheet) {
        return null;
    }

    @Override
    public void deleteAllBySpreadsheet(String spreadsheet) {
        pageMetricRepository.deleteAllBySpreadsheet(spreadsheet);
    }

    @Override
    public List<PageMetrics> findAll() {
        return null;
    }
}
