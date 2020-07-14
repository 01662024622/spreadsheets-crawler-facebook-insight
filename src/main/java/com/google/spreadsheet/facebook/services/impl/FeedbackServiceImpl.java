package com.google.spreadsheet.facebook.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.DTO.DTOFeedback;
import com.google.spreadsheet.facebook.model.Feedback;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.repository.FeedbackRepository;
import com.google.spreadsheet.facebook.services.isservice.FeedbackService;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.data.FeedbackUtil;
import com.google.spreadsheet.facebook.util.googleapi.SheetsServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    SendEmailService sendEmailService;

    Sheets sheetsService;

    public FeedbackServiceImpl() throws GeneralSecurityException, IOException {
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
        List<DTOFeedback> dtoFeedbacks= FeedbackUtil.getTitleFromFileImport(fileImported,sheetsService,sendEmailService);
        for (DTOFeedback dto:dtoFeedbacks){
            List<Feedback> feedbackList = FeedbackUtil.getAllDataFromFile(sheetsService, fileImported.getFileId(), fileImported.getFileName(),dto);
            if (feedbackList == null) {
                throw new QTTTException("Post metric" + fileImported.getFileName() + dto+ "is not have record");
            } else {
                log.info("Have " + String.valueOf(feedbackList.size()) + " recorde to created by: " + fileImported.getFileName()+"_"+dto.SHEET_INFOR.SHEET_NAME);
                save(feedbackList);
                log.info("Have " + String.valueOf(feedbackList.size()) + " recorde save to Database from " + fileImported.getFileName()+"_"+dto.SHEET_INFOR.SHEET_NAME);
            }
        }
    }

    @Override
    public void save(Feedback object) {

        feedbackRepository.save(object);
    }

    @Override
    public void save(List<Feedback> objects) {
//        save(objects.get(0));
        feedbackRepository.saveAll(objects);
    }

    @Override
    public Feedback findById(Long aLong) {
        return null;
    }

    @Override
    public List<Feedback> findAllBySpreadsheet(String spreadsheet) {
        return null;
    }

    @Override
    public void deleteAllBySpreadsheet(String spreadsheet) {
        feedbackRepository.deleteAllBySpreadsheet(spreadsheet);
    }

    @Override
    public List<Feedback> findAll() {
        return null;
    }
}
