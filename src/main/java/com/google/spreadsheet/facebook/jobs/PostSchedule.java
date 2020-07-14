package com.google.spreadsheet.facebook.jobs;

import com.google.api.services.sheets.v4.Sheets;
import com.google.spreadsheet.facebook.exception.QTTTException;
import com.google.spreadsheet.facebook.file.GoogleDriveFile;
import com.google.spreadsheet.facebook.jobs.base.SheduleJob;
import com.google.spreadsheet.facebook.model.FileImported;
import com.google.spreadsheet.facebook.services.impl.GoogleDriveService;
import com.google.spreadsheet.facebook.services.isservice.*;
import com.google.spreadsheet.facebook.util.data.GoogleDriveFileUtil;
import com.google.spreadsheet.facebook.util.googleapi.SheetsServiceUtil;
import com.google.spreadsheet.facebook.util.sender.SendMailContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Component
@Slf4j
public class PostSchedule implements SheduleJob {
    @Autowired
    MetricService metricService;
    @Autowired
    ActionService actionService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    EvenService evenService;
    @Autowired
    FileImportService fileImportService;
    @Autowired
    SendEmailService sendEmailService;
    @Value("${crawler.cron.driveid.fb.post}")
    private String folderId;
//    private String folderId = "1QK2nBQbzPt0jk_kcZMnsAGDbI4wXsgy_";

    Sheets sheetService;

    public PostSchedule() throws GeneralSecurityException, IOException {
        this.sheetService = SheetsServiceUtil.getSheetsService();
    }

    //        @Scheduled(fixedRate = 5)
//    @Scheduled(cron = "${crawler.cron.daily}")
    @Scheduled(cron = "${crawler.cron.post.daily}")
    public void importData() throws IOException, QTTTException {
        log.info("----------------------Started:Post Crawler--------------------");
        List<GoogleDriveFile> listSheet = GoogleDriveService
                .listAllSpreadsheetsFiles(folderId, "^\\d{8}_\\d{2}");
        List<GoogleDriveFile> listSheetErrorFormat = GoogleDriveService
                .listAllSpreadsheetsFiles(folderId, "^((?!(\\d{8}_\\d{2})).)*$");
        for (GoogleDriveFile sheetErrorFormat : listSheetErrorFormat) {
            FileImported fileEror = GoogleDriveFileUtil.convertGoogleDriveFileToFileImported(sheetErrorFormat);
            FileImported fileErrorSave = fileImportService.findByFileID(fileEror.getFileId());
            if (fileErrorSave == null) {
                log.info(fileEror.getFileName() + " format is error");
                String content = SendMailContent.checkSheet(false, "post", fileEror.getFileName(), false);
//                sendEmailService.sendSimpleMessage(content);
                fileImportService.saveOrUpdate(fileEror);
            } else {
                if (!fileErrorSave.getModifiedTime().equals(fileEror.getModifiedTime())) {
                    log.info(fileEror.getFileName() + " format is error");
                    String content = SendMailContent.checkSheet(false, "post", fileEror.getFileName(), true);
//                    sendEmailService.sendSimpleMessage(content);
                    fileImportService.saveOrUpdate(fileEror);
                }
            }
        }
        log.info(String.valueOf(listSheet.size()));
        for (GoogleDriveFile file : listSheet) {
            FileImported newFile = GoogleDriveFileUtil.convertGoogleDriveFileToFileImported(file);
            FileImported fileSaved = fileImportService.findByFileID(newFile.getFileId());
            if (fileSaved == null) {
                log.info("Import new data from sheet: " + newFile.getFileName());
                importDataAfterCheckSheet(newFile);
                fileImportService.saveOrUpdate(newFile);
            } else {
                if (!fileSaved.getModifiedTime().equals(newFile.getModifiedTime())) {
                    log.info("Import older data from sheet: " + newFile.getFileName() + "have been updated at: " + newFile.getModifiedTime());
                    importDataAfterCheckSheet(newFile);
                    fileImportService.saveOrUpdate(newFile);
                }
            }
        }
    }

    public void importDataAfterCheckSheet(FileImported fileImported) throws IOException, QTTTException {

        actionService.importData(fileImported);
        metricService.importData(fileImported);
        feedbackService.importData(fileImported);
        evenService.importData(fileImported);
    }


}
