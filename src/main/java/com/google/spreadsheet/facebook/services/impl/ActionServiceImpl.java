package com.google.spreadsheet.facebook.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.model.Action;
import com.google.spreadsheet.facebook.model.DTO.DTOAction;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.model.Metrics;
import com.google.spreadsheet.facebook.repository.ActionRepository;
import com.google.spreadsheet.facebook.services.isservice.ActionService;
import com.google.spreadsheet.facebook.services.isservice.SendEmailService;
import com.google.spreadsheet.facebook.util.data.ActionUtil;
import com.google.spreadsheet.facebook.util.data.MetricsUntil;
import com.google.spreadsheet.facebook.util.googleapi.SheetsServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
@Slf4j
public class ActionServiceImpl implements ActionService {
    @Autowired
    ActionRepository actionRepository;

    Sheets sheetsService;

    @Autowired
    SendEmailService sendEmailService;

    public ActionServiceImpl() throws GeneralSecurityException, IOException {
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

        List<DTOAction> dtoActions= ActionUtil.getTitleFromFileImport(fileImported,sheetsService,sendEmailService);
        for (DTOAction dtoAction:dtoActions){
            List<Action> actionList = ActionUtil.getAllDataFromFile(sheetsService, fileImported.getFileId(), fileImported.getFileName(),dtoAction);
            if (actionList == null) {
                throw new QTTTException("Post metric" + fileImported.getFileName() + dtoAction+ "is not have record");
            } else {
                log.info("Have " + String.valueOf(actionList.size()) + " recorde to created by: " + fileImported.getFileName()+"_"+dtoAction.SHEET_INFOR.SHEET_NAME);
                save(actionList);
                log.info("Have " + String.valueOf(actionList.size()) + " recorde save to Database from " + fileImported.getFileName()+"_"+dtoAction.SHEET_INFOR.SHEET_NAME);
            }
        }


    }

    @Override
    public void save(Action object) {
        actionRepository.save(object);

    }

    @Override
    public void save(List<Action> objects) {
//        save(objects.get(0));
        actionRepository.saveAll(objects);
    }

    @Override
    public Action findById(Long aLong) {
        return null;
    }

    @Override
    public List<Action> findAllBySpreadsheet(String spreadsheet) {
        return null;
    }

    @Override
    public void deleteAllBySpreadsheet(String spreadsheet) {
        actionRepository.deleteAllBySpreadsheet(spreadsheet);
    }

    @Override
    public List<Action> findAll() {
        return null;
    }
}
