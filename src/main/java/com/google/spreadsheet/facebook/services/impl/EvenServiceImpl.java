package com.google.spreadsheet.facebook.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.DTO.DTOEven;
import com.google.spreadsheet.facebook.model.Even;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.repository.EvenRepository;
import com.google.spreadsheet.facebook.services.isservice.EvenService;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.data.EvenUtil;
import com.google.spreadsheet.facebook.util.googleapi.SheetsServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
@Slf4j
public class EvenServiceImpl implements EvenService {
    @Autowired
    EvenRepository evenRepository;

    @Autowired
    SendEmailService sendEmailService;

    Sheets sheetsService;

    public EvenServiceImpl() throws GeneralSecurityException, IOException {
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
        List<DTOEven> dtoEven= EvenUtil.getTitleFromFileImport(fileImported,sheetsService,sendEmailService);
        for (DTOEven dto:dtoEven){
            List<Even> actionList = EvenUtil.getAllDataFromFile(sheetsService, fileImported.getFileId(), fileImported.getFileName(),dto);
            if (actionList == null) {
                throw new QTTTException("Post metric" + fileImported.getFileName() + dto+ "is not have record");
            } else {
                log.info("Have " + String.valueOf(actionList.size()) + " recorde to created by: " + fileImported.getFileName()+"_"+dto.SHEET_INFOR.SHEET_NAME);
                save(actionList);
                log.info("Have " + String.valueOf(actionList.size()) + " recorde save to Database from " + fileImported.getFileName()+"_"+dto.SHEET_INFOR.SHEET_NAME);
            }
        }


    }

    @Override
    public void save(Even object) {


        evenRepository.save(object);
    }

    @Override
    public void save(List<Even> objects) {
//        save(objects.get(0));
        evenRepository.saveAll(objects);
    }

    @Override
    public Even findById(Long aLong) {
        return null;
    }

    @Override
    public List<Even> findAllBySpreadsheet(String spreadsheet) {
        return null;
    }

    @Override
    public void deleteAllBySpreadsheet(String spreadsheet) {
        evenRepository.deleteAllBySpreadsheet(spreadsheet);
    }

    @Override
    public List<Even> findAll() {
        return null;
    }
}
